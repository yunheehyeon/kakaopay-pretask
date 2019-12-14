package kakaopay.problem.aipservice.domain.support;

import kakaopay.problem.aipservice.domain.region.Region;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "support_rule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class SupportRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "support_rule_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "region_id", foreignKey = @ForeignKey(name = "fk_support_region"))
    private Region region;

    @Embedded
    private SupportContent supportContent;

    @Builder
    public SupportRule(Region region, SupportContent supportContent) {
        this.region = region;
        this.supportContent = supportContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupportRule that = (SupportRule) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(region, that.region) &&
                Objects.equals(supportContent, that.supportContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, region, supportContent);
    }
}
