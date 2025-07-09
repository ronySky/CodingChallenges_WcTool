
# Coding challenge 1:  Build Your Own wc Tool

from
https://codingchallenges.fyi/challenges/challenge-wc/

Starting the coding challenges with things that are already in my wheelhouse, Java with Maven. The idea is to grow out of my comfort zone a bit with each challenge.

# Install and run

## Build
We are using Apache Maven to build and package our project. You can install using the instructions on the [installation page](https://maven.apache.org/), but I highly recommend using [SDKMAN!](https://sdkman.io/) to manage your JDKs and Tools. 
After you have Maven installed, go to the project folder (WcTool) and run:
```bash
mvn clean install
```
This will compile the project, run the unit tests, and package everything in a jar file. This process only needs to be done once, unless you make any changes to the code. After this a ```target/WcTool.jar``` file should have been created.  

## Config
Instead of running the jar we have just created, we will use the ```ccwc``` script as required in the challenge description. To make sure we are able to run it, execute the following command:
```bash
chmod -x ccwc
```
This also needs to be done only once. We could add this to ```$PATH``` to make the command usable from any directory, but that's outside our current scope. 

## Run
### Run on a test file
Run the following commands in a terminal to check its functionality:

- Without any flags:
```bash 
./ccwc src/test/resources/test.txt
  7145    58164   342190  src/test/resources/test.txt
```

- Using flags for specific counts
```bash 
./ccwc -l src/test/resources/test.txt
  7145 src/test/resources/test.txt
```

- Reading from ```stdin```:
```bash 
cat src/test/resources/test.txt | ./ccwc -w
  58164
```


# Final remark
This was a fun challenge, and I was happy to write it only using the Standard Java Libraries, but parsing varargs can be a pain, and unless you're learning/practicing, I recommend using [Picocli](https://picocli.info/) when you need to turn Java code into a CLI.