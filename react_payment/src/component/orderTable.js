import React, { useEffect, useState } from "react";
import "../App.css";
import Pay from "../page/Pay";
import { Link, useLocation } from "react-router-dom";
import { useSelector } from "react-redux";
const OrderTable = () => {
  const [data, setData] = useState([]);
  const location = useLocation();
  const token = useSelector((state) => state.athorize.id);
  const tableStyle = {
    textAlign: "center",
    borderRadius: 5,
  };

  useEffect(() => {
    const ac = new AbortController();
    fetch("http://localhost:8080/receive", {
      method: "GET",
      headers: new Headers({
        Authorization: token,
        "Content-Type": "application/json",
      }),
    })
      .then((res) => res.json())
      .then((json) => {
        setData(json);
      });
    return () => ac.abort();
  }, []);

  const renderData = (value) => {
    if (location.pathname === "/") {
      if (value.created_date === null) {
        return (
          <tr key={value.id}>
            <td>{value.id}</td>
            <td>{value.order_id}</td>
            <td>
              <Link
                to={{
                  pathname: `/Pay/${value.order_id}`,
                  state: {
                    taltalPrice: value.taltalPrice,
                    netPayment: value.netPayment,
                    discount: value.discount,
                    promotions: value.promotions,
                    date: value.created_date,
                  },
                }}
                className="nav-link"
              >
                ชำระเงิน
              </Link>
            </td>
          </tr>
        );
      }
    } else {
      if (value.created_date !== null) {
        return (
          <tr key={value.id}>
            <td>{value.id}</td>
            <td>{value.order_id}</td>
            <td>
              <Link
                to={{
                  pathname: `/Pay/${value.order_id}`,
                  state: {
                    taltalPrice: value.taltalPrice,
                    netPayment: value.netPayment,
                    discount: value.discount,
                    promotions: value.promotions,
                    date: value.created_date,
                  },
                }}
                className="nav-link"
              >
                ดู
              </Link>
            </td>
          </tr>
        );
      }
    }
  };
  return (
    <table style={tableStyle}>
      <thead>
        <tr>
          <th>#</th>
          <th>หมายเลขออเดอร์</th>
          <th></th>
        </tr>
      </thead>
      <tbody>{data.map((value) => renderData(value))}</tbody>
    </table>
  );
};

export default OrderTable;
