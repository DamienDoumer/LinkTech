import React, {FunctionComponent} from "react";

import {MainLayout} from "../layouts/MainLayout";
import {LoginComponent} from "../components/Login/LoginComponent";

export const LoginPage: FunctionComponent = () => {
    return (
        <MainLayout>
            <LoginComponent/>
        </MainLayout>
    );
};
