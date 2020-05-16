/*
 * Copyright (C) 2020 Clivern <http://clivern.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.clivern.racter.utils;

import java.io.IOException;
import junit.framework.TestCase;

/** Config Class Test Cases */
public class ConfigTest extends TestCase {

    /** @var Config an instance of config class */
    protected Config configs;

    /**
     * Class Constructor
     *
     * @return void
     */
    public ConfigTest() {
        this.configs = new Config();
    }

    /** Test Set and Get Config */
    public void testGetConfig() {
        this.configs.set("app_name", "Racter");
        assertEquals(this.configs.get("app_name", ""), "Racter");
    }

    /**
     * Test Store Prop. File
     *
     * @throws IOException
     */
    public void testStorePropFile() throws IOException {
        this.configs.set("app_name", "Racter Changed");
        this.configs.storePropertiesFile("src/main/java/resources/test_config.properties");
    }

    /**
     * Test Load Prop. File
     *
     * @throws IOException
     */
    public void testLoadPropFile() throws IOException {
        this.configs.loadPropertiesFile("src/main/java/resources/test_config.properties");
        assertEquals(this.configs.get("app_name", ""), "Racter Changed");
    }
}
