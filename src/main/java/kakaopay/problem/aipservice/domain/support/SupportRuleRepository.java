package kakaopay.problem.aipservice.domain.support;

import kakaopay.problem.aipservice.domain.region.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupportRuleRepository extends JpaRepository<SupportRule, Long> {
    Optional<SupportRule> findByRegion(Region region);

    Optional<SupportRule> findByRegionName(String regionName);
}
