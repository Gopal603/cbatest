# cbatest

About Framework:

Datadriven test approach has been taken where test case number, test case description, http method, URL, Input request (if any), expected output response and expected response http code is fed to the framework via datadrivensheet.csv.

At run time, whole response file content will be compared to decide the test result. If http response code and response are matched then result is Pass else Fail. This result is written to an testresult.csv file which exists in target folder along with the response received at run time. This can be used for test debugging in case of failure scenario. This is also configured to download in the GitHub actions.

Sample target folder is also checked-in for reference purpose only.

How to Run:

From IDE:
Clone the repository -> open the "test" project in IDE -> test.testCBA

From command line:
Clone the repository -> cd "test" -> java -classpath bin:resources test.testCBA


Next Steps:
1. Ignoring elements of the response which are dynamic.
2. Multithreading of the tests/parallel execution.
3. Grouping of multiple steps into test case.




