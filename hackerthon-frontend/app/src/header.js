import React ,{useState} from "react";
import { BrowserRouter, Route, Link } from "react-router-dom";
import Mainpage from "./main"
import Login from "./component/login2"
import timetable from "./component/timetable"
import show from "./show"
import logoimg from "./img/weblogoblue.png";
import Mypage from "./component/mypage";
import GraduatingSimulaiton from "./component/graduatingSimulaiton"



import "./style/Header.css";


const Header = () => {
  const [showHeader, setShowHeader] = useState(true);

  const handlePageChange =() => {
    setShowHeader(false);
  };
  return (
    <BrowserRouter>
      <div  class="header-container">
        <div  class= "logoLink" >
          <Link to="/" style={{ textDecoration: "none" }}>
            <img class = "intro-logo" src ={logoimg}/>
          </Link>
        </div>
        <div class = "header-inside">
          <div class ="login">
            <Link to="/login" style={{ textDecoration: "none" , color : "rgb(0, 84, 121)" ,fontSize:"18px" ,fontWeight:700 }}>
                로그인
            </Link>
          </div>
          <div class ="Timetable">
            <Link to="/Timetable" style={{ textDecoration: "none" ,color :"rgb(0, 84, 121)",fontSize:"18px", marginRight:"50px",fontWeight:700 }}>
                시간표
            </Link>
          </div>
          <div class ="Mypage">
            <Link to="/Mypage" style={{ textDecoration: "none" ,color :"rgb(0, 84, 121)",fontSize:"18px", marginRight:"50px",fontWeight:700 }}>
                마이페이지
            </Link>
          </div>
          <div class ="show">
            <Link to="/show" style={{ textDecoration: "none" ,color : "rgb(0, 84,121)",fontSize:"18px", marginRight:"50px",fontWeight:700 }}>
                메인페이지
            </Link>
          </div>
         
       
        </div>
       
      </div>

      <br />
      
        {" "}
        <Route exact path="/" component={Mainpage} />
        <Route path="/show" component={show} />
        <Route path="/login" component={Login} />
        <Route path="/Timetable" component={timetable} />
        <Route path="/Mypage" component={GraduatingSimulaiton} />
        
      
    
    </BrowserRouter>
  );
}

export default Header;