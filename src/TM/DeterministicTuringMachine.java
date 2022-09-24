package TM;

import java.io.*;
import java.util.*;

public class DeterministicTuringMachine {
    private final boolean silentMode;

    private final Set<String> states = new HashSet<>();
    private final Map<String, Set<Transition>> transitions = new TreeMap<>();
    private final Set<String> acceptStates = new HashSet<>();

    private String initialState;
    private final Pointer pointer;

    public DeterministicTuringMachine(File program, String word, boolean silentMode) {
        this.silentMode = silentMode;
        this.pointer = new Pointer(word.trim());
        initTuringMachine(program);
    }

    private void initTuringMachine(File program) {
        try {
            BufferedReader r = new BufferedReader(new FileReader(program));
            String line;

            while ((line = r.readLine()) != null) {
                if (line.contains("states:")) {
                    String[] s = line.split(":")[1].split(",");
                    states.addAll(Arrays.asList(s));

                } else if (line.contains("acceptStates:")) {
                    String[] s = line.split(":")[1].split(",");
                    for (String st : s) {
                        if (!states.contains(st)) {
                            throw new InterruptedException(String.format("Error while initializing TM's accept states : TM has no state %s", st));
                        }
                        acceptStates.add(st);
                    }

                } else if (line.contains("initialState:")) {
                    String[] s = line.split(":");
                    if (s[1].contains(",")) {
                        throw new InterruptedException("TM can only have one start state");
                    }
                    if (!states.contains(s[1])) {
                        throw new InterruptedException(String.format("Error while initializing TM's initial state : TM has no state %s", s[1]));
                    }
                    initialState = s[1];

                } else if (line.contains("transitions:{")) {
                    while (!Objects.equals(line = r.readLine(), "}")) {
                        String[] s = line.split(",");

                        if (s.length != 5) {
                            throw new InterruptedException(
                                    String.format("Error while initializing TM's transitions : illegal number of arguments for transition %s: has to be 5", line));
                        }
                        if (hasTransitionConflict(s[0], s[2].charAt(0))) {
                            throw new InterruptedException(String.format("Error while initializing TM's transitions : only one transition can exist for state %s", s[0]));
                        }
                        else {
                            String readState = s[0];
                            String writeState = s[1];
                            char readSymbol = s[2].equals("BLANK") ? Pointer.BLANK : s[2].charAt(0);
                            char writeSymbol = s[3].equals("BLANK") ? Pointer.BLANK : s[3].charAt(0);
                            String direction = s[4];

                            Transition t = new Transition(readState, writeState, readSymbol, writeSymbol, direction);
                            if (transitions.get(readState) == null) {
                                transitions.put(readState, new HashSet<>());
                                transitions.get(readState).add(t);
                            }
                            else {
                                transitions.get(readState).add(t);
                            }
                        }
                    }
                }
            }

            if (states.size() == 0) {
                throw new InterruptedException("Error while initializing TM : TM has no states");
            }
            if (initialState == null) {
                throw new InterruptedException("Error while initializing TM : TM has no initial state");
            }

            r.close();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean hasTransitionConflict(String initState, char readChar) { // Non-deterministic not allowed i.e. no double transition with same readSymbol from same readState
        if (transitions.get(initState) == null) {
            return false;
        }

        for (Transition t : transitions.get(initState)) {
            if (t.getReadSymbol() == readChar && t.getReadState().equals(initState)) {
                return true;
            }
        }

        return false;
    }

    public boolean compute() {
        String currentState = initialState;
        boolean transitionFound;

        if (!silentMode) pointer.printTape();

        while (true) {
            if (acceptStates.contains(currentState)) { // Accepts word
                return true;
            }

            transitionFound = false;

            for (Transition transition : transitions.get(currentState)) {
                if (transition.getReadState().equals(currentState)) {
                    if (transition.getReadSymbol() == pointer.read()) {
                        transitionFound = true;
                        pointer.write(transition.getWriteSymbol());

                        switch (transition.getDirection()) {
                            case Pointer.LEFT -> pointer.moveLeft();
                            case Pointer.RIGHT -> pointer.moveRight();
                            // if direction is STAND, don't move pointer
                        }

                        currentState = transition.getWriteState();
                        break;
                    }
                }
            }

            if (!silentMode) pointer.printTape();

            if (!transitionFound) { // Rejects word
                return false;
            }
        }
    }
}
