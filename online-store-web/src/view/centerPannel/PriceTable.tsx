import React, {useEffect, useState} from 'react';
import {makeStyles} from "@material-ui/core/styles";
import TableContainer from "@material-ui/core/TableContainer";
import Table from "@material-ui/core/Table";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import TableBody from "@material-ui/core/TableBody";
import TablePagination from "@material-ui/core/TablePagination";
import Title from "../common/Title";
import {PriceDataResponse, usePriceData} from "../../reducers/priceData/priceData";
import {isPending, isSuccess} from "../../reducers/networkStateReducer";
import {CircularProgress, Typography} from "@material-ui/core";

interface Column {
    id: 'numberOfItems' | 'penguinEarPrice' | 'horseShoePrice';
    label: string;
    minWidth?: number;
    align?: 'right';
    format?: (value: number) => string;
}

const columns: Column[] = [
    { id: 'numberOfItems', label: 'Number of Items' },
    { id: 'penguinEarPrice', label: 'Penguine-Ear Price (Rs.)', format: value => value.toFixed(2) },
    { id: 'horseShoePrice', label: 'Horse-Shoe Price (Rs.)', format: value => value.toFixed(2) }
];

interface Data {
    numberOfItems: number,
    penguinEarPrice: number,
    horseShoePrice: number
}

function createData(numberOfItems: number, penguinEarPrice: number, horseShoePrice: number): Data {
    return { numberOfItems, penguinEarPrice, horseShoePrice };
}

const useStyles = makeStyles({
    root: {
    },
    container: {
    }
});

function PriceTable() {
    const classes = useStyles();
    const [page, setPage] = useState(0);
    const [priceData, fetchPriceData] = usePriceData();

    const handleChangePage = (event: unknown, newPage: number) => {
        setPage(newPage);
    };

    useEffect(() => {
        fetchPriceData(page);
    }, [page]);

    if (isSuccess(priceData)) {
        const response = priceData.data as PriceDataResponse;
        return (
            <>
                <Title description={"Price Table"}/>
                <TableContainer className={classes.container}>
                    <Table size="small" stickyHeader={true} aria-label="sticky table">
                        <TableHead>
                            <TableRow>
                                {columns.map((column) => (
                                    <TableCell
                                        key={column.id}
                                        align={column.align}
                                        style={{ minWidth: column.minWidth }}
                                    >
                                        {column.label}
                                    </TableCell>
                                ))}
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {response.prices.map((row) => {
                                let data = createData(row.quantity, row.penguinEarPrice, row.horseShoePrice);
                                return (
                                    <TableRow hover role="checkbox" tabIndex={-1} key={row.quantity}>
                                        {columns.map((column) => {
                                            const value = data[column.id];
                                            return (
                                                <TableCell key={column.id} align={column.align}>
                                                    {column.format && typeof value === 'number' ? column.format(value) : value}
                                                </TableCell>
                                            );
                                        })}
                                    </TableRow>
                                );
                            })}
                        </TableBody>
                    </Table>
                </TableContainer>
                <TablePagination
                    rowsPerPageOptions={[10]}
                    component="div"
                    count={response.totalPrices}
                    rowsPerPage={10}
                    page={page}
                    onChangePage={handleChangePage}
                />
            </>
        );
    } else {
        if (isPending(priceData)) {
            return <CircularProgress disableShrink />;
        } else {
            return (
                <Typography style={{textAlign: 'center'}} component="h2" variant="h6" color="secondary" gutterBottom>
                    API Connection Error
                </Typography>
            );
        }
    }
}

export default PriceTable