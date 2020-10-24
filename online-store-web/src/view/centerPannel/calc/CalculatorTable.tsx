import React, {useState} from 'react';
import {makeStyles} from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import {
    CalculationData,
    CalculationDataResponse,
    useCalculation
} from "../../../reducers/calc/calculationData";
import {isInit, isPending, isSuccess} from "../../../reducers/networkStateReducer";

export interface Data {
    id: number,
    product: string,
    type: string,
    quantity: number,
    amount: number
}

export interface CalculatorTableProps {
    data: Array<Data>
}

const createData = (data: CalculationData): Data => {
    return {
        id: data.id,
        product: data.product,
        type: data.type,
        quantity: data.quantity,
        amount: data.amount
    }
};

function CalculatorTable() {
    const [data, setData] = useState<Data[]>([]);
    const [calculationData, doCalculation] = useCalculation();

    if (isSuccess(calculationData) && calculationData.data) {
        setData([...data, createData(calculationData.data.productDetails)]);
    }

    return (
        <>
            <Table stickyHeader={true} aria-label="sticky table" style={{marginTop: 12, marginBottom: 12}}>
                <TableHead>
                    <TableRow>
                        <TableCell align={"left"}  style={{backgroundColor: '#bdc3c7'}}>Product</TableCell>
                        <TableCell align={"center"} style={{backgroundColor: '#bdc3c7'}}>Type</TableCell>
                        <TableCell align={"center"} style={{backgroundColor: '#bdc3c7'}}>QTY</TableCell>
                        <TableCell align={"right"} style={{backgroundColor: '#bdc3c7'}}>Amount (Rs.)</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {data.slice(0, 2).map((row) => {
                        return (
                            <TableRow hover role="checkbox" tabIndex={-1} key={row.id}>
                                <TableCell align={"left"}>
                                    {row.product}
                                </TableCell>
                                <TableCell align={"center"}>
                                    {row.type}
                                </TableCell>
                                <TableCell align={"center"}>
                                    {row.quantity}
                                </TableCell>
                                <TableCell align={"right"}>
                                    {row.amount}
                                </TableCell>
                            </TableRow>
                        );
                    })}
                </TableBody>
            </Table>
        </>
    );
}

export default CalculatorTable