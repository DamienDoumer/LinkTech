import React, {FunctionComponent, useContext, useEffect, useState} from "react";

import axios from "axios";
import {config} from "../../config";
import {AuthContext} from "../../contexts/AuthContext";

export const InstitutionListComponent: FunctionComponent = () => {
    const context = useContext(AuthContext);

    const [data, setData] = useState<any>(null);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        if (context.state.token === undefined) return;

        axios({
            url: config.institutionsServiceUrl,
            method: 'GET',
            headers: {Authorization: `Bearer ${context.state.token}`}
        }).then((response) => {
            setData(response.data);
            setLoading(false)
        }).catch((reason) => {
            setError("");
            setLoading(false);
        })
    }, [context.state.token]);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error!</p>;

    return (
        <>
            <h1>Institutions List</h1>
            <pre>{JSON.stringify(data, null, 2)}</pre>
        </>
    );
}
