import React, {FunctionComponent, useState} from "react";
import {Button, Checkbox, Form, Input} from 'antd';

export const RegisterComponent: FunctionComponent = () => {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(undefined);

    const onFinish = (values: any) => {
        console.log(values);
    };

    const onFinishFailed = (errorInfo: any) => {
        setError(errorInfo);
    };

    if (loading) return <p>Loading...</p>
    if (error) return <p>Error! <code>{error}</code></p>

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
