package enigma;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class RotorTest {

    private Rotor rotorI;
    private int abl = 2 * 10 + 6;
    @Before
    public void initializer() {
        rotorI = new Rotor("I");
    }

    @Test
    public void toIndex1() {
        assertTrue("Wrong output", Rotor.toIndex('A') == 0);
    }

    @Test
    public void convertForward1() {
        boolean condition = true;
        for (int k = 0; k < abl; k += 1) {
            if (rotorI.convertForward(k) < 0) {
                condition = false;
            }
        }
        assertTrue("Returns negative index", condition);
    }

    @Test
    public void convertBackward1() {
        boolean condition = true;
        for (int k = 0; k < abl; k += 1) {
            if (rotorI.convertBackward(k) < 0) {
                condition = false;
            }
        }
        assertTrue("Returns negative index", condition);
    }

}
