package TM;

public class Pointer {
    public static final int LEFT = -1;
    public static final int STAND = 0;
    public static final int RIGHT = 1;
    public static final char BLANK = '-';
    private final int TAPE_LENGTH = 50; // Initial tape's length

    private int position;
    private StringBuffer tape;

    public Pointer(String word) {
        this.position =  TAPE_LENGTH / 4 + 1; // Start at ribbon's first quarter
        initRibbon(word);
    }

    private void initRibbon(String word) {
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

    public void move(int direction) {
        try {
            if (direction == LEFT || direction == RIGHT) {
                position += direction;

                if (read() == '$') throw new InterruptedException("Pointer out of ribbon");

            } else throw new InterruptedException("Direction value can only be -1 (LEFT), 0 (STAND) or 1 (RIGHT)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printTape() {
        System.out.println("TAPE:\n"+tape.toString());
        for (int i = 0; i < position; i++) {
            System.out.print(" ");
        }
        System.out.println("^"); // Pointer
    }
}
