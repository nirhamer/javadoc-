##How to go to the commandline
* press Alt+F12

##How To Test
1. Run the build cycle: ```mvn clean package```
2. Mutation coverage testing: ```mvn org.pitest:pitest-maven:mutationCoverage```
* you can combine: ```mvn clean package org.pitest:pitest-maven:mutationCoverage```

```
1) int java code line /** the commented part */ the end of the line; 
2) int java code line /* the c
commented part */ the end of the line;
3) int java code line ; // /**end the line;