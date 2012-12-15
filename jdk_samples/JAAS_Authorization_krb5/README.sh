javac SampleAction.java JaasAzn.java
jar -cvf JaasAzn.jar JaasAzn.class
jar -cvf SampleAction.jar SampleAction.class

java -classpath JaasAzn.jar:SampleAction.jar \
  -Djava.security.manager \
  -Djava.security.krb5.realm=EXAMPLE.COM \
  -Djava.security.krb5.kdc=jingguolin.example.com \
  -Djava.security.policy=jaasazn.policy \
  -Djava.security.auth.login.config=jaas.conf JaasAzn
