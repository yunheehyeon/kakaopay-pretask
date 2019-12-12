package kakaopay.problem.aipservice.filereader.exception;

public class ReaderCreatException extends RuntimeException {
    private static final String MESSAGE = "Reader를 생설할 수 없습니다.";

    public ReaderCreatException() {
        super(MESSAGE);
    }
}
