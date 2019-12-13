package kakaopay.problem.aipservice.filereader.exception;

public class CSVParserCreateException extends RuntimeException {
    private static final String MESSAGE = "CSVParser를 생성할 수 없습니다.";

    public CSVParserCreateException() {
        super(MESSAGE);
    }
}
