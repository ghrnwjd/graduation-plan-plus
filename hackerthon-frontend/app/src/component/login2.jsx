
import React from "react";
import PropTypes from "prop-types";

import "../style/login2.css";
import "../img/weblogoblue.png"
import { useNavigate } from "react-router";

function Login(){

  const onClickLogin = () =>{
    window.location.href("./show.js")
  };
    
    
    return (
                        <div data-layer="a932601a-dad2-40f7-99be-b585e861add7" className="login2">        <div data-layer="cb3f3372-efbe-4519-ac7e-2b2042ab00ff" className="whitewholebox"></div>
                      <div data-layer="34a31553-a63c-4ebd-805b-817c8fa01041" className="namebox"></div>
                      <div data-layer="7976cfce-14aa-4611-a1b3-c92db923e7a8" className="passwordbox"></div>
                      <div data-layer="69c1f3c9-e349-47c1-ae02-c90d2c7709cb" className="xb5cd8d78">아이디를 입력하세요</div>
                      <div data-layer="1a3dc6eb-4dbe-493b-9704-af8ce6dcc9b2" className="x">비밀번호를 입력하세요</div>
                      <div data-layer="e081b7e4-7a2a-4d39-9a0d-ddf5ee5c4f28" className="loginbox"></div>
                      <div data-layer="6bca0caa-8196-478b-ac81-20dcb245840e" className="x1626565e"><button onClick={onClickLogin}></button>로그인</div>
                      <div data-layer="65032b51-5d30-4993-afc3-46ab3a144712" className="x328de864">아이디찾기</div>
                      <div data-layer="de9152bf-b18d-41a6-a4c2-f58cb9c9bb60" className="xa7eced56">비밀번호찾기</div>
                      <div data-layer="45ed5461-611a-404a-99e6-8b07bea919e3" className="xe419880f">회원가입</div>
                      <svg data-layer="b8ec027f-7f43-455e-bc17-f1dd79d0e4f9" preserveAspectRatio="none" viewBox="-0.5 0 1 23" className="line2"><path d="M 0 0 L 0 23"  /></svg>
                      <svg data-layer="62056715-e102-4dda-a096-19a9b6a5de14" preserveAspectRatio="none" viewBox="-0.5 0 1 23" className="line1"><path d="M 0 0 L 0 23"  /></svg>
                      <div data-layer="591d55cb-8bf6-4ac6-83eb-eb0538ab4de9" className="graduatePlane">Graduate Plane+</div>
                      <div data-layer="64d4822b-b850-4e8f-9fc1-b5603ab05b51" className="weblogoblue"></div>
              </div>

    );

}

export default Login;
          