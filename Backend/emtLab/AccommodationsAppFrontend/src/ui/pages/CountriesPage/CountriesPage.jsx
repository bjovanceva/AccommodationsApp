import React, {useState} from 'react';
import {Box, CircularProgress, Button} from "@mui/material";
import AddCountryDialog from "../../components/countries/AddCountryDialog/AddCountryDialog.jsx";
import useCountries from "../../../hooks/useCountries.js";
import CountriesGrid from "../../components/countries/CountriesGrid/CountriesGrid.jsx";


const CountriesPage = () => {
    // const {accommodations, loading} = useAccommodations();
    const {countries, loading, onAdd, onEdit, onDelete} = useCountries();
    const [addCountryDialogOpen, setAddCountryDialogOpen] = useState(false);


    return (
        <>
            <Box className="products-box">
                {loading && (
                    <Box className="progress-box">
                        <CircularProgress/>
                    </Box>
                )}
                {!loading &&
                    <>
                        <Box sx={{display: "flex", justifyContent: "flex-end", mb: 2}}>
                            <Button variant="contained" color="primary" onClick={() => setAddCountryDialogOpen(true)}>
                                Add Country
                            </Button>
                        </Box>
                        <CountriesGrid countries={countries} onEdit={onEdit} onDelete={onDelete}/>
                    </>}
            </Box>
            <AddCountryDialog
                open={addCountryDialogOpen}
                onClose={() => setAddCountryDialogOpen(false)}
                onAdd={onAdd}
            />
        </>

    );
};

export default CountriesPage;
