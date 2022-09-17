package TM;

import java.io.File;

public class Main {

    /*
    Compile code:
    javac -d .\out\production\TP1_COMPLEXITE\ -cp . .\src\TM\*.java && cd .\out\production\TP1_COMPLEXITE\ && java TM.Main <args>
    Cmd from .\TP1_COMPLEXITE\

    Run code:
    java TM.Main <pathToMTProgram> <word>
    Cmd from .\out\production\TP1_COMPLEXITE\

    Compile & run:
    javac -d .\out\production\TP1_COMPLEXITE\ -cp . .\src\TM\*.java && cd .\out\production\TP1_COMPLEXITE\ && java TM.Main <args> && cd ../../..
    Cmd from .\TP1_COMPLEXITE\
     */

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java TM.Main <pathToMTProgram> <word>");
            System.exit(-1);
        }
        String word = args[1];
        String path = args[0];

        DeterministicTuringMachine turingMachine = new DeterministicTuringMachine(new File(path), word, false);
        if (turingMachine.compute()) {
            System.out.printf("The word %s is accepted%n", word);
        } else {
            System.out.printf("The word %s is rejected%n", word);
        }
    }
}
