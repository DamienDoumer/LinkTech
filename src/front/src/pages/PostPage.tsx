import React, {FunctionComponent, useMemo} from "react";
import {useParams} from "react-router-dom"

import {MainLayout} from "../layouts/MainLayout";
import {postFake} from "../models/PostModel";
import {PostComponent} from "../components/Post/PostComponent";

export const PostPage: FunctionComponent = () => {
    const {postId} = useParams();
    const post = useMemo(() => {
        let post = postFake();
        post.id = postId;
        return post;
    }, [postId]);

    return (
        <MainLayout>
            <PostComponent post={post}/>
        </MainLayout>
    );
};
