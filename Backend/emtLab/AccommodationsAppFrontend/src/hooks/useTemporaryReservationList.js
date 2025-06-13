import {useCallback, useEffect, useState} from "react";
import temporaryReservationListRepository from "../repository/temporaryReservationListRepository.js";


const useTemporaryReservationList = () => {
    const [temporaryReservationList, setTemporaryReservationList] = useState({
        "id": null,
        "user": null,
        "accommodations": [],
        "temporaryListStatus": null,
    });

    const getActive = useCallback(() => {
        temporaryReservationListRepository
            .getActive()
            .then((response) => {
                setTemporaryReservationList(response.data)
            })
            .catch((error) => console.log(error));
    }, []);

    const onConfirmReservation = useCallback(() => {
        temporaryReservationListRepository
            .confirmReservations()
            .then(() => {
                console.log("Successfully confirmed reservation")
                getActive()
            })
            .catch((error) => console.log(error));
    }, [getActive]);

    const onAddToTemporaryList = useCallback((id) => {
        temporaryReservationListRepository
            .add_accommodation(id)
            .then(() => {
                console.log("Successfully added accommodation to reservation list")
                getActive()
            })
            .catch((error) => console.log(error));
    }, [getActive]);

    useEffect(() => {
        getActive();
    }, [getActive]);

    return {...temporaryReservationList, onConfirmReservation, onAddToTemporaryList};

};

//     useEffect(() => {
//         getActive()
//         // temporaryReservationListRepository
//         //     .getActive()
//         //     .then((response) => setTemporaryReservationList(response.data))
//         //     .catch((error) => console.log(error));
//     }, [getActive]);
//
// return {...state, onConfirmReservation, onEdit: onEdit, onDelete: onDelete};

// };

export default useTemporaryReservationList;
