import React, {useState} from 'react';
import InfoIcon from '@mui/icons-material/Info';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import {Box, Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import EditHostDialog from "../EditHostDialog/EditHostDialog.jsx"
import DeleteHostDialog from "../DeleteHostDialog/DeleteHostDialog.jsx"
import useCountries from "../../../../hooks/useCountries.js";
// import {useNavigate} from "react-router";


const HostCard = ({host, onEdit, onDelete}) => {
    // const navigate = useNavigate();
    const countries=useCountries();
    const [editHostDialogOpen, setEditHostDialogOpen] = useState(false);
    const [deleteHostDialogOpen, setDeleteHostDialogOpen] = useState(false);

    return (
        <>
            <Card sx={{boxShadow: 3, borderRadius: 2, p: 1}}>
                <CardContent>
                    <Typography variant="h5">{host.name}</Typography>
                    <Typography variant="body1" fontWeight="bold"
                                sx={{textAlign: "right", fontSize: "1.25rem"}}>{host.surname}</Typography>
                    <Typography variant="body2" sx={{textAlign: "right"}}>{countries.countries.find(x=> x.id===host.country)?.name}</Typography>
                </CardContent>
                <CardActions sx={{justifyContent: "space-between"}}>
                    {/*<Button*/}
                    {/*    size="small"*/}
                    {/*    color="info"*/}
                    {/*    startIcon={<InfoIcon/>}*/}
                    {/*    onClick={() => navigate(`/products/${product.id}`)}*/}
                    {/*>*/}
                    {/*    Info*/}
                    {/*</Button>*/}
                    <Box>
                        <Button
                            size="small"
                            color="warning"
                            startIcon={<EditIcon/>}
                            sx={{mr: "0.25rem"}}
                            onClick={() => setEditHostDialogOpen(true)}
                        >
                            Edit
                        </Button>
                        <Button
                            size="small"
                            color="error"
                            startIcon={<DeleteIcon/>}
                            onClick={() => setDeleteHostDialogOpen(true)}
                        >
                            Delete
                        </Button>
                    </Box>
                </CardActions>
            </Card>
            <EditHostDialog
                open={editHostDialogOpen}
                onClose={() => setEditHostDialogOpen(false)}
                host={host}
                onEdit={onEdit}
            />
            <DeleteHostDialog
                open={deleteHostDialogOpen}
                onClose={() => setDeleteHostDialogOpen(false)}
                host={host}
                onDelete={onDelete}
            />
        </>
    );
};

export default HostCard;
