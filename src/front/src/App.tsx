import React, {FunctionComponent} from 'react';

import {Router} from "./Router";
import {AuthContextProvider} from "./contexts/AuthContext";
import './App.css';

export const App: FunctionComponent = () => (
    <AuthContextProvider>
        <Router />
    </AuthContextProvider>
);
