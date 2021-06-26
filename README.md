## Character Management Service
1.  Place all the test files under folder structure: "src/main/resources/".
2. Run maven install.
3. To run the application jar, go to project directory,

> java -jar target\extract-0.0.1-SNAPSHOT.jar

## Postman Testing
1. Import postman collection and choose the file with which you want to test with.
> src/main/resources/Character-Management.postman_collection.json

2. Alternatively, select GET as a HTTP method, use below URL along with it.
   http://localhost:9090/extract/numerics
   Select "Body" then "form-data".
   Provide key as a "file" and select the type of input as a File.
   For value, browse the reosponse received.

## Problem statement
Java (CES)

We need to develop a rest service in Java which is responsible to extract all numbers from the input text. Next to that,
we would like to know on what line and on what position on that line the result was found. Please note accuracy in
extraction of numbers is not critical here.

Evalutation Criteria
The input document for the rest service can be plain text or html or xml content.
Input size can very from KB to MB, If you want to implement things in a better way but do not have time to do
so, please describe the changes in comments.
The output needs to be json formatted, please design your own input and output payloads.
Please provide a complete (and production-ready) solution, including (but not limited to) a README file with
instructions on how to use.
If there are any unclear requirements, or if you need to make assumptions, please specify them as code
comments or in the README file.

Additional
What would you recommend if a Number can be represented as a word ("Seven") or in numeric format (7)?
What would you advise to implement further if this needs to be deployed in a production environment?
We expect you to no spend more than 4 - 6 hours on this. Please send back your comlete source code, and any
instructions to build your project.