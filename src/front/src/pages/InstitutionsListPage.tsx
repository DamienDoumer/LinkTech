import React, {FunctionComponent, useCallback, useContext, useEffect, useState} from "react";

import axios from "axios";
import {Button, List} from 'antd';
import {Link} from "react-router-dom";

import {config} from "../config";
import {AuthContext} from "../contexts/AuthContext";
import {InstitutionCreateComponent} from "../components/Institution/InstitutionCreateComponent";
import {InstitutionModel} from "../models/InstitutionModel";
import {MainLayout} from "../layouts/MainLayout";

export const InstitutionsListPage: FunctionComponent = () => {
    const context = useContext(AuthContext);

    const [data, setData] = useState<InstitutionModel[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    const fetchData = useCallback(() => {
        if (context.state.token === undefined) return;

        setLoading(true);

        axios({
            url: config.institutionsServiceUrl,
            method: 'GET',
            headers: {Authorization: `Bearer ${context.state.token}`}
        }).then((response) => {
            setData(response.data.map((e: any): InstitutionModel => ({
                id: e.id,
                name: e.name,
                type: e.type,
                location: e.location,
                country: e.countryName,
                sector: e.sector
            })));
            setLoading(false)
        }).catch((reason) => {
            setError("");
            setLoading(false);
        })
    }, [context.state.token]);

    useEffect(fetchData, [fetchData, context.state.token]);

    if (loading) return <MainLayout><p>Loading...</p></MainLayout>;
    if (error) return <MainLayout><p>Error!</p></MainLayout>;

    return (
        <MainLayout>
            <h1>Institutions List</h1>
            <InstitutionCreateComponent/>
            <Button onClick={() => {
                fetchData()
            }}>Refetch</Button>
            <List
                dataSource={data}
                renderItem={institution => (
                    <List.Item>
                        <h2><Link to={`/institution/${institution.id}`}>{institution.name}</Link></h2>
                        <p>{institution.type}</p>
                        <p>{institution.country}, {institution.location}</p>
                        <p>{institution.sector}</p>
                    </List.Item>
                )}
            />
        </MainLayout>
    );
}
