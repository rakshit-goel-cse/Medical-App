import React, { useState } from 'react';

const PrescriptionForm = ({ prescription, isEditable }) => {
    const [formData, setFormData] = useState({
        id: prescription.id,
        drugName: prescription.drug.name,
        creationDate: prescription.creationDate,
        pickupDate: prescription.pickupDate,
        prescriberName: prescription.prescriber.name,
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('Updated Prescription:', formData);
        // Add your update logic here
    };

    return (
        <div className="max-w-md mx-auto bg-white p-8 rounded shadow-md">
            <h2 className="text-2xl font-bold mb-4">Prescription Details</h2>
            <form onSubmit={isEditable ? handleSubmit : (e) => e.preventDefault()}>
                <div className="mb-4">
                    <label className="block text-gray-700">Prescription ID</label>
                    <input
                        type="text"
                        name="id"
                        value={formData.id}
                        className={`w-full px-3 py-2 border rounded ${!isEditable && 'pointer-events-none'}`}
                        readOnly
                    />
                </div>
                <div className="mb-4">
                    <label className="block text-gray-700">Drug Name</label>
                    <input
                        type="text"
                        name="drugName"
                        value={formData.drugName}
                        onChange={handleInputChange}
                        className={`w-full px-3 py-2 border rounded ${!isEditable && 'pointer-events-none'}`}
                        readOnly={!isEditable}
                    />
                </div>
                <div className="mb-4">
                    <label className="block text-gray-700">Creation Date</label>
                    <input
                        type="text"
                        name="creationDate"
                        value={formData.creationDate}
                        onChange={handleInputChange}
                        className={`w-full px-3 py-2 border rounded ${!isEditable && 'pointer-events-none'}`}
                        readOnly={!isEditable}
                    />
                </div>
                <div className="mb-4">
                    <label className="block text-gray-700">Pickup Date</label>
                    <input
                        type="text"
                        name="pickupDate"
                        value={formData.pickupDate}
                        onChange={handleInputChange}
                        className={`w-full px-3 py-2 border rounded ${!isEditable && 'pointer-events-none'}`}
                        readOnly={!isEditable}
                    />
                </div>
                <div className="mb-4">
                    <label className="block text-gray-700">Prescriber Name</label>
                    <input
                        type="text"
                        name="prescriberName"
                        value={formData.prescriberName}
                        onChange={handleInputChange}
                        className={`w-full px-3 py-2 border rounded ${!isEditable && 'pointer-events-none'}`}
                        readOnly={!isEditable}
                    />
                </div>
                {isEditable && (
                    <button
                        type="submit"
                        className="w-full bg-blue-500 text-white px-4 py-2 rounded"
                    >
                        Update Prescription
                    </button>
                )}
            </form>
        </div>
    );
};

export default PrescriptionForm;