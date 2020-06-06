import React, {FunctionComponent, useMemo} from 'react';

import {MainLayout} from "../layouts/MainLayout";
import {PostModel, postsFake} from "../models/PostModel";
import {PostFeedComponent} from "../components/Post/PostFeedComponent";

export const FeedPage: FunctionComponent = () => {
    const posts = useMemo<PostModel[]>(() => postsFake(), []);

    return (
        <MainLayout>
            <h1>Fake Data...</h1>
            <PostFeedComponent posts={posts}/>
        </MainLayout>
    );
};
