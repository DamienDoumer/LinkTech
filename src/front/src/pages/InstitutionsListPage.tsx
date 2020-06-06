import React, {FunctionComponent} from "react";
import {MainLayout} from "../layouts/MainLayout";
import {InstitutionListComponent} from "../components/Institution/InstitutionListComponent";

export const InstitutionsListPage: FunctionComponent = () => {
    return (
        <MainLayout>
            <InstitutionListComponent/>
        </MainLayout>
    )
}
