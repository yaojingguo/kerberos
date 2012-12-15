javac SampleServer.java
javac SampleClient.java

java -Djava.security.krb5.realm=EXAMPLE.COM \
  -Djava.security.krb5.kdc=jingguolin.example.com \
  -Djavax.security.auth.useSubjectCredsOnly=false \
  -Djava.security.auth.login.config=bcsLogin.conf \
  SampleServer 4444

java -Djava.security.krb5.realm=EXAMPLE.COM \
  -Djava.security.krb5.kdc=jingguolin.example.com \
  -Djavax.security.auth.useSubjectCredsOnly=false \
  -Djava.security.auth.login.config=bcsLogin.conf \
  SampleClient xiaoyu/play@EXAMPLE.COM jingguolin.example.com 4444
