# Drone-fleet-service

Following is the Api Documentation for the application
https://github.com/Erandauh/drone-fleet-service/blob/main/dronefleetservice/DroneFleetService.yaml

You can load it in to https://editor.swagger.io/
![image](https://user-images.githubusercontent.com/7019484/186344089-22a6ed98-593d-4709-a2c6-ce598c610d0b.png)

Or if you have the IDE supported for standard Open Api spec 3.0, you can use that as well
![image](https://user-images.githubusercontent.com/7019484/186344542-c98d8226-2869-4e77-bac8-8e30f5290dc3.png)

Once you cloned a copy of the project, you can use the standard gradlew plugin to build it
"./gradlew build"

![image](https://user-images.githubusercontent.com/7019484/186345081-c3d337cb-a8ba-49cf-8252-6dcb8e64abfb.png)

Once you started the application, it will open in the standard port (localhost:8080)
![image](https://user-images.githubusercontent.com/7019484/186345631-cf70f030-c98e-4bd9-8c81-c03ec9fcf6b1.png)

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




