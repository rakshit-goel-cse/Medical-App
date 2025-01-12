import React, { useEffect, useLayoutEffect, useState } from 'react';
import 'tailwindcss/tailwind.css';
import Cookies from 'js-cookie';

const MainPage = () => {

    const [patient, setPatient] = useState(null);

    useEffect(() => {
        const userCookie = Cookies.get('user');
        if (userCookie) {
            const user = JSON.parse(userCookie);
            console.log(user);
            if (user) {
                setPatient(user.patient);
                console.log(user.patient); // This will log the patient object
            }
        }
    }, []); // Empty dependency array means this effect runs once after the initial render


    return (
        <div className="main-page bg-gray-100 min-h-screen p-4">
            <div className="user-info bg-white p-6 rounded shadow-md mb-4">
                <h2 className="text-2xl font-bold mb-2">User Information</h2>
                <p className="mb-1"><strong>Name:</strong> {patient?patient.name:"John Doe"}</p>
                <p className="mb-1"><strong>Date of Birth:</strong>  {patient?patient.dob:""}</p>
                <p><strong>Address:</strong> 
                    {patient && patient.patAdd && patient.patAdd[0]
                        ?patient.patAdd[0].addLine1+" "+(patient.patAdd[0].addLine2?patient.patAdd[0].addLine2:"")
                        :""
                }
                </p>
            </div>
            <div className="future-data bg-gray-200 p-6 rounded shadow-md">
                <p>Future data will be displayed here.</p>
            </div>
        </div>
    );
};

export default MainPage;