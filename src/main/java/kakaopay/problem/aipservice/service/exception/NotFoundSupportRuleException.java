package kakaopay.problem.aipservice.service.exception;

public class NotFoundSupportRuleException extends RuntimeException {
    private static final String MESSAGE = "지원 정보를 찾을 수 없습니다.";

    public NotFoundSupportRuleException() {
        super(MESSAGE);
    }
}
