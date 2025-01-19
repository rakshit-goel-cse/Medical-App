import logo from './logo.svg';
import './App.css';
import FrontPage from './components/login_page/FrontPage';
import { useEffect, useState } from 'react';
import MainPage from './components/MainPage/mainPage';
import { validateJWT } from './components/login_page/validateJWT';

function App() {

  const [isLogedIn,setLogedIn]=useState(false);

  useEffect(() => {
    const checkToken = async () => {
      const isValid = await validateJWT();
      if (isValid) {
        setLogedIn(true);
      }
    };

    checkToken();
  }, []);

  return (
    isLogedIn
    ?<MainPage setLogedIn={setLogedIn}/>
    :<FrontPage setLogedIn={setLogedIn}/>
  );
}

export default App;
