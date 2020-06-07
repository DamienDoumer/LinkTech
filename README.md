# LinkTech

A simple microservice based "linkedin like" social network with springboot.
Project meant for learning purposes only, 

# Testing the app

The back-end provides nearly all of the functionalities required, but
Since the front end provides just basic functionalities, you could run the front end with: __npm start__
- To test other back-end functionalities, there is a little postman collection with basic api calls, 
- You can use that collection and test the back-end
- The name of the postman collection is __"linktechApiQueries.postman_collection.json"__

# After the application is run successfully, the following user is created

- Email: MarkAngelo@user.com
- Password: password
- __By default this user is an admin__

## To run the back-end With DOCKER
- Enter the back folder
- Run docker-compse up in the back-end directory
- If the jar files are not present, run the build.sh 

## Run the back-end Without Docker
- You should have the jvm installed
- Install mongodb
- Inside every microservice you have a file named: application.properties
- Uncomment the configurations under the comment : "# mongodb configs without docker: "
- Run the build.sh script
- Run the run.sh script
- __Every__ microservice should be started

## Building the application on linux 
- Run the build.sh file in the back-end directory
## On windows, run the bash script on power shell
- Open git bash, navigate to the directory containing the .sh
- run the build.sh file using the command : __sh build.sh__
