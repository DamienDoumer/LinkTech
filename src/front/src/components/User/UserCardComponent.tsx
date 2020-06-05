import React, {FunctionComponent} from "react";
import {Avatar, Card, Space, Typography} from "antd";
import {CommentOutlined, DeleteOutlined, EditOutlined, UserAddOutlined} from "@ant-design/icons/lib";

import './UserCardComponent.less';
import {UserModel} from "../../models/UserModel";

const {Text} = Typography;

type UserCardComponentProps = {
    user: UserModel,
    editable: boolean,
}

export const UserCardComponent: FunctionComponent<UserCardComponentProps> = ({editable, user: {name, title, age, country}}) => {
    const actions = editable ? [
        <Text><EditOutlined key="edit"/> Edit</Text>,
        <Text type="danger"><DeleteOutlined key="delete"/> Delete</Text>,
    ] : [
        <Text><UserAddOutlined key="add-relation"/> Add relation</Text>,
        <Text><CommentOutlined key="send-pm"/> Send message</Text>,
    ];

    return (
        <Card className="profile-card" actions={actions}>
            <Space direction="vertical">
                <Avatar
                    size={128}
                    src="https://via.placeholder.com/512"
                    alt={{name} + " Avatar"}
                />
                <div>
                    <div><Text className="profile-card-name">{name}</Text></div>
                    <div><Text className="profile-card-title">{title}</Text></div>
                </div>
                <hr/>
                <div>
                    <div><Text className="profile-card-meta">{country}</Text></div>
                    <div><Text className="profile-card-meta">{age} yo</Text></div>
                </div>
            </Space>
        </Card>
    );
};
