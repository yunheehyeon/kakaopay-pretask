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
        Region region = new Region("성남");
        Region persistRegion = testEntityManager.persist(region);

        SupportContent supportContent = new SupportContent(
                "성남시 소재 중소기업으로서 성남시장의 추천을 받은 자",
                "운전 및 시설",
                "5억원 이내",
                "1.80%",
                "성남시",
                "성남하이테크지",
                "전 영업점"
        );

        SupportRule supportRule = SupportRule.builder()
                .region(region)
                .supportContent(supportContent).build();

        SupportRule persistSupportRule = testEntityManager.persist(supportRule);
        testEntityManager.flush();
        testEntityManager.clear();

        SupportRule repoSupportRule = supportRuleRepository.findByRegion(persistRegion).get();

        assertThat(repoSupportRule.getRegion()).isEqualTo(persistRegion);
    }

    @Test
    @DisplayName("SupportRule 저장후 regionName으로 조회")
    void find() {
        Region region = new Region("성남");
        Region persistRegion = testEntityManager.persist(region);

        SupportContent supportContent = new SupportContent(
                "성남시 소재 중소기업으로서 성남시장의 추천을 받은 자",
                "운전 및 시설",
                "5억원 이내",
                "1.80%",
                "성남시",
                "성남하이테크지",
                "전 영업점"
        );

        SupportRule supportRule = SupportRule.builder()
                .region(region)
                .supportContent(supportContent).build();

        SupportRule persistSupportRule = testEntityManager.persist(supportRule);
        testEntityManager.flush();
        testEntityManager.clear();

        SupportRule repoSupportRule = supportRuleRepository.findByRegionName(region.getName()).get();

        assertThat(repoSupportRule.getRegion()).isEqualTo(persistRegion);
    }
}