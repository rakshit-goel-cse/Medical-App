import React, { useEffect, useState } from 'react';
import 'tailwindcss/tailwind.css';
import Cookies from 'js-cookie';
import UserHeader from './userHeader';
import PrescriptionList from './prescriptionList';
import PatientList from './patientList';

const MainPage = ({setLogedIn}) => {
    const [user, setUser] = useState({
        user:null,
        type:"EMP"
    });
    const [data,setData] = useState(null);
    const [backActive,setBackActive] = useState(true);

    useEffect(() => {
        if(user.user && user.type==="PAT"){
            const getPrescriptions = (id) => {
    
                console.log("PrescriptionList for pat id: ",id);
                fetch(`http://localhost:8088/prescription/getActiveByPatId?patId=${id}&offSet=`+(0),{
                    method: 'GET',
                    headers: {
                        'Authorization': `Bearer ${Cookies.get('token')}`,
                        'Content-Type': 'application/json',
                    },
                })
                    .then(response => response.json())
                    .then(data => {
                        setData(data);
                    })
                    .catch(error => console.error('Error fetching prescriptions:', error));
        };
        getPrescriptions(user.user.id);
        }
    }   , [user]);

    


    useEffect(() => {
        const userCookie = Cookies.get('user');
        if (userCookie) {
            const userRes = JSON.parse(userCookie);
            console.log("Logged in user- ",user);
            if (userRes) {
                //setType(userRes.type);
                if(userRes.type==="PAT") {
                    setBackActive(false);
                    setUser({
                        user:userRes.patient,
                        type:userRes.type
                    });    
                } 
                else{
                    setBackActive(true);
                    //setUser(userRes.employee);
                    setUser({
                        user:userRes.patient,
                        type:userRes.type
                    });
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
        <div className="main-page bg-gray-100 h-screen p-4">
            
            <UserHeader patient={user.user} type={user.type}/>

            <div className="flex justify-between items-center mb-4">
                {backActive && user.type === "PAT" && (
                    <button className="bg-blue-500 text-white px-4 py-2 rounded" onClick={() => setBackActive(null)}>Back</button>
                )}
                <button className="bg-blue-500 text-white px-4 py-2 rounded ml-auto" onClick={doLogOut}>Log Out</button>
            </div>


            <div className="future-data bg-gray-200 p-6 rounded shadow-md h-5/6 mt-4">
            { user.type=="PAT" && data?
                 <PrescriptionList id={user.user.id} pres={data.content} tPages={data.totalPages}/>
                                    :<PatientList setUser={setUser}/>
            }
            </div>
        </div>
    );
};

export default MainPage;

