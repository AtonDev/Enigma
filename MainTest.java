package enigma;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MainTest {

    @Test
    public void testIsLegalConf() {
        assertTrue("DNF wrong # of args",
                   !Main.isLegalConf("* B I II AAAA"));
        assertTrue("DNF rotors misnamed",
                   !Main.isLegalConf("* B I II IIII AAAA"));
        assertTrue("DNF repeated rotors",
                   !Main.isLegalConf("* B I II II AAAA"));
        assertTrue("DNF first rotor not reflector",
                   !Main.isLegalConf("* III B I II AAAA"));
        assertTrue("DNF position string non-AB",
                   !Main.isLegalConf("* B I II IV 1AAA"));
        assertTrue("DNF position string wrong length",
                   !Main.isLegalConf("* B I VII II AAA"));
        assertTrue("DNP legal configuration",
                   Main.isLegalConf("* B I VII II AAAA"));
    }

    @Test
    public void testIsLegalOrder() {
        assertTrue("DNF input line in wrong place",
                   !Main.isLegalOrder("hello world"));
        assertTrue("DNP conf line in right place",
                   Main.isLegalOrder("* B I II IV AAAA"));
    }

    @Test
    public void testIsLegalChar() {
        assertTrue("DNF msg has non-AB chars",
                   !Main.isLegalChar("hello world!"));
        assertTrue("DNF msg has non-AB chars",
                   !Main.isLegalChar("* hello world"));
        assertTrue("DNP msg has only AB chars",
                   Main.isLegalChar(" hello world"));
    }

}
