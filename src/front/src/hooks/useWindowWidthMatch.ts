import {useEffect, useState} from 'react';
import {useWindowDimensions} from "./useWindowDimensions";

const _sizes = {
    xs: (w: number) => w < 576,
    sm: (w: number) => w >= 576 && w < 768,
    md: (w: number) => w >= 768 && w < 992,
    lg: (w: number) => w >= 992 && w < 1200,
    xl: (w: number) => w >= 1200 && w < 1600,
    xxl: (w: number) => w >= 1600,
};

export const useWindowWidthMatch = (sizes: ("xs" | "sm" | "md" | "lg" | "xl" | "xxl")[]) => {
    const {width} = useWindowDimensions();
    const [match, setMatch] = useState(false);

    useEffect(() => {
        setMatch(false);
        for (let i = 0; i < sizes.length; i++) {
            if (_sizes[sizes[i]](width)) {
                setMatch(true);
                break;
            }
        }
    }, [sizes, width]);

    return match;
};
