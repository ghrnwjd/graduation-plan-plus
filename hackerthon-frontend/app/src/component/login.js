

import React ,{useState,useEffect} from "react";
import "../style/login.css";
import axios from 'axios';
import { useNavigate } from "react-router";


function Login(){

  const navigate = useNavigate();

  const[inputId, setInputId] = useState('')
  const[inputPw, setInputPw] = useState('')

  const handleInputId = (e) => {
    setInputId(e.target.value)
  }
  const handleInputPw =(e) => {
    setInputPw(e.target.value)
  }
  const onClickLogin = () =>{
    navigate("../show")
    // console.log('click login')
    // console.log('ID :' , inputId)
    // console.log('PW :' , inputPw)
    // axios.post('',null, {    //url 넣어주기,
    //   params:{
    //     'user_id' : inputId,
    //     'user_pw' : inputPw
    //   }
    // })
    // .then(res => {
    //   console.log(res)
    //   console.log('res.data.userId ::' ,res.data.userId )
    //   console.log('res.data.msg ::' , res.data.msg)
    //   if(res.data.userId === undefined){
    //     console.log('=====' , res.data.msg)
    //     alert("입력하신 비밀번호가 일치하지 않는다")

    //   }else if (res.data.userId ===null){
    //     console.log('===', '입력하신 비밀번호가 일치하지않습니다')
    //   }else if (res.data.userId === inputId){
    //     console.log('====' ,'로그인성공')
    //     sessionStorage.setItem('user_id',inputId)
    //   }

    //   //document.location.href ='/'
    // })
    // .catch()

  }

  useEffect(() =>{
    axios.get('') //url
    .then(res=>console.log(res))
    .catch()
  },[])
  
  return (
    <div class ="background">    
      <div class = "form1">
        <div>
          
          <input class ="id" type ="text" id="login" name="input_id" value ={inputId} onChange = {handleInputId} placeholder="201903322"/>
        </div><br></br>
        <div>
          <input class ="password" type ="password" id="password" name="input_pw"value={inputPw} onChange ={handleInputPw} placeholder="*********"/>
        </div>
      </div>
      <div class = "form2">
        <div>
            <button type ="submit" onClick={onClickLogin} class = "loginbutton">로그인</button>
        </div>
      </div>
     
  </div>

  )

}

    // function href(){
    //   window.location.replace("./component/afterlogin")
    // }

export default Login;

