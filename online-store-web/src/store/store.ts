import {createStore, Reducer, combineReducers, Action, applyMiddleware} from 'redux'
import axiosMiddleware from 'redux-axios-middleware'
import axios from 'axios'
import {reducer} from "../reducers/reducers";

let client = axios.create({
    baseURL: "http://127.0.0.1:10030/api-server/v1",
    responseType: "json"
});

export const store = (history: any) => createStore(reducer(history), applyMiddleware(axiosMiddleware(client)));