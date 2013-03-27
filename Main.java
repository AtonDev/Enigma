package enigma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/** Enigma simulator.
 *  @author Arturo Pacifico Griffini
 */
public final class Main {

    /** Process a sequence of encryptions and decryptions, as
     *  specified in the input from the standard input.  Print the
     *  results on the standard output. Exits normally if there are
     *  no errors in the input; otherwise with code 1. */
    public static void main(String[] unused) {
        Machine M = new Machine();
        BufferedReader input =
            new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                String line = input.readLine();
                if (line == null) {
                    break;
                } else if (line.equals("")) {
                    printMessageLine("");
                } else if (!line.equals("")) {
                    legalArguments(line);
                    if (isConfigurationLine(line)) {
                        configure(M, line);
                    } else {
                        printMessageLine(M.convert(standardize(line)));
                    }
                    lineCounter += 1;
                }
            }
        } catch (IOException excp) {
            System.err.printf("Input error: %s%n", excp.getMessage());
            System.exit(1);
        }
    }

    /** Keeps count of how many lines of input were given. */
    private static int lineCounter;


    /** Return true iff LINE is an Enigma configuration line. */
    static boolean isConfigurationLine(String line) {
        return line.charAt(0) == '*';
    }

    /** Configure M according to the specification given on CONFIG,
     *  which must have the format specified in the assignment. */
    static void configure(Machine M, String config) {
        StringTokenizer st = new StringTokenizer(config);
        st.nextToken();
        Reflector rf = new Reflector(st.nextToken());
        Rotor r1 = new Rotor(st.nextToken());
        Rotor r2 = new Rotor(st.nextToken());
        Rotor r3 = new Rotor(st.nextToken());
        String conf = st.nextToken();
        M.setRotors(rf, r1, r2, r3);
        M.setPositions(conf);
    }

    /** Return the result of converting LINE to all upper case,
     *  removing all blanks.  It is an error if LINE contains
     *  characters other than letters and blanks. */
    static String standardize(String line) {
        line = line.toUpperCase();
        line = line.replaceAll("\\s", "");
        line = line.trim();
        return line;
    }

    /** Print MSG in groups of five (except that the last group may
     *  have fewer letters). */
    static void printMessageLine(String msg) {
        System.out.println(msg);
    }

    /** Stops the execution of the program if
     *  the arguments ARGS are not legal. */
    static void legalArguments(String args) {
        boolean stopExecution = false;
        if (args.equals("")) {
            System.exit(1);
        } else {
            if (!isLegalOrder(args)) {
                stopExecution = true;
            }
            if (isConfigurationLine(args)) {
                if (!isLegalConf(args)) {
                    stopExecution = true;
                }
            } else {
                if (!isLegalChar(args)) {
                    stopExecution = true;
                }
                if (args.equals("")) {
                    stopExecution = true;
                }
            }
            if (stopExecution) {
                System.exit(1);
            }
        }
    }

    /** Returns true iff the configuration line CONF is legal. */
    static boolean isLegalConf(String conf) {
        boolean legal = true;
        StringTokenizer st = new StringTokenizer(conf);
        if (st.countTokens() != 6) {
            legal = false;
        } else {
            st.nextToken();
            String rf = st.nextToken();
            String r1 = st.nextToken();
            String r2 = st.nextToken();
            String r3 = st.nextToken();
            String cf = st.nextToken();
            String[] rotors = {"I", "II", "III", "IV",
                               "V", "VI", "VII", "VIII"};
            if (!(rf.equals("B") || rf.equals("C"))) {
                legal = false;
            }
            if (r1.equals(r2) || r2.equals(r3) || r3.equals(r1)) {
                legal = false;
            }
            boolean containsr1 = false;
            boolean containsr2 = false;
            boolean containsr3 = false;
            for (String type: rotors) {
                if (r1.equals(type)) {
                    containsr1 = true;
                }
                if (r2.equals(type)) {
                    containsr2 = true;
                }
                if (r3.equals(type)) {
                    containsr3 = true;
                }
            }
            if (!(containsr1 && containsr2 && containsr3)) {
                legal = false;
            }
            if (!(cf.length() == 4 && Pattern.matches("[A-Z]+", cf))) {
                legal = false;
            }
        }
        return legal;
    }

    /** Returns true iff the order of input LINE is correct. */
    static boolean isLegalOrder(String line) {
        boolean result = true;
        if (lineCounter == 0 && !isConfigurationLine(line)) {
            result = false;
        }
        return result;
    }

    /** Returns true iff the input LINE contains only alphabetic characters. */
    static boolean isLegalChar(String line) {
        String tmp = line.replaceAll(" ", "");
        tmp.trim();
        return Pattern.matches("[A-Za-z]+", tmp);
    }
}

