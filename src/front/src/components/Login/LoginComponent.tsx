import React, {FunctionComponent, useContext, useState} from "react";

import axios from "axios";
import {Button, Checkbox, Form, Input} from 'antd';

import {config} from "../../config";
import {AuthContext} from "../../contexts/AuthContext";

export const LoginComponent: FunctionComponent = () => {
    const authContext = useContext(AuthContext);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(undefined);

    const login = (email: string, password: string, remember: boolean) => {
        setLoading(true);
        axios({
            url: config.authServiceUrl + '/authenticate',
            method: "POST",
            data: {username: email, password},
            withCredentials: true,
        })
            .then((response) => {
                authContext.login(email, response.data.jwt);
                // setLoading(false);
            })
            .catch((error) => {
                setLoading(false);
                setError(error);
            })
    }

    const onFinish = (values: any) => {
        login(values.email, values.password, values.remember);
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
