/*
 * Copyright (c) 2017 Clivern. <https://clivern.com>.
 */

package com.clivern.racter;

import junit.framework.TestCase;
import java.io.IOException;

public class BotPlatformTest extends TestCase {

    public void testGetName() throws IOException
    {
        BotPlatform bp = new BotPlatform("src/main/java/resources/test_config.properties");
        assertEquals(bp.getName(), "Racter");
    }
}