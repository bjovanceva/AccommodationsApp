import React from 'react';
import useTemporaryReservationList from "../../../../hooks/useTemporaryReservationList.js";
import {
    Box,
    Card,
    CardContent,
    Typography,
    IconButton,
    Button,
    Divider,
    List,
    ListItem,
    ListItemText,
} from '@mui/material';
import {Delete} from '@mui/icons-material';

// import temporaryReservationListRepository from "../../../../repository/temporaryReservationListRepository.js";

const TemporaryList = () => {
    const {accommodations, temporaryListStatus, onConfirmReservation} = useTemporaryReservationList();

    // const confirmReservation = () => {
    //     temporaryReservationListRepository
    //         .confirmReservations()
    //         .then(() => (
    //
    //             console.log(`Successfully confirmed reservation.`)))
    //         .catch((error) => console.log(error));
    // };

    // const getTotal = () =>
    //     products.reduce((sum, product) => sum + product.price, 0).toFixed(2);

    return (
        <Box my={3} width={500} mx="auto">
            <Card>
                <CardContent>
                    <Typography variant="h5" gutterBottom>
                        Temporary Reservation List
                    </Typography>
                    <Divider sx={{mb: 2}}/>
                    <List>
                        {accommodations.map(item => (
                            <ListItem
                                key={item.id}
                                secondaryAction={<IconButton edge="end" color="error"><Delete/></IconButton>}
                            >
                                <ListItemText
                                    primary={item.name}
                                    // secondary={`$${item.price.toFixed(2)}`}
                                />
                            </ListItem>
                        ))}
                    </List>
                    <Typography variant="body1" fontWeight="bold"
                                sx={{textAlign: "right", fontSize: "1rem"}}>Status: {temporaryListStatus}</Typography>
                    <Divider sx={{my: 2}}/>
                    {/*<Typography variant="h6">Total: ${getTotal()}</Typography>*/}
                    <Button variant="contained" color="primary" fullWidth sx={{mt: 2}}
                            onClick={onConfirmReservation}>
                        Make Reservation
                    </Button>
                </CardContent>
            </Card>
        </Box>
    );
};

export default TemporaryList;
