import React, {FunctionComponent, useCallback, useContext, useEffect, useState} from "react";

import axios from "axios";
import {Button, List} from 'antd';
import {Link} from "react-router-dom";

import {config} from "../config";
import {AuthContext} from "../contexts/AuthContext";
import {ProfileModel} from "../models/ProfileModel";
import {MainLayout} from "../layouts/MainLayout";

export const ProfilesListPage: FunctionComponent = () => {
    const context = useContext(AuthContext);

    const [data, setData] = useState<ProfileModel[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    const fetchData = useCallback(() => {
        if (context.state.token === undefined) return;

        setLoading(true);

        axios({
            url: config.usersServiceUrl,
            method: 'GET',
            headers: {Authorization: `Bearer ${context.state.token}`}
        }).then((response) => {
            setData(response.data.map((e: any): ProfileModel => ({
                id: e.id,
                firstname: e.firstName,
                lastname: e.secondName,
                email: e.email,
                following: e.following,
                followers: e.followers,
                institutionsFollowing: e.institutionsFollowing,
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
            <h1>Profiles List</h1>
            <Button onClick={() => {
                fetchData()
            }}>Refetch</Button>
            <List
                dataSource={data}
                renderItem={profile => (
                    <List.Item>
                        <h2><Link to={`/profile/${profile.id}`}>{profile.firstname} {profile.lastname}</Link></h2>
                    </List.Item>
                )}
            />
        </MainLayout>
    );
}
