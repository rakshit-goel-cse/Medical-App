import React, { useEffect, useState } from 'react';
import 'tailwindcss/tailwind.css';
import Cookies from 'js-cookie';
import UserHeader from './userHeader';

const MainPage = () => {
    const [patient, setPatient] = useState(null);
    const [type,setType] = useState(null);
    useEffect(() => {
        const userCookie = Cookies.get('user');
        if (userCookie) {
            const user = JSON.parse(userCookie);
            console.log(user);
            if (user) {
                setType(user.type);
                setPatient(user.patient);
                console.log(user.patient); // This will log the patient object
            }
        }
    }, []); // Empty dependency array means this effect runs once after the initial render

    


    return (
        <div className="main-page bg-gray-100 min-h-screen p-4">
            
            <UserHeader patient={patient} type={type}/>

            <div className="future-data bg-gray-200 p-6 rounded shadow-md">
                <p>Future data will be displayed here.</p>
            </div>
        </div>
    );
};

export default MainPage;