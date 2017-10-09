/*
 * Copyright (c) 2017 Clivern. <https://clivern.com>
 */

package com.clivern.racter.utils;

import junit.framework.TestCase;
import java.io.IOException;

/**
 * Config Class Test Cases
 */
public class ConfigTest extends TestCase {

    /**
     * @var Config an instance of config class
     */
    protected Config configs;

    /**
     * Class Constructor
     *
     * @return void
     */
    public ConfigTest()
    {
        this.configs = new Config();
    }

    /**
     * Test Set and Get Config
     */
    public void testGetConfig()
    {
        this.configs.set("app_name", "Racter");
        assertEquals(this.configs.get("app_name", ""), "Racter");
    }

    /**
     * Test Store Prop. File
     *
     * @throws IOException
     */
    public void testStorePropFile() throws IOException
    {
        this.configs.set("app_name", "Racter Changed");
        this.configs.storePropertiesFile("src/main/java/resources/test_config.properties");
    }

    /**
     * Test Load Prop. File
     *
     * @throws IOException
     */
    public void testLoadPropFile() throws IOException
    {
        this.configs.loadPropertiesFile("src/main/java/resources/test_config.properties");
        assertEquals(this.configs.get("app_name", ""), "Racter Changed");
    }
}