import axiosInstance from "../axios/axios.js";

const temporaryReservationListRepository = {
    getActive: async () => {
        return await axiosInstance.get("/temporary-reservation-list");
    },
    add_accommodation: async (id) => {
        return await axiosInstance.post(`/temporary-reservation-list/add-accommodation/${id}`);
    },
    confirmReservations: async () => {
        return await axiosInstance.post("/temporary-reservation-list/confirmReservations");
    }
};

export default temporaryReservationListRepository;