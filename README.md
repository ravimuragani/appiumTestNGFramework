# appiumTestNGFramework
Appium app TestNG framework for Sauce Lab App

Scenarios Covered:
1.E2E Purchase flow
2.Add and remove products
3.Validations on pages


follow steps to execute project

download project from Git
Build can be run from Cloud (BrowserStack) just update credentials and scripts will run in cloud.

for manual follow below steps.
make sure all dependends are available in machine as below Download Java and set Java_Home in environmental variables Download Android STUDIO from below link https://developer.android.com/studio/index.html Check Android installation path in Machine Set Android_Home Environmental variables path to SDK location and include bin folder paths in PATH variable Open Android Studio and configure Virtual device/Emulator Open Emulator and check if it is working. Download Node.js
https://nodejs.org/en/download/ Set Node_Home Environmental variables path Set npm Environmental variables path Download Appium Server from Node Download Appium and Selenium Java client library Install Eclipse – Create a Project in Eclipse - configure Appium libraries Start Appium Server-
post setup is done project exection can be invoked from CLI or eclipse CLI --> move project folder open cmd line >mvn clean >mvn compile >mvn test Eclipse --> Import project to eclipse select testng file and run as TestNG
build and run from Jenkins
create new jenkins Job set SCM from GIT select build action and set maven command to run select post Build actions for report
