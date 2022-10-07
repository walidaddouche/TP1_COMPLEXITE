package Exo3.TM;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Exo3.TM.Main <pathToTMProgram> <word>");
            System.exit(-1);
        }
        String word = args[1];
        String path = args[0];

        // Silent mode deactivates the print of the tape
        DeterministicTuringMachine turingMachine = new DeterministicTuringMachine(new File(path), word, false);
        if (turingMachine.compute()) {
            System.out.printf("The word %s is accepted%n", word);
        } else {
            System.out.printf("The word %s is rejected%n", word);
        }
    }
}
