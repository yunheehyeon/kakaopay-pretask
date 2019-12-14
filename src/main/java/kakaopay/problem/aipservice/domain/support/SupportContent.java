package kakaopay.problem.aipservice.domain.support;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class SupportContent {

    @Column(name = "target", nullable = false)
    private String target;

    @Column(name = "usage", nullable = false)
    private String usage;

    @Column(name = "amount", nullable = false)
    private String limit;

    @Column(name = "rate", nullable = false)
    private String rate;

    @Column(name = "institute", nullable = false)
    private String institute;

    @Column(name = "mgmt", nullable = false)
    private String mgmt;

    @Column(name = "reception", nullable = false)
    private String reception;

    @Builder
    public SupportContent(String target, String usage, String limit, String rate, String institute, String mgmt, String reception) {
        this.target = target;
        this.usage = usage;
        this.limit = limit;
        this.rate = rate;
        this.institute = institute;
        this.mgmt = mgmt;
        this.reception = reception;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupportContent that = (SupportContent) o;
        return Objects.equals(target, that.target) &&
                Objects.equals(limit, that.limit) &&
                Objects.equals(rate, that.rate) &&
                Objects.equals(institute, that.institute) &&
                Objects.equals(mgmt, that.mgmt) &&
                Objects.equals(reception, that.reception);
    }

    @Override
    public int hashCode() {
        return Objects.hash(target, limit, rate, institute, mgmt, reception);
    }
}
