import React, {FunctionComponent, useContext, useMemo} from 'react';
import {Layout, Menu} from "antd";
import {Link, matchPath, useLocation} from "react-router-dom";
import {routes} from "../Router";
import {AuthContext} from "../contexts/AuthContext";

const {Sider} = Layout;

type SiderComponentType = {
    collapsed: boolean
}

export const SiderComponent: FunctionComponent<SiderComponentType> = ({collapsed}) => {
    const authContext = useContext(AuthContext);
    const authenticated = useMemo(() => authContext.state.token !== undefined, [authContext.state.token]);
    const {pathname} = useLocation();
    const routeMatched = useMemo(() => {
        let routeMatched = 0;
        routes
            .filter((r) => authenticated ? (r.auth === "authenticated") : (r.auth === "not-authenticated"))
            .forEach((r, i) => {
                if (matchPath(pathname, {path: r.path}) != null) routeMatched = i;
            });
        return routeMatched.toString();
    }, [pathname]);

    return (
        <Sider className={collapsed ? "collapsed" : ""} collapsed={collapsed} collapsedWidth={0}>
            <div className="logo"/>
            <Menu theme="dark" mode="inline" selectedKeys={[routeMatched]}>
                {routes
                    .filter((r) => r.onSidebar)
                    .filter((r) => authenticated ? (r.auth === "authenticated") : (r.auth === "not-authenticated"))
                    .map((r, i) => (
                        <Menu.Item key={i}>
                            <Link to={r.path}>
                                {r.icon()}
                                <span>{r.name}</span>
                            </Link>
                        </Menu.Item>
                    ))}
            </Menu>
        </Sider>
    );
}
