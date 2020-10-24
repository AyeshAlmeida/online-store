import React from 'react';
import makeStyles from "@material-ui/core/styles/makeStyles";
import {createStyles, Theme} from "@material-ui/core";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import Select from "@material-ui/core/Select";
import TextField from "@material-ui/core/TextField";
import IconButton from "@material-ui/core/IconButton";
import AddShoppingCartIcon from '@material-ui/icons/AddShoppingCart';

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

function CalculatorForm() {
    const classes = useStyles();
    const [state, setState] = React.useState<{ product: string; type: string , quantity: number}>({
        product: '',
        type: 'carton',
        quantity: 0
    });

    const handleChange = (event: React.ChangeEvent<{ name?: string; value: unknown }>) => {
        const name = event.target.name as keyof typeof state;
        setState({
            ...state,
            [name]: event.target.value,
        });
    };

    return (
        <div style={{paddingTop: 20, paddingBottom: 10}}>
            <FormControl className={classes.formControl}>
                <InputLabel htmlFor="product-native-simple">Product</InputLabel>
                <Select
                    native
                    value={state.product}
                    onChange={handleChange}
                    inputProps={{
                        name: 'product',
                        id: 'product-native-simple',
                    }}
                >
                    <option value={1}>Penguine-Ear</option>
                    <option value={2}>Horse-Leg</option>
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
                    <option value={"carton"}>Carton (s)</option>
                    <option value={"unit"}>Unit (s)</option>
                </Select>
            </FormControl>

            <FormControl style={{margin: 10, maxWidth: 80}}>
                <TextField
                    id="product-quantity"
                    label="Quantity"
                    type="number"
                    size="small"
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
                        onClick={() => console.log("button clicked...")}
                    />
                </IconButton>
            </FormControl>
        </div>
    );
}

export default CalculatorForm