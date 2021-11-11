const { ApolloServer, defaultPlaygroundOptions } = require("apollo-server-express");
const { ApolloGateway, RemoteGraphQLDataSource  } = require("@apollo/gateway");

const express = require('express')
const { configureKeycloak } = require('./lib/common');
const { KeycloakContext, auth } = require('keycloak-connect-graphql');
const expressPlayground = require('graphql-playground-middleware-express').default;
const { graphql } = require("graphql");

const app = express();
const graphqlPath = '/graphql';


const gateway = new ApolloGateway({
  // This entire `serviceList` is optional when running in managed federation
  // mode, using Apollo Graph Manager as the source of truth.  In production,
  // using a single source of truth to compose a schema is recommended and
  // prevents composition failures at runtime using schema validation using
  // real usage-based metrics.
  serviceList: [
    { name: "management", url: "http://localhost:8082/management/graphql" },
 
  ],
  buildService({ name, url }) {
    return new RemoteGraphQLDataSource({
      url,
      willSendRequest({ request, context }) {
      /*  const bearer = 'eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJwU0laV0ItUEYtZjZQbjJham43RVE5SDk2alB6UVBuUTFYMWQ0UVpRTTg4In0.eyJleHAiOjE2MzY2MjA1MjMsImlhdCI6MTYzNjYyMDIyMywiYXV0aF90aW1lIjoxNjM2NjIwMjIzLCJqdGkiOiI2ZjlmOTc5Ni1lNjhiLTQwMjItYmI4YS0zMGQ2NzEyZjcxMzQiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgxODAvYXV0aC9yZWFsbXMvbXlfcmVhbG0iLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiZTVhMTlkZDYtYjkzYy00MzNjLTk4YzktZjM1MGI4YjhmMzhjIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiYXBvbGxvX2dhdGV3YXlfY2xpZW50Iiwic2Vzc2lvbl9zdGF0ZSI6Ijc4NjU5YTYyLWM4OWUtNGI4Mi05ZmE3LWVkNDc1NzMzYWNkNiIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiLyoiLCJodHRwOi8vbG9jYWxob3N0OjQwMDAiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImRlZmF1bHQtcm9sZXMtbXlyZWFsbSIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iLCJVU0VSIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJvcGVuaWQgcHJvZmlsZSBlbWFpbCIsInNpZCI6Ijc4NjU5YTYyLWM4OWUtNGI4Mi05ZmE3LWVkNDc1NzMzYWNkNiIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwibmFtZSI6Im1hZCBtYXgiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJtYWRtYXgiLCJnaXZlbl9uYW1lIjoibWFkIiwiZmFtaWx5X25hbWUiOiJtYXgiLCJlbWFpbCI6Im1ha3NpbV9saWFzaGNodWtAZXBhbS5jb20ifQ.j3MRH84z8EJX5_-NZxX_jMh-P6Zsefr2f6LvJGAUkhkD3hpZjDamCd12cBzXp8QJ5EjZr1Po1tqRSdwRnVpBWd3dNvD4P4e_Dvz8B5Fj89T9BwihmO4sIcCilVT4iJSs-umUNEbfAnCBHKNwXJ0m_q2n89dj_G4W7_HJ4XE2cXplxF-PfVie7c5C7lZkDZMdPpavMC-RIVbQihQ6jhu_wL4bJB1v_pgEWSt85UWf6mrCpa6_1OcdwTXxnWTasqv2-OdusR3Jb55atr3DBMpmf1f7RD5c3T8ZJ-Z1Mbxm1EyPQI-Jsjrqjnp-7-FAoP9JyA2QhmyAIf8vwF9t8miKlg'
        console.log(request.http.headers)
        request.http.headers.set('Authorization', 'bearer '+ bearer);
        */
        // Passing Keycloak Access Token to services
        if (context.kauth && context.kauth.accessToken) {
          request.http.headers.set('Authorization', 'bearer '+ context.kauth.accessToken.token);
        }
      }
    })
  },

  // Experimental: Enabling this enables the query plan view in Playground.
  __exposeQueryPlanExperimental: true,
});

(async () => {
  console.log('2')
  // perform the standard keycloak-connect middleware setup on our app
  const { keycloak } = configureKeycloak(app, graphqlPath);
  
  // Ensure entire GraphQL Api can only be accessed by authenticated users
  app.use(graphqlPath, keycloak.protect());

  const server = new ApolloServer({
    gateway,

    // Apollo Graph Manager (previously known as Apollo Engine)
    // When enabled and an `ENGINE_API_KEY` is set in the environment,
    // provides metrics, schema management and trace reporting.
    engine: false,

    // Subscriptions are unsupported but planned for a future Gateway version.
    subscriptions: false,
    context: ({ req }) => {
      return {
        kauth: new KeycloakContext({ req })
      }
    }
  });
  console.log(this.context)
  // Handle custom GraphQL Playground to use dynamics header token from keycloak
  app.get(graphqlPath, (req, res, next) => {
    const bearer = 'Bearer' + ' ' + req.kauth.grant.access_token.token;
    const headers = JSON.stringify({
     'Authorization': bearer,
    });
    console.log("headers")
    console.log(headers)
    expressPlayground({
  
      endpoint: `${graphqlPath}?headers=${encodeURIComponent(headers)}`,
      settings: {
  
        'request.credentials': 'same-origin',
      },
      version: "",
      tabs: ""
    })(req, res, next);
  });
 
  await server.start()
  server.applyMiddleware({ app })
  
  // server.listen().then(({ url }) => {
  //   console.log(`🚀 Server ready at ${url}`);
  // });

  const port = 4000;
  
  app.listen({ port },() => {
    console.log(`🚀 Server ready at http://localhost:${port}${server.graphqlPath}`);
  })

})();