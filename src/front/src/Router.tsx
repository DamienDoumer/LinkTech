import React, {FunctionComponent, ReactElement, useContext, useMemo} from "react"

import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import {HomeOutlined, UserOutlined} from "@ant-design/icons/lib";

import {LoginPage} from "./pages/LoginPage";
import {FeedPage} from "./pages/FeedPage";
import {ProfilePage} from "./pages/ProfilePage";
import {PostPage} from "./pages/PostPage";
import {AuthContext} from "./contexts/AuthContext";
import {RegisterPage} from "./pages/RegisterPage";

export type RouteType = {
    icon: () => ReactElement,
    component: () => ReactElement,
    name: string,
    path: string[],
    onSidebar: boolean,
    auth: "authenticated" | "not-authenticated",
}

export const routes: RouteType[] = [
    {
        icon: () => <HomeOutlined/>,
        component: () => <LoginPage/>,
        name: 'Login',
        path: ['/login'],
        onSidebar: true,
        auth: "not-authenticated",
    },
    {
        icon: () => <HomeOutlined/>,
        component: () => <RegisterPage/>,
        name: 'Register',
        path: ['/register'],
        onSidebar: true,
        auth: "not-authenticated",
    },
    {
        icon: () => <HomeOutlined/>,
        component: () => <FeedPage/>,
        name: 'Home',
        path: ['/feed', '/'],
        onSidebar: true,
        auth: "authenticated",
    },
    {
        icon: () => <UserOutlined/>,
        component: () => <ProfilePage/>,
        name: 'Profile',
        path: ['/profile', '/profile/:userId'],
        onSidebar: true,
        auth: "authenticated",
    },
    {
        icon: () => <span/>,
        component: () => <PostPage/>,
        name: 'Post',
        path: ['/post/:postId'],
        onSidebar: false,
        auth: "authenticated",
    }
];

const NotAuthenticatedRoute: FunctionComponent<{ route: RouteType, path: string, authenticated: boolean }> = ({route, path, authenticated}) => {
    if (authenticated) {
        return (<Route exact path={path}><Redirect to="/"/></Route>);
    }
    return (<Route exact path={path} component={route.component}/>);
}

const AuthenticatedRoute: FunctionComponent<{ route: RouteType, path: string, authenticated: boolean }> = ({route, path, authenticated}) => {
    if (!authenticated) {
        return (<Route exact path={path}><Redirect to="/login"/></Route>);
    }
    return (<Route exact path={path} component={route.component}/>);
}

export const Router = () => {
    const authContext = useContext(AuthContext);

    const authenticated = useMemo(() => authContext.token !== undefined, [authContext]);

    return (
        <BrowserRouter>
            <Switch>
                {routes.map((r) => (
                        r.path.map((p) => (r.auth === "not-authenticated")
                            ? (<NotAuthenticatedRoute key={r.name + "::" + p} path={p} route={r}
                                                      authenticated={authenticated}/>)
                            : (<AuthenticatedRoute key={r.name + "::" + p} path={p} route={r}
                                                   authenticated={authenticated}/>))
                    )
                )}
                <Route path="*">
                    <Redirect to="/"/>
                </Route>
            </Switch>
        </BrowserRouter>
    );
};
