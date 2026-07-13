# Cleanup (defensive).
rm -f *.class 2>/dev/null

# Compile and run.
javac Tp3.java
java Tp3 data/carte3.txt test_output.txt

# Cleanup.
rm *.class