import './App.css';
import Home from "./page/Home"
import Pay from "./page/Pay"
import Record from "./page/Record"
import Unpaid from "./page/Unpaid"
import TopUp from "./page/TopUp"
import PaymentLog from "./page/PaymentLog"
import React from 'react';
import { BrowserRouter, Route } from "react-router-dom"
import { Provider } from "react-redux";
import { createStore, combineReducers } from "redux";
import balanceReducer from "./redux/reducer/balanceReducer";
import {Reducer} from './redux/reducer/athourize'

const rootReducer = combineReducers({
  balance: balanceReducer,
  athorize: Reducer
  });

  const store = createStore(rootReducer);
function App() {
  return (
    <Provider store={store}> 
    <BrowserRouter>
      <Route exact={true} path='/'>
        <Home/>
      </Route>
      <Route exact={true} path='/Pay/:orderId'>
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
      <Route exact={true} path='/PaymentLog/:userId'>
        <PaymentLog/>
      </Route>
    </BrowserRouter>
    </Provider>

  );
}

export default App;
