import './App.css'
import React from 'react';

import {BrowserRouter, Routes, Route} from "react-router";
import Layout from "./ui/components/layout/Layout/layout.jsx";
import HomePage from "./ui/pages/HomePage/HomePage.jsx";
import AccommodationsPage from "./ui/pages/AccommodationsPage/AccommodationsPage.jsx";
import CountriesPage from "./ui/pages/CountriesPage/CountriesPage.jsx";
import HostsPage from "./ui/pages/HostsPage/HostsPage.jsx";
import ProtectedRoute from "./ui/components/routing/ProtectedRoute/ProtectedRoute.jsx";
import Register from "./ui/components/auth/Register/Register.jsx";
import Login from "./ui/components/auth/Login/Login.jsx";
import TemporaryReservationList from "./ui/components/temporary_list/TemporaryList/TemporaryList.jsx";


function App() {
   return (

       <BrowserRouter>
           <Routes>
               <Route path="/register" element={<Register/>}/>
               <Route path="/login" element={<Login/>}/>
               <Route path="/" element={<Layout/>}>
                   <Route index element={<HomePage/>}/>
                   <Route element={<ProtectedRoute/>}>
                       <Route path="accommodations" element={<AccommodationsPage/>}/>
                       <Route path="hosts" element={<HostsPage/>}/>
                       <Route path="countries" element={<CountriesPage/>}/>
                       <Route path="temporary-reservation-list" element={<TemporaryReservationList/>}/>
                   </Route>
               </Route>
           </Routes>
       </BrowserRouter>


   );
};

export default App
