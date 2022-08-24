# Drone-fleet-service

Following are the keypoints
 - API Spec first development approach was taken
 - Java version 11/Gradle as build tool
 - All functionalities are covered with unit tests(Junit 5)
 - TDD approach is taken to the Service layer
 - Lombok(https://projectlombok.org/) is used to eliminate boileplate code -Note:Please enable the IDE support for lombok before running the code
 - No Git branching model was considered as this is just a test application (All commits in the main branch)

From Application point of view
 - Audit entries are written to the DB only if drone battery status change occured
 - Assumption : Once loaded a drone, its state is considered LOADED regardless capacity been achived
 - Image storage is in temp dir for now (Ideally has to be s3 or somewhere for storing the images)
 - All validations listed in the requirment doc is been addressed, others are ignored at this stage (please refer the api spec for more details) 
 - Current scheduler runs in 60 second fixed rate frequency, which is configurable via properties file


Following is the Api Documentation for the application
https://github.com/Erandauh/drone-fleet-service/blob/main/dronefleetservice/DroneFleetService.yaml

You can load it in to https://editor.swagger.io/
![image](https://user-images.githubusercontent.com/7019484/186399769-92f47fa6-363e-47b8-b60c-ab354ddeb810.png)

Or if you have the IDE supported for standard Open Api spec 3.0, you can use that as well
![image](https://user-images.githubusercontent.com/7019484/186399616-f297a54b-74a7-4684-92a2-ad8fc1b2f056.png)


Once you cloned a copy of the project, you can use the standard gradlew plugin to build it
"./gradlew build"

Once build completed build artifact will be in the "build\libs" folder
You can run the artifact with following command
java -jar dronefleetservice-0.0.1-SNAPSHOT.jar
![image](https://user-images.githubusercontent.com/7019484/186405535-a3a3f9d3-ab6a-4939-9e79-03cbb09cbb18.png)

Or you can run the application via any IDE

![image](https://user-images.githubusercontent.com/7019484/186345081-c3d337cb-a8ba-49cf-8252-6dcb8e64abfb.png)

Once you started the application, it will open in the standard port (localhost:8080)
![image](https://user-images.githubusercontent.com/7019484/186345631-cf70f030-c98e-4bd9-8c81-c03ec9fcf6b1.png)

Application is preloaded with some Drone data into H2 in-memory database
http://localhost:8080/h2-console/login.jsp?jsessionid=a14f45ea3cbd1c9fcfaf3c8a05a9d5e9
username: sa
password: password
![image](https://user-images.githubusercontent.com/7019484/186404309-e09ede2e-0185-4388-ab01-fc93f1a9f221.png)



Following are the urls based on the requirment specification

# POST localhost:8080/api/v1/drone/register

curl --location --request POST 'localhost:8080/api/v1/drone/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "serialNumber": "12232",
    "model": "Middleweight",
    "weightLimit": 500,
    "batteryCapacity": 100,
    "state": "IDLE"
}'

# GET localhost:8080/api/v1/drone/availability

curl --location --request GET 'localhost:8080/api/v1/drone/availability' \
--data-raw ''

# GET localhost:8080/api/v1/drone/1a/battery

curl --location --request GET 'localhost:8080/api/v1/drone/1a/battery' \
--data-raw ''


# POST localhost:8080/api/v1/drone/1a/medication

curl --location --request POST 'localhost:8080/api/v1/drone/1a/medication' \
--form 'metadata="{
        \"code\": \"1D\",
        \"name\": \"Amoxil\",
        \"weight\": 50        
    }";type=application/json' \
--form 'content=@"/C:/Users/ehoranagam/OneDrive - Wiley/Desktop/download (1).jpg"'

You can use the following to upload as bulk. <B>Please note that image upload is not supported this way, Only metadata can be uploaded as bulk</B>

curl --location --request POST 'localhost:8080/api/v1/drone/1a/medication' \
--header 'Content-Type: application/json' \
--data-raw '[{
    "code": "1D",
    "name": "Amoxil",
    "weight": 5
},
{
    "code": "2D",
    "name": "Pencilin",
    "weight": 5
}
]'

# GET localhost:8080/api/v1/drone/availability

curl --location --request GET 'localhost:8080/api/v1/drone/availability' \
--data-raw ''

# GET localhost:8080/api/v1/medication/1D/image

curl --location --request GET 'localhost:8080/api/v1/medication/1D/image' \
--form 'metadata="{
        \"code\": \"1D\",
        \"name\": \"Amoxil\",
        \"weight\": 50        
    }";type=application/json' \
--form 'content=@"/C:/Users/ehoranagam/OneDrive - Wiley/Desktop/119666217_10223411612415357_1058103639898233472_n.jpg"'


# Few screen shots of working application
![image](https://user-images.githubusercontent.com/7019484/186402352-029c4ba0-e0db-443e-904c-8aafaddf11a8.png)

![image](https://user-images.githubusercontent.com/7019484/186403052-a927fdfc-97ed-4eb3-8db4-521d55f32e67.png)

![image](https://user-images.githubusercontent.com/7019484/186403595-cea4491f-5342-4aeb-b5ed-f843864938b8.png)


# All core functionality is covered with unit tests
![image](https://user-images.githubusercontent.com/7019484/186404753-49f66363-ee2f-43da-8a0e-7022a5160c17.png)
![image](https://user-images.githubusercontent.com/7019484/186404811-70e8d2e1-4a2a-456f-a87e-366504593484.png)

# You will see the Audit log entries from the application scheduler in console(These are written to a DB table as well)
![image](https://user-images.githubusercontent.com/7019484/186406020-f1e68b90-a3a3-434f-bc7a-5baa59952710.png)



