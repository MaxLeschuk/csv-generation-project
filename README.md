# csv-generation-project
class AuthService {
  login(name, pass) {
    const querystring = require('querystring');
    return axios
      .post("http://localhost:8180/auth/realms/AudioServiceKeycloak/protocol/openid-connect/token",  
      querystring.stringify({
        client_id: 'my_client',
        username: name,
        password: pass,
        grant_type:'password'
}), {
  headers: { 
    "Content-Type": "application/x-www-form-urlencoded"
  }
})
      .then(response => {
        if (response.data.token) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }

        return response.data;
      });
  }
