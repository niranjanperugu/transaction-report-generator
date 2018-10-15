# transaction-report-generator

Customer Transaction Reports


### Prerequisites

Install the following software's

```
Java, Maven, Git, AngularJs(libreries)
```

### Pull the project from git

Open git and enter following command

```
git clone https://github.com/niranjanperugu/transaction-report-generator.git
```

### Building Application

Navigate to pom.xml location and enter the following command to build application

Open Cmd / run mvn build form eclipse

```
mvn clean install
```

## Deployment

Deploy the application using embaded tomcat 

### Steps

* Navigate to **transaction-report-generator/target**
* Enter the following command deploy the application 
* 
```
java -jar application-name.war
```

## Steps to run application

* Open browser and enter [localhost:8080](localhost:8080)

#### Transaction Report

* Upload a valid XML or CSV
* Click browse and select xml or csv file
* Click upload button
* Displays the Valid Transactions and Invalid Transactions

#### CSV Report

* Upload a valid CSV
* Click browse and select a valid csv file
* Click upload button
* Upon uploading displays the date in table
* Click on header to sort based on column



## Authors

* **Niranjan Babu** - *Initial work* - [Git Repository](https://github.com/niranjanperugu/transaction-report-generator)
* **Niranjan Babu** - *Portfolio Website* - [www.niranjanbabu.tk](www.niranjanbabu.tk)



