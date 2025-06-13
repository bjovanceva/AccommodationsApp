import React, {useState} from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    FormControl,
    InputLabel, MenuItem, Select,
    TextField
} from "@mui/material";
import useCategories from "../../../../hooks/useCategories.js";
import useHosts from "../../../../hooks/useHosts.js";

const EditAccommodationDialog = ({open, onClose, accommodation, onEdit}) => {
    const [formData, setFormData] = useState({
        "name": accommodation.name,
        "category": accommodation.category,
        "host": accommodation.host,
        "numRooms": accommodation.numRooms,
        // "isRented": accommodation.isRented,
    });

    const categories = useCategories();
    const hosts = useHosts();


    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    const handleSubmit = () => {
        onEdit(accommodation.id, formData);
        setFormData(formData);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Edit Accommodation</DialogTitle>
            <DialogContent>
                <TextField
                    margin="dense"
                    label="Name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    fullWidth
                />
                <FormControl fullWidth margin="dense">
                    <InputLabel>Category</InputLabel>
                    <Select
                        name="category"
                        value={formData.category}
                        onChange={handleChange}
                        label="Category"
                        variant="outlined">
                        {categories.map((category) => (
                            <MenuItem key={category} value={category}>{category}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <FormControl fullWidth margin="dense">
                    <InputLabel>Host</InputLabel>
                    <Select
                        name="host"
                        value={formData.host}
                        onChange={handleChange}
                        label="Host"
                        variant="outlined">
                        {hosts.hosts.map((host) => (
                            <MenuItem key={host.id} value={host.id}>{host.name}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <TextField
                    margin="dense"
                    label="Number of Rooms"
                    name="numRooms"
                    value={formData.numRooms}
                    onChange={handleChange}
                    fullWidth
                />
                {/*<TextField*/}
                {/*    margin="dense"*/}
                {/*    label="is it rented?"*/}
                {/*    name="isRented"*/}
                {/*    value={formData.isRented}*/}
                {/*    onChange={handleChange}*/}
                {/*    fullWidth*/}
                {/*/>*/}

            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="warning">Edit</Button>
            </DialogActions>
        </Dialog>
    );
};

export default EditAccommodationDialog;
