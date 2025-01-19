import React, { useEffect, useState } from 'react';
import Cookies from 'js-cookie';

const PrescriptionList = ({id}) => {
    const [prescriptions, setPrescriptions] = useState([]);
    const [page,setPage]=useState(1);
    const [totalPages, setTotalPages] = useState(1);

    useEffect(() => {
        fetch(`http://localhost:8088/prescription/getActiveByPatId?patId=${id}&offSet=`+(page-1),{
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${Cookies.get('token')}`,
                'Content-Type': 'application/json',
            },
        })
            .then(response => response.json())
            .then(data => {
                setPrescriptions(data.content);
                setTotalPages(data.totalPages);
                console.log("Prescriptions",data.content);
            })
            .catch(error => console.error('Error fetching prescriptions:', error));
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

    return (
        <div>
            <h2>Prescription List</h2>
            <ul>
                {prescriptions && prescriptions.map(prescription => (
                     <li className='border border-blue-500 p-2 mb-2 cursor-pointer' key={prescription.id} onClick={() => "console.log('Clicked')"}>
                     <div className="flex space-x-4">
                        <div><strong>Pres Id:</strong> {prescription.id}</div>
                         <div><strong>Drug Name:</strong> {prescription.drug.name}</div>
                         <div><strong>Creation Date:</strong> {prescription.creationDate}</div>
                         <div><strong>Pickup Date:</strong> {prescription.pickupDate}</div>
                         <div><strong>Prescriber Name:</strong> {prescription.prescriber.name}</div>
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

export default PrescriptionList;