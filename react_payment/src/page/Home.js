import React from 'react';
import '../App.css';
import Navbar from "../component/navbar"
import OrderTable from "../component/orderTable"

const Home = () =>{
    return(
        <React.Fragment>
            <Navbar/>
            <div className="container">
                <div style={{marginTop: 70}}>
                    <OrderTable/>
                </div>               
            </div>
        </React.Fragment>

    );
}

export default Home;