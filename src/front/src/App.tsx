import React, {FunctionComponent} from 'react';

import {Router} from "./Router";
import {AuthContext, initialContext} from "./contexts/AuthContext";
import './App.css';

export const App: FunctionComponent = () => (
    <AuthContext.Provider value={initialContext}>
        <Router />
    </AuthContext.Provider>
);
