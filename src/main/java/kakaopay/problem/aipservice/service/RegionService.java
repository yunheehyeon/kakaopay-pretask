package kakaopay.problem.aipservice.service;

import kakaopay.problem.aipservice.domain.region.Region;
import kakaopay.problem.aipservice.domain.region.RegionRepository;
import kakaopay.problem.aipservice.service.exception.NotFoundRegionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RegionService {
    private static final Logger log = LoggerFactory.getLogger(RegionService.class);

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public Region findByName(String regionName) {
        return regionRepository.findByName(regionName)
                .orElseThrow(NotFoundRegionException::new);
    }

    public Region save(String regionName) {
        Region region = new Region(regionName);

        return regionRepository.save(region);
    }

    public Region findOrSave(String regionName) {
        try {
            return findByName(regionName);
        } catch (NotFoundRegionException e) {
            log.error("{}", e.getMessage());
            return save(regionName);
        }
    }
}
