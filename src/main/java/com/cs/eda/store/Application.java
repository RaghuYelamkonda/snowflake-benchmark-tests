package com.cs.eda.store;

import com.cs.eda.store.services.TestSnowflakeService;

public class Application {

    public static void main(String[] args) throws Exception {
        String serviceName = System.getProperty("Application.Service.Name", null);
        if (serviceName == null) {
            TestSnowflakeService.testSnowflakeConnection();
        }
    }
}
