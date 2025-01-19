import React, { useEffect, useState } from 'react';
import Cookies from 'js-cookie';

const PatientList = ({setPatient,setType}) => {
    const [patient, setpatient] = useState([]);
    const [page,setPage]=useState(1);
    const [totalPages, setTotalPages] = useState(1);

    useEffect(() => {
        fetch('http://localhost:8088/patient/getPatientList',{
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${Cookies.get('token')}`,
                'Content-Type': 'application/json',
            },
        })
            .then(response => response.json())
            .then(data => {
                setpatient(data.content);
                console.log("patient",data.content);
            })
            .catch(error => console.error('Error fetching patient:', error));
    }, [page]);

    const handleNextPage = () => {
        if (page < totalPages) {
            setPage(page + 1);
        }
    };

    const handlePrevPage = () => {
        if (page > 1) {
            setPage(page - 1);
        }
    };

    const selectPatient = (pat) => {
        console.log("Selected Patient: ",pat);
        setPatient(pat);
        setType("PAT");
    }

    return (
        <div>
            <h2>patient List</h2>
            <ul>
                {patient && patient.map(pat => (
                     <li className='border border-blue-500 p-2 mb-2 cursor-pointer' key={pat.id} onClick={() => selectPatient(pat)}>
                     <div className="flex space-x-4">
                         <div><strong>Drug Name:</strong> {pat.name}</div>
                         <div><strong>DOB:</strong> {pat.dob}</div>
                         <div><strong>Gender:</strong> {pat.gender}</div>
                         <div><strong>Height:</strong> {pat.height}</div>
                         <div><strong>Weight:</strong> {pat.weight}</div>
                     </div>
                 </li>
                ))}
            </ul>
            {/* pageer*/ }
            <div className="flex justify-between items-center mt-4">
                <span>Page {page} of {totalPages}</span>
                <button className="bg-blue-500 text-white px-4 py-2 rounded" onClick={handlePrevPage} disabled={page == 1}>
                    Prev Page
                </button>
                <button className="bg-blue-500 text-white px-4 py-2 rounded" onClick={handleNextPage} disabled={page >= totalPages}>
                    Next Page
                </button>
            </div>
        </div>
    );
};

export default PatientList;