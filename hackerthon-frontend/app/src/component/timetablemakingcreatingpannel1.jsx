
import React ,{useState} from "react";
import PropTypes from "prop-types";
import plus from "../img/plus4.png"
import Modal from 'react-modal';

import "../style/timetablemakingcreatingpannel1.css";

Modal.setAppElement('#root'); 

const Timetablemakingcreatingpannel1 = () => {
    const [isOpen, setIsOpen] = useState(false);

    const openPopup = () => {
        setIsOpen(true);
    };

    const closePopup = () => {
        setIsOpen(false);
    };
    const [isChecked, setIsChecked] = useState(false);
    const [selectedText, setSelectedText] = useState('');
    const [isChecked2, setIsChecked2] = useState(false);
    const [selectedText2, setSelectedText2] = useState('');
    const [isChecked3, setIsChecked3] = useState(false);
    const [selectedText3, setSelectedText3] = useState('');
    const [isChecked4, setIsChecked4] = useState(false);
    const [selectedText4, setSelectedText4] = useState('');

    const handleCheckboxChange = (event) => {
        setIsChecked(event.target.checked);
        setSelectedText(event.target.checked ? '데이터통신 및 실험' : '');
    };
    const handleCheckboxChange2 = (event) => {
        setIsChecked2(event.target.checked);
        setSelectedText2(event.target.checked ? '데이터 통신 및 실험 ': '');
    };
    const handleCheckboxChange3 = (event) => {
        setIsChecked3(event.target.checked);
        setSelectedText3(event.target.checked ? '데이터 통신 및 실험  ': '');
    };
    const handleCheckboxChange4 = (event) => {
        setIsChecked4(event.target.checked);
        setSelectedText4(event.target.checked ? '데이터 통신 및 실험, 컴퓨터구조': '');
    };
    

    
    return (
        <div class = "mainmain">
            <img onClick={openPopup} class="plus" src={plus} />
            <Modal
                isOpen={isOpen}
                onRequestClose={closePopup}
                contentLabel="팝업 제목"
            >
                            <div data-layer="fbeab69a-24a5-4efe-b244-2db427eecc74" className="timetablemakingcreatingpannel1">        <svg data-layer="6d573df9-21ab-4e86-902d-c6d17d361257" preserveAspectRatio="none" viewBox="0 -0.5 1127 1" className="x125"><path d="M 0 0 L 1127 0"  /></svg>
                        <svg data-layer="d72d76fa-a943-4bf1-b610-4ee4ae745d72" preserveAspectRatio="none" viewBox="-0.0009765625 -1.5 1128.251953125 3.5" className="x126"><path d="M 0 0 L 1128.25 0.5"  /></svg>
                        <div data-layer="a1f231bf-54bc-481a-b970-193ca071acf2" className="x773c7c36">필수과목</div>
                        <div data-layer="588b2e6e-5417-4372-aec0-a9c528d20cd5" className="x997f9b8e">재수강</div>
                        <div data-layer="12150485-261d-405d-aeb9-3ddd9a387418" className="x6105479b">수업 찾기</div>
                        <svg data-layer="2c618dfa-4806-4a65-afad-42c0f041715d" preserveAspectRatio="none" viewBox="-0.5 0 1 495.60174560546875" className="x127"><path d="M 0 0 L 0 495.6017456054688"  /></svg>
                        <svg data-layer="3f702787-f583-48ae-afa2-ed44c7104be9" preserveAspectRatio="none" viewBox="-0.5 0 1 495.60174560546875" className="x128"><path d="M 0 0 L 0 495.6017456054688"  /></svg>
                        <svg data-layer="35a509a9-bf81-459f-912d-bd1c20eeeeaf" preserveAspectRatio="none" viewBox="-0.5 0 1 495.60174560546875" className="x129"><path d="M 0 0 L 0 495.6017456054688"  /></svg>
                        <svg data-layer="1160efe4-7782-4145-8250-fec3b24d4c9f" preserveAspectRatio="none" viewBox="-0.5 0 1 495.60174560546875" className="x130"><path d="M 0 0 L 0 495.6017456054688"  /></svg>
                        <svg data-layer="edb72e91-ab9d-40d8-a485-39397b6b2689" preserveAspectRatio="none" viewBox="1.2392578125 -0.771728515625 1128.5 1.5" className="x11"><path d="M 1.9892578125 -0.021728515625 L 1128.9892578125 -0.021728515625"  /></svg>
                        <div data-layer="9732bdca-6497-498c-b697-6ecfb9be0572" className="xc609fea3"><span className="xc609fea3-0">시간표 상세 설정</span><span className="xc609fea3-9"> </span></div>
                        <div data-layer="7806743a-9e15-44ea-9764-bdee5abe112f" className="x84"></div>
                        <div data-layer="59d6adda-ff08-48f8-a510-be2bb2b9a98e" className="xb36e9cb8">선택한 과목</div>
                        <div data-layer="e5080a29-e894-489b-9813-71c09f7c343c" className="x31">            <div data-layer="97e4b295-3535-4ef3-ac76-ef726bcd731b" className="x36025f21"><label><input type='checkbox' checked={isChecked3} onChange={handleCheckboxChange3}/>데이터통신및실험</label></div>
                            
                </div>
                        <svg data-layer="73bba6d2-ad65-425c-be98-2787cbb91613" preserveAspectRatio="none" viewBox="0 -0.5 187 1" className="x132"><path d="M 0 0 L 187 0"  /></svg>
                        <svg data-layer="b22952b9-c969-4cba-9bfa-c0885a75e105" preserveAspectRatio="none" viewBox="0 -0.5 187 1" className="x133"><path d="M 0 0 L 187 0"  /></svg>
                        <div data-layer="f2832a38-ca1e-416f-8835-10a404090328" className="xca49d6f5">전공</div>
                        <svg data-layer="e023a695-b8aa-48c1-8c63-59f0cc519f0f" preserveAspectRatio="none" viewBox="0 -0.5 187 1" className="x134"><path d="M 0 0 L 187 0"  /></svg>
                        <div data-layer="dd3ebd44-f691-400e-8302-9197cd42ace3" className="x24dcad30">교양</div>
                        <div data-layer="1be4054f-8126-4b28-8281-53bd288d98e5" className="x33">            <div class = ".checkche" data-layer="4641c917-f80b-4dac-b91d-1d6d8a64e2d2" className="x61b26b08"><label><input class = "ho" type='checkbox' checked={isChecked} onChange={handleCheckboxChange}/>신호및시스템</label></div>
                           
                </div>
                        <div data-layer="371c20dd-47a3-4209-91f0-aa0ea6ca0513" className="x34">            <div data-layer="f0014363-ba9b-4193-a362-5a60f8c15ded" className="x649a52d3"><label><input type='checkbox' checked={isChecked} onChange={handleCheckboxChange}/>컴퓨팅사고</label></div>
            
                </div>
                        <div data-layer="bf14da05-3c7d-4cb9-bab1-77509146bb79" className="x36">            <div data-layer="41f21d4e-fd6c-4f6d-a480-602efeca5161" className="x3960ef7d"><label><input type='checkbox' checked={isChecked} onChange={handleCheckboxChange}/>데이터통신및실험</label></div>
                            
                </div>
                        <svg data-layer="e3cce700-8c16-441e-ab1f-e69e89c77621" preserveAspectRatio="none" viewBox="0 -0.5 187 1" className="x135"><path d="M 0 0 L 187 0"  /></svg>
                        <div data-layer="7d60d2ba-b8fd-4adc-9953-bfd5362c8bc4" className="d">D</div>
                        <svg data-layer="b6a1874c-2496-443a-851d-319a6eff066b" preserveAspectRatio="none" viewBox="0 -0.5 187 1" className="x136"><path d="M 0 0 L 187 0"  /></svg>
                        <div data-layer="25768008-3b91-401f-8db1-8ca03b0d2448" className="f">F</div>
                        <svg data-layer="bb3951d9-8a79-4ef4-9051-a7749c5b0ea5" preserveAspectRatio="none" viewBox="0 -0.5 187 1" className="x137"><path d="M 0 0 L 187 0"  /></svg>
                        <div data-layer="d1e86aa3-66f2-4ec2-b904-a09aa3d7e7cb" className="x37">            <div data-layer="d2930c0b-65d4-425c-96e5-a5d881e902e6" className="x34ddda3d">머신러닝</div>
                            <div data-layer="0fb80e49-2535-405a-a4cc-37e223728c51" className="x851e9ab740"></div>
                            <div data-layer="f17aa174-236d-4d80-bd81-01d8635c24e3" className="x19f67ecc">머신러닝</div>
                </div>
                        <div data-layer="262ee542-7efd-4dc3-b183-301877a8f5b7" className="x38">            <div data-layer="ddbd543f-0aaa-4a13-8f93-0715a91f3773" className="iotSystem">IoT System</div>
                            <div data-layer="1638bd08-4760-4563-89c0-6223034e6fde" className="x852890cf7e"></div>
                </div>
                        <div data-layer="f6191bcf-1b69-4d14-a5de-8b1350455b4f" className="x39">            <div data-layer="e3335971-790b-48bd-a2e7-cea1f0ac3cb7" className="x67068911">게임프로그래밍</div>
                            <div data-layer="2b46d83f-22dc-498b-a6c0-4064669fc864" className="x85220f25c5"></div>
                </div>
                        <div data-layer="1d199460-a482-498d-a015-7cc82ab26c7c" className="xc2d5948c">전공 선택</div>
                        <svg data-layer="b4c437d1-3208-404a-8e55-7bbea815a050" preserveAspectRatio="none" viewBox="-0.5 0 1 437.2215576171875" className="x138"><path d="M 0 0 L 0 437.2215576171875"  /></svg>
                        <svg data-layer="f26829fb-6c4c-4107-a7d7-de61fff4fde2" preserveAspectRatio="none" viewBox="0 -0.5 177 1" className="x139"><path d="M 0 0 L 177 0"  /></svg>
                        <div data-layer="7ff5ee91-0976-4017-acd3-f63457129203" className="x40">            <div data-layer="3db1659b-43c2-4a0e-9e62-dacba964e1a2" className="x32b27107">게임프로그래밍</div>
                            <div data-layer="0e89c4d2-0c8c-48f8-9c20-74cd3eb6ab7a" className="x8597335b4f"></div>
                </div>
                        <div data-layer="15255d3a-29a0-4450-9916-ab94ed873709" className="x41">            <div data-layer="eace2d20-c078-460f-8646-b702648db232" className="xe31d01f9">교양</div>
                            <div data-layer="f4ad0e5a-c59c-4ef4-971a-53fa2d4da22a" className="x856a215c67"></div>
                </div>
                        <div data-layer="7a8f8ffb-11b3-48a2-91d6-f2d270cc3a14" className="x42">            <div data-layer="6443ab83-f882-43a5-ba6e-f951e97b8461" className="xdbe4f511">정보통신공학과</div>
                            <div data-layer="bebfdb9f-dd0f-4823-8b53-9ffc67c4b52b" className="x855d77a9f1"></div>
                </div>
                        <div data-layer="0900136e-fd4c-426a-85e3-96028309bdfb" className="x43">            <div data-layer="ae6c242e-524d-46d7-ab20-7fcec75494ac" className="x0d6109c9">컴퓨터공학과</div>
                            <div data-layer="e9a89aae-5a98-4a37-8d50-3ee397dfce67" className="x85d30a8ece"></div>
                </div>
                        <div data-layer="52ed6a7d-b5f2-4c36-8d62-b6ccd8175e86" className="x44">            <div data-layer="65eda21a-de30-4d5c-bc50-a1a8fa6ae0aa" className="xfe343f48">산업공학과</div>
                            <div data-layer="9efa5a3b-d540-4cab-a083-4ddcfde6bce6" className="x859ef3f59e"></div>
                </div>
                        <div data-layer="bda3e462-4648-4e3e-9637-376d9e8da5bd" className="x45">            <div data-layer="95390f12-4554-4df9-acfa-fa125ddad750" className="x47e0f0ba">전자물리학과</div>
                            <div data-layer="fce546e6-9564-4947-80d1-963dcc7c800d" className="x85faf90234"></div>
                </div>
                        <div data-layer="ec2bbe45-30da-468a-a27a-a05e80daf430" className="x46">            <div data-layer="a129bb96-acc8-4950-9942-53565fcb8456" className="x5edaf730"></div>
                            
                            <div data-layer="12a28d70-d259-4c79-b449-4d39f8fd89eb" className="xe3315923"><label><input type='checkbox' checked={isChecked2} onChange={handleCheckboxChange2}/>운영체제</label></div>
                </div>
                        <div data-layer="72bd59e3-ce18-4de1-acff-2348b7be7eca" className="x47">            <div data-layer="23b21db7-0470-4153-848f-77a24401e5fc" className="x04ae0919"><label><input type='checkbox' checked={isChecked} onChange={handleCheckboxChange}/>데이터통신및실험</label></div>
                            
                </div>
                        <div data-layer="5c7679a8-153a-4512-9b0b-6196c63b319c" className="x48">            <div data-layer="d359e289-4ec0-408f-b36a-41287053023f" className="x0fb2dfcb"><label><input type='checkbox' checked={isChecked} onChange={handleCheckboxChange}/>통신공학</label></div>
                            
                </div>
                        <div data-layer="83d3308b-eb02-4866-9e06-b1a2e23953b3" className="x49">            <div data-layer="61634438-7865-4594-964b-cb9eb368401b" className="xcce0a97b"><label><input type='checkbox' checked={isChecked} onChange={handleCheckboxChange}/>딥러닝</label></div>
                        
                </div>
                        <div data-layer="425dbbed-4dbc-4ca4-b7c1-e1e0e4a09ec1" className="x50">            <div data-layer="c0824eac-b859-4c0b-9ade-712e1e080780" className="x54c38b36"><label><input type='checkbox' checked={isChecked} onChange={handleCheckboxChange}/>오픈소스소프트웨어및실습</label></div>
                        
                </div>
                        <div data-layer="e4905de4-c34c-4f23-94d2-6e4f9db07546" className="x51">            <div data-layer="faa74f06-d808-4de4-a2b3-838f1563c1d8" className="x"><label><input type='checkbox' checked={isChecked} onChange={handleCheckboxChange}/>회로이론의 이해</label></div>
                            
                </div>
                        <div data-layer="cef4b5e5-b178-4fce-88d7-0ec8135287c2" className="x52">            <div data-layer="3f34dba1-40be-46a9-a76e-17a2e307a101" className="x22b5d6eb"><label><input type='checkbox' checked={isChecked} onChange={handleCheckboxChange}/>네트워크 보안</label></div><button class ="test_btn2" onClick={closePopup}>close</button>
                        
                </div>
                        <svg data-layer="6bba7d5b-905c-48c2-948e-01fb8a69eaf3" preserveAspectRatio="none" viewBox="0 -0.5 177 1" className="x140"><path d="M 0 0 L 177 0"  /></svg>
                        <div data-layer="b1e94221-186b-4833-b3ff-74b8c1cb831d" className="xae0345f1">학년</div>
                        <div data-layer="21ce1541-f070-4021-b0a9-61e426e33819" className="x53">            
                            <div data-layer="2c0c02fb-d572-47ee-a805-1b9fe99920aa" className="x8ed35573"><label><input type='checkbox' checked={isChecked4} onChange={handleCheckboxChange4}/>전체</label></div>
                </div>
                        <div data-layer="44a5fb53-abcb-452c-8261-ce9b5b0c7608" className="x54">            
                            <div data-layer="a8fd192c-250a-4e26-bb47-f2138d996e8d" className="x1"><label><input type='checkbox' />1학년</label></div>
                </div>
                        <div data-layer="dd5c1858-a576-4446-bef2-3bdddd716921" className="x55">            
                            <div data-layer="20163e03-a1b4-4da7-840c-99ad909d6301" className="x2"><label><input type='checkbox'/>2학년</label></div>
                </div>
                        <div data-layer="da8dc5d9-27e9-4c73-bed5-cc68764fc097" className="x56">            
                            <div data-layer="678fd658-c9a9-4e2c-a845-361371d1dd2f" className="x3"><label><input type='checkbox' /> 3학년 </label></div>
                </div>
                        <div data-layer="f833bd22-9e4b-4fc6-820b-4d16c8e47249" className="x57">            
                            <div data-layer="625ede92-3bdf-43db-b366-4c3bd18e28cb" className="x4"><label><input type='checkbox' />4학년</label></div>
                </div>
                        <svg data-layer="3847d85a-0697-42db-bdd8-4cdd37246d60" preserveAspectRatio="none" viewBox="0 -0.5 177 1" className="x141"><path d="M 0 0 L 177 0"  /></svg>
                        <div data-layer="26ec9f0f-8028-47d2-b45d-2e679ac6ab82" className="x59">            
                         {selectedText3 && <p>{selectedText3}</p>} 
                            <div data-layer="53fc4fa6-bca6-4a1f-a788-b1278aa82c31" className="x588c10e96e">                <svg data-layer="8bcbaa60-b477-4457-a015-3d1fb5eb514b" preserveAspectRatio="none" viewBox="-0.3720703125 -0.33448028564453125 9.744140625 10.668960571289062" className="x1429bdbde40"><path d="M 9 0 L 0 10"  /></svg>
                                <svg data-layer="31fbe2dd-1e95-45a4-b35e-71f892810df2" preserveAspectRatio="none" viewBox="-0.3720703125 -0.33448028564453125 9.744140625 10.668960571289062" className="x14337cba3b6"><path d="M 0 0 L 9 10"  /></svg>
                                
                </div>
                </div>
                        <div data-layer="506cc4fc-9b33-479b-8bbc-818d99ff34f5" className="x60">            
                            <div data-layer="6da5e29c-81a4-4f2a-bcf1-3c7f2bfb7001" className="xe1911b41"></div><button class ="test_btn1" onClick={closePopup}>next</button>
                            <div data-layer="4de71062-a630-4859-94f7-a489acc55dfc" className="x58">                <svg data-layer="b3b811dc-1195-4d48-87e0-3b2bc294152c" preserveAspectRatio="none" viewBox="-0.3720703125 -0.33448028564453125 9.744140625 10.668960571289062" className="x142"><path d="M 9 0 L 0 10"  /></svg>
                            
                            
                            
                </div>
                </div>
                </div>

            </Modal>



        </div>
         

    );
  }


Timetablemakingcreatingpannel1.propTypes = {

}

Timetablemakingcreatingpannel1.defaultProps = {

}


export default Timetablemakingcreatingpannel1;
          