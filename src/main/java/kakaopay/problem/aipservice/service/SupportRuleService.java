package kakaopay.problem.aipservice.service;

import kakaopay.problem.aipservice.domain.region.Region;
import kakaopay.problem.aipservice.domain.support.SupportContent;
import kakaopay.problem.aipservice.domain.support.SupportRule;
import kakaopay.problem.aipservice.domain.support.SupportRuleRepository;
import kakaopay.problem.aipservice.dto.SupportRuleDto;
import kakaopay.problem.aipservice.filereader.CSVFileReader;
import kakaopay.problem.aipservice.filereader.FileReader;
import kakaopay.problem.aipservice.filereader.Record;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupportRuleService {
    private final SupportRuleRepository supportRuleRepository;
    private final RegionService regionService;

    public SupportRuleService(SupportRuleRepository supportRuleRepository, RegionService regionService) {
        this.supportRuleRepository = supportRuleRepository;
        this.regionService = regionService;
    }

    public List<SupportRuleDto> saveCSVFile(File file) {
        FileReader fileReader = new CSVFileReader(new File("./src/main/resources/static/csv/problem1.csv"));

        return fileReader.getRecodes().stream()
                .map(this::saveRecode)
                .map(SupportRuleDto::from)
                .collect(Collectors.toList());
    }

    private SupportRule saveRecode(Record record) {
        SupportContent supportContent = SupportContent.from(record);
        Region region = regionService.findOrSave(record.getRegion());

        return supportRuleRepository.save(new SupportRule(region, supportContent));
    }
}
