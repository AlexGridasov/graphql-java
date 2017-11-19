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