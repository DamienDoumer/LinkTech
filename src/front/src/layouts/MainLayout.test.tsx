import React from 'react';
import {render} from '@testing-library/react';
import {MainLayout} from './MainLayout';

test('renders 1', () => {
    const {getByText} = render(
        <MainLayout>
            <div>Hello world</div>
        </MainLayout>
    );
    const helloWorldText = getByText(/Hello world/i);
    expect(helloWorldText).toBeInTheDocument();
});

test('renders 2', () => {
    const {getByText} = render(
        <MainLayout>
            <div>Hello Fred</div>
        </MainLayout>
    );
    const helloFredText = getByText(/Hello Fred/i);
    expect(helloFredText).toBeInTheDocument();
});
