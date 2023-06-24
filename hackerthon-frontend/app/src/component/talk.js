import React, { useState } from "react";
import PropTypes from "prop-types";
import Modal from 'react-modal';
import "../style/talk.css";
import cute from "../img/cute.png"
import axios from "axios"
Modal.setAppElement('#root');


const Talk = () => {
  
  const [istalk, setIstalk] = useState(false);
  const [subjects, setSubjects] = useState([{추천과목1:"", 추천과목2:"", 추천과목3:"", 추천과목4:"", 추천과목5:"", 추천과목6:""}]);
  const [value, setValue] = useState("")
  const [returnValue, setReturnValue] = useState("")
  const opentalk = () => {
      setIstalk(true);
  };

  const closetalk = () => {
      setIstalk(false);
  };
  
  function saveText() {
    var textarea = document.getElementById("chatt");
    setValue(textarea.value);    
          
    axios.post("http://localhost:8081/student/recommend/input/201903322", {
      userInput : value
    }).then(function (res) {                
        var temp = res.data;       
        // setMessage([{전공남은학점:res.data.전공남은학점, 전공필요학점:res.data.전공필요학점, 전공들은학점: temp.전공들은학점, 교양남은학점:temp.교양남은학점, 교양필요학점:temp.교양필요학점, 교양들은학점: temp.교양들은학점}]);
        setKeywords(temp);  

    }).catch((error) => console.log(error));

    setReturnText()
    
  }
  function setReturnText() {
    let returnText = "추천과목은 다음과 같습니다.<br>  추천과목1: "
     + subjects.추천과목1 + "<br>추천과목2: "
     + subjects.추천과목2 + "<br>추천과목3: "
     + subjects.추천과목3 + "<br>추천과목4: "
     + subjects.추천과목4 + "<br>추천과목5: "
     + subjects.추천과목5 + "<br>추천과목6: "
     + subjects.추천과목6;
    

    console.log(returnText);
    setReturnValue(returnText);
  }

  function setKeywords(data) {
    setSubjects(data);
    console.log("subject is:", subjects)
  }

    return (
      <div>
        <img onClick = {opentalk} class = "hi" src ={cute}/>
        <Modal
                isOpen={istalk}
                onRequestClose={closetalk}
                contentLabel="팝업 제목"
            >

            <div data-layer="50b68100-dc7b-451b-9af1-9c39f3503b9c" className="talk">        
            <div data-layer="00773717-572e-447d-b321-7de994d7efes" className="bluebox"><div class = "response1">
              
              <h2> {subjects.추천과목1}</h2>
              <h2> {subjects.추천과목2}</h2>
              <h2> {subjects.추천과목3}</h2>
              <h2> {subjects.추천과목4}</h2>
              <h2> {subjects.추천과목5}</h2>
              </div>
            <div class = "response2"><h2>{value}</h2></div></div>
                          <div data-layer="60e5aec4-5405-407b-8f9d-156a32aeb844" className="yellowbox"></div>
                          <div data-layer="0c52fff2-8c09-4eb9-aaec-29625ce0e6ac" className="talkwhite"><textarea rows="4" placeholder="..." id = "chatt"/></div>
                          <div data-layer="fcfd92a6-05d2-4ff0-87a7-70b3e4e60345" className="selectbutton3"></div>
                          <div data-layer="5b62489a-8933-417a-bbbe-2eec69a3520e" className="x3e35f770">팀플 없는 수업</div>
                          <div data-layer="67568f38-cc88-495b-943a-6acd958d37c9" className="selectbutton2"></div>
                          <div data-layer="21b51402-1ee6-4e03-957a-1e7dc5c41702" className="xef2b7594">과제 적은 수업</div>
                          <div data-layer="37b8da6c-1877-40f5-9c30-3ec52c3bf546" className="selectbutton1"><button class="okokok" onClick={saveText}>확인</button></div>
                          
                          <div data-layer="7a956bab-6e8e-4ec3-a916-020004769f9d" className="x">과제 적은 수업</div>
                          <div>
                            <button onClick={closetalk}>닫기</button>
                          </div>    
            </div>
            
        </Modal>




      </div>
               
    );
}

export default Talk;
          