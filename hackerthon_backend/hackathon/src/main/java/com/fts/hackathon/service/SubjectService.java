package com.fts.hackathon.service;

import com.fts.hackathon.dto.KeywordDto;
import com.fts.hackathon.dto.StudentDto;
import com.fts.hackathon.dto.UserInputDto;
import com.fts.hackathon.model.*;
import com.fts.hackathon.repository.LearnedSubjectRepository;
import com.fts.hackathon.repository.ProfessorRepository;
import com.fts.hackathon.repository.StudentRepository;
import com.fts.hackathon.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private LearnedSubjectRepository learnedSubjectRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private StudentRepository studentRepository;

    public SubjectService(SubjectRepository subjectRepository, LearnedSubjectRepository learnedSubjectRepository, ProfessorRepository professorRepository, StudentRepository studentRepository) {
        this.subjectRepository = subjectRepository;
        this.learnedSubjectRepository = learnedSubjectRepository;
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
    }

    //일단 전공에 대해서만 구현
    public List<String> getRemainEssentialSubject(List<LearnedSubject> learnedSubjectList, Student student) {

        List<String> remainedMajor = getEssentialSubject(student.getStudentDepartment());

        for (LearnedSubject subjectTemp : learnedSubjectList) {
            List<Subject> subjectList = subjectRepository.findAllBySubjectNameAndSubjectSemester(
                    subjectTemp.getLearnedSubjectName(), subjectTemp.getLearnedSubjectSemester()
            ).orElseGet(() -> {
                return null;
            });

            // 과목명이 겹치는 수업이 존재...
            if (subjectList == null) {
                System.out.println("과목리스트가 존재하지 않아요");
                return null;
            }

            Subject subject = null;

            for (int i = 0; i < subjectList.size(); i++) {
                if (subjectList.get(i).getSubjectDepartment().equals(student.getStudentDepartment().toString())) {
                    subject = subjectList.get(i);
                }
            }

            if (subject == null) {
                System.out.println("과목이 존재하지 않아요");
                return null;
            }
            if (subject.getSubjectType() == SubjectType.MAJOR && subject.getSubjectEssential() == Essential.TRUE) {

                int subjectIndex = remainedMajor.indexOf(subject.getSubjectName());
                remainedMajor.remove(subjectIndex);

            }
        }

        return remainedMajor;
    }

    public ArrayList<String> getEssentialSubject(Department studentDepartment) {

        ArrayList<String> majorEssetialSubject = new ArrayList<>();
        if (studentDepartment == Department.정보통신공학과) {
            majorEssetialSubject.add("자료구조");
            majorEssetialSubject.add("데이터통신및실습");
            majorEssetialSubject.add("컴퓨터네트워크");
            majorEssetialSubject.add("신호및시스템");
        } else if (studentDepartment == Department.컴퓨터공학부) {
            majorEssetialSubject.add("자료구조");
            majorEssetialSubject.add("캡스톤설계및실습");
        }

        return majorEssetialSubject;
    }

    public List<Integer> getSubjectCreditInfo(List<LearnedSubject> learnedSubjectList, Department department) {
        // 들은 학점 계산
        int majorCredit = 0;
        int minorCredit = 0;

        for (LearnedSubject subjectTemp : learnedSubjectList) {

            List<Subject> subjectList = subjectRepository.findAllBySubjectNameAndSubjectSemester(
                    subjectTemp.getLearnedSubjectName(), subjectTemp.getLearnedSubjectSemester()
            ).orElseGet(() -> {
                return null;
            });

            // 과목명이 겹치는 수업이 존재...
            if (subjectList == null) {
                return null;
            }

            Subject subject = null;

            for (int i = 0; i < subjectList.size(); i++) {
                subject = subjectList.get(i);

                if(subject == null) {
                    continue;
                }

                if (subject.getSubjectDepartment().equals(department.toString()) || subject.getSubjectDepartment().equals("공과대학")) {
                    majorCredit += subject.getSubjectCredit();
                }
                else {
                    minorCredit += subject.getSubjectCredit();
                }
            }


        }

        return Arrays.asList(majorCredit, minorCredit);
    }


    public HashMap<String, String> recommendSubject6 (KeywordDto keywordDto, String studentNum) {
        List<Subject> recommendSubjectList = recommendSubject(keywordDto, studentNum);

        HashMap<String, String> recommendSubjectWithIndex = new HashMap<String, String>();
        for(int i = 0; i < 6; i++){
            if (i >= recommendSubjectList.size()){
                break;
            }
            recommendSubjectWithIndex.put("추천과목"+(i+1), recommendSubjectList.get(i).getSubjectName());
        }

        return recommendSubjectWithIndex;
    }

    public List<Subject> recommendSubject(KeywordDto keywordDto, String studentNum) {

        StudentDto studentDto = StudentDto.builder().studentNum(studentNum).build();
        String [] keywords = new String[3];
        keywords[0] = keywordDto.getKeyword1();
        keywords[1] = keywordDto.getKeyword2();
        keywords[2] = keywordDto.getKeyword3();


        List<Subject> subjectList = recommendSubject10(studentDto, keywords);

        List<Subject> recommendSubject = new ArrayList<>();

        // timeArray[0][0] = 월요일 1교시, 금요일 10교시까지 존재
        boolean[][] canLearnTimeArray = new boolean[5][11]; // default = false;


        for (int i = 0; i < subjectList.size(); i++) {

            String subjectTime = subjectList.get(i).getSubjectTime().trim();

            System.out.println(subjectList.get(i).getSubjectName());

            String firstDay = null, secondDay = null;
            List<Integer> firstTime = new ArrayList<>();
            List<Integer> secondTime = new ArrayList<>();


            // 여러 요일이면 "-"으로 일단 나눠주고, 요일-교시로 나눠 줌.
            if (subjectTime.contains("-")) {
                String temp0 = subjectTime.replaceAll(" ", "");
                String temp1 = temp0.split("-")[0]; // 시작시간 월12
                String temp2 = temp0.split("-")[1]; // 끝시간 화1

                String[] temp1Array = temp1.split(""); // 월, 1, 2
                String[] temp2Array = temp2.split("");

                firstDay = temp1Array[0];
                for (int j = 1; j < temp1Array.length; j++) {
                    if (temp1Array[j] != " ") {
                        firstTime.add(Integer.parseInt(temp1Array[j]));
                    }

                }

                secondDay = temp2Array[0];
                for (int j = 1; j < temp2Array.length; j++) {
                    if (temp2Array[j] != " ") {
                        secondTime.add(Integer.parseInt(temp2Array[j]));
                    }
                }

            } else {
                String[] tempArray = subjectTime.split("");
                firstDay = tempArray[0];
                for (int j = 1; j < tempArray.length; j++) {
                    if (tempArray[j] != " ") {
                        firstTime.add(Integer.parseInt(tempArray[j]));
                    }
                }
            }

            HashMap<String, Integer> matchDay = new HashMap<>();
            matchDay.put("월", 0);
            matchDay.put("화", 1);
            matchDay.put("수", 2);
            matchDay.put("목", 3);
            matchDay.put("금", 4);

            boolean flag = true;

            for (int j = 0; j < firstTime.size(); j++) {
                int index = matchDay.get(firstDay);
                if (canLearnTimeArray[index][firstTime.get(j)]) {
                    flag = false;
                }
            }

            if (secondDay != null) {
                for (int j = 0; j < secondTime.size(); j++) {
                    int index = matchDay.get(secondDay);
                    if (canLearnTimeArray[index][secondTime.get(j)]) {
                        flag = false;
                    }
                }
            }

            if (flag) {
                recommendSubject.add(subjectList.get(i));

                for (int j = 0; j < firstTime.size(); j++) {
                    int index = matchDay.get(firstDay);
                    canLearnTimeArray[index][firstTime.get(j)] = true;


                }

                if (secondDay != null) {
                    for (int j = 0; j < secondTime.size(); j++) {
                        int index = matchDay.get(secondDay);
                        canLearnTimeArray[index][secondTime.get(j)] = true;
                    }
                }
            }
        }

//        HashMap<String, String> recommendSubjectWithIndex
//        for (int i = 0; i < 6; i++){
//            if(recommendSubject.size() < i){
//                break;
//            }
//
//        }
        return recommendSubject;
    }

    public List<Subject> recommendSubject10(StudentDto studentDto, String[] keywords) {

        List<Subject> recommendSubject = new ArrayList<>();
        Student student = studentRepository.findByStudentNum(studentDto.getStudentNum()).orElseGet(() -> {
            return null;
        });

        if (student == null) {
            return null;
        }

        List<Subject> ableSubject = ableSubject(student);
        HashMap<Professor, Integer> professorWeights = getKeywordsWeights(keywords);
        List<Subject> ableSubject_23_1 = new ArrayList<>();

        for(int i = 0; i < ableSubject.size(); i++) {
            if(ableSubject.get(i).getSubjectSemester().equals("23년 1학기")) {
                ableSubject_23_1.add(ableSubject.get(i));
            }
        }

        List<Professor> sortProfessor = new ArrayList<>(professorWeights.keySet());
        Collections.sort(sortProfessor, ((o1, o2) ->
                (professorWeights.get(o2).compareTo(professorWeights.get(o1)))));

        HashMap<Professor, List<Subject>> professorSubjectList = new HashMap<>();

        for (int i = 0; i < sortProfessor.size(); i++) {
            professorSubjectList.put(sortProfessor.get(i), subjectRepository.findAllByProfessor(sortProfessor.get(i)));
        }

        int subjectIndex = 0;
        boolean flag = false;
        while (!flag && recommendSubject.size() <= 10) {
            for (int i = 0; i < sortProfessor.size(); i++) {
                if(professorSubjectList.get(sortProfessor.get(i)).size() > subjectIndex ) {
                    Subject subject = professorSubjectList.get(sortProfessor.get(i)).get(subjectIndex);
                    if (ableSubject_23_1.contains(subject)) {
                        recommendSubject.add(subject);
                    }
                }
                else{
                    flag = true;
                    break;
                }

            }
            subjectIndex++;
        }

        return recommendSubject;
    }

    public HashMap<Professor, Integer> getKeywordsWeights(String[] keywords) { //받는 키워드가 3개라고 가정

        HashMap<String, List<Integer>> professorKeywordWeights = calculateKeywordWeights();

        if (Keyword.GOODLECTURE.toString().equals(keywords[0])) {
            professorKeywordWeights = multipleKeywordsWeights(0, 5, professorKeywordWeights);
        }
        if (Keyword.GOODLECTURE.toString().equals(keywords[1])) {
            professorKeywordWeights = multipleKeywordsWeights(0, 3, professorKeywordWeights);
        }
        if (Keyword.GOODLECTURE.toString().equals(keywords[2])) {
            professorKeywordWeights = multipleKeywordsWeights(0, 2, professorKeywordWeights);
        }
        if (Keyword.MANYEXAM.toString().equals(keywords[0])) {
            professorKeywordWeights = multipleKeywordsWeights(1, 5, professorKeywordWeights);
        }
        if (Keyword.MANYEXAM.toString().equals(keywords[1])) {
            professorKeywordWeights = multipleKeywordsWeights(1, 3, professorKeywordWeights);
        }
        if (Keyword.MANYEXAM.toString().equals(keywords[2])) {
            professorKeywordWeights = multipleKeywordsWeights(1, 2, professorKeywordWeights);
        }
        if (Keyword.OPENBOOK.toString().equals(keywords[0])) {
            professorKeywordWeights = multipleKeywordsWeights(2, 5, professorKeywordWeights);
        }
        if (Keyword.OPENBOOK.toString().equals(keywords[1])) {
            professorKeywordWeights = multipleKeywordsWeights(2, 3, professorKeywordWeights);
        }
        if (Keyword.OPENBOOK.toString().equals(keywords[2])) {
            professorKeywordWeights = multipleKeywordsWeights(2, 2, professorKeywordWeights);
        }
        if (Keyword.HIGHGRADE.toString().equals(keywords[0])) {
            professorKeywordWeights = multipleKeywordsWeights(3, 5, professorKeywordWeights);
        }
        if (Keyword.HIGHGRADE.toString().equals(keywords[1])) {
            professorKeywordWeights = multipleKeywordsWeights(3, 3, professorKeywordWeights);
        }
        if (Keyword.HIGHGRADE.toString().equals(keywords[2])) {
            professorKeywordWeights = multipleKeywordsWeights(3, 2, professorKeywordWeights);
        }
        if (Keyword.TEAMPROJECT.toString().equals(keywords[0])) {
            professorKeywordWeights = multipleKeywordsWeights(4, 5, professorKeywordWeights);
        }
        if (Keyword.TEAMPROJECT.toString().equals(keywords[1])) {
            professorKeywordWeights = multipleKeywordsWeights(4, 3, professorKeywordWeights);
        }
        if (Keyword.TEAMPROJECT.toString().equals(keywords[2])) {
            professorKeywordWeights = multipleKeywordsWeights(4, 2, professorKeywordWeights);
        }
        if (Keyword.MANYASSIGNMENT.toString().equals(keywords[0])) {
            professorKeywordWeights = multipleKeywordsWeights(5, 5, professorKeywordWeights);
        }
        if (Keyword.MANYASSIGNMENT.toString().equals(keywords[1])) {
            professorKeywordWeights = multipleKeywordsWeights(5, 3, professorKeywordWeights);
        }
        if (Keyword.MANYASSIGNMENT.toString().equals(keywords[2])) {
            professorKeywordWeights = multipleKeywordsWeights(5, 2, professorKeywordWeights);
        }
        if (Keyword.EARLYSTOPPING.toString().equals(keywords[0])) {
            professorKeywordWeights = multipleKeywordsWeights(6, 5, professorKeywordWeights);
        }
        if (Keyword.EARLYSTOPPING.toString().equals(keywords[1])) {
            professorKeywordWeights = multipleKeywordsWeights(6, 3, professorKeywordWeights);
        }
        if (Keyword.EARLYSTOPPING.toString().equals(keywords[2])) {
            professorKeywordWeights = multipleKeywordsWeights(6, 2, professorKeywordWeights);
        }


        HashMap<Professor, Integer> professorWeights = new HashMap<>();

        for (Map.Entry<String, List<Integer>> entrySet : professorKeywordWeights.entrySet()) {
            List<Integer> tempWeights = entrySet.getValue();
            int sum = 0;
            for (int i = 0; i < tempWeights.size(); i++) {
                sum += tempWeights.get(i);
            }

            Professor tempProfessor = professorRepository.findByProfessorName(entrySet.getKey()).orElseGet(() -> {
                return null;
            });

            if (tempProfessor != null) {
                professorWeights.put(tempProfessor, sum);
            }
        }

        return professorWeights;

    }

    public HashMap<String, List<Integer>> multipleKeywordsWeights
            (int index, int weights, HashMap<String, List<Integer>> temp) {

        for (Map.Entry<String, List<Integer>> entrySet : temp.entrySet()) {
            List<Integer> tempWeights = entrySet.getValue();
            tempWeights.set(index, tempWeights.get(index) * weights);
            temp.put(entrySet.getKey(), tempWeights);
        }

        return temp;
    }

    public HashMap<String, List<Integer>> calculateKeywordWeights() { // 키워드가 3개라고 가정
        List<Professor> professorList = professorRepository.findAll();

        // 교수님 이름, keyword 값
        HashMap<String, List<Integer>> professorKeywordWeights = new HashMap<>();

        // True : 1
        // False : -1

        for (int i = 0; i < professorList.size(); i++) {
            Professor professor = professorList.get(i);

            String professorName = professor.getProfessorName();
            List<Integer> keywordWeights = new ArrayList<>();

            if (professor.getKeyword1() == Keyword.NONE) {
                keywordWeights.add(-1);
            } else {
                keywordWeights.add(1);
            }

            if (professor.getKeyword2() == Keyword.NONE) {
                keywordWeights.add(-1);
            } else {
                keywordWeights.add(1);
            }

            if (professor.getKeyword3() == Keyword.NONE) {
                keywordWeights.add(-1);
            } else {
                keywordWeights.add(1);
            }

            if (professor.getKeyword4() == Keyword.NONE) {
                keywordWeights.add(-1);
            } else {
                keywordWeights.add(1);
            }

            if (professor.getKeyword5() == Keyword.NONE) {
                keywordWeights.add(-1);
            } else {
                keywordWeights.add(1);
            }

            if (professor.getKeyword6() == Keyword.NONE) {
                keywordWeights.add(-1);
            } else {
                keywordWeights.add(1);
            }

            if (professor.getKeyword7() == Keyword.NONE) {
                keywordWeights.add(-1);
            } else {
                keywordWeights.add(1);
            }

            professorKeywordWeights.put(professorName, keywordWeights);

        }

        return professorKeywordWeights;

    }

    public List<Subject> ableSubject(Student student) {
        List<LearnedSubject> learnedSubject = learnedSubjectRepository.findAllByStudent(student).orElseGet(() -> {
            return null;
        });


        if(learnedSubject == null) {
            return null;
        }

        List<String> learnedSubjectName = new ArrayList<>();


        for(int i = 0; i < learnedSubject.size(); i++) {
            learnedSubjectName.add(learnedSubject.get(i).getLearnedSubjectName());
        }

        List<Subject> ableSubject = subjectRepository.findAllBySubjectNameNotIn(learnedSubjectName).orElseGet(() -> {
            return null;
        });


        return (ableSubject != null) ? ableSubject : null;
    }



    @Transactional
    public void readSubjectTime(List<Subject> subjectList) {

        // timeArray[0][0] = 월요일 1교시, 금요일 10교시까지 존재
        boolean[][] canLearnTimeArray = new boolean[5][10];


        //temp에 각 과목별 요일당 시간 담김
        HashMap<String, List<String>> temp = new HashMap<>();

        for(int i = 0; i <subjectList.size(); i++) {
            for(int k = 0; k < canLearnTimeArray.length; k++){
                for(int j = 0; k < canLearnTimeArray[k].length; j++){
                    canLearnTimeArray[i][j] = true;
                }
            }

            String subjectTime = subjectList.get(i).getSubjectTime();

            // 여러 요일이면 "-"으로 일단 나눠주고, 요일-교시로 나눠 줌.
            if (subjectTime.contains("-")){
                String temp1 = subjectTime.split("-")[0];
                String temp2 = subjectTime.split("-")[1];

                String[] temp1Array = temp1.split("");
                List<String> tempList1 = Arrays.asList(temp1Array);
                tempList1.remove(1);

                String[] temp2Array = temp2.split("");
                List<String> tempList2 = Arrays.asList(temp2Array);
                tempList2.remove(1);

                temp.put(temp1.substring(0, 0), tempList1);
                temp.put(temp2.substring(0, 0), tempList2);
            }
            else{
                String[] tempArray = subjectTime.split("");
                List<String> tempList = Arrays.asList(tempArray);
                tempList.remove(1);

                temp.put(subjectTime.substring(0, 0), tempList);
            }

            for(String s : temp.get("월")){
                if(canLearnTimeArray[0][Integer.parseInt(s)-1] == true){
                    canLearnTimeArray[0][Integer.parseInt(s)-1] = false;
                }
            }
            for(String s : temp.get("화")){
                if(canLearnTimeArray[1][Integer.parseInt(s)-1] == true){
                    canLearnTimeArray[1][Integer.parseInt(s)-1] = false;
                }

            }
            for(String s : temp.get("수")){
                if(canLearnTimeArray[2][Integer.parseInt(s)-1] == true){
                    canLearnTimeArray[2][Integer.parseInt(s)-1] = false;
                }

            }
            for(String s : temp.get("목")){
                if(canLearnTimeArray[3][Integer.parseInt(s)-1] == true){
                    canLearnTimeArray[3][Integer.parseInt(s)-1] = false;
                }

            }
            for(String s : temp.get("금")){
                if(canLearnTimeArray[4][Integer.parseInt(s)-1] == true){
                    canLearnTimeArray[4][Integer.parseInt(s)-1] = false;
                }

            }

            // 뭔가 append , return 하거나 하겠지??

        }


    }


    @Transactional
    public void readSubjectCSV() {
        BufferedReader br = null;

        String path = "C:\\hackerthon\\subjectList.CSV";
        String line = "";

        int count = 0;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));

            while ((line = br.readLine()) != null) {
                count++;
                if (count == 1) continue;

                List<String> temp = new ArrayList<String>();
                String[] cont = line.split(",");
                // 0 학과 1 전공 과목명 2 전필 과목 여부 3 교양 과목명 4 교필 과목명 5 강의시간 6 학 기 7 교수 8 학 점
                Subject subject = new Subject();

                Professor professor = professorRepository.findByProfessorName(cont[7]).orElseGet(() -> {
                    return null;
                });

                if (professor == null) continue;

                subject.setSubjectCredit(Integer.parseInt(cont[8]));
                subject.setSubjectDepartment(cont[0]);
                subject.setSubjectTime(cont[5]);
                subject.setProfessor(professor);
                subject.setSubjectSemester(cont[6]);


                if (!cont[1].equals("")) {
                    // 전공과목이라면
                    subject.setSubjectType(SubjectType.MAJOR);
                    subject.setSubjectName(cont[1]);

                    if (!cont[2].equals("")) {
                        // 필수과목이라면
                        subject.setSubjectEssential(Essential.TRUE);
                    } else {
                        subject.setSubjectEssential(Essential.FALSE);
                    }
                }

                if (!cont[3].equals("")) {
                    // 전공과목이라면
                    subject.setSubjectType(SubjectType.MINOR);
                    subject.setSubjectName(cont[3]);
                    if (!cont[4].equals("")) {
                        // 필수과목이라면
                        subject.setSubjectEssential(Essential.TRUE);
                    } else {
                        subject.setSubjectEssential(Essential.FALSE);
                    }
                }

                System.out.println("데이터 삽입 중");
                subjectRepository.save(subject);


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    @Transactional
    public void readKeywordsCSV() {
        BufferedReader br = null;
        String path = "C:\\hackerthon\\keywords.CSV";
        String line = "";

        int count = 0;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));

            while ((line = br.readLine()) != null) {
                count++;
                if (count == 1) continue;

                List<String> temp = new ArrayList<String>();
                String[] cont = line.split(",");
                // 0 교수명 1 학과 2 강의력좋은가 3 시험횟수많은가 4 오픈북인가
                // 5 성적후한가 6 팀플있는가 7 과제많은가 8 일찍마쳐주는가

                Professor professor = new Professor();

//                for(int j = 0; j < cont.length; j ++) {
//                    System.out.println(j + " 번째 " + cont[j]);
//                }

                professor.setProfessorName(cont[0]);

                if (cont[2].equals("T")) {
                    professor.setKeyword1(Keyword.GOODLECTURE);
                } else {
                    professor.setKeyword1(Keyword.NONE);
                }

                if (cont[3].equals("T")) {
                    professor.setKeyword2(Keyword.MANYEXAM);
                } else {
                    professor.setKeyword2(Keyword.NONE);
                }

                if (cont[4].equals("T")) {
                    professor.setKeyword3(Keyword.OPENBOOK);
                } else {
                    professor.setKeyword3(Keyword.NONE);
                }

                if (cont[5].equals("T")) {
                    professor.setKeyword4(Keyword.HIGHGRADE);
                } else {
                    professor.setKeyword4(Keyword.NONE);
                }

                if (cont[6].equals("T")) {
                    professor.setKeyword5(Keyword.TEAMPROJECT);
                } else {
                    professor.setKeyword5(Keyword.NONE);
                }

                if (cont[7].equals("T")) {
                    professor.setKeyword6(Keyword.MANYASSIGNMENT);
                } else {
                    professor.setKeyword6(Keyword.NONE);
                }

                if (cont[8].equals("T")) {
                    professor.setKeyword7(Keyword.EARLYSTOPPING);
                } else {
                    professor.setKeyword7(Keyword.NONE);
                }


                System.out.println("데이터 삽입 중");
                professorRepository.save(professor);


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public HashMap<String, String> getKeywordsFromUserInput(String studentNum, UserInputDto userInputDto) {
        String data = userInputDto.getUserInput();
        String sentence = data.replace("은", "").replace("는", "").replace("이", "").replace("가", "").replace("을", "").replace("이고", "").replace("에서", "");

        List<String> keywords = new ArrayList<>();
        keywords.add("강의력이 좋다");
        keywords.add("시험 많다");
        keywords.add("오픈북 이다");
        keywords.add("성적 잘 준다");
        keywords.add("팀프로젝트 많다");
        keywords.add("과제 많다");
        keywords.add("일찍 마쳐준다");

        HashMap<Integer, Double> keywordsWeight = new HashMap<>();

        double sum = 0;
        for (int i = 0; i < keywords.size(); i++) {
            sum += similarity(sentence, keywords.get(i));
        }

        for (int i = 0; i < keywords.size(); i++) {
            keywordsWeight.put(i + 1, similarity(sentence, keywords.get(i)) / (keywords.get(i)).length());
        }

//        for(Map.Entry<Integer, Double> entry : keywordsWeight.entrySet()) {
//        	System.out.println(entry.getKey() + "    " + entry.getValue());
//        }
//
        List<Integer> sortKeywordIndex = new ArrayList<>(keywordsWeight.keySet());
        Collections.sort(sortKeywordIndex, ((o1, o2) ->
                (keywordsWeight.get(o2).compareTo(keywordsWeight.get(o1)))));

        HashMap<Integer, String> keywordHashMap = new HashMap<>();

        keywordHashMap.put(0, "GOODLECTURE");
        keywordHashMap.put(1, "MANYEXAM");
        keywordHashMap.put(2, "OPENBOOK");
        keywordHashMap.put(3, "HIGHGRADE");
        keywordHashMap.put(4, "TEAMPROJECT");
        keywordHashMap.put(5, "MANYASSIGNMENT");
        keywordHashMap.put(6, "EARLYSTOPPING");



        KeywordDto keywordDto = KeywordDto.builder()
                .keyword1(keywordHashMap.get(sortKeywordIndex.get(0)))
                .keyword2(keywordHashMap.get(sortKeywordIndex.get(1)))
                .keyword3(keywordHashMap.get(sortKeywordIndex.get(2)))
                .build();

        return recommendSubject6(keywordDto, studentNum);
    }

    private static double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;

        if (s1.length() < s2.length()) {
            longer = s2;
            shorter = s1;
        }

        int longerLength = longer.length();
        if (longerLength == 0) return 1.0;
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
    }

    private static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        int[] costs = new int[s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    costs[j] = j;
                } else {
                    if (j > 0) {
                        int newValue = costs[j - 1];

                        if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                            newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
                        }

                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }

            if (i > 0) costs[s2.length()] = lastValue;
        }

        return costs[s2.length()];
    }


    public HashMap<String, Double> calculateSemesterGrade(String studentNum) {

        Student student = studentRepository.findByStudentNum(studentNum).orElseGet(()-> {
            return null;
        });

        HashMap<String, Double> correspondGrade = new HashMap<>();


        List<LearnedSubject> learnedSubjectList_21_1 = learnedSubjectRepository.findAllByStudentAndLearnedSubjectSemester(student, "21년 1학기")
                .orElseGet(() -> {
                    return null;
                });

        SubjectCredit subjectCredit1 = calculateCorresponed(learnedSubjectList_21_1, student);
        if(learnedSubjectList_21_1!= null) {
            correspondGrade.put("21년 1학기", Math.round((subjectCredit1.grade / subjectCredit1.credit) * 10) / 10.0);
        }


        List<LearnedSubject> learnedSubjectList_21_2 = learnedSubjectRepository.findAllByStudentAndLearnedSubjectSemester(student, "21년 2학기")
                .orElseGet(() -> {
                    return null;
                });
        SubjectCredit subjectCredit2 = calculateCorresponed(learnedSubjectList_21_2, student);
        if(learnedSubjectList_21_2!= null) {
            correspondGrade.put("21년 2학기", Math.round((subjectCredit2.grade / subjectCredit2.credit) * 10) / 10.0);
        }


        List<LearnedSubject> learnedSubjectList_22_1 = learnedSubjectRepository.findAllByStudentAndLearnedSubjectSemester(student, "22년 1학기")
                .orElseGet(() -> {
                    return null;
                });
        SubjectCredit subjectCredit3 = calculateCorresponed(learnedSubjectList_22_1, student);
        if(learnedSubjectList_22_1!= null) {
            correspondGrade.put("22년 1학기", Math.round((subjectCredit3.grade / subjectCredit3.credit) * 10) / 10.0);
        }


        List<LearnedSubject> learnedSubjectList_22_2 = learnedSubjectRepository.findAllByStudentAndLearnedSubjectSemester(student, "22년 2학기")
                .orElseGet(() -> {
                    return null;
                });
        SubjectCredit subjectCredit4 = calculateCorresponed(learnedSubjectList_22_2, student);
        if(learnedSubjectList_22_2!= null) {
            correspondGrade.put("22년 2학기", Math.round((subjectCredit4.grade / subjectCredit4.credit) * 10) / 10.0);
        }

        return correspondGrade;
    }

    public SubjectCredit calculateCorresponed(List<LearnedSubject> temp, Student student) {
        int credit = 0;
        double grade = 0.;

        HashMap<String, Double> corresponedGrade = new HashMap<>();
        corresponedGrade.put("A+", 4.5);
        corresponedGrade.put("A0", 4.0);
        corresponedGrade.put("B+", 3.5);
        corresponedGrade.put("B0", 3.0);
        corresponedGrade.put("C+", 2.5);


        for(int i = 0; i < temp.size(); i++) {

            if(!corresponedGrade.get(temp.get(i).getLearnedSubjectGrade()).equals("F")) {
                Subject subject = subjectRepository.findBySubjectSemesterAndSubjectDepartmentAndSubjectName(
                        temp.get(i).getLearnedSubjectSemester(),
                        student.getStudentDepartment().toString(),
                        temp.get(i).getLearnedSubjectName()).orElseGet(()-> {
                            return null;
                });

                if(subject != null) {
                    credit += subject.getSubjectCredit();
                    grade += corresponedGrade.get(temp.get(i).getLearnedSubjectGrade()) * subject.getSubjectCredit();
                }


            }
        }

        return new SubjectCredit(credit, grade);
    }

    class SubjectCredit {
        int credit;
        double grade;

        public SubjectCredit(int credit, double grade) {
            this.credit=credit;
            this.grade =grade;
        }
    }

    public HashMap<String, String[]> recommedAllSubjectInfo(KeywordDto keywordDto, String studentNum) {
        HashMap<String, String[]> allSubjectInfo = new HashMap<>();
        allSubjectInfo.put("월", new String[10]);
        allSubjectInfo.put("화", new String[10]);
        allSubjectInfo.put("수", new String[10]);
        allSubjectInfo.put("목", new String[10]);
        allSubjectInfo.put("금", new String[10]);


        List<Subject> subjectList = recommendSubject(keywordDto, studentNum);

        int max_length = Math.min(subjectList.size(), 6);

        for(int i = 0; i < max_length; i++) {
            Subject subject = subjectList.get(i);
            String subjectTime = subject.getSubjectTime().trim();

            String firstDay = null, secondDay = null;


            // 여러 요일이면 "-"으로 일단 나눠주고, 요일-교시로 나눠 줌.
            if (subjectTime.contains("-")) {
                String temp0 = subjectTime.replaceAll(" ", "");
                String temp1 = temp0.split("-")[0]; // 시작시간 월12
                String temp2 = temp0.split("-")[1]; // 끝시간 화1

                String[] temp1Array = temp1.split(""); // 월, 1, 2
                String[] temp2Array = temp2.split("");

                firstDay = temp1Array[0];
                secondDay = temp2Array[0];
                String [] tempFirstDayArray = allSubjectInfo.get(firstDay);
                String [] tempSecondDayArray = allSubjectInfo.get(secondDay);


                for (int j = 1; j < temp1Array.length; j++) {
                    if (temp1Array[j] != " ") {
                        tempFirstDayArray[j] = subject.getSubjectName();
                    }
                }


                for (int j = 1; j < temp2Array.length; j++) {
                    if (temp2Array[j] != " ") {
                        tempSecondDayArray[j]=subject.getSubjectName();
                    }
                }

                allSubjectInfo.put(firstDay, tempFirstDayArray);
                allSubjectInfo.put(secondDay, tempSecondDayArray);

            } else {
                String[] tempArray = subjectTime.split("");
                firstDay = tempArray[0];
                String [] tempFirstDayArray = allSubjectInfo.get(firstDay);

                for (int j = 1; j < tempArray.length; j++) {
                    if (tempArray[j] != " ") {
                        tempFirstDayArray[j]=subject.getSubjectName();
                    }
                }
                allSubjectInfo.put(firstDay, tempFirstDayArray);
            }
        }

        return allSubjectInfo;

    }

    public HashMap<String, String> subjectInfo(UserInputDto userInputDto) throws Exception {
        Professor professor = professorRepository.findByProfessorName(userInputDto.getProfessorName()).orElseGet(()-> {
            return null;
        });

        Subject subject = subjectRepository.findByProfessorAndSubjectNameAndSubjectSemester(
                professor, userInputDto.getSubjectName(), "23년 1학기").orElseGet(()-> {
            return null;
        });

        if(subject == null) {
            throw new Exception();
        }

        String subjectTime = subject.getSubjectTime();


        List<String> subjectInfo = new ArrayList<>();

        String firstDay = null, secondDay = null;

        if (subjectTime.contains("-")) {
            String temp0 = subjectTime.replaceAll(" ", "");
            String temp1 = temp0.split("-")[0]; // 시작시간 월12
            String temp2 = temp0.split("-")[1]; // 끝시간 화1

            String[] temp1Array = temp1.split(""); // 월, 1, 2
            String[] temp2Array = temp2.split("");

            firstDay = temp1Array[0];
            for (int j = 1; j < temp1Array.length; j++) {
                if (temp1Array[j] != " ") {
                    subjectInfo.add(firstDay + temp1Array[j]);
                }

            }

            secondDay = temp2Array[0];
            for (int j = 1; j < temp2Array.length; j++) {
                if (temp2Array[j] != " ") {
                    subjectInfo.add(secondDay + temp2Array[j]);
                }
            }

        } else {
            String[] tempArray = subjectTime.split("");
            firstDay = tempArray[0];
            for (int j = 1; j < tempArray.length; j++) {
                if (tempArray[j] != " ") {
                    subjectInfo.add(firstDay + tempArray[j]);
                }
            }
        }

        HashMap<String, String> subjectInfoWithIndex = new HashMap<String, String>();

        for(int i = 0; i < 4; i++){
            if (i >= subjectInfo.size()){
                break;
            }
            subjectInfoWithIndex.put("강의시간"+(i+1), subjectInfo.get(i));
        }

        return subjectInfoWithIndex;

    }

}
