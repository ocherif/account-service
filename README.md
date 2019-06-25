# account-service
The application offers several services related to a user account.

* SSL configuration :

```
1- %JAVA_HOME%\bin\keytool -genkeypair -alias account-service-cert -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore account-service-cert.p12 -validity 3650
=> keypass = password
2- %JAVA_HOME%\bin\keytool -exportcert -keystore account-service-cert.p12 -storetype PKCS12 -storepass password -alias account-service-cert -file account-service-cert.crt
=> kypass = password
3- %JAVA_HOME%\bin\keytool -import -alias account-service-cert -keystore  %JAVA_HOME%\jre\lib\security\cacerts -file account-service-cert.crt
=> keypass = changeit
```