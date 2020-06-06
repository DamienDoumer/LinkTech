import React, {FunctionComponent} from "react";

import {useParams} from "react-router-dom";

import {MainLayout} from "../layouts/MainLayout";
import {InstitutionComponent} from "../components/Institution/InstitutionComponent";

export const InstitutionPage: FunctionComponent = () => {
    const {institutionId} = useParams();

    return (
        <MainLayout>
            <InstitutionComponent institutionId={institutionId} />
        </MainLayout>
    )
}
