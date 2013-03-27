package enigma;

/** Class that represents a complete enigma machine.
 *  @author Arturo Pacifico Griffini
 */
class Machine {


    /** Constructs the machine instance and initiates rotorsSlot. */
    public Machine() {
        rotorsSlot = new Rotor[4];
    }

    /** An array that holds the rotors and the reflector in
     *  in the proper order (ie at o the reflector and at
     *  3 the rightmost rotor). */
    private final Rotor[] rotorsSlot;

    /** Set my rotors to (from left to right), REFLECTOR, LEFT,
     *  MIDDLE, and RIGHT.  Initially, their positions are all 'A'. */
    void setRotors(Reflector reflector,
                   Rotor left, Rotor middle, Rotor right) {
        rotorsSlot[0] = reflector;
        rotorsSlot[1] = left;
        rotorsSlot[2] = middle;
        rotorsSlot[3] = right;
    }

    /** Set the positions of my rotors according to SETTING, which
     *  must be a string of 4 upper-case letters. The first letter
     *  refers to the reflector position, and the rest to the rotor
     *  positions, left to right. */
    void setPositions(String setting) {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int k = 0; k < 4; k += 1) {
            rotorsSlot[k].setPosition(letters.indexOf(setting.charAt(k)));
        }

    }

    /** Returns the encoding/decoding of MSG, updating the state of
     *  the rotors accordingly. */
    String convert(String msg) {
        String result = "";
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Rotor[] rs = rotorsSlot;
        for (int k = 0; k < msg.length(); k += 1) {
            boolean middleDidAdvance = false;
            if (rs[2].atNotch()) {
                rs[1].advance();
                rs[2].advance();
                middleDidAdvance = true;
            }
            if (rs[3].atNotch() && !middleDidAdvance) {
                rs[2].advance();
            }
            rs[3].advance();
            if (letters.indexOf(msg.charAt(k)) >= 0) {
                int input = letters.indexOf(msg.charAt(k));
                int s1 = rs[3].convertForward(input);
                s1 = rs[2].convertForward(s1);
                s1 = rs[1].convertForward(s1);
                s1 = rs[0].convertForward(s1);
                s1 = rs[1].convertBackward(s1);
                s1 = rs[2].convertBackward(s1);
                s1 = rs[3].convertBackward(s1);
                result += letters.charAt(s1);
            }
            if (k % 5 == 4) {
                result += " ";
            }
        }
        result.trim();
        return result;
    }
}
