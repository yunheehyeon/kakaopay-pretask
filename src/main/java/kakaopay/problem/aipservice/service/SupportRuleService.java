package kakaopay.problem.aipservice.service;

import kakaopay.problem.aipservice.domain.region.Region;
import kakaopay.problem.aipservice.domain.support.SupportContent;
import kakaopay.problem.aipservice.domain.support.SupportRule;
import kakaopay.problem.aipservice.domain.support.SupportRuleRepository;
import kakaopay.problem.aipservice.dto.RegionSearchDto;
import kakaopay.problem.aipservice.dto.SupportRuleDto;
import kakaopay.problem.aipservice.filereader.CSVFileReader;
import kakaopay.problem.aipservice.filereader.FileReader;
import kakaopay.problem.aipservice.filereader.Record;
import kakaopay.problem.aipservice.service.exception.NotFoundSupportRuleException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupportRuleService {
    private final SupportRuleRepository supportRuleRepository;
    private final RegionService regionService;
    private final FileReader fileReader;

    public SupportRuleService(SupportRuleRepository supportRuleRepository, RegionService regionService, CSVFileReader csvFileReader) {
        this.supportRuleRepository = supportRuleRepository;
        this.regionService = regionService;
        this.fileReader = csvFileReader;
    }

    public List<SupportRuleDto> saveCSVFile() {
        return fileReader.getRecodes("static/csv/data.csv").stream()
                .map(this::saveRecode)
                .map(SupportRuleDto::from)
                .collect(Collectors.toList());
    }

    private SupportRule saveRecode(Record record) {
        SupportContent supportContent = SupportContent.from(record);
        Region region = regionService.findOrSave(record.getRegion());

        return supportRuleRepository.save(new SupportRule(region, supportContent));
    }

    public List<SupportRuleDto> read(Pageable pageable) {
        return supportRuleRepository.findAll(pageable).stream()
                .map(SupportRuleDto::from)
                .collect(Collectors.toList());
    }

    public void deleteAll() {
        supportRuleRepository.deleteAll();
    }

    public SupportRuleDto read(RegionSearchDto regionSearchDto) {
        return SupportRuleDto.from(supportRuleRepository.findByRegionName(regionSearchDto.getRegion())
                .orElseThrow(NotFoundSupportRuleException::new));
    }

    @Transactional
    public SupportRuleDto update(SupportRuleDto updateSupportRuleDto) {
        SupportRule supportRule = supportRuleRepository.findByRegionName(updateSupportRuleDto.getRegion())
                .orElseThrow(NotFoundSupportRuleException::new);

        supportRule.update(updateSupportRuleDto.createSupportContent());

        return SupportRuleDto.from(supportRule);
    }

    public SupportRuleDto readMinRate() {
        return SupportRuleDto.from(
                supportRuleRepository.findTop1ByOrderBySupportContentRateAsc()
                        .orElseThrow(NotFoundSupportRuleException::new)
        );
    }
}
