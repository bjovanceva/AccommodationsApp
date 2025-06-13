import React, {useState} from 'react';
import InfoIcon from '@mui/icons-material/Info';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import {Box, Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import EditAccommodationDialog from "../EditAccommodationDialog/EditAccommodationDialog.jsx";
import DeleteAccommodationDialog from "../DeleteAccommodationDialog/DeleteAccommodationDialog.jsx";
import {useNavigate} from "react-router";
import temporaryReservationListRepository from "../../../../repository/temporaryReservationListRepository.js";


const AccommodationCard = ({accommodation, onEdit, onDelete}) => {
    const navigate = useNavigate();
    const [editAccommodationDialogOpen, setEditAccommodationDialogOpen] = useState(false);
    const [deleteAccommodationDialogOpen, setDeleteAccommodationDialogOpen] = useState(false);

    const addToTemporaryList = () => {
        console.log(accommodation.id)
        temporaryReservationListRepository
            .add_accommodation(accommodation.id)
            .then(() => console.log(`Successfully added accommodation with ID ${accommodation.id} to temporary reservation list.`))
            .catch((error) => console.log(error));
    };


    return (
        <>
            <Card sx={{boxShadow: 3, borderRadius: 2, p: 1}}>
                <CardContent>
                    <Typography variant="h5">{accommodation.name}</Typography>
                    <Typography variant="subtitle2">
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab aperiam assumenda blanditiis cum
                        ducimus enim modi natus odit quibusdam veritatis.
                    </Typography>
                    <Typography variant="body1" fontWeight="bold"
                                sx={{textAlign: "right", fontSize: "1.25rem"}}>{accommodation.category}</Typography>
                    <Typography variant="body2" sx={{textAlign: "right"}}>{accommodation.numRooms} rooms
                        available</Typography>
                </CardContent>
                <CardActions sx={{justifyContent: "space-between"}}>
                    <Box>
                        <Button
                            size="small"
                            color="warning"
                            startIcon={<EditIcon/>}
                            sx={{mr: "0.25rem"}}
                            onClick={() => setEditAccommodationDialogOpen(true)}
                        >
                            Edit
                        </Button>
                        <Button
                            size="small"
                            color="error"
                            startIcon={<DeleteIcon/>}
                            onClick={() => setDeleteAccommodationDialogOpen(true)}
                        >
                            Delete
                        </Button>
                        <Button
                            size="small"
                            color="primary"
                            onClick={addToTemporaryList}
                        >
                            Add to Reservation List
                        </Button>

                    </Box>
                </CardActions>
            </Card>
            <EditAccommodationDialog
                open={editAccommodationDialogOpen}
                onClose={() => setEditAccommodationDialogOpen(false)}
                accommodation={accommodation}
                onEdit={onEdit}
            />
            <DeleteAccommodationDialog
                open={deleteAccommodationDialogOpen}
                onClose={() => setDeleteAccommodationDialogOpen(false)}
                accommodation={accommodation}
                onDelete={onDelete}
            />
        </>
    );
};

export default AccommodationCard;
