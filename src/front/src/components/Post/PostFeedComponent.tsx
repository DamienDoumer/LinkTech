import React, {FunctionComponent} from "react";
import {Button, Empty, Space} from "antd";

import {PostModel} from "../../models/PostModel";
import {PostComponent} from "./PostComponent";

type PostFeedComponentProps = {
    posts: PostModel[],
    userName?: string,
}

export const PostFeedComponent: FunctionComponent<PostFeedComponentProps> = ({posts, userName}) => {
    if (posts.length === 0) return (
        <Empty
            image={Empty.PRESENTED_IMAGE_DEFAULT}
            description={
                userName ? (userName + " didn't post yet") : "Your relations didn't posts yet"
            }
        />
    );

    return (
        <Space direction="vertical" size={20}>
            {posts.map((p) => (<PostComponent key={p.id} post={p}/>))}
        </Space>
    );
}
