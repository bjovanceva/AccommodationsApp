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
import useCategories from "../../../../hooks/useCategories.js"
import useHosts from "../../../../hooks/useHosts.js";

const initialFormData = {
    "name": "",
    "category": "",
    "host": "",
    "numRooms": "",
    // "isRented": "",
};

const AddAccommodationDialog = ({open, onClose, onAdd}) => {
    const [formData, setFormData] = useState(initialFormData);
    const hosts = useHosts();
    const categories = useCategories();


    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    const handleSubmit = () => {
        onAdd(formData);
        setFormData(initialFormData);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Add Accommodation</DialogTitle>
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
                <Button onClick={handleSubmit} variant="contained" color="primary">Add</Button>
            </DialogActions>
        </Dialog>
    );
};

export default AddAccommodationDialog;
