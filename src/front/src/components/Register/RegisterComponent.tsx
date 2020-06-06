import React, {FunctionComponent, useState} from "react";
import {Button, Checkbox, Form, Input} from 'antd';
import axios from "axios";
import {config} from "../../config";

export const RegisterComponent: FunctionComponent = () => {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(undefined);

    const register = (email: string, firstname: string, lastname: string, password: string, remember: boolean) => {
        setLoading(true);
        axios({
            url: config.authServiceUrl + '/signup',
            method: "POST",
            data: {email, firstname, lastname, password},
            withCredentials: true,
        })
            .then((response) => {
                console.log(response);
                setLoading(false);
            })
            .catch((error) => {
                setLoading(false);
                setError(error);
            })
    }

    const onFinish = (values: any) => {
        register(values.email, values.firstname, values.lastname, values.password, values.remember);
    };

    const onFinishFailed = (errorInfo: any) => {
        setError(errorInfo);
    };

    if (loading) return <p>Loading...</p>
    if (error) return <p>Error! <code>{JSON.stringify(error)}</code></p>

    return (
        <Form
            name="basic"
            initialValues={{remember: true}}
            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
        >
            <Form.Item
                label="Email"
                name="email"
                rules={[{required: true, message: 'Please input your email!'}]}
            >
                <Input/>
            </Form.Item>

            <Form.Item
                label="Firsname"
                name="firstname"
                rules={[{required: true, message: 'Please input your firstname!'}]}
            >
                <Input/>
            </Form.Item>

            <Form.Item
                label="Lastname"
                name="lastname"
                rules={[{required: true, message: 'Please input your lastname!'}]}
            >
                <Input/>
            </Form.Item>

            <Form.Item
                label="Password"
                name="password"
                rules={[{required: true, message: 'Please input your password!'}]}
            >
                <Input.Password/>
            </Form.Item>

            <Form.Item name="remember" valuePropName="checked">
                <Checkbox>Remember me</Checkbox>
            </Form.Item>

            <Form.Item>
                <Button type="primary" htmlType="submit">
                    Submit
                </Button>
            </Form.Item>
        </Form>
    );
};
