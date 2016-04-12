/*
Copyright (c) 2006, Nathan Winant, nw@exegetic.net.
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
import org.markdownj.MarkdownProcessor;

import static org.junit.Assert.assertEquals;

public class EscapeSpecialCharsWithinTagAttributes {
    MarkdownProcessor m;

    @Before
    public void createProcessor() {
        m = new MarkdownProcessor();
    }

    @Test
    public void testImages() {
        String url = "![an *image*](/images/an_image_with_underscores.jpg \"An_image_title\")";
        String processed = m.markdown(url);
        String output = "<p><img src=\"/images/an_image_with_underscores.jpg\" alt=\"an *image*\" title=\"An_image_title\" /></p>\n";
        assertEquals(output, processed);
    }

    @Test
    public void testAutoLinks() {
        String url = "[a _link_](http://url.com/a_tale_of_two_cities?var1=a_query_&var2=string \"A_link_title\")";
        String processed = m.markdown(url);
        String output = "<p><a href=\"http://url.com/a_tale_of_two_cities?var1=a_query_&amp;var2=string\" title=\"A_link_title\">a <em>link</em></a></p>\n";
        assertEquals(output, processed);
    }

}
