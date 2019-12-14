package kakaopay.problem.aipservice.domain.support;

import kakaopay.problem.aipservice.domain.region.Region;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SupportRuleRepositoryTest {

    @Autowired
    private SupportRuleRepository supportRuleRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("SupportRule 저장후 region으로 조회")
    void save() {
        Region region = Region.builder().name("성남").build();
        Region persistRegion = testEntityManager.persist(region);

        SupportContent supportContent = SupportContent.builder()
                .target("성남시 소재 중소기업으로서 성남시장의 추천을 받은 자")
                .usage("운전 및 시설")
                .limit("5억원 이내")
                .rate("1.80%")
                .institute("성남시")
                .mgmt("성남하이테크지")
                .reception("전 영업점").build();

        SupportRule supportRule = SupportRule.builder()
                .region(region)
                .supportContent(supportContent).build();

        SupportRule persistSupportRule = testEntityManager.persist(supportRule);
        testEntityManager.flush();
        testEntityManager.clear();

        SupportRule repoSupportRule = supportRuleRepository.findByRegion(persistRegion).get();

        assertThat(repoSupportRule.getRegion()).isEqualTo(persistRegion);
    }

}