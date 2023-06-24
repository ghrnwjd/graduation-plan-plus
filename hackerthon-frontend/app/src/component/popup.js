import React, { useState } from 'react';
import Modal from 'react-modal';
import plus from "../img/cute.png"
import "../style/popup.css"
Modal.setAppElement('#root'); // 모달이 루트 요소 외부에 마운트되는 경우 필요합니다.

const Popup = () => {
    const [isOpen, setIsOpen] = useState(false);

    const openPopup = () => {
        setIsOpen(true);
    };

    const closePopup = () => {
        setIsOpen(false);
    };
    const [isChecked, setIsChecked] = useState(false);
    const [selectedText, setSelectedText] = useState('');

    const handleCheckboxChange = (event) => {
        setIsChecked(event.target.checked);
        setSelectedText(event.target.checked ? '텍스트가 선택됨' : '');
    };



    return (
        <div>
            
            <img onClick={openPopup} class = "intro-logoss" src ={plus}/>
            <Modal
                isOpen={isOpen}
                onRequestClose={closePopup}
                contentLabel="팝업 제목"
            >
                <div  class="popupbox" id="title">
                    <h2>시간표 상세 설정</h2>
                    <p >선택한 과목</p>
                    <div class="select">
                        <div class="select1">
                            데이터통신및 실험 , 운영체제
                        </div>

                    </div>

                    {/* <table>
                <tr>
                    <th>필수과목</th>
                    <th>재수강</th>
                    <th>수업 찾기</th>
                </tr>
                <tr>
                    <th>전공</th>
                    <th>D</th>
                    <th>학년</th>
                </tr>
                <tr>
                    <th>데이터통신및실험</th>
                    <th>머신러닝</th>
                    <th>전체</th>
                </tr>
                <tr>
                    <th>교양</th>
                    <th>F</th>
                    <th>전공선택</th>
                </tr>
            </table> */}

                    {/* <button onClick={closePopup}>닫기</button> */}


                </div>
                <form>
                    <div id="screenLeft">
                        <p class = "middleTitle">필수 과목</p><br></br>
                        <div id="name">
                            <div id="major"><p class="smallTitle">전공</p>
                                <div class = "subList">
                                    <input type="checkbox" id="major1" value="major1"></input>
                                    <label id = "subjectList" for="major1">데이터통신및실험</label><br></br>
                                    <input type="checkbox" id="major2" value="major2"></input>
                                    <label><input type='checkbox' checked={isChecked} onChange={handleCheckboxChange}/>컴퓨터구조</label>
                                    <label><input type='checkbox' checked={isChecked} onChange={handleCheckboxChange}/>컴퓨터네트워크</label>
                                    <label><input type='checkbox' checked={isChecked} onChange={handleCheckboxChange}/>데이터통신및실험</label>
                                    <label><input type='checkbox' checked={isChecked} onChange={handleCheckboxChange}/>신호및시스템</label>
                                    {selectedText && <p>{selectedText}</p>}
                                </div>
                            </div>

                        </div>

                        <div id="nonmajor"><p class="smallTitle">교양</p>
                            <div id="nonmajor" class = "subList">
                                <input type="checkbox" id="nonmajor1" value="nonmajor1"></input>
                                <label for="nonmajor1">컴퓨팅사고</label><br></br>
                                <input type="checkbox" id="nonmajor2" value="nonmajor2"></input>
                                <label for="nonmajor2">미네르바인문</label><br></br>

                            </div>
                        </div>

                    </div>
                    <div id="screenRight">
                        <p class = "middleTitle">수업 찾기</p><br></br>
                        <div id="screenLeft2">
                            <div id="grade"><p class="smallTitle">학년</p>
                                <div class = "subList">
                                    <input type="checkbox" id="all" value="all"></input>
                                    <label for="all">전체</label><br></br>
                                    <input type="checkbox" id="1" value="1"></input>
                                    <label for="1">1학년</label><br></br>
                                    <input type="checkbox" id="2" value="2"></input>
                                    <label for="2">2학년</label><br></br>
                                    <input type="checkbox" id="3" value="3"></input>
                                    <label for="3">3학년</label><br></br>
                                    <input type="checkbox" id="4" value="4"></input>
                                    <label for="4">4학년</label>
                                </div>
                            </div>
                            <div id="name">
                                <div id="selecmajor"><p class="smallTitle">전공선택</p>
                                    <div class = "subList">
                                        <input type="checkbox" id="m1" value="m1"></input>
                                        <label for="m1">교양</label><br></br>
                                        <input type="checkbox" id="m2" value="m2"></input>
                                        <label for="m2">정보통신공학과</label><br></br>
                                        <input type="checkbox" id="m3" value="m3"></input>
                                        <label for="m3">컴퓨터공학과</label><br></br>
                                        <input type="checkbox" id="m4" value="m4"></input>
                                        <label for="m4">산업공학과</label><br></br>
                                        <input type="checkbox" id="m5" value="m5"></input>
                                        <label for="m5">전자물리학과</label>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div id="screenRight2">
                            {/* 만약 백한테 list형식으로 받아오면 그걸 그냥 출력하면 될듯(지금은 큰 틀만) */}
                            <div class = "subList">
                                <input type="checkbox" id="m1" value="m1"></input>
                                <label for="m1">운영체제</label><br></br>
                                <input type="checkbox" id="m2" value="m2"></input>
                                <label for="m2">데이터통신및실험</label><br></br>
                                <input type="checkbox" id="m3" value="m3"></input>
                                <label for="m3">통신공학</label><br></br>
                                <input type="checkbox" id="m4" value="m4"></input>
                                <label for="m4">오픈소프트</label><br></br>
                                <input type="checkbox" id="m5" value="m5"></input>
                                <label for="m5">네트워크보안</label>
                                <input type="checkbox" id="m6" value="m6"></input>
                                <label for="m6">딥러닝</label>
                            </div>
                        </div>
                    </div>
                </form>

                <div>
                    <button onClick={closePopup}>닫기</button>
                </div>
            </Modal>
        </div>
    );
};

export default Popup;
