import moment, {Moment} from "moment";
import faker from "faker";

import {userFake, UserModel} from "./UserModel";

export type PostModel = {
    id: string,
    user: UserModel,
    text: string,
    likes: number,
    creationDate: Moment,
};

export const postFake = (user?: UserModel): PostModel => ({
    id: faker.random.uuid(),
    user: user ? user : userFake(),
    text: faker.lorem.paragraphs(faker.random.number(10) + 1, '\n'),
    likes: faker.random.number(512),
    creationDate: moment(faker.date.past())
});

export const postsFake = (user?: UserModel): PostModel[] => {
    let posts: PostModel[] = [];
    let n = faker.random.number(10);

    for (let i = 0; i < n; i++) {
        posts[i] = postFake(user);
    }

    return posts;
};
