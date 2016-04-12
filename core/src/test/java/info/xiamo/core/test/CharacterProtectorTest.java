package info.xiamo.core.test;

import org.junit.Before;
import org.junit.Test;
import org.markdownj.CharacterProtector;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author alexbcoles
 */
public class CharacterProtectorTest {

    private CharacterProtector characterProtector;

    @Before
    public void createCharacterProtector() {
        characterProtector = new CharacterProtector();
    }

    @Test
    public void testEncodeAndDecodeRoundtrip() {
        String encoded = characterProtector.encode("<h4>Warnemünde</h4>");
        assertEquals("<h4>Warnemünde</h4>", characterProtector.decode(encoded));
    }

    @Test
    public void testGetAllEncodedTokens() {
        Collection tokens = characterProtector.getAllEncodedTokens();
        assertEquals(0, tokens.size());

        characterProtector.encode("<nav><div></div></nav>");
        characterProtector.encode("<h1 id='heading'>Schifffahrt nach Warnemünde</h1>");
        characterProtector.encode("<br/>");
        assertEquals(3, tokens.size());
    }

    @Test(expected=UnsupportedOperationException.class)
    public void testGetAllEncodedTokensCanNotModified1() {
        Collection tokens = characterProtector.getAllEncodedTokens();
        tokens.clear();
    }

    @Test(expected=UnsupportedOperationException.class)
    public void testGetAllEncodedTokensCanNotModified2() {
        Collection tokens = characterProtector.getAllEncodedTokens();
        tokens.add("another_token");
        tokens.remove("another_token");
    }

}
