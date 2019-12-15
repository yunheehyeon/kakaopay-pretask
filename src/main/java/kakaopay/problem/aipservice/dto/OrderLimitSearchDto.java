package kakaopay.problem.aipservice.dto;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter
public class OrderLimitSearchDto {
    private int size;

    public OrderLimitSearchDto() {
    }

    public OrderLimitSearchDto(int size) {
        this.size = size;
    }

    public void setSize(int size) {
        int maxSize = 50;
        this.size = Math.min(size, maxSize);
    }

    public PageRequest toPageable() {
        return PageRequest.of(0, size, Sort.Direction.DESC, "supportContentLimit", "supportContentRate");
    }

}
