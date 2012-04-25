/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.rendering.test.cts;

import java.util.Map;
import java.util.Set;

import org.junit.Test;

import junit.framework.Assert;

public class TestDataGeneratorTest
{
    @Test
    public void findTestPrefixes()
    {
        TestDataGenerator generator = new TestDataGenerator();
        Set<String> prefixes = generator.findTestPrefixes("cts.type", ".*\\.txt");
        Assert.assertEquals(1, prefixes.size());
        Assert.assertEquals("cts/type/test/test1", prefixes.iterator().next());
    }

    @Test
    public void readTestData() throws Exception
    {
        TestDataGenerator generator = new TestDataGenerator();
        Map<String, TestData> data = generator.generateTestData("syntax/1.0", "cts.type", ".*\\.txt");
        Assert.assertEquals(1, data.size());

        Map.Entry<String, TestData> entry = data.entrySet().iterator().next();

        Assert.assertEquals("cts/type/test/test1", entry.getKey());
        TestData testData = entry.getValue();
        String expectedCTSContent =
              "beginDocument\n"
            + "beginParagraph\n"
            + "onWord [test]\n"
            + "endParagraph\n"
            + "endDocument";
        Assert.assertEquals(expectedCTSContent, testData.ctsInput);
        Assert.assertEquals(expectedCTSContent, testData.ctsOutput);
        Assert.assertEquals("test", testData.syntaxInput);
        Assert.assertEquals("test", testData.syntaxOutput);
    }
}
