import React, {createContext, useReducer} from "react";

export type ContextType = {
    state: {
        token?: string,
        email?: string,
    },
    login: (email: string, token: string) => void
};

export interface Action {
    type: string
}

export interface LoginAction extends Action {
    payload: { email: string, token: string }
}

function isLoginAction(action: Action): action is LoginAction {
    return action.type === "LOGIN";
}

export const initialContext = {
    state: {
        token: undefined,
        email: undefined,
    },
    login: (email: string, token: string): void => {
    }
};

function reducer(context: ContextType, action: Action & LoginAction): ContextType {
    if (isLoginAction(action)) {
        return {...context, state: {...context.state, email: action.payload.email, token: action.payload.token}};
    }

    return context;
}

export const AuthContext = createContext<ContextType>(initialContext)

// @ts-ignore
export const AuthContextProvider = ({children}) => {
    const [context, dispatch] = useReducer(reducer, initialContext);

    const contextProviderValue = {
        ...context,
        login: (email: string, token: string): void => {
            dispatch({
                type: 'LOGIN',
                payload: {email, token}
            });
        }
    };

    return (
        <AuthContext.Provider value={contextProviderValue}>
            {children}
        </AuthContext.Provider>
    )
}
