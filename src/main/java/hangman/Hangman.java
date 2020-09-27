package hangman;

import io.ConsoleReader;
import io.ConsoleWriter;
import io.Reader;
import io.Writer;
import util.ListUtils;
import util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Hangman {

    private static List<String> AVAILABLE_WORDS = Arrays.asList("banana", "coconut", "apple");
    private static int AVAILABLE_MISTAKES_COUNT = 5;

    private final Reader reader;
    private final Writer writer;
    private final List<Character> wordToGuess;
    private final List<Character> guessedChars = new ArrayList<>();
    private int mistakesCount = 0;

    private Hangman(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
        String wordToGuess = ListUtils.getRandomItem(AVAILABLE_WORDS);
        this.wordToGuess = StringUtils.toCharList(wordToGuess);
    }

    public Hangman(Reader reader, Writer writer, String wordToGuess) {
        this.reader = reader;
        this.writer = writer;
        this.wordToGuess = StringUtils.toCharList(wordToGuess);
    }

    public Hangman() {
        this(new ConsoleReader(), new ConsoleWriter());
    }

    public void run() {
        try {
            while (!isMistakeLimitExceeded() && !isWordGuessed()) {
                writer.write("Guess a letter:");
                Character current = reader.read().charAt(0);
                if (wordToGuess.contains(current)) {
                    writer.write("Hit!");
                } else {
                    writer.write("Missed, mistake %d out of %d.", ++mistakesCount, AVAILABLE_MISTAKES_COUNT);
                }
                guessedChars.add(current);
                writer.write("The word: %s", encryptWord());
            }
            writer.write(isWordGuessed() ? "You won!" : "You lost!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isMistakeLimitExceeded() {
        return mistakesCount >= AVAILABLE_MISTAKES_COUNT;
    }

    private boolean isWordGuessed() {
        return guessedChars.containsAll(wordToGuess);
    }

    private String encryptWord() {
        return wordToGuess.stream()
                .map(c -> guessedChars.contains(c) ? c.toString() : "*")
                .collect(Collectors.joining());
    }

}
