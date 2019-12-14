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
        Region region = Region.builder().name("성남").build();
        Region persistRegion = testEntityManager.persist(region);
        testEntityManager.flush();
        testEntityManager.clear();

        Region repoRegion = regionRepository.findById(persistRegion.getId()).get();

        assertThat(repoRegion.getId()).contains("reg");
    }
}