import React, { useState } from "react";

const FrontPage = () => {
  const [isPatient, setIsPatient] = useState(true);
  const [isLogin, setIsLogin] = useState(true);
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });

  const [userData, setUserData] = useState({
    userName: '',
    userId: '',
    password: ''
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

  const handleChangeUser = (e) => {
    const { name, value } = e.target;
    setUserData({
      ...userData,
      [name]: value
    });
  };

  const handleSubmitUser = (e) => {
    e.preventDefault();
    console.log('Form data submitted:', userData);
  };

  const getMoreDetails = () => {
    return (
      <>
        <form onSubmit={handleSubmitUser}>
          <div className="mb-4">
            <label htmlFor="userName" className="block text-gray-700">User Name:</label>
            <input
              type="text"
              id="userName"
              name="userName"
              value={userData.userName}
              onChange={handleChangeUser}
              className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring focus:ring-blue-400"
              required
            />
          </div>
          <div className="mb-4">
            <label htmlFor="userId" className="block text-gray-700">User ID:</label>
            <input
              type="text"
              id="userId"
              name="userId"
              value={userData.userId}
              onChange={handleChangeUser}
              className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring focus:ring-blue-400"
              required
            />
          </div>
          <div className="mb-4">
            <label htmlFor="password" className="block text-gray-700">Password:</label>
            <input
              type="password"
              id="password"
              name="password"
              value={userData.password}
              onChange={handleChangeUser}
              className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring focus:ring-blue-400"
              required
            />
          </div>
        </form>
      </>
    );
  }

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100 flex-col">
      <div className="w-full max-w-md p-6 bg-white rounded-lg shadow-lg">
        <h1 className="text-2xl font-semibold text-center mb-4">
          {isLogin ? "Login" : "Register"}
        </h1>

        {!isLogin ? getMoreDetails() : null}

        <div className="mb-4">
          <label className="block text-gray-700">{isLogin?"Username":"Registerer UserName"}</label>
          <input
            type="text"
            name="username"
            value={formData.username}
            onChange={handleInputChange}
            placeholder="Enter Username"
            className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring focus:ring-blue-400"
          />
        </div>

        <div className="mb-6">
          <label className="block text-gray-700">{isLogin?"Password":"Registerer Password"}</label>
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleInputChange}
            placeholder="Enter Password"
            className="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring focus:ring-blue-400"
          />
        </div>

        <div className="relative flex items-center justify-center mb-4">
          <span
            className={`font-medium mr-3 cursor-pointer ${isPatient ? "text-blue-500" : "text-gray-500"}`}
            onClick={() => setIsPatient(true)}
          >
            Patient
          </span>
          <div
            className="w-14 h-8 flex items-center bg-gray-300 rounded-full p-1 cursor-pointer"
            onClick={() => setIsPatient(!isPatient)}
          >
            <div
              className={`w-6 h-6 bg-blue-500 rounded-full shadow-md transform transition-transform duration-300 ${isPatient ? "translate-x-0" : "translate-x-6"}`}
            ></div>
          </div>
          <span
            className={`font-medium ml-3 cursor-pointer ${!isPatient ? "text-blue-500" : "text-gray-500"}`}
            onClick={() => setIsPatient(false)}
          >
            Client
          </span>
        </div>

        <div className="relative flex items-center justify-center mb-6">
          <span
            className={`font-medium mr-3 cursor-pointer ${isLogin ? "text-blue-500" : "text-gray-500"}`}
            onClick={() => setIsLogin(true)}
          >
            Login
          </span>
          <div
            className="w-14 h-8 flex items-center bg-gray-300 rounded-full p-1 cursor-pointer"
            onClick={() => setIsLogin(!isLogin)}
          >
            <div
              className={`w-6 h-6 bg-blue-500 rounded-full shadow-md transform transition-transform duration-300 ${isLogin ? "translate-x-0" : "translate-x-6"}`}
            ></div>
          </div>
          <span
            className={`font-medium ml-3 cursor-pointer ${!isLogin ? "text-blue-500" : "text-gray-500"}`}
            onClick={() => setIsLogin(false)}
          >
            Register
          </span>
        </div>

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
