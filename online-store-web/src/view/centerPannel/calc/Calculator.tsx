import React, {useState} from 'react';
import {createStyles, Theme, Typography} from "@material-ui/core";
import Title from "../../common/Title";
import makeStyles from "@material-ui/core/styles/makeStyles";
import CalculatorForm from "./CalculatorForm";
import CalculatorTable, {Data} from "./CalculatorTable";
import FormControl from "@material-ui/core/FormControl";
import Button from "@material-ui/core/Button";

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

function Calculator() {
    const classes = useStyles();
    const [total, setTotal] = useState<number>(0.00);
    const [tableData, setTableData] = useState<Array<Data>>([
        {
            id: 1,
            product: "Penguin Ear",
            type: "Carton (s)",
            quantity: 100,
            amount: 200.00
        }
    ]);

    return (
        <>
            <Title description={"Price Calculator"}/>

            <CalculatorForm/>

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