package game;

public class GameOverException extends Exception { 
    public GameOverException(String errorMessage) {
        super(errorMessage);
    }
}