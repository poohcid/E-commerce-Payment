import React, {useState} from 'react';
import '../App.css';
import { Link } from "react-router-dom"
import styled from "styled-components"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faBars } from '@fortawesome/free-solid-svg-icons'

const Navbar = () =>{
    const [menuEnable, setMenuEnable] = useState(false)
    return(
        <React.Fragment>
        <nav>
            <div className="nav-flex">
                <Link to="/" className="nav-link" style={{fontSize: 24}}>Payment</Link>
                <ul className="navMenu-flex">
                    <li><Link to="/Unpaid" className="nav-link">ออเดอร์ที่ค้างชำระ</Link></li>
                    <li><Link to="/Record" className="nav-link">ประวัติการสั่งซื้อ</Link></li>
                    <li><Link to="/TopUp" className="nav-link">เติมเงิน</Link></li>
                    <li><H2>100 บาท</H2></li>
                 </ul>
                 <BarButton><FontAwesomeIcon icon={faBars} style={{fontSize: 25}} onClick={() => setMenuEnable(!menuEnable)}/></BarButton>
            </div>
        </nav>
        <MobileMenu className="navMobile" style={{ transform: menuEnable ? "translate(0%, 0%)" : "translate(-100%, 0%)"}}>
            <ul className="navMenu-flex-mobile">
                <LI><Link to="/Unpaid" className="nav-link">ออเดอร์ที่ค้างชำระ</Link></LI>
                <LI><Link to="/Record" className="nav-link">ประวัติการสั่งซื้อ</Link></LI>
                <LI><Link to="/TopUp" className="nav-link">เติมเงิน</Link></LI>
                <LI><H2>100 บาท</H2></LI>
            </ul>
        </MobileMenu>
        </React.Fragment>
    );
}

const H2 = styled.h3`
    margin: 0px;
    color: #282c34;
`


const BarButton = styled.button`
    border: none;
    outline: none;
    cursor: pointer;
    display: none;
    @media (max-width: 768px) {
        display: initial;
    }
`

const LI = styled.li`
    list-style: none;
    padding: 15px 0px;
    fontSize: 18px;
`

const MobileMenu = styled.div`
    background: #f0f0f0;
    box-shadow: 0 3px 6px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.23);
    margin: 0;
    position: absolute;
    width: 100%;
    display: none;
    @media (max-width: 768px) {
        display: initial;
    }
    transform: translate(-100%, 0%);
`

export default Navbar;
