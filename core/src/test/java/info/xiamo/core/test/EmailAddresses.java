/*
Copyright (c) 2005, Pete Bevin.
<http://markdownj.petebevin.com>

All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are
met:

* Redistributions of source code must retain the above copyright notice,
  this list of conditions and the following disclaimer.

* Redistributions in binary form must reproduce the above copyright
  notice, this list of conditions and the following disclaimer in the
  documentation and/or other materials provided with the distribution.

* Neither the name "Markdown" nor the names of its contributors may
  be used to endorse or promote products derived from this software
  without specific prior written permission.

This software is provided by the copyright holders and contributors "as
is" and any express or implied warranties, including, but not limited
to, the implied warranties of merchantability and fitness for a
particular purpose are disclaimed. In no event shall the copyright owner
or contributors be liable for any direct, indirect, incidental, special,
exemplary, or consequential damages (including, but not limited to,
procurement of substitute goods or services; loss of use, data, or
profits; or business interruption) however caused and on any theory of
liability, whether in contract, strict liability, or tort (including
negligence or otherwise) arising in any way out of the use of this
software, even if advised of the possibility of such damage.

*/

package info.xiamo.core.test;

import org.junit.Before;
import org.junit.Test;
import org.markdownj.HTMLDecoder;
import org.markdownj.MarkdownProcessor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class EmailAddresses {
    MarkdownProcessor m;

    @Before
    public void createProcessor() {
        m = new MarkdownProcessor();
    }

    @Test
    public void testDecoder() {
        String encoded = "&#98;&#105;&#x6C;&#x6C;&#x67;&#64;&#x6D;i&#x63;&#x72;&#x6F;&#115;&#x6F;&#x66;&#116;&#x2E;c&#111;&#109;";
        String billg = "billg@microsoft.com";

        assertEquals(billg, HTMLDecoder.decode(encoded));
        assertEquals("", HTMLDecoder.decode(""));
    }

    @Test
    public void testEmail() {
        String html = m.markdown("<billg@microsoft.com>");
        String plain = HTMLDecoder.decode(html);
        assertEquals("<p><a href=\"mailto:billg@microsoft.com\">billg@microsoft.com</a></p>\n", plain);
        assertFalse("Email addresses are masked", plain.equals(html));
    }
}
