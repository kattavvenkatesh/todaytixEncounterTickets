# UIandAPIAutomationFramework

<h4>UI and API Test Framework for EncoreTickets web application in Java language, Selenium (for web automation) and RestAssured (for API automation), along with Maven as a build tool.</h4>

<h4>Overview</h4>

1. This UI test framework uses Java as a tech stack with ‘Selenium’ tool and 'RestAssured' for api
2. Outputs (TestNG report) present in \src\test\resources\outputResult folder

<h4>How to setup</h4>

1. Pull (clone) source code from Git as below command:
2. **git clone** 
3. Then tests can be run as mentioned in the next step.

Note: Git client and Maven are required to setup and run
•	Git Installation
•	Apache Maven Installation

<h4>How to run tests and generate reports</h4>

1. Navigate to project folder and run the below command 
2. **mvn clean test**
3. This command will run all the Test cases 


<h4>Where to find reports</h4>

1. \src\test\resources\outputResult folder

<h4>Jenkins (CI/CD) Integration setup guideline</h4>

1. In your new Jenkins Job:Under Git Integration: pull source code from github: git clone “”
2. Add step: to run maven (build and run goal as 'test')
3. Run with the 'default' tags under Cucumber.options {...} in TestRunner.java mvn test 
4. If needed, XML and HTML reports can be used for further integrations (Jenkins build itself or other integrations; such as Jira, qTest.
  


<h4>Important Note</h4>

1. **OS Compatability** – Developed and tested on **Windows**
2. **Browser Compatability** - Kindly use Chrome Latest Version (102.0.5005.63)
3. **JDK** - 17
4. **Improvements** - 

• Calcaulate the screen resolution after that take the mid point of the screen then hover on the screen to select the Seats
• Improve OS and Brwosers compatability 
