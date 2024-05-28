CPATH='.:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar'

rm -rf student-submission
rm -rf grading-area

mkdir grading-area

# Step 1: clone the students repo
git clone $1 student-submission
echo 'Finished cloning'

# Step 2: check student code contains ListExamples.java
if [[ -f student-submission/ListExamples.java ]]; then 
    echo "ListExamples.java Exists"
else
    echo "ListExamples.java does not exist"
    echo "Grade: 0"
    exit 
fi 

# Step 3: Put all the relevant files in the grading-area directory. 
# ListExamples.java, TestListExamples.java, lib directory
cp TestListExamples.java grading-area/  # Ensure this is the correct reference
cp student-submission/ListExamples.java grading-area/
cp -r lib grading-area/


#Step 4: Compile the java files and check that they compiled successfully
cd grading-area
javac -cp $CPATH ListExamples.java TestListExamples.java

echo "The exit code of java is $?"

if [[ $? -ne 0 ]]; then
    echo "Failed to compile"
    exit 1
else
    echo "Compiled successfully"
fi


#Step 5: Run the tests and report the grade based on the JUnit output

# Capture output of JUnit
junit_result=$(java -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar org.junit.runner.JUnitCore TestListExamples)
echo "JUnit Output:"
echo "$junit_result"

# Assuming junit_result contains the output from JUnit
total_tests=$(echo "$junit_result" | grep -o "OK ([0-9]* tests)" | grep -o "[0-9]*")
failures=$(echo "$junit_result" | grep -o "Failures: [0-9]*" | awk '{print $2}')
total_tests=${total_tests:-0}
failures=${failures:-0}

# echo "Total tests parsed: $total_tests"
# echo "Failures parsed: $failures"

if [ "$total_tests" -gt 0 ]; then
    successful_tests=$((total_tests - failures))
    success_rate=$((100 * successful_tests / total_tests))
    echo "Grade: $success_rate%"
else
    echo "Grade: 0%"
fi


    



# Draw a picture/take notes on the directory structure that's set up after
# getting to this point

# Then, add here code to compile and run, and do any post-processing of the
# tests