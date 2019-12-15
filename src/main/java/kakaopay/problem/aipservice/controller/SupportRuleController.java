package kakaopay.problem.aipservice.controller;

import kakaopay.problem.aipservice.dto.RegionSearchDto;
import kakaopay.problem.aipservice.dto.OrderLimitSearchDto;
import kakaopay.problem.aipservice.dto.SupportRuleDto;
import kakaopay.problem.aipservice.service.SupportRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/support")
public class SupportRuleController {
    private final SupportRuleService supportRuleService;

    public SupportRuleController(SupportRuleService supportRuleService) {
        this.supportRuleService = supportRuleService;
    }

    @GetMapping
    public ResponseEntity<List<SupportRuleDto>> read(Pageable pageable) {
        return ResponseEntity.ok(supportRuleService.read(pageable.of()));
    }

    @GetMapping("/minRate")
    public ResponseEntity<SupportRuleDto> read() {
        return ResponseEntity.ok(supportRuleService.readMinRate());
    }

    @PostMapping("/search")
    public ResponseEntity<List<SupportRuleDto>> read(@RequestBody OrderLimitSearchDto orderLimitSearchDto) {
        return ResponseEntity.ok(supportRuleService.read(orderLimitSearchDto.toPageable()));
    }

    @PostMapping
    public ResponseEntity<SupportRuleDto> read(@RequestBody RegionSearchDto regionSearchDto) {
        return ResponseEntity.ok(supportRuleService.read(regionSearchDto));
    }

    @PutMapping
    public ResponseEntity<SupportRuleDto> update(@RequestBody SupportRuleDto supportRuleDto) {
        return ResponseEntity.ok(supportRuleService.update(supportRuleDto));
    }

    @DeleteMapping
    public ResponseEntity removeAll() {
        supportRuleService.deleteAll();

        return ResponseEntity.ok().build();
    }
}
