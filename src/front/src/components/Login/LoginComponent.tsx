import React, {FunctionComponent, useState} from "react";

import axios from "axios";
import {Button, Checkbox, Form, Input} from 'antd';

import {config} from "../../config";

export const LoginComponent: FunctionComponent = () => {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(undefined);

    const login = (username: string, password: string, remember: boolean) => {
        setLoading(true);
        axios({
            url: config.authServiceUrl,
            method: "POST",
            data: {username, password},
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
        login(values.username, values.password, values.remember);
    };

    const onFinishFailed = (errorInfo: any) => {
        setError(errorInfo);
    };

    if (loading) return <p>Loading...</p>
    if (error) return <p>Error!</p>

    return (
        <Form
            name="basic"
            initialValues={{remember: true}}
            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
        >
            <Form.Item
                label="Username"
                name="username"
                rules={[{required: true, message: 'Please input your username!'}]}
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
