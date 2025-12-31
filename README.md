# Main milestones to achieve

- [x] TEST current connection with redis

- [x] GraphQL integration

## Last Run-down

- [x] Clean all the code

- [x] Rewrite the new README.md file

- [ ] Screencast

---

# Java for enterprise applications: Assignment 2

*Author:* André Martins  
*ID:* 0230991223  

**External links:**

- Screencast: [Youtube](https://somelink)
- Repository: [GitHub](https://somelink)

## Project Design and flaws

**Project design:**

The small graphql back-end server as been implemented inside a Spring-boot application as uses a Redis Database server to store all persistent data.  
Additionally, the Spring-boot application server also provides a graphical UI to test the GraphQL server as well as to represent all possible queries, mutations and types available in the several endpoints.

To complement this project, a `docker-compose.yml` file as been implemented that provides a Redis database implementation together with a graphical framework that allows to visualize the current state of the DB.

**Project flaws:**

The project as only implemented the required features to the respective assignment, not all features have been implemented in order to have a complete tweeter like application, like providing all CRUD operations for all entities. This decision as been made since I've preferred to concentrate more on how to use these new tool and provide the best implementation possible instead of trying to provide a complete application that would not have been implemented in the right way.

Additionally, another technical flaw for this project is related with the relationship with Feedbacks/Tweets and User in the GraphQL side. Currently only a reference of the element is shown witch goes a little bit against the power and goal of GraphQL where the client defines what he wants to see and we is not under-fetched or over-fetched by any ways. I've decided to keep the API in this way since in order to solve this issue I would need to implement a simple lookup in the service side in order to give use it in the resolvers. The consequence of this implementation in a real-world implementation would be that we could easily overload the server (even if we could use the power of the keys indexing in the redis database) and so I've decided to store the user key as well as the username (technically information that will never change during the lifetime of the user) of the user and only allowing this information to be returned to the client.


## How to run the project

**Running both docker containers**

The first part of running this project in your local machine is to run your both docker containers that are defined in the `docker-compose.yml` file. To do so, you shall run the following command in the root of this project:

```bash
docker compose up -d
```

Once both containers are up and running, a Redis database instance will be available in the port `6379` and you will be able to access the redis insight UI at `http://localhost:5540`.

**Running the gradle based Spring application**

The second part of having this project fully running is to run the Spring application. In order to run the application, it is as simple as running the following commands in the root of this project:

```bash
# For Windows
.\gradlew.bat bootRun
# Linux
gradle bootRun
```

Once the project is up and running, you will be able to directly communicate via the following ports:

- Web interface: `http://localhost:8080/graphiql`

- GraphQL endpoint: `http://localhost:8080/graphql`