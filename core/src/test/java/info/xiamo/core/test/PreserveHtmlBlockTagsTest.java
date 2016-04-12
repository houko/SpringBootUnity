package info.xiamo.core.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.markdownj.MarkdownProcessor;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PreserveHtmlBlockTagsTest {
    private MarkdownProcessor m;
    private String value;

    @Parameters
    public static Collection<Object[]> testHtml() {
        return Arrays.asList(new Object[][] {
            {"<h1>Chapter One</h1>"},
            {"<H1>Chapter One</H1>"},
            {"<div>\n  <div>Text</div>\n</div>"},
            {"<DIV>\n  <DIV>Text</DIV>\n</DIV>"},
            {"<TABLE>\n<TR>\n<TD>Cell</TD>\n</TR>\n</TABLE>"},
            {"<BlockQuote>All the world’s a stage…</BlockQuote>"},
            {"<iFrame src='http://microsoft.com/'></IFRAME>"},
            {"<hr/>"},
            {"<HR>"},
            {"<!-- a comment -->"}
        });
    }

    public PreserveHtmlBlockTagsTest(String value) {
        this.m      = new MarkdownProcessor();
        this.value  = value;
    }

    @Test
    public void testRoundtripPreservesTags() {
        assertEquals(value, m.markdown(value).trim());
    }

}
