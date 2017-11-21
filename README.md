# graphql-java

Run project
-----
mvn jetty:run


GraphiQL
-----
http://localhost:8080

Query allLinks
-----
```graphql
{
  allLinks {
    url
    description
  }
}
```

Mutation createLink
-----
```graphql
mutation createLink {
  createLink(url: "https://www.graph.cool/", description: "Serverless GraphQL Backend") {
    url
    description
  }
}
```

Mutation createUser
-----
```graphql
mutation createUser {
  createUser(name: "BoJack Horseman", authProvider: {email: "bojack@example.com", password: "secret"}) {
    id
    name
  }
}
```