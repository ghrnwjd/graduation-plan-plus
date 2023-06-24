import React from "react";
import img from "../img/profile.jpg"
import "../style/timetable.css"
import faker from "faker";
import Table from "./table"
import plus from "../img/plus4.png"
import zoom from "../img/zoom.png"
import zoom2 from "../img/zoom.png"
import zoom3 from "../img/zoom.png"
import zoom4 from "../img/zoom.png"
import Popup from "./popup"
import Talk from "./talk"
import logos from "../img/cute.png";
import talktalk from "../img/TalkIntroducing.jpg";
import talks from "../img/TimeRingTalk.png";
import Timetablemakingcreatingpannel1 from "./timetablemakingcreatingpannel1"



import { BrowserRouter, Route, Link } from "react-router-dom";

faker.seed(100);
function timetable() {
  const columns = ["월", "화", "수" , "목", "금"];
  const raws = ["1교시" , "2교시" ,"3교시", "4교시" ,"5교시","6교시","7교시","8교시","9교시"]


  const data = Array(9)
    .fill()
    .map(() => ({
      index : ["1","2","3","4","5","6","7","8","9"],
      rowindex : [9,8,7,6,5,4,3,2,1],
      mon: faker.name.lastName() + faker.name.firstName(),
      tu: faker.internet.email(),
      wed: faker.phone.phoneNumber(),
      th: faker.phone.phoneNumber(),
      fri: faker.phone.phoneNumber(),
    }));
  function handleclick(e){
    window.location.replace("https://wis.hufs.ac.kr/src08/jsp/index.jsp")
  }
  function handleclick2(e){
    window.location.replace("https://ice.hufs.ac.kr/ice/index.do")
  }
  function handleclick3(e){
    window.location.replace("https://hufsability.hufs.ac.kr/common/user/login.do?rtnUrl=21306aa07dd2f16c34d6c553b2e842ba014b25b816e5523c22ff3f6c59ab3da5")
  }
  return (
    
      <div class ="timetable">
        <div class = "timetable1" >
          <div class = "timetable1title" align="center" style={{fontWeight:700,fontSize:"23px",marginTop:"15px" , color:" rgb(0, 84, 121)"}} >
            시간표 리스트 
          </div>
          <div class = "firsttable" style={{marginTop:"40px"}}>
            이번학기 최종 시간표
            <img class ="zoom" src ={zoom}/>
            
          </div>
      
          <div class = "secondtable">
            보류 시간표
            <img class ="zoom2" src ={zoom2}/>
          </div>
          <div class = "thirdtable">
            보류 시간표 2
            <img class ="zoom4" src ={zoom4}/>
          </div>
          <div class = "fourtable">
            재미로 만들어본 시간표
            <img class ="zoom3" src ={zoom3}/>
          </div>
          <div class = "import">
           
            <Timetablemakingcreatingpannel1/>
          </div>
  
   
     
        </div>
        <div class ="timetable2">
          <div class = "nametable" style={{color:" rgb(0, 84, 121)"}}>
            <h2 align="left">시간표 메이킹</h2> <div class ="cute"><img class = "intro-logosss" src ={talks}/><div class = "cutecute"><Talk/></div></div>
            
          </div>
        
       
          <div class = "time">
            <div class  = "realtime">
              <Table  columns={columns} data = {data} />
            </div>
          
            
          </div>
        </div>
        <div class = "imgimgimg">
            <img class = "intro-logossss" src ={talktalk}/>
        </div>
      </div>
    
  );
}

export default timetable;