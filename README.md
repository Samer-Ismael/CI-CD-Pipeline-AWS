# Cat Database

Welcome to the Cat Database app! This project is a CD/CI Pipeline in Java, consisting of an API with CRUD endpoints for a "Cat" entity class connected to a MySQL database.

## Features

- CRUD operations for managing cat data
- API endpoints for interacting with cat entities
- Integration with AWS Elastic Beanstalk for deployment
- Automated deployment using AWS CodePipeline
- Utilization of AWS RDS for database storage
- Frontend app built with Vaadin, allowing for web app development in Java

## Usage

To use this API, follow these steps:

1. Start the frontend app on your local machine or open your browser to `localhost:8080`.
2. Use the provided endpoints to add, edit, or delete cats from the database.
3. Retrieve a list of all cats from the database using the appropriate endpoint.
4. Explore the API documentation using Swagger by clicking om API documentation button.

## Deployment

The server is deployed on AWS Elastic Beanstalk within a CodePipeline. Pushing new code to the main branch in the GitHub repository will trigger automatic updates to the deployed server. This is achieved through a `buildspec.yml` file, which contains instructions for AWS to execute, including the Maven build command and running unit tests.

## Security

Sensitive database information is stored as environment variables, providing a safer alternative to embedding database credentials directly into the application properties.

## Frontend

The frontend of the application is built using Vaadin, enabling developers to create web apps using only Java. Upon starting the app, users are presented with options to add, edit, or delete cats from the database. The frontend communicates with the backend via a backend link stored in the environment variables.

Additionally, the frontend features a health check mechanism that continuously monitors the server's status. If any issues arise or if the backend is inaccessible, the frontend notifies the user through notifications.

## Contributors

- Samer Ismael

## License

MIT License

Copyright (c) 2024 Samer Ismael

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
