const axios = require('axios');
async function getToken() {
  const querystring = require('querystring');
  return axios
    .post("http://localhost:8180/auth/realms/my_realm/protocol/openid-connect/token",
      querystring.stringify({
        client_id: 'apollo_gateway_client',
        username: 'madmax',
        password: 'madmax',
        grant_type: 'password'
      }), {
      headers: {
        "Content-Type": "application/x-www-form-urlencoded"
      }
    });

};



module.exports = {
  getToken
}