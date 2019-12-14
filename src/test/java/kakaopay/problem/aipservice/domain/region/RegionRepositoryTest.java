package kakaopay.problem.aipservice.domain.region;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RegionRepositoryTest {
    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Region 커스텀키로 저장")
    void save() {
        Region region = new Region("성남");
        Region persistRegion = testEntityManager.persist(region);
        testEntityManager.flush();
        testEntityManager.clear();

        Region repoRegion = regionRepository.findById(persistRegion.getId()).get();

        assertThat(repoRegion.getId()).contains("reg");
    }

    @Test
    @DisplayName("Region name으로 조회")
    void findByName() {
        String regionName = "성남";

        Region region = new Region(regionName);
        Region persistRegion = testEntityManager.persist(region);
        testEntityManager.flush();
        testEntityManager.clear();

        Region repoRegion = regionRepository.findByName(regionName).get();

        assertThat(repoRegion.getName()).contains(regionName);
    }
}