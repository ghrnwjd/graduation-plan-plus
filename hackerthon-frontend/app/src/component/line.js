import "../style/line.css";
import React from "react";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend
} from "recharts";

const data = [
  {
    name: "1학년 1학기",
    score: 3.2,

 
  },
  {
    name: "1학년 2학기",
    score: 2.5,
   
  
  },
  {
    name: "2학년 1학기",
    score: 3.5,

   
  },
  {
    name: "2학년 2학기",
    score: 3.7,
 
   
  },
  {
    name: "3학년 1학기",
    score: 3.6,
    
  
  },
  {
    name:"3학년 2학기",
    score: 3.9,
  
  },
  {
    name: "4학년 1학기",
    score: 3.4,
 
    
  },
  {
    name: "4학년 2학기",
    score: 4.5,
   

  }
];

export default function Lines() {
  return (
    <LineChart width={900} height={250} data={data}>
      <CartesianGrid strokeDasharray="3 3" />
      <XAxis dataKey="name" padding={{ left: 30, right: 30 }} />
      <YAxis domain={[0, 4.5]} tickCount={10} />
      <Tooltip />
      <Legend />
      
      <Line type="monotone" dataKey="score" stroke="#964b00" />
    </LineChart>
  );
}
