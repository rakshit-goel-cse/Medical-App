import Cookies from 'js-cookie';

export const validateJWT = async () => {
    const token = Cookies.get('token');
    //console.log("token- "+token);
    if (!token) {
        console.error('No token found in cookies');
        return false;
    }

    const url = new URL('http://localhost:8088/user/validateJWT');
    url.searchParams.append('jwt-token', token);

    try {
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
        });
        console.info(response);
        if (response.ok) {
            console.info('Token validation passed');
            return true;
        }

        /*const data = await response.json();
        if (data.username) {
            const userCookie = Cookies.get('user');
            if(userCookie){
                const user = JSON.parse(userCookie);
                if(user && user.userName==data.username){
                    console.log('Token is valid:', data.username);
                    return true;
                }
            }
        }
        console.error('Token validation failed: No username in response');*/
        return false;
    } catch (error) {
        console.error('There was a problem with the token validation request:', error);
        return false;
    }
};