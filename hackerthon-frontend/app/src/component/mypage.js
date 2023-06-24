import React, { useState , useEffect } from "react";
import { BrowserRouter, Route, Link } from "react-router-dom";
import "../style/mypage.css"
import img from "../img/profile.jpg"
import axios from "axios"
import $ from "jquery"

function Mypage() {
    const [message, setMessage] = useState([{전공남은학점:0, 전공필요학점:0, 전공들은학점:0, 교양남은학점:0, 교양필요학점:0, 교양들은학점:0}]);
   
 
    useEffect(() => {        
        axios.get("http://localhost:8081/student/creditInfo/201903322")
        .then(function (res) {                
            var temp = res.data;       
            // setMessage([{전공남은학점:res.data.전공남은학점, 전공필요학점:res.data.전공필요학점, 전공들은학점: temp.전공들은학점, 교양남은학점:temp.교양남은학점, 교양필요학점:temp.교양필요학점, 교양들은학점: temp.교양들은학점}]);
            setKeyword(res.data);               
        })
        .catch((error) => console.log(error));
    }, []);    
    
    function setKeyword(temp) {
        setMessage(temp);
        console.log(temp);
    }
    
    
    
    return (
        
        <div class = "fa" >
            {/* <button onClick={get}>눌러봐</button> */}
            <div class = "one">
                <img class = "humanimage2" src ={img} width ="200px" />
                <div class = "textname">
                    201802083 심준보
                    정보통신공학과/4학년

                </div>
            <div class = "main">
                <table class = "plustable">
                    <tr>
                        <th></th>
                        <th>이수구분</th>
                        <th>이수기준 학점</th>
                        <th>학생이수 학점</th>
                   
                        <th>잔여학점</th>
                
                    </tr>
                    <tr>
                        <th>전공</th>
                        <th>제 1 전공</th>
                        <td>{message.전공필요학점}</td>
                        <td>{message.전공들은학점}</td>
                        <th>{message.전공남은학점}</th>
                    </tr>
                    <tr>
                        <th>전공</th>
                        <th>제 2 전공</th>
                        <td>33</td>
                        <td>21</td>
                        <th>12</th>
                    </tr>
                    <tr>
                        <th>전공</th>
                        <th>제 2 전공</th>
                        <td>33</td>
                        <td>21</td>
                        <th>12</th>
                    </tr>
                    <tr>
                        <th>교양</th>
                        <th>필수교양</th>
                        <td>{message.교양필요학점}</td>
                        <td>{message.교양들은학점}</td>
                        <th>{message.교양남은학점}</th>
                    </tr>
                    <tr>
                        <th>교양</th>
                        <th>필수교양</th>
                        <td>16</td>
                        <td>8</td>
                        <th>8</th>
                    </tr>
                    

                </table>         
                    
                
                
            </div>
        </div>
      </div>

    );
}


export default Mypage;