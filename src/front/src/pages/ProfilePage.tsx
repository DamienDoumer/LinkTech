import React, {FunctionComponent, useMemo} from 'react';
import {useParams} from 'react-router-dom';
import {Affix, Col, Row, Space} from "antd";

import {MainLayout} from "../layouts/MainLayout";
import {UserCardComponent} from "../components/User/UserCardComponent";
import {UserTimelineComponent} from "../components/User/UserTimelineComponent";
import {userFake} from "../models/UserModel";
import {PostFeedComponent} from "../components/Post/PostFeedComponent";
import {postsFake} from "../models/PostModel";
import {useWindowWidthMatch} from "../hooks/useWindowWidthMatch";

export const ProfilePage: FunctionComponent = () => {
    const {userId} = useParams();
    const user = useMemo(() => {
        let user = userFake();
        user.id = userId;
        return user;
    }, [userId]);
    const posts = useMemo(() => postsFake(user), [user]);
    const editable = useMemo<boolean>(() => userId === undefined, [userId]);
    const match = useWindowWidthMatch(["xs", "sm", "md", "lg"]);

    return (
        <MainLayout>
            <Row gutter={[24, 32]}>
                <Col xs={24} sm={24} md={24} lg={24} xl={8}>
                    {match
                        ? <UserCardComponent user={user} editable={editable}/>
                        : <Affix offsetTop={20}><UserCardComponent user={user} editable={editable}/></Affix>
                    }
                </Col>
                <Col xs={24} sm={24} md={24} lg={24} xl={16}>
                    <Space direction="vertical" size={50}>
                        <UserTimelineComponent/>
                        <PostFeedComponent posts={posts} userName={editable ? "You" : user.name}/>
                    </Space>
                </Col>
            </Row>
        </MainLayout>
    )
};
