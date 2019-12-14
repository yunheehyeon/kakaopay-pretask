package kakaopay.problem.aipservice.controller;

import kakaopay.problem.aipservice.dto.FileDto;
import kakaopay.problem.aipservice.dto.SupportRuleDto;
import kakaopay.problem.aipservice.service.SupportRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/support")
public class SupportRuleController {
    private final SupportRuleService supportRuleService;

    public SupportRuleController(SupportRuleService supportRuleService) {
        this.supportRuleService = supportRuleService;
    }

    @PostMapping
    public ResponseEntity<List<SupportRuleDto>> save(@RequestBody FileDto fileDto) {
        return ResponseEntity.ok(supportRuleService.saveCSVFile(fileDto.getFile()));
    }
}
