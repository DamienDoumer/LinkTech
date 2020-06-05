import React, {FunctionComponent} from "react";

import {MainLayout} from "../layouts/MainLayout";
import {RegisterComponent} from "../components/Register/RegisterComponent";

export const RegisterPage: FunctionComponent = () => {
    return (
        <MainLayout>
            <RegisterComponent/>
        </MainLayout>
    );
};
