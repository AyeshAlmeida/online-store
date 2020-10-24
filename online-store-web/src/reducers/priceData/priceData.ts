import {get, NetworkState, networkStateReducer, SuccessState} from "../networkStateReducer";
import {useDispatch, useSelector} from "react-redux";
import {RootState} from "../reducers";

export interface PriceData {
    quantity: number,
    penguinEarPrice: number,
    horseShoePrice: number
}

export interface PriceDataResponse {
    status: string,
    statusDescription: string,
    totalPrices: number,
    prices: Array<PriceData>
}

const domain = "price-data";

export const priceDataReducer = networkStateReducer(domain);

export function usePriceData(): [NetworkState<PriceDataResponse>, (pageNumber: number) => void] {
    const dispatch = useDispatch();
    return [
        useSelector<RootState, NetworkState<PriceDataResponse>>(state => state.priceData),
        (pageNumber) => {
            dispatch(get(domain, "/prices/" + pageNumber))
        }
    ]
}