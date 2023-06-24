import "../style/graph.css";
import React , { useState , useEffect } from "react";
import axios from "axios";

import {
  ComposedChart,
  Line,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  Area
} from "recharts";



export default function Gra() {

  

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
  
  const data = [
    {
      name: "1 전공",
      required: message.전공필요학점,
      earned: message.전공들은학점
   
    },
    {
      name: "2 전공",
      required: 31,
      earned: 20,
    },
    {
      name: "교양",
      required: message.교양필요학점,
      earned: message.교양들은학점, 
    },
    {
      name: "전체",
      required: 134,
      earned: message.교양들은학점 + message.전공들은학점,
    }
  ];

  return (
    <ComposedChart
      layout="vertical"
      width={900}
      height={270}
      data={data}
      margin={{
        top: 20,
        right: 20,
        bottom: 20,
        left: 20
      }}
    >
      <CartesianGrid stroke="#f5f5f5" />
      <XAxis type="number" />
      <YAxis dataKey="name" type="category" scale="band" />
      <Tooltip />
      <Legend />
      {/* <Bar dataKey="required" fill="#808080" barSize={20} /> */}
      <Bar dataKey="earned" barSize={20} fill="#000080" />
      
    </ComposedChart>
  );
}
