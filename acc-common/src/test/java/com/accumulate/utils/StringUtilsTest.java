package com.accumulate.utils;

import static junit.framework.Assert.*;
import org.junit.Test;

/**
 * 字符工具测试
 * Created by tjwang on 2017/1/16.
 */
public class StringUtilsTest {

    @Test
    public void testFormatNumStr() {
        int n = 0;
        int len = 4;
        String str;

        n = 1;
        str = StringUtils.formatNumStr(n, len);
        assertEquals("0001", str);

        n = 10;
        str = StringUtils.formatNumStr(n, len);
        assertEquals("0010", str);

        n = 100;
        str = StringUtils.formatNumStr(n, len);
        assertEquals("0100", str);

        n = 1000;
        str = StringUtils.formatNumStr(n, len);
        assertEquals("1000", str);

        n = 10000;
        str = StringUtils.formatNumStr(n, len);
        assertEquals("10000", str);
    }

}
