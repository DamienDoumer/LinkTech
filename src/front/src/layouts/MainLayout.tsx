import React, {FunctionComponent, ReactElement} from "react";
import {BackTop, Layout} from 'antd';

import {FooterComponent} from "../components/FooterComponent";
import {SiderComponent} from "../components/SiderComponent";
import {useWindowWidthMatch} from "../hooks/useWindowWidthMatch";
import {useScrollRestoration} from "../hooks/useScrollRestoration";

import './MainLayout.less';

const {Header, Content} = Layout;

type MainLayoutType = {
    children: ReactElement | ReactElement[]
}

export const MainLayout: FunctionComponent<MainLayoutType> = ({children}) => {
    const match = useWindowWidthMatch(["xs", "sm", "md"]);
    useScrollRestoration();

    return (
        <Layout className="main-layout">
            <BackTop/>
            <SiderComponent collapsed={match}/>
            <Layout className={match ? "no-margin" : ""}>
                <Header hidden={!match}/>
                <Content>
                    <div>
                        {children}
                    </div>
                </Content>
                <FooterComponent/>
            </Layout>
        </Layout>
    );
}
