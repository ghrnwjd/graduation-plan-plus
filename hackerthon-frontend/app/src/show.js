import React, { useState , useEffect } from "react";
import img from "./img/ho.jpg"
import Gra from "./component/graph"
import Lines from "./component/line"
import Pies from "./component/pie"
import Piestwo from "./component/pietwo"
import Piesthree from "./component/piethree"


import "./style/show.css"
import axios from "axios"

import { BrowserRouter, Route, Link } from "react-router-dom";

function Show() {
  function handleclick(e){
      window.location.replace("https://wis.hufs.ac.kr/src08/jsp/index.jsp")
    }
  function handleclick2(e){
      window.location.replace("https://ice.hufs.ac.kr/ice/index.do")
    }
  function handleclick3(e){
      window.location.replace("https://hufsability.hufs.ac.kr/common/user/login.do?rtnUrl=21306aa07dd2f16c34d6c553b2e842ba014b25b816e5523c22ff3f6c59ab3da5")
    }
  const [creditInfo, setCreditInfo] = useState([{전공남은학점:0, 전공필요학점:0, 전공들은학점:0, 교양남은학점:0, 교양필요학점:0, 교양들은학점:0}]);
    
  
  useEffect(() => {        
          axios.get("http://localhost:8081/student/creditInfo/201903322")
          .then(function (res) {                
              var temp = res.data;       
              // setMessage([{전공남은학점:res.data.전공남은학점, 전공필요학점:res.data.전공필요학점, 전공들은학점: temp.전공들은학점, 교양남은학점:temp.교양남은학점, 교양필요학점:temp.교양필요학점, 교양들은학점: temp.교양들은학점}]);
              setKeywords(res.data);               
          })
          .catch((error) => console.log(error));
  }, []);    
      
  function setKeywords(temp) {
        setCreditInfo(temp);
        console.log(temp);
  }
  
  
    
    
  return (
    
    
      <div class ="show">
        <div class = "show1">
          <div class = "title">
  
          </div>
          <div class = "show11">
            <div class ="show111" align ="left">
        
            </div>
            <div class ="show112">
              <div class = "profile">
                <img class= "humanimage" src={img} width="200px"   />
                <div class = "information" style={{color :"rgb(121, 122, 123)",fontWeight:600}} >
                   201802083  심준보
                   <br>
                   </br>
                   정보통신공학과/4학년
            
              </div>
              </div>
             
            </div>
            
          </div>
          
          <div class = "show12" >
            <div  class =" show120" align = "center" style={{color :"rgb(0, 84, 121)"}}>

            </div>
            <div class = "show121" style={{color :"rgb(0, 84, 121)" ,fontWeight:700 }}>
              <h  onClick = {handleclick}>HUFS 종합 정보 시스템</h> 
            </div>
            <div class = "show122" style={{color :"rgb(0, 84, 121)",fontWeight:700 }}>
              <h onClick = {handleclick2}>학과 홈페이지</h>
            </div>
            <div class = "show123"style={{color :"rgb(0, 84, 121)",fontWeight:700 }} >
              <h onClick = {handleclick3}>HUFS Ability</h>
            </div>
          </div>
        </div>
        <div class ="show2">
          <div class = "show21" >
            <div class = "center1">
              <h2 align="left">학점 취득 현황</h2>
              <Gra/>
            </div>
            <div class = "center2">
              <b>{creditInfo.전공들은학점}/{creditInfo.전공필요학점}</b>
              <br></br>
              <br></br>
          
              <b>20/21</b>
              <br></br>
              <br></br>
              
              <b>25/{creditInfo.교양필요학점}</b>
              <br></br>
              <br></br>
            
              <b>95/123</b>
            </div>
          </div>
          <div class = "show22" align ="left">
            <h2>학점취득 추이</h2>
            
              <Lines/>
           
           
          </div>
          <div class = "show23" >
            <h2 align="left">어학성적</h2>
            <div class ="pie1">
              <h3>TOEIC</h3>
              <Pies/>
            </div>
            <div class ="pie2">
              <h3>TOEFL</h3>
              <Piestwo/>
            </div>
            <div class ="pie3">
              <h3>TOEIC Speaking</h3>
              <Piesthree/>
            </div>
          </div>
        </div>
      </div>
 
    
        
    
   

  
  );
}

export default Show;