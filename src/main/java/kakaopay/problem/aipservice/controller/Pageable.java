package kakaopay.problem.aipservice.controller;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter
public class Pageable {
    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_SIZE = 10;
    private static final Sort.Direction DEFAULT_DIRECTION = Sort.Direction.ASC;

    private int page;
    private int size;
    private Sort.Direction direction;

    public Pageable() {
        this.page = DEFAULT_PAGE;
        this.size = DEFAULT_SIZE;
        this.direction = Sort.Direction.ASC;
    }

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size) {
        int MAX_SIZE = 50;
        this.size = Math.min(size, MAX_SIZE);
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = (direction != Sort.Direction.ASC) && (direction != Sort.Direction.DESC) ? DEFAULT_DIRECTION : direction;
    }

    public PageRequest of() {
        return PageRequest.of(page -1, size, direction, "region_id");
    }
}
