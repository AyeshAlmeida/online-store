import React, {useState} from 'react';
import {Typography} from "@material-ui/core";

export interface TitleProps {
    description: string
}

function Title({description}: TitleProps) {
    return (
        <Typography component="h2" variant="h6" color="primary" gutterBottom>
            {description}
        </Typography>
    );
}

export default Title