package Exo3.TM;

public class Pointer {
    public static final int LEFT = -1;
    public static final int STAND = 0;
    public static final int RIGHT = 1;
    public static final char BLANK = '-';
    private final int TAPE_LENGTH = 100; // Initial tape's length

    private int position;
    private StringBuffer tape;

    public Pointer(String word) {
        this.position =  TAPE_LENGTH / 4 + 1; // Start at tape's first quarter
        initTape(word);
    }

    private void initTape(String word) {
        tape = new StringBuffer(TAPE_LENGTH);

        tape.append('$'); // '$' represent tape's debut/end
        tape.append(String.valueOf(BLANK).repeat(TAPE_LENGTH / 4));
        tape.append(word);
        tape.append(String.valueOf(BLANK).repeat(Math.max(0, TAPE_LENGTH - tape.length())));
        tape.append('$');
    }

    public void write(char letter) {
        tape.setCharAt(position, letter);
    }

    public char read() {
        return tape.charAt(position);
    }

    public void moveLeft() {
        position += Pointer.LEFT;

        if (read() == '$') try {
            throw new InterruptedException("Pointer out of tape (left side)");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void moveRight() {
        position += Pointer.RIGHT;

        if (read() == '$') try {
            throw new InterruptedException("Pointer out of tape (right side)");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void printTape() {
        System.out.println("TAPE:\n"+tape.toString());
        System.out.println(" ".repeat(position) + "^");
    }
}
