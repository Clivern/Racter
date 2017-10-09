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
	 * Test Set and Get Config
	 */
    public void testGetConfig()
    {
        Config.instance().set("app_name", "Racter");
        assertEquals(Config.instance().get("app_name", ""), "Racter");
    }

    /**
     * Test Store Prop. File
     *
     * @throws IOException
     */
    public void testStorePropFile() throws IOException
    {
    	Config.instance().set("app_name", "Racter Changed");
    	Config.instance().storePropertiesFile("src/main/java/resources/test_config.properties");
    }

    /**
     * Test Load Prop. File
     *
     * @throws IOException
     */
    public void testLoadPropFile() throws IOException
    {
    	Config.instance().loadPropertiesFile("src/main/java/resources/test_config.properties");
    	assertEquals(Config.instance().get("app_name", ""), "Racter Changed");
    }
}