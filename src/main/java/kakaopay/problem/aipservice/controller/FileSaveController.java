package kakaopay.problem.aipservice.controller;

import kakaopay.problem.aipservice.dto.SupportRuleDto;
import kakaopay.problem.aipservice.service.SupportRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FileSaveController {
    private final SupportRuleService supportRuleService;

    public FileSaveController(SupportRuleService supportRuleService) {
        this.supportRuleService = supportRuleService;
    }

    @GetMapping("/api/fileread")
    public ResponseEntity<List<SupportRuleDto>> save() {
        return ResponseEntity.ok(supportRuleService.saveCSVFile());
    }
}
