package enigma;

/** Class that represents a rotor in the enigma machine.
 *  @author Arturo Pacifico Griffini
 */
class Rotor {

    /** My current position (index 0..25, with 0 indicating that 'A'
     *  is showing). */
    private int position;

    /** The length of the english alphabet. */
    private static int abl = 2 * 10 + 6;

    /** The position of the first notch on the rotor (index 0..25, with 0
     *  indicating that the notch is at 'A'). */
    private int notch1Position;

    /** The position of the second notch on the rotor (index 0..25, with 0
     *  indicating that the notch is at 'A'). */
    private int notch2Position;

    /** Indicates whether the rotor has either one or two notches.
     *  True iff it has two notches. */
    private boolean has2Notches;

    /** Wiring of the rotors represent in cycle representation where
     *  each cycle is a string and each complete permutation is an
     *  array of strings (ie cycles). */
    private String[] rotorWiring;

    /** Returns and instance of Rotor.class. The ROTOR variable
     *  which rotor is built. */
    public Rotor(String rotor) {
        if (rotor.equals("I")) {
            this.rotorWiring = new String[] {
                "AELTPHQXRU", "BKNW", "CMOY", "DFG", "IV", "JZ", "S"};
            this.notch1Position = toIndex('Q');
            this.has2Notches = false;
        } else if (rotor.equals("II")) {
            this.rotorWiring = new String[] {
                "FIXVYOMW", "CDKLHUP", "ESZ", "BJ", "GR", "NT", "A", "Q"};
            this.notch1Position = toIndex('E');
            this.has2Notches = false;
        } else if (rotor.equals("III")) {
            this.rotorWiring = new String[] {
                "ABDHPEJT", "CFLVMZOYQIRWUKXSG", "N"};
            this.notch1Position = toIndex('V');
            this.has2Notches = false;
        } else if (rotor.equals("IV")) {
            this.rotorWiring = new String[] {
                "AEPLIYWCOXMRFZBSTGJQNH", "DV", "KU"};
            this.notch1Position = toIndex('J');
            this.has2Notches = false;
        } else if (rotor.equals("V")) {
            this.rotorWiring = new String[] {
                "AVOLDRWFIUQ", "BZKSMNHYC", "EGTJPX"};
            this.notch1Position = toIndex('Z');
            this.has2Notches = false;
        } else if (rotor.equals("VI")) {
            this.rotorWiring = new String[] {
                "AJQDVLEOZWIYTS", "CGMNHFUX", "BPRK"};
            this.notch1Position = toIndex('Z');
            this.notch2Position = toIndex('M');
            this.has2Notches = true;
        } else if (rotor.equals("VII")) {
            this.rotorWiring = new String[] {
                "ANOUPFRIMBZTLWKSVEGCJYDHXQ"};
            this.notch1Position = toIndex('Z');
            this.notch2Position = toIndex('M');
            this.has2Notches = true;
        } else if (rotor.equals("VIII")) {
            this.rotorWiring = new String[] {
                "AFLSETWUNDHOZVICQ", "BKJ", "GXY", "MPR"};
            this.notch1Position = toIndex('Z');
            this.notch2Position = toIndex('M');
            this.has2Notches = true;
        } else if (rotor.equals("B")) {
            this.rotorWiring = new String []
            {"AY", "BR", "CU", "DH", "EQ", "FS", "GL",
             "IP", "JX", "KN", "MO", "TZ", "VW"};
        } else if (rotor.equals("C")) {
            this.rotorWiring = new String []
            {"AF", "BV", "CP", "DJ", "EI", "GO", "HY",
             "KR", "LZ", "MX", "NW", "TQ", "SU"};
        }
    }

    /** Assuming that P is an integer in the range 0..25, returns the
     *  corresponding upper-case letter in the range A..Z. */
    static char toLetter(int p) {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return letters.charAt(p % abl);
    }

    /** Assuming that C is an upper-case letter in the range A-Z, return the
     *  corresponding index in the range 0..25. Inverse of toLetter. */
    static int toIndex(char c) {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return letters.indexOf(c);
    }

    /** Return my current rotational position as an integer between 0
     *  and 25 (corresponding to letters 'A' to 'Z').  */
    int getPosition() {
        return position;
    }

    /** Set getPosition() to POSN.  */
    void setPosition(int posn) {
        position = posn;
    }

    /** Return the conversion of P (an integer in the range 0..25)
     *  according to my permutation. */
    int convertForward(int p) {
        int output = 0;
        char input = toLetter((this.position + p) % abl);
        for (int k = 0; k < rotorWiring.length; k += 1) {
            int index = rotorWiring[k].indexOf(input);
            if (index >= 0) {
                int t = (index + 1) % rotorWiring[k].length();
                output = (toIndex(rotorWiring[k].charAt(t))
                          - this.position + abl) % abl;
            }
        }
        return output;
    }

    /** Return the conversion of E (an integer in the range 0..25)
     *  according to the inverse of my  permutation. */
    int convertBackward(int e) {
        int output = 0;
        char input = toLetter((e + this.position) % abl);
        for (int k = 0; k < rotorWiring.length; k += 1) {
            int index = rotorWiring[k].indexOf(input);
            if (index >= 0) {
                int l = rotorWiring[k].length();
                int t = (index + l - 1) % l;
                output = (toIndex(rotorWiring[k].charAt(t))
                          - this.position + abl) % abl;
            }
        }
        return output;
    }

    /** Returns true iff I am positioned to allow the rotor to my left
     *  to advance. */
    boolean atNotch() {
        if (this.position == this.notch1Position) {
            return true;
        } else if (this.has2Notches) {
            return this.position == this.notch2Position;
        } else {
            return false;
        }
    }

    /** Advance me one position. */
    void advance() {
        this.position = (this.position + 1) % abl;
    }
}
