# bank-system
2019 QMUL EBU5304 Software Engineering Case Study : An example of applying Agile methods to the software development

## Description

A Java software used to manage the bank system. An assignment from QMUL-EBU5304 to practice Agile methods.

## Features

* Stable setup platform and easily Installation	

  - [x] Three Main class correspond to three types of software
    - [x] Easily test and add function pages
  - [x] `Setup.bat` for Windows version

* Appropriate swing GUI layout

  - [x] Main control and time refresh drivers

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

Double click the `Setup.bat` in order to install our software. Then You can find the `bank-system.jar` in your installation path.

We can also use the command-line interface to install this program everywhere you want with source files (`*.java` and `MANIFEST-*.MF`).

1. Open Command Line (CMD) for Windows or Terminal Navigation Commands for MacOS.

2. Enter your file path with `cd \?`.

3. And then input the following commands:

   ```powershell
    copy src\main\images\* bin\main\images\

    javac -d bin\main -classpath bin\main .\src\main\application\*.java .\src\main\layout\*.java .\src\main\database\*.java .\src\main\database\entity\*.java .\src\main\database\dao\*.java .\src\main\database\dao\impl\*.java
    java -classpath bin\main application.Init
    jar cfm bank-system.jar .\MANIFEST\MANIFEST.MF -C bin\main .
   ```

4. You can find the `bank-system.jar` in your installation path.

## Get Help

Thank you for using this application. If you have any problems, don't hesitate and contact me with following e-mail:

- Github: github.com/zolars
- E-mail: yif_xin@gmail.com

Besides, I hope you can give valuable advice to me. It's really helpful for me to improve this application.

