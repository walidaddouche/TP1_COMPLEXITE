package TM;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DeterministicTuringMachine {
    private final boolean silentMode;

    private final Set<String> states = new HashSet<>();
    private final Set<Transition> transitions = new HashSet<>();
    private final Set<String> acceptStates = new HashSet<>();

    private String startState;
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
                }
                else if (line.contains("acceptStates:")) {
                    String[] s = line.split(":")[1].split(",");
                    for (String st: s) {
                        if (!states.contains(st)) {
                            throw new InterruptedException(String.format("Error while initializing TM : TM has no state %s", st));
                        }
                        else acceptStates.add(st);
                    }
                }
                else if (line.contains("startState:")) {
                    String[] s = line.split(":");
                    if (s[1].contains(",")) {
                        throw new InterruptedException("TM can only have one start state");
                    }
                    if (!states.contains(s[1])) {
                        throw new InterruptedException(String.format("Error while initializing TM : TM has no state %s", s[1]));
                    }
                    else startState = s[1];
                }
                else if (line.contains("transitions:")) {
                    while ((line = r.readLine()) != null) {
                        String[] s = line.split(",");

                        if (s.length != 5) {
                            throw new InterruptedException("Error while initializing TM : illegal number of argument for transition : has to be 5");
                        }
                        if (hasTransitionConflict(s[0], s[2].charAt(0))) {
                            throw new InterruptedException(String.format("Error while initializing TM : only one transition can exist for state %s", s[0]));
                        }
                        else {
                            transitions.add(new Transition(s[0], s[1], s[2].equals("BLANK") ? Pointer.BLANK : s[2].charAt(0),
                                    s[3].equals("BLANK") ? Pointer.BLANK : s[3].charAt(0), s[4]));
                        }
                    }
                }
            }

            if (states.size() == 0) throw new InterruptedException("Error while initializing TM : TM has no states");
            if (acceptStates.size() == 0) throw new InterruptedException("Error while initializing TM : TM has no accept state(s)");
            if (startState == null) throw new InterruptedException("Error while initializing TM : TM has no start state");
            if (transitions.size() == 0) {
                if (!acceptStates.contains(startState))
                    throw new InterruptedException("Error while initializing TM : TM's start state has to be final as TM has no transitions");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean hasTransitionConflict(String initState, char readChar) { // Non-deterministic not allowed i.e. no double transition with same readSymbol from same state
        for (Transition t : transitions) {
            if (t.getReadChar() == readChar && t.getReadState().equals(initState)) {
                return true;
            }
        }
        return false;
    }

    public boolean compute() {
        String currentState = startState;
        boolean transitionFound;
        if (!silentMode) pointer.printTape();

        while (true) {
            Iterator<Transition> transitionIterator = transitions.iterator();
            transitionFound = false;

            while (transitionIterator.hasNext() && !transitionFound) {
                Transition transition = transitionIterator.next();
                if (transition.getReadState().equals(currentState)) {
                    if (transition.getReadChar() == pointer.read()) {
                        transitionFound = true;
                        pointer.write(transition.getWriteChar());
                        pointer.move(transition.getDirection());
                        currentState = transition.getWriteState();
                    }
                }
            }
            if (!silentMode) pointer.printTape();

            if (acceptStates.contains(currentState)) {
                return true;
            }

            if (!transitionFound) {
                System.err.printf("No valid transition found for this phase (state=%s, symbol=%s)%n", currentState, pointer.read());
                return false;
            }
        }
    }
}
