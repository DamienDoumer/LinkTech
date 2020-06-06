import React, {FunctionComponent, useCallback, useContext, useEffect, useState} from "react";

import axios from "axios";
import {Link, useParams} from "react-router-dom";

import {config} from "../config";
import {AuthContext} from "../contexts/AuthContext";
import {InstitutionModel} from "../models/InstitutionModel";
import {MainLayout} from "../layouts/MainLayout";

export const InstitutionPage: FunctionComponent = () => {
    const context = useContext(AuthContext);

    const {institutionId} = useParams();

    const [institution, setInstitution] = useState<InstitutionModel | null>(null);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    const fetchData = useCallback(() => {
        if (context.state.token === undefined) return;

        setLoading(true);

        axios({
            url: `${config.institutionsServiceUrl}/${institutionId}`,
            method: 'GET',
            headers: {Authorization: `Bearer ${context.state.token}`}
        }).then((response) => {
            setInstitution({
                id: response.data.id,
                name: response.data.name,
                type: response.data.type,
                location: response.data.location,
                country: response.data.countryName,
                sector: response.data.sector
            });
            setLoading(false)
        }).catch((reason) => {
            setError("");
            setLoading(false);
        })
    }, [context.state.token, institutionId]);

    useEffect(fetchData, [fetchData, context.state.token, institutionId]);

    if (loading) return <MainLayout><p>Loading...</p></MainLayout>;
    if (error || institution === null) return <MainLayout><p>Error!</p></MainLayout>;

    return (
        <MainLayout>
            <h1>Institution</h1>
            <h2><Link to={`/institution/${institution.id}`}>{institution.name}</Link></h2>
            <p>{institution.type}</p>
            <p>{institution.country}, {institution.location}</p>
            <p>{institution.sector}</p>
        </MainLayout>
    );
}
