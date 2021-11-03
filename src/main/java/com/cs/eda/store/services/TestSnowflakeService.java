package com.cs.eda.store.services;

import com.cs.eda.store.connection.ConnectionUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class TestSnowflakeService {
    public static void main(String[] args) throws Exception {
        testSnowflakeConnection();
    }

    public static void testSnowflakeConnection() throws Exception {
        // get connection
        System.out.println("Create JDBC connection");
        Connection connection = ConnectionUtils.getConnection();
        System.out.println("Done creating JDBC connectionn");
        // create statement
        System.out.println("Create JDBC statement");
        Statement statement = connection.createStatement();
        System.out.println("Done creating JDBC statementn");
        // create a table
        System.out.println("Create demo table");
        statement.executeUpdate("create or replace table demo(C1 STRING)");
        statement.close();
        System.out.println("Done creating demo tablen");
        // insert a row
        System.out.println("Insert 'hello world'");
        statement.executeUpdate("insert into demo values ('hello world')");
        statement.close();
        System.out.println("Done inserting 'hello world'n");
        // query the data
        System.out.println("Query demo");
        ResultSet resultSet = statement.executeQuery("SELECT * FROM demo");
        System.out.println("Metadata:");
        System.out.println("================================");
        // fetch metadata
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        System.out.println("Number of columns=" +
                resultSetMetaData.getColumnCount());
        for (int colIdx = 0; colIdx < resultSetMetaData.getColumnCount();
             colIdx++) {
            System.out.println("Column " + colIdx + ": type=" +
                    resultSetMetaData.getColumnTypeName(colIdx + 1));
        }
        // fetch data
        System.out.println("nData:");
        System.out.println("================================");
        int rowIdx = 0;
        while (resultSet.next()) {
            System.out.println("row " + rowIdx + ", column 0: " +
                    resultSet.getString(1));
        }
        statement.close();
    }
}
