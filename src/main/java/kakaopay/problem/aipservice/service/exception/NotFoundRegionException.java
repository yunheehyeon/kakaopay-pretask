package kakaopay.problem.aipservice.service.exception;

public class NotFoundRegionException extends RuntimeException {
    private static final String MESSAGE = "Region을 찾을 수 없습니다.";

    public NotFoundRegionException() {
        super(MESSAGE);
    }
}
