import {createContext} from "react";

export type ContextType = {
    token?: string
}

export const initialContext = {
    token: undefined,
}

export const AuthContext = createContext<ContextType>(initialContext)
