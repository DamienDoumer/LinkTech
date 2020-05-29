# After the application is run successfully, the following user is created
- Email: MarkAngelo@user.com
- Password: password
- __By default this user is an admin__

# To run the back-end
- Enter the back folder
- Run docker-compse up
- If the jar files are not present, run the build.sh file which will create the jar files.
- Copy the docker files in the target forlder and change their names to Dockerfile

# IF and only if the .jar files are not present in the target folder of the different services, Do these: 

## Building the application on linux 
- Run the build.sh file in the back-end directory
## On windows, run the bash script on power shell
- Open git bash, navigate to the directory containing the .sh
- run the build.sh file using the command : __sh build.sh__
