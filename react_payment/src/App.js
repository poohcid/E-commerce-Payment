import './App.css';
import Home from "./page/Home"
import Pay from "./page/Pay"
import Record from "./page/Record"
import Unpaid from "./page/Unpaid"
import TopUp from "./page/TopUp"
import React from 'react';
import { BrowserRouter, Route } from "react-router-dom"
function App() {
  return (
    <BrowserRouter>
      <Route exact={true} path='/'>
        <Home/>
      </Route>
      <Route exact={true} path='/Pay'>
        <Pay/>
      </Route>
      <Route exact={true} path='/Record'>
        <Record/>
      </Route>
      <Route exact={true} path='/Unpaid'>
        <Unpaid/>
      </Route>
      <Route exact={true} path='/TopUp'>
        <TopUp/>
      </Route>
    </BrowserRouter>
  );
}

export default App;
