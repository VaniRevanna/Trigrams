# Trigrams
Mutate text into new with Trigrams 
http://codekata.com/kata/kata14-tom-swift-under-the-milkwood/

# Development Approach
 Followed test driven development
 to start with wrtiting testcases which is defined under src/java/test folder test file
 
# preRequisits 

1) Install java 1.8 jdk
2) Maven 

# Comilation and instalation
Start with go inside trigram folder
1) run : mvn clean install
     to clean to down load and build the project
	 
2) project comprise of two file Genrator class and TrigramVO under "com.interview.trigram" package.
Genrator class as actual Implemnattion of Trigram algorithm
TrigramVO data structure to store Trigram

3) Created one Small invocation application TriGramApplication under "com.interview.trigram.app" which has invocation of TrigramAlgoorith with sample Data

4) to run the test cases : mvn test

