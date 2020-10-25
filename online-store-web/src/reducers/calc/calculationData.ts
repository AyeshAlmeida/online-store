import {post, NetworkState, networkStateReducer} from "../networkStateReducer";
import {useDispatch, useSelector} from "react-redux";
import {RootState} from "../reducers";

export interface CalculationData {
    id: number,
    product: string,
    type: string,
    quantity: number,
    amount: number
}

export interface CalculationDataResponse {
    status: string,
    statusDescription: string,
    productDetails: CalculationData,
    total: number
}

const domain = "product-calculation";

export const calculationReducer = networkStateReducer(domain);

export function useCalculation(): [NetworkState<CalculationDataResponse>, (productId: number, type: string, quantity: number, currentTotal: number) => void] {
    const dispatch = useDispatch();
    return [
        useSelector<RootState, NetworkState<CalculationDataResponse>>(state => state.calculationData),
        (productId, type, quantity, currentTotal) => {
            dispatch(post(domain, "/prices/calculate", {productId: productId, type: type, quantity: quantity, currentTotal: currentTotal}))
        }
    ]
}