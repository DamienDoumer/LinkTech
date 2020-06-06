import React, {FunctionComponent, ReactElement, useContext, useMemo} from "react"

import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import {HomeOutlined, UserOutlined} from "@ant-design/icons/lib";

import {LoginPage} from "./pages/LoginPage";
import {FeedPage} from "./pages/FeedPage";
import {AuthContext} from "./contexts/AuthContext";
import {RegisterPage} from "./pages/RegisterPage";
import {InstitutionsListPage} from "./pages/InstitutionsListPage";
import {InstitutionPage} from "./pages/InstitutionPage";
import {ProfilesListPage} from "./pages/ProfilesListPage";
import {ProfilePage} from "./pages/ProfilePage";

export type RouteType = {
    icon: () => ReactElement,
    component: () => ReactElement,
    name: string,
    path: string,
    onSidebar: boolean,
    auth: "authenticated" | "not-authenticated",
}

export const routes: RouteType[] = [
    {
        icon: () => <HomeOutlined/>,
        component: () => <LoginPage/>,
        name: 'Login',
        path: '/login',
        onSidebar: true,
        auth: "not-authenticated",
    },
    {
        icon: () => <HomeOutlined/>,
        component: () => <RegisterPage/>,
        name: 'Register',
        path: '/register',
        onSidebar: true,
        auth: "not-authenticated",
    },
    {
        icon: () => <HomeOutlined/>,
        component: () => <FeedPage/>,
        name: 'Home',
        path: '/',
        onSidebar: true,
        auth: "authenticated",
    },
    {
        icon: () => <UserOutlined/>,
        component: () => <InstitutionsListPage/>,
        name: 'Institutions',
        path: '/institutions',
        onSidebar: true,
        auth: "authenticated",
    },
    {
        icon: () => <UserOutlined/>,
        component: () => <InstitutionPage/>,
        name: 'Institution',
        path: '/institution/:institutionId',
        onSidebar: false,
        auth: "authenticated",
    },
    {
        icon: () => <UserOutlined/>,
        component: () => <ProfilesListPage/>,
        name: 'Profiles',
        path: '/profiles',
        onSidebar: true,
        auth: "authenticated",
    },
    {
        icon: () => <UserOutlined/>,
        component: () => <ProfilePage/>,
        name: 'Profile',
        path: '/profile/:profileId',
        onSidebar: false,
        auth: "authenticated",
    },
];

const NotAuthenticatedRoute: FunctionComponent<{ route: RouteType, authenticated: boolean }> = ({route, authenticated}) => {
    if (authenticated) {
        return (<Route exact path={route.path}><Redirect to="/"/></Route>);
    }
    return (<Route exact path={route.path} component={route.component}/>);
}

const AuthenticatedRoute: FunctionComponent<{ route: RouteType, authenticated: boolean }> = ({route, authenticated}) => {
    if (!authenticated) {
        return (<Route exact path={route.path}><Redirect to="/login"/></Route>);
    }
    return (<Route exact path={route.path} component={route.component}/>);
}

export const Router = () => {
    const authContext = useContext(AuthContext);

    const authenticated = useMemo(() => authContext.state.token !== undefined, [authContext.state.token]);

    return (
        <BrowserRouter>
            <Switch>
                {routes.map((r) => (
                        <Route exact path={r.path} component={r.component}/>
                    )
                )}
                <Route path="*">
                    <Redirect to="/"/>
                </Route>
            </Switch>
        </BrowserRouter>
    );
};
