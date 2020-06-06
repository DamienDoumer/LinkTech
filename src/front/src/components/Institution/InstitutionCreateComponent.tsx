import React, {FunctionComponent, useContext, useState} from "react";

import {Button, Form, Input, Select} from 'antd';

import {AuthContext} from "../../contexts/AuthContext";
import {config} from "../../config";
import axios from "axios";
import {InstitutionModel} from "../../models/InstitutionModel";

const {Option} = Select;

export const InstitutionCreateComponent: FunctionComponent = () => {
    const context = useContext(AuthContext);

    const [form] = Form.useForm();
    const [loading, setLoading] = useState<boolean>(false);
    const [error, setError] = useState<string | null>(null);

    const createInstitution = (name: string, location: string, type: string, country: string, sector: string) => {
        axios({
            url: config.institutionsServiceUrl,
            method: 'POST',
            data: {name, location, type, countryName: country, sector},
            headers: {Authorization: `Bearer ${context.state.token}`}
        }).then((response) => {
            form.resetFields();
            setLoading(false)
        }).catch((reason) => {
            setError("");
            setLoading(false);
        });
    }

    const onFinish = (values: any) => {
        createInstitution(values.name, values.location, values.type, values.country, values.sector);
    };

    const onFinishFailed = (errorInfo: any) => {
        setError(errorInfo);
    };

    if (loading) return <p>Loading...</p>
    if (error) return <p>Error! <code>{JSON.stringify(error)}</code></p>

    return (
        <Form
            name="basic"
            form={form}
            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
        >
            <Form.Item
                label="Name"
                name="name"
                rules={[{required: true, message: 'Please input the institution name!'}]}
            >
                <Input/>
            </Form.Item>

            <Form.Item
                label="Location"
                name="location"
                rules={[{required: true, message: 'Please input the institution location!'}]}
            >
                <Input/>
            </Form.Item>

            <Form.Item
                label="Type"
                name="type"
                rules={[{required: true, message: 'Please input the institution type!'}]}
            >
                <Select>
                    <Option value="School">School</Option>
                    <Option value="Company">Company</Option>
                </Select>
            </Form.Item>

            <Form.Item
                label="Country"
                name="country"
                rules={[{required: true, message: 'Please input the institution country!'}]}
            >
                <Input/>
            </Form.Item>

            <Form.Item
                label="Sector"
                name="sector"
                rules={[{required: true, message: 'Please input the institution sector!'}]}
            >
                <Input/>
            </Form.Item>

            <Form.Item>
                <Button type="primary" htmlType="submit">
                    Submit
                </Button>
            </Form.Item>
        </Form>
    );
}
