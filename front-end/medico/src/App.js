import logo from './logo.svg';
import './App.css';
import FrontPage from './components/login_page/FrontPage';
import { useState } from 'react';
import MainPage from './components/MainPage/mainPage';

function App() {

  const [isLogedIn,setLogedIn]=useState(false);

  return (
    isLogedIn
    ?<MainPage/>
    :<FrontPage setLogedIn={setLogedIn}/>
  );
}

export default App;
