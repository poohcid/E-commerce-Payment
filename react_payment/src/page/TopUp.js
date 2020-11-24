import React, { useState, useEffect } from "react";
import "../App.css";
import Navbar from "../component/navbar";
import styled from "styled-components";
import { useSelector, useDispatch } from "react-redux";
import { topUp } from "../redux/action/balanceAction";

const TopUp = () => {
  const dispatch = useDispatch();
  const token = useSelector((state) => state.athorize.id);
  const [money, setMoney] = useState("");
  const [wallet, setWallet] = useState(0);

  useEffect(() => {
    const abort = new AbortController();
    const signal = abort.signal;
    const url = "http://localhost:8080/wallet/";
    fetch(url, {
      method: "GET",
      signal,
      headers: {
        Authorization: token,
        "Content-Type": "application/json",
      },
    }).then(async (response) => {
      if (response.status === 200) {
        const json = await response.json();
        dispatch(topUp(json.balance));
        setWallet(json.balance);
      }
    });

    return () => {
      abort.abort();
    };
  }, []);

  const withdraw = async (money) => {
    const changeType = Number(money);
    const signal = new AbortController().signal;
    const url = "http://localhost:8080/wallet/addBalance/";
    const body = {
      balance: changeType < 0 ? 0 : changeType,
    };
    const response = await fetch(url, {
      method: "PUT",
      body: JSON.stringify(body),
      signal,
      headers: {
        Authorization: token,
        "Content-Type": "application/json",
      },
    });
    if (response.status === 200) {
      const json = await response.json();
      dispatch(topUp(json.balance));
      setWallet(json.balance);
      setMoney("");
    }
  };

  return (
    <React.Fragment>
      <Navbar />
      <div className="container">
        <center>
          <h1>เติมเงิน</h1>
          <Input
            type="number"
            placeholder="จำนวนเงินที่ต้องการเติม"
            min={0}
            value={money}
            onChange={(e) => setMoney(e.target.value)}
          />{" "}
          <br />
          <Button
            onClick={() => {
              withdraw(money);
            }}
            disabled={money === ""}
          >
            เติมเงิน
          </Button>
        </center>
      </div>
    </React.Fragment>
  );
};

const Input = styled.input`
  margin: 0 auto;
  padding: 5px;
  width: 290px;
  font-size: 18px;
  border-radius: 10px;
  border: none;
  background-color: #f0f0f0;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  font-family: "Mitr", sans-serif;
  outline: none;
`;

const Button = styled.button`
  padding: 10px 25px;
  border: none;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  background-image: linear-gradient(
    to right top,
    #051937,
    #004267,
    #006d76,
    #00965a,
    #87b51f
  );
  border-radius: 10px;
  font-family: "Mitr", sans-serif;
  margin: 20px;
  font-size: 18px;
  color: #fff;
  outline: none;
  cursor: pointer;
`;

export default TopUp;
