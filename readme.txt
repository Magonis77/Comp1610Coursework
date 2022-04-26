1. Download XAMPP Control Panel
2. Start apache + mySQL
3. press Admin on mysql
4. create database named coursework 
5. Add the Coursework.sql file in import.
6. Open standalone-full.xml file for the wildfly server.
7. Add following code under the Programming enterprise </datasource>
:
:
Add this:
<datasource jndi-name="java:/MySqlDS" pool-name="MySqlDS">
                    <connection-url>jdbc:mysql://localhost/Coursework</connection-url>
                    <driver-class>com.mysql.jdbc.Driver</driver-class>
                    <driver>mysql-connector.jar</driver>
                    <security>
                        <user-name>root</user-name>
                    </security>
                    <validation>
                        <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"/>
                        <validate-on-match>true</validate-on-match>
                        <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"/>
                    </validation>

8. Setup a WildFly server and make sure to choose the updated standalone-full.xml

9. If you have done it right you can now run the application.
