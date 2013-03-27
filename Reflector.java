package enigma;

/** Class that represents a reflector in the enigma.
 *  @author Arturo Pacifico Griffini
 */
class Reflector extends Rotor {

    /** Constructor that initiates instance of
     *  Reflector with type REFLECTOR. */
    public Reflector(String reflector) {
        super(reflector);
    }

    /** Returns a useless value; should never be called. */
    @Override
    int convertBackward(int unused) {
        throw new UnsupportedOperationException();
    }

    /** Reflectors do not advance. */
    @Override
    void advance() {
    }

}
