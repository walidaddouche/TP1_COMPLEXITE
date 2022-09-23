package TM;

public class Transition {
    private final String readState;
    private final String writeState;
    private final char readSymbol;
    private final char writeSymbol;
    private final int direction;

    public Transition(String readState, String writeState, char readChar, char writeChar, String direction) throws InterruptedException {
        this.readState = readState;
        this.writeState = writeState;
        this.readSymbol = readChar;
        this.writeSymbol = writeChar;

        this.direction = switch (direction) {
            case "LEFT" -> Pointer.LEFT;
            case "STAND" -> Pointer.STAND; // Allows only word STAND and no other word for standing pointer
            case "RIGHT" -> Pointer.RIGHT;
            default ->
                    throw new InterruptedException(
                            String.format("Error while initializing TM's transition : unknown direction %s : has to be LEFT, STAND or RIGHT", direction));
        };
    }

    public char getReadSymbol() {
        return readSymbol;
    }

    public String getReadState() {
        return readState;
    }

    public String getWriteState() {
        return writeState;
    }

    public char getWriteSymbol() {
        return writeSymbol;
    }

    public int getDirection() {
        return direction;
    }
}
