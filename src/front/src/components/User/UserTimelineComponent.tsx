import React, {FunctionComponent} from "react";
import {Card, Timeline} from "antd";

import "./UserTimelineComponent.less";

export const UserTimelineComponent: FunctionComponent = () => (
    <Card className="profile-timeline">
        <Timeline>
            <Timeline.Item color="blue">Started to work at <a>Adfinitas</a> since September 2020</Timeline.Item>
            <Timeline.Item color="green">Obtained high school graduation at <a>Epitech</a> on 2020</Timeline.Item>
            <Timeline.Item color="blue">Started a work-study at <a>Adfinitas</a> from September 2018 to September 2020 (2 years)</Timeline.Item>
            <Timeline.Item color="blue">Started an internship at <a>Adfinitas</a> from April 2018 to September 2018 (6 months)</Timeline.Item>
            <Timeline.Item color="blue">Started an internship at <a>La Redoute</a> from November 2017 to April 2018 (6 months)</Timeline.Item>
            <Timeline.Item color="blue">Started an internship at <a>Nuukik</a> from July 2016 to December 2016 (6 months)</Timeline.Item>
            <Timeline.Item color="green">Started high school formation at <a>Epitech</a> on 2015</Timeline.Item>
            <Timeline.Item color="green">Obtained Baccalaureate on 2015</Timeline.Item>
        </Timeline>
    </Card>
);
