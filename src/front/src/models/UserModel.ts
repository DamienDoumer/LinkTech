import moment, {Moment} from "moment";
import faker from "faker";

export type UserModel = {
    id: string,
    name: string,
    title: string,
    age: number,
    country: string,
    creationDate: Moment,
}

export const userFake = (): UserModel => ({
    id: faker.random.uuid(),
    name: faker.name.findName(),
    title: faker.name.jobTitle(),
    age: faker.random.number(54),
    country: faker.address.country(),
    creationDate: moment(faker.date.past())
});
