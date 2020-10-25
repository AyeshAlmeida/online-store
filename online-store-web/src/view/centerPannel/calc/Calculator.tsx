import React, {useEffect, useState} from 'react';
import {createStyles, Theme, Typography} from "@material-ui/core";
import Title from "../../common/Title";
import makeStyles from "@material-ui/core/styles/makeStyles";
import CalculatorForm from "./CalculatorForm";
import CalculatorTable, {Data} from "./CalculatorTable";
import Button from "@material-ui/core/Button";
import {CalculationData, useCalculation} from "../../../reducers/calc/calculationData";
import {isSuccess} from "../../../reducers/networkStateReducer";

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        root: {
            '& > *': {
                margin: theme.spacing(1),
            },
        },
        depositContext: {
            flex: 1,
        },
    }),
);

const createData = (data: CalculationData): Data => {
    return {
        id: data.id,
        product: data.product,
        type: data.type,
        quantity: data.quantity,
        amount: data.amount
    }
};

function Calculator() {
    const classes = useStyles();
    const [total, setTotal] = useState<number>(0.00);
    const [tableData, setTableData] = useState<Array<Data>>([]);
    const [calculationData, doCalculation] = useCalculation();
    const [addedToCart, setAddedToCart] = useState<boolean>(false);

    useEffect(() => {
        if (isSuccess(calculationData) && calculationData.data) {
            setTableData([...tableData, createData(calculationData.data.productDetails)]);
            setTotal(total + calculationData.data.total)
        }
    }, [addedToCart, calculationData]);

    return (
        <>
            <Title description={"Price Calculator"}/>

            <CalculatorForm currentTotal={total} currentCartState={addedToCart} updateCartState={setAddedToCart}/>

            <CalculatorTable data={tableData}/>

            <div className={classes.root} style={{textAlign: 'center', marginTop: 20, marginBottom: 10}}>
                <Typography component="p" variant="h5">
                    Total Amount Rs: {total.toFixed(2)}
                </Typography>
                <Button
                    color="primary"
                    variant="contained"
                    onClick={() => {
                        setTotal(0.00);
                        setTableData([]);
                    }}>
                    Complete Transaction
                </Button>
            </div>
        </>
    )
}

export default Calculator