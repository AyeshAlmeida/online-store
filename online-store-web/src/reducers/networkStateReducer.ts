import {Action, Reducer} from 'redux'

export interface NetworkAction<T> extends Action {

}

export interface NetworkPendingAction<T> extends NetworkAction<T> {
    readonly type: string,
    readonly payload: {
        request: {
            method: 'GET' | 'POST'| 'DELETE'| 'PUT',
            url: string,
            data?: any
        }
    }
}

export function get<Req,Resp>(domain: string, url: string): NetworkPendingAction<Resp> {
    return {
        type: domain,
        payload: {
            request: {
                method: "GET",
                url: url
            }
        }
    }
}

export function post<Req, Resp>(domain: string, url: string, body?: Req): NetworkPendingAction<Resp> {
    return {
        type: domain,
        payload: {
            request: {
                method: "POST",
                url: url,
                data: body
            }
        }
    }
}
export function del<Req, Resp>(domain: string, url: string): NetworkPendingAction<Resp> {
    return {
        type: domain,
        payload: {
            request: {
                method: "DELETE",
                url: url
            }
        }
    }
}
export function update<Req, Resp>(domain: string, url: string, body: Req): NetworkPendingAction<Resp> {
    return {
        type: domain,
        payload: {
            request: {
                method: "PUT",
                url: url,
                data: body
            }
        }
    }
}
function isPendingAction<T>(action: NetworkAction<T>, domain: string): action is NetworkPendingAction<T> {
    return action.type === domain
}

export interface NetworkSuccessAction<T> extends NetworkAction<T> {
    readonly type: string
    readonly payload: {
        data: T
    }
}

function isSuccessAction<T>(action: NetworkAction<T>, domain: string): action is NetworkSuccessAction<T> {
    return action.type === `${domain}_SUCCESS`
}

export interface CurrentStateFailAction<T> extends NetworkAction<T> {
    readonly type: string,
    readonly error: {
        data: string
    }
}

function isFailAction<T>(action: NetworkAction<T>, domain: string): action is CurrentStateFailAction<T> {
    return action.type === `${domain}_FAIL`
}

export interface NetworkState<T> {
    state: "init"|"pending"|"success"|"error"
}

export interface InitState<T> extends NetworkState<T>{
    state: 'init'
}

export function isInit<T>(state: NetworkState<T>): state is InitState<T> {
    return state.state === "init"
}

export interface PendingState<T> extends NetworkState<T>{
    state: "pending"
}

export function isPending<T>(state: NetworkState<T>): state is PendingState<T> {
    return state.state === "pending"
}

export interface SuccessState<T> extends NetworkState<T>{
    state: "success"
    data: T
}

export function isSuccess<T>(state: NetworkState<T>): state is SuccessState<T> {
    return state.state === "success"
}

export interface ErrorState<T> extends NetworkState<T>{
    state: "error"
    error: string
}

export function isError<T>(state: NetworkState<T>): state is ErrorState<T> {
    return state.state === "error"
}

function initState<T>(): NetworkState<T> {
    return {
        state: "init"
    }
}

export function networkStateReducer<T>(domain: string): Reducer<NetworkState<T>, NetworkAction<T>> {
    return (state = initState(), action) => {

        if (isPendingAction(action, domain)) {
            let newState: PendingState<T> = {
                state: "pending"
            };
            return newState
        } else if (isSuccessAction(action, domain)) {
            let newState: SuccessState<T> = {
                state: "success",
                data: (action.payload.data as T)
            };
            return newState
        } else if (isFailAction(action, domain)) {
            let newState: ErrorState<T> = {
                state: "error",
                error: action.error.data
            };
            return newState
        }

        return state
    }
}