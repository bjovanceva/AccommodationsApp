import React, {useState} from 'react';
import {Box, CircularProgress, Button} from "@mui/material";
import useAccommodations from "../../../hooks/useAccommodations.js";
import AccommodationsGrid from "../../components/accommodations/AccommodationsGrid/AccommodationsGrid.jsx"
import AddAccommodationDialog from "../../components/accommodations/AddAccommodationDialog/AddAccommodationDialog.jsx";
import useTemporaryReservationList from "../../../hooks/useTemporaryReservationList.js";


const AccommodationsPage = () => {
    // const {accommodations, loading} = useAccommodations();
    const {accommodations, loading, onAdd, onEdit, onDelete} = useAccommodations();
    const [addAccommodationDialogOpen, setAddAccommodationDialogOpen] = useState(false);



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
                            <Button variant="contained" color="primary" onClick={() => setAddAccommodationDialogOpen(true)}>
                                Add Accommodation
                            </Button>
                        </Box>
                        <AccommodationsGrid accommodations={accommodations} onEdit={onEdit} onDelete={onDelete} />
                    </>}
            </Box>
            <AddAccommodationDialog
                open={addAccommodationDialogOpen}
                onClose={() => setAddAccommodationDialogOpen(false)}
                onAdd={onAdd}
            />
        </>

    );
};

export default AccommodationsPage;
