import Cookies from 'js-cookie';

export const handleLogin = async ( userName, passWord ) => {
    try {
        if(!userName && !passWord) return alert("username and password passing issue");
        const url = new URL('http://localhost:8088/user/login');
        url.searchParams.append('user-name', userName);
        url.searchParams.append('password', passWord);

        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
            
            
        });
        console.log(response);
        if (!response.ok) {
            return false;
        }

        const data = await response.json();
        console.log('Login successful:', data);
        Cookies.set('token', data.token); // Save the token in cookies
        Cookies.set('user', JSON.stringify(data.user)); // Save the token in cookies

        return true;

    } catch (error) {
        console.error('There was a problem with the login request:', error);
    }
};