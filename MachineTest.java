package enigma;

import org.junit.Before;
import org.junit.Test;

public class MachineTest {

    private Machine m;

    @Before
    public void initialization() {
        m = new Machine();
        Reflector rf = new Reflector("B");
        Rotor r1 = new Rotor("I");
        Rotor r2 = new Rotor("II");
        Rotor r3 = new Rotor("III");
        m.setRotors(rf, r1, r2, r3);
    }

    @Test
    public void convert1() {
        String input1 = "AAAAAAAAAA";
        String input2 = "GBPCSDQEUF";
        String output1 = m.convert(input1);
        m.setPositions("AAAA");
        String output2 = m.convert(input2);
        System.out.println(output1);
        System.out.println(output2);
    }

}
