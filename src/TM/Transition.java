package TM;

public class Transition {
    private final String readState;
    private final String writeState;
    private final char readChar;
    private final char writeChar;
    private final int direction;

    public Transition(String readState, String writeState, char readChar, char writeChar, String direction) throws InterruptedException {
        this.readState = readState;
        this.writeState = writeState;
        this.readChar = readChar;
        this.writeChar = writeChar;

        switch (direction) {
            case "LEFT" -> this.direction = Pointer.LEFT;
            case "STAND" -> this.direction = Pointer.STAND;
            case "RIGHT" -> this.direction = Pointer.RIGHT;
            default ->
                    throw new InterruptedException(String.format("Error while initializing TM : unknown direction %s : has to be LEFT, STAND or RIGHT", direction));
        }
    }

    public char getReadChar() {
        return readChar;
    }

    public String getReadState() {
        return readState;
    }

    public String getWriteState() {
        return writeState;
    }

    public char getWriteChar() {
        return writeChar;
    }

    public int getDirection() {
        return direction;
    }
}
