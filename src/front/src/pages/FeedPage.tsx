import React, {FunctionComponent, useMemo} from 'react';

import {MainLayout} from "../layouts/MainLayout";
import {PostModel, postsFake} from "../models/PostModel";
import {PostFeedComponent} from "../components/Post/PostFeedComponent";

export const FeedPage: FunctionComponent = () => {
    const posts = useMemo<PostModel[]>(() => postsFake(), []);

    return (
        <MainLayout>
            <PostFeedComponent posts={posts}/>
        </MainLayout>
    );
};
