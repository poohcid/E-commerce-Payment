import React, {useState} from 'react';
import '../App.css';
import Navbar from "../component/navbar"
import styled from "styled-components"
const TopUp = () =>{
    const [ money, setMoney ] = useState("")
    return(
        <React.Fragment>
            <Navbar/>
            <div className="container">
                <center>
                <h1>เติมเงิน</h1>
                <Input type="number" placeholder="จำนวนเงินที่ต้องการเติม" min={0} value={money} onChange={e => setMoney(e.target.value)}/> <br/>
                <Button onClick={() => console.log(money)}>เติมเงิน</Button>
                
                </center>
            </div>
        </React.Fragment>
    );
}

const Input = styled.input`
    margin: 0 auto;
    padding: 5px;
    width: 290px;
    font-size: 18px;
    border-radius: 10px;
    border: none;
    background-color: #f0f0f0;
    box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);
    font-family: 'Mitr', sans-serif;
    outline: none;
`

const Button = styled.button`
    padding: 10px 25px;
    border: none;
    box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);
    background-image: linear-gradient(to right top, #051937, #004267, #006d76, #00965a, #87b51f);
    border-radius: 10px;
    font-family: 'Mitr', sans-serif;
    margin: 20px;
    font-size: 18px;
    color: #FFF;
    outline: none;
    cursor: pointer;
`

export default TopUp;