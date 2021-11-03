package com.cs.eda.store.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionUtils {

    public static Connection getConnection() throws Exception {

        Class.forName("net.snowflake.client.jdbc.SnowflakeDriver");
        Properties properties = new Properties();
        properties.put("user", getNonNullSystemProperty("user"));
        properties.put("password", getNonNullSystemProperty("password"));
        properties.put("account", getNonNullSystemProperty("account"));
        properties.put("db", getNonNullSystemProperty("db"));
        properties.put("schema", getNonNullSystemProperty("schema"));

        String connectStr = "jdbc:snowflake://" + getNonNullSystemProperty("account") + ".snowflakecomputing.com";
        return DriverManager.getConnection(connectStr, properties);
    }

    private static String getNonNullSystemProperty(String propName) {
        String propertyValue = System.getProperty(propName);
        if (propertyValue == null) {
            throw new RuntimeException("Snowflake connection details can not be null");
        }
        return propertyValue;
    }
}
