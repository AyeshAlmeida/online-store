import {Action, combineReducers, Reducer} from 'redux'
import {connectRouter, RouterState} from "connected-react-router";
import {priceDataReducer, PriceDataResponse} from "./priceData/priceData";
import {NetworkState} from "./networkStateReducer";

export interface RootState {
    router: RouterState<unknown>,
    priceData: NetworkState<PriceDataResponse>
}

export function reducer(history: any): Reducer<RootState, Action> {
    return combineReducers<RootState>({
        router: connectRouter(history),
        priceData: priceDataReducer
    })
}