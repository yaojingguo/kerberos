javac Login.java Sample.java

jar -cvf Login.jar Login.class MyAction.class
jar -cvf Sample.jar Sample.class

java -classpath Login.jar:Sample.jar \
  -Djava.security.manager \
  -Djava.security.krb5.realm=EXAMPLE.COM \
  -Djava.security.krb5.kdc=jingguolin.example.com \
  -Djava.security.policy=sample.policy \
  -Djava.security.auth.login.config=sample.conf \
  Login Sample
