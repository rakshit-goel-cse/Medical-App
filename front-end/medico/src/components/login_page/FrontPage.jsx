import React, { useState } from "react";

const FrontPage = () => {
  const [isPatient, setIsPatient] = useState(true);
  const [isLogin, setIsLogin] = useState(true);
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = () => {
    alert(
      `${isLogin ? "Logging in" : "Registering"} as ${
        isPatient ? "Patient" : "Client"
      } with Username: ${formData.username} and Password: ${formData.password}`
    );
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <div className="w-full max-w-md p-6 bg-white rounded-lg shadow-lg">
        <h1 className="text-2xl font-semibold text-center mb-4">
          {isLogin ? "Login" : "Register"}
        </h1>

        {/* Username */}
        <div className="mb-4">
          <label className="block text-gray-700">Username</label>
          <input
            type="text"
            name="username"
            value={formData.username}
            onChange={handleInputChange}
            placeholder="Enter Username"
            className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring focus:ring-blue-400"
          />
        </div>

        {/* Password */}
        <div className="mb-6">
          <label className="block text-gray-700">Password</label>
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleInputChange}
            placeholder="Enter Password"
            className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring focus:ring-blue-400"
          />
        </div>

        {/* Patient/Client Slider */}
        <div className="relative flex items-center justify-center mb-4">
          <span
            className={`font-medium mr-3 cursor-pointer ${
              isPatient ? "text-blue-500" : "text-gray-500"
            }`}
            onClick={() => setIsPatient(true)}
          >
            Patient
          </span>
          <div
            className="w-14 h-8 flex items-center bg-gray-300 rounded-full p-1 cursor-pointer"
            onClick={() => setIsPatient(!isPatient)}
          >
            <div
              className={`w-6 h-6 bg-blue-500 rounded-full shadow-md transform transition-transform duration-300 ${
                isPatient ? "translate-x-0" : "translate-x-6"
              }`}
            ></div>
          </div>
          <span
            className={`font-medium ml-3 cursor-pointer ${
              !isPatient ? "text-blue-500" : "text-gray-500"
            }`}
            onClick={() => setIsPatient(false)}
          >
            Client
          </span>
        </div>

        {/* Login/Register Slider */}
        <div className="relative flex items-center justify-center mb-6">
          <span
            className={`font-medium mr-3 cursor-pointer ${
              isLogin ? "text-blue-500" : "text-gray-500"
            }`}
            onClick={() => setIsLogin(true)}
          >
            Login
          </span>
          <div
            className="w-14 h-8 flex items-center bg-gray-300 rounded-full p-1 cursor-pointer"
            onClick={() => setIsLogin(!isLogin)}
          >
            <div
              className={`w-6 h-6 bg-blue-500 rounded-full shadow-md transform transition-transform duration-300 ${
                isLogin ? "translate-x-0" : "translate-x-6"
              }`}
            ></div>
          </div>
          <span
            className={`font-medium ml-3 cursor-pointer ${
              !isLogin ? "text-blue-500" : "text-gray-500"
            }`}
            onClick={() => setIsLogin(false)}
          >
            Register
          </span>
        </div>

        {/* Submit Button */}
        <button
          onClick={handleSubmit}
          className="w-full px-3 py-2 text-white bg-blue-500 rounded-lg hover:bg-blue-600 focus:outline-none focus:ring focus:ring-blue-400"
        >
          {isLogin ? "Login" : "Register"}
        </button>
      </div>
    </div>
  );
};

export default FrontPage;
