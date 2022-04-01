1. Download XAMPP Control Panel
2. Start apache + mySQL
3. press Admin on mysql
4. Add the Coursework.sql file in import.
5. Open standalone-full.xml file for the wildfly server.
6. Add following code under the Programming enterprise </datasource>
7. Name the SQL database  - Coursework
8. Do the tutorial for program enterprise week 4 "Accessing the DB" to setup the server and make prototype work.
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