package kakaopay.problem.aipservice.dto;

import kakaopay.problem.aipservice.domain.support.SupportContent;
import kakaopay.problem.aipservice.domain.support.SupportRule;
import lombok.Getter;

@Getter
public class SupportRuleDto {
    private String region;

    private String target;

    private String usage;

    private String limit;

    private String rate;

    private String institute;

    private String mgmt;

    private String reception;

    public SupportRuleDto(String region, String target, String usage, String limit,
                          String rate, String institute, String mgmt, String reception) {
        this.region = region;
        this.target = target;
        this.usage = usage;
        this.limit = limit;
        this.rate = rate;
        this.institute = institute;
        this.mgmt = mgmt;
        this.reception = reception;
    }

    public static SupportRuleDto from(SupportRule supportRule) {
        SupportContent content = supportRule.getSupportContent();

        return new SupportRuleDto(
                supportRule.getRegion().getName(),
                content.getTarget(),
                content.getUsage(),
                content.getLimit(),
                content.getRate(),
                content.getInstitute(),
                content.getMgmt(),
                content.getReception()
        );
    }
}
