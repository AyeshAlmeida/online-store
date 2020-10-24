import React, {useEffect, useState} from 'react';
import makeStyles from "@material-ui/core/styles/makeStyles";
import {CircularProgress, createStyles, Theme, Typography} from "@material-ui/core";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import Select from "@material-ui/core/Select";
import TextField from "@material-ui/core/TextField";
import IconButton from "@material-ui/core/IconButton";
import AddShoppingCartIcon from '@material-ui/icons/AddShoppingCart';
import {ProductDataResponse, useProductData} from "../../../reducers/products/productData";
import {isPending, isSuccess} from "../../../reducers/networkStateReducer";
import {useCalculation} from "../../../reducers/calc/calculationData";

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        root: {
            '& .MuiTextField-root': {
                margin: theme.spacing(1),
                width: 200,
            },
        },
        formControl: {
            margin: theme.spacing(1),
            minWidth: 120,
        },
        selectEmpty: {
            marginTop: theme.spacing(2),
        },
    }),
);

export interface CalculatorFormProps {
    currentTotal: number
}

interface CalculatorFormState {
    productId: number,
    type: string,
    quantity: number,
    currentTotal: number
}

function CalculatorForm({currentTotal}: CalculatorFormProps) {
    const classes = useStyles();
    const [productData, fetchProductData] = useProductData();
    const [calculationData, doCalculation] = useCalculation();

    useEffect(() => {
        fetchProductData();
    }, []);

    const [state, setState] = useState<CalculatorFormState>({
        productId: 1,
        type: 'CARTON',
        quantity: 0,
        currentTotal: currentTotal
    });

    const handleChange = (event: React.ChangeEvent<{ name?: string; value: unknown }>) => {
        const name = event.target.name as keyof typeof state;
        setState({
            ...state,
            [name]: event.target.value,
        });
    };

    if (isSuccess(productData)) {
        const response = productData.data as ProductDataResponse;

        return (
            <div style={{paddingTop: 20, paddingBottom: 10}}>
                <FormControl className={classes.formControl}>
                    <InputLabel htmlFor="product-native-simple">Product</InputLabel>
                    <Select
                        native
                        value={state.productId}
                        onChange={handleChange}
                        inputProps={{
                            name: 'productId',
                            id: 'product-native-simple',
                        }}
                    >
                        {response && response.products.map(prod => {
                            return (
                                <option value={prod.id}>{prod.productName}</option>
                            );
                        })}
                    </Select>
                </FormControl>

                <FormControl className={classes.formControl}>
                    <InputLabel htmlFor="buying-category-native-simple">Type</InputLabel>
                    <Select
                        native
                        value={state.type}
                        onChange={handleChange}
                        inputProps={{
                            name: 'type',
                            id: 'buying-category-native-simple',
                        }}
                    >
                        <option value={"CARTON"}>Carton (s)</option>
                        <option value={"UNIT"}>Unit (s)</option>
                    </Select>
                </FormControl>

                <FormControl style={{margin: 10, maxWidth: 80}}>
                    <TextField
                        id="product-quantity"
                        label="Quantity"
                        type="number"
                        size="small"
                        onChange={handleChange}
                        InputLabelProps={{
                            shrink: true,
                        }}
                        defaultValue={0}
                        inputProps={{
                            name: 'quantity',
                            id: 'product-quantity'
                        }}
                    />
                </FormControl>

                <FormControl style={{margin: 10}}>
                    <IconButton color="primary" aria-label="add to shopping cart">
                        <AddShoppingCartIcon
                            onClick={() => {
                                if (state.quantity > 0) {
                                    doCalculation(1, "CARTON", 10, 0)
                                }
                            }}
                        />
                    </IconButton>
                </FormControl>
            </div>
        );
    } else {
        if (isPending(productData)) {
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

export default CalculatorForm