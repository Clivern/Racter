/*
 * Copyright (c) 2017 Clivern. <https://clivern.com>.
 */

package com.clivern.racter;

import junit.framework.TestCase;

public class BotPlatformTest extends TestCase {

    public void testGetName()
    {
        BotPlatform bp = new BotPlatform();
        assertEquals(bp.getName(), "Racter");
    }
}