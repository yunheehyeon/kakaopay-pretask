package kakaopay.problem.aipservice.dto;

import lombok.Getter;

@Getter
public class RegionSearchDto {
    private String region;

    public RegionSearchDto() {
    }

    public RegionSearchDto(String region) {
        this.region = region;
    }
}
