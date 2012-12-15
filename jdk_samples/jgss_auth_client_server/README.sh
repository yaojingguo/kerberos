# Server
javac Login.java SampleServer.java
jar -cvf Login.jar Login.class MyAction.class
jar -cvf SampleServer.jar SampleServer.class

java -classpath Login.jar:SampleServer.jar \
  -Djava.security.manager \
  -Djava.security.krb5.realm=EXAMPLE.COM \
  -Djava.security.krb5.kdc=jingguolin.example.com \
  -Djava.security.policy=server.policy \
  -Djava.security.auth.login.config=csLogin.conf \
  Login SampleServer 4444

# Client
javac Login.java SampleClient.java
jar -cvf Login.jar Login.class MyAction.class
jar -cvf SampleClient.jar SampleClient.class

java -classpath Login.jar:SampleClient.jar \
  -Djava.security.manager \
  -Djava.security.krb5.realm=EXAMPLE.COM \
  -Djava.security.krb5.kdc=jingguolin.example.com \
  -Djava.security.policy=client.policy \
  -Djava.security.auth.login.config=csLogin.conf \
  Login SampleClient xiaoyu/play@EXAMPLE.COM jingguolin.example.com 4444
