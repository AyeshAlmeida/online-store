import {get, NetworkState, networkStateReducer} from "../networkStateReducer";
import {useDispatch, useSelector} from "react-redux";
import {RootState} from "../reducers";

export interface ProductData {
    id: number,
    productName: string,
    packsPerCarton: number,
    cartonPrice: number
}

export interface ProductDataResponse {
    status: string,
    statusDescription: string,
    products: Array<ProductData>
}

const domain = "product-data";

export const productDataReducer = networkStateReducer(domain);

export function useProductData(): [NetworkState<ProductDataResponse>, () => void] {
    const dispatch = useDispatch();
    return [
        useSelector<RootState, NetworkState<ProductDataResponse>>(state => state.productData),
        () => {
            dispatch(get(domain, "/products"))
        }
    ]
}
