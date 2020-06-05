import React, {createElement, Fragment, FunctionComponent, useState} from 'react';
import {Avatar, Card, Comment, Tooltip, Typography} from 'antd';
import {Link} from "react-router-dom";
import {CommentOutlined, LikeFilled, LikeOutlined} from '@ant-design/icons/lib';

import {PostModel} from "../../models/PostModel";

const {Paragraph} = Typography;

type PostComponentProps = {
    post: PostModel
}

export const PostComponent: FunctionComponent<PostComponentProps> = ({post: {user, likes, text, creationDate}}) => {
    const [userLikes, setUserLikes] = useState<boolean>(false);

    const like = () => {
        setUserLikes(!userLikes);
    };

    const actions = [
        <span key="comment-basic-like">
            <Tooltip title="Like" arrowPointAtCenter>
                <span>
                    {createElement(userLikes ? LikeFilled : LikeOutlined, {
                        onClick: like,
                    })}
                    {' '}
                    {likes}
                </span>
            </Tooltip>
        </span>,
        <span key="comment-basic-reply-to">
            <Tooltip title="Comments" arrowPointAtCenter>
                <span>
                    <CommentOutlined />
                    {' '}
                    {0}
                </span>
            </Tooltip>
        </span>,
    ];

    return (
        <Card>
            <Comment
                actions={actions}
                author={<Link to={"/profile/" + user.id}>{user.name}</Link>}
                avatar={
                    <Avatar
                        size="large"
                        src="https://via.placeholder.com/50"
                        alt="Lorem Ipsum"
                    />
                }
                content={<Paragraph ellipsis={{rows: 4, expandable: true}}>
                    {text.split('\n').map((t, i) => <Fragment key={i}>{t}<br/></Fragment>)}
                </Paragraph>}
                datetime={
                    <Tooltip title={creationDate.format('YYYY-MM-DD HH:mm:ss')}>
                        <span>{creationDate.fromNow()}</span>
                    </Tooltip>
                }
            />
        </Card>
    );
};
