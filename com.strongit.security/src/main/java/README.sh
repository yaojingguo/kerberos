javac *.java

jar -cvf Callee.jar Callee*.class
jar -cvf Caller.jar Caller*.class

java -cp Caller.jar:Callee.jar \
  -Djava.security.manager \
  -Djava.security.policy=stack.policy \
  Caller

java -Djava.security.auth.login.config=login.config \
  Login
