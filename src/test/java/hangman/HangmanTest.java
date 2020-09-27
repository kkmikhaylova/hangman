package hangman;

import io.ConsoleReader;
import io.ConsoleWriter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class HangmanTest {

    private static final String WORD_TO_GUESS = "banana";

    @Mock
    private ConsoleReader consoleReader;
    @Mock
    private ConsoleWriter consoleWriter;

    @Before
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void victoryTest() throws IOException {
        Hangman hangman = new Hangman(consoleReader, consoleWriter, WORD_TO_GUESS);
        when(consoleReader.read()).thenReturn("a", "b", "c", "n");

        hangman.run();

        verify(consoleWriter, times(4)).write("Guess a letter:");
        verify(consoleWriter, times(3)).write("Hit!");
        verify(consoleWriter, times(1)).write("Missed, mistake %d out of %d.", 1, 5);

        verify(consoleWriter, times(1)).write("The word: %s", "*a*a*a");
        verify(consoleWriter, times(2)).write("The word: %s", "ba*a*a");
        verify(consoleWriter, times(1)).write("The word: %s", "banana");
        verify(consoleWriter, times(1)).write("You won!");
        verify(consoleWriter, times(0)).write("You lost!");
    }

    @Test
    public void lossTest() throws IOException {
        Hangman hangman = new Hangman(consoleReader, consoleWriter, WORD_TO_GUESS);
        when(consoleReader.read()).thenReturn("c", "d", "e", "f", "g");

        hangman.run();

        verify(consoleWriter, times(5)).write("Guess a letter:");
        verify(consoleWriter, times(0)).write("Hit!");
        verify(consoleWriter, times(5)).write(eq("Missed, mistake %d out of %d."), anyInt(), eq(5));

        verify(consoleWriter, times(5)).write("The word: %s", "******");
        verify(consoleWriter, times(0)).write("You won!");
        verify(consoleWriter, times(1)).write("You lost!");
    }

}
