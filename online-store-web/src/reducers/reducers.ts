import {Action, combineReducers, Reducer} from 'redux'
import {connectRouter, RouterState} from "connected-react-router";
import {priceDataReducer, PriceDataResponse} from "./priceData/priceData";
import {NetworkState} from "./networkStateReducer";
import {productDataReducer, ProductDataResponse} from "./products/productData";
import {CalculationDataResponse, calculationReducer} from "./calc/calculationData";

export interface RootState {
    router: RouterState<unknown>,
    priceData: NetworkState<PriceDataResponse>,
    productData: NetworkState<ProductDataResponse>,
    calculationData: NetworkState<CalculationDataResponse>
}

export function reducer(history: any): Reducer<RootState, Action> {
    return combineReducers<RootState>({
        router: connectRouter(history),
        priceData: priceDataReducer,
        productData: productDataReducer,
        calculationData: calculationReducer
    })
}