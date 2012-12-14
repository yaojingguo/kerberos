# Without a security manager
java -Djava.security.krb5.realm=EXAMPLE.COM \
  -Djava.security.krb5.kdc=jingguolin.example.com \
  -Djava.security.auth.login.config=jaas.conf \
  JaasAcn

# Will fail
java -Djava.security.krb5.realm=EXAMPLE.COM \
  -Djava.security.krb5.kdc=jingguolin.example.com \
  -Djava.security.auth.login.config=jaas.conf \
  -Djava.security.manager \
  JaasAcn

jar -cvf JaasAcn.jar JaasAcn.class

java -classpath JaasAcn.jar \
-Djava.security.krb5.realm=EXAMPLE.COM \
  -Djava.security.krb5.kdc=jingguolin.example.com \
  -Djava.security.auth.login.config=jaas.conf \
  -Djava.security.policy=jaasacn.policy \
  -Djava.security.manager \
  JaasAcn

