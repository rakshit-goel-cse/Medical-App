const UserHeader=({patient,type})=>{

    console.log("patient header- ",patient);

    return (
    <div className="user-info bg-blue-200 p-6 rounded shadow-md mb-4 flex items-center justify-between border-4 border-blue-500" style={{ height: '20%' }}>
    <div className="flex space-x-4">
        <p className="mb-1"><strong>Name:</strong> {patient ? patient.name : "John Doe"}</p>
        <p className="mb-1"><strong>Date of Birth:</strong> {patient ? patient.dob : ""}</p>
        <p><strong>Address: </strong> 
            {patient && patient.patAdd && patient.patAdd[0]
                ? patient.patAdd[0].addLine1 + " " + (patient.patAdd[0].addLine2 ? patient.patAdd[0].addLine2 : "")
                : ""
            }
        </p>
        <p><strong>City: </strong> 
            {patient && patient.patAdd && patient.patAdd[0]
                ? patient.patAdd[0].city
                : ""
            }
        </p>
        <p><strong>State: </strong> 
            {patient && patient.patAdd && patient.patAdd[0]
                ? patient.patAdd[0].state
                : ""
            }
        </p>
        <p><strong>Zip Code: </strong> 
            {patient && patient.patAdd && patient.patAdd[0]
                ? patient.patAdd[0].zipcode
                : ""
            }
        </p>
    </div>
    <p><strong>Type: </strong> 
            {type
                ? type
                : ""
            }
        </p>
</div>
);
}

export default UserHeader;