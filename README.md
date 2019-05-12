# bank-system
2019 QMUL EBU5304 Software Engineering Case Study : An example of applying Agile methods to the software development

## Description

A Java software used to manage the bank system. An assignment from QMUL-EBU5304 to practice Scrum.

## Features

* Stable setup platform and easily Installation	

  - [x] Three Main class correspond to three types of software
    - [x] Easily test and add function pages
  - [x] `Setup.bat` for Windows version

* Appropriate swing GUI layout

  - [x] Main control and refresh layout

* Function Pages

  - [x] Default Layer page 

  - [x] User pages
    - [x] Login page
    - [x] Register page
    - [x] Money page
    


* Replaceable Data Access Objects (Dao) with high Robustness

  - [x] BaseDao
  - [x] Entity
    - [x] Account
    - [x] CurrentAccount
    - [x] JuniorAccount
    - [x] SaverAccount
    - [x] Customer
  - [x] Dao
    - [x] AccountDao
    - [x] CurrentAccountDao
    - [x] JuniorAccountDao
    - [x] SaverAccountDao
  - [x] DaoImpl
    - [x] AccountDaoImpl
    - [x] CurrentAccountDaoImpl
    - [x] JuniorAccountDaoImpl
    - [x] SaverAccountDaoImpl

## Launch Instruction

You can just double-click the file `bank-system.jar` to run this program (With JVM). And then operate with the GUI.

## Install Instruction

Double click the `Setup.bat` in order to install our software. Then You can find the `BikeShareSystem-*.jar` in your installation path.

We can also use the command-line interface to install this program everywhere you want with source files (`*.java` and `MANIFEST-*.MF`).

1. Open Command Line (CMD) for Windows or Terminal Navigation Commands for MacOS.

2. Enter your file path with `cd \?`.

3. And then input the following commands:

   For Admin-type:

   ```powershell
   javac -d bin -classpath bin .\src\application\*.java .\src\layout\*.java
   jar cfm BikeShareSystem-Admin.jar .\MANIFEST\MANIFEST-ADMIN.MF -C bin .
   java -jar BikeShareSystem-Admin.jar
   ```
   
   or for User-type:

   ```powershell
   javac -d bin -classpath bin .\src\application\*.java .\src\layout\*.java
   jar cfm BikeShareSystem-User.jar .\MANIFEST\MANIFEST-USER.MF -C bin .
   java -jar BikeShareSystem-User.jar
   ```
   
   or for Station-type:

   ```powershell
   javac -d bin -classpath bin .\src\application\*.java .\src\layout\*.java
   jar cfm BikeShareSystem-Station.jar .\MANIFEST\MANIFEST-STATION.MF -C bin .
   java -jar BikeShareSystem-Station.jar
   ```

4. You can find the `BikeShareSystem-*.jar` in your installation path.

## Get Help

Thank you for using this application. If you have any problems, don't hesitate and contact me with following e-mail:

- Github: https://github.com/zolars/BikeShareSystem
- E-mail: xinyf_bupt@outlook.com

Besides, I hope you can give valuable advice back to me. It's really helpful for me to improve this application.

