import React, { useEffect, useState } from 'react';
import 'tailwindcss/tailwind.css';
import Cookies from 'js-cookie';
import UserHeader from './userHeader';
import PrescriptionList from './prescriptionList';
import PatientList from './patientList';

const MainPage = ({setLogedIn}) => {
    const [user, setUser] = useState(null);
    const [type,setType] = useState("EMP");
    const [backActive,setBackActive] = useState(true);

    useEffect(() => {
        const userCookie = Cookies.get('user');
        if (userCookie) {
            const userRes = JSON.parse(userCookie);
            console.log("Logged in user- ",user);
            if (userRes) {
                setType(userRes.type);
                if(userRes.type=="PAT") {
                    setBackActive(false);
                    setUser(userRes.patient);    
                } 
                else{
                    setBackActive(true);
                    setUser(userRes.employee);
                } 
                
                console.log("Set user- ",user); // This will log the patient object
            }
        }
    }, [backActive]); // Empty dependency array means this effect runs once after the initial render

    
    const doLogOut = () => {
        Cookies.remove('user');
        Cookies.remove('token');
        setLogedIn(false);
    }

    return (
        <div className="main-page bg-gray-100 min-h-screen p-4">
            
            <UserHeader patient={user} type={type}/>

            {backActive && type=="PAT" && <button className="bg-blue-500 text-white px-4 py-2 rounded mr-2" onClick={() => setBackActive(null)}>Back</button>}

            <button className="bg-blue-500 text-white px-4 py-2 rounded" onClick={doLogOut}>Log Out</button>

            <div className="future-data bg-gray-200 p-6 rounded shadow-md">
            { (type==="PAT" ?
                <PrescriptionList id={user.id}/>
                :<PatientList setPatient={setUser} setType={setType}/>
            )}
            </div>
        </div>
    );
};

export default MainPage;