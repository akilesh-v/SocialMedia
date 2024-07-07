package com.akilesh.socialmedia.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.Getter;
import lombok.Setter;

/**
 * @author AkileshVasudevan
 */
@Getter
@Setter
public class PageRequestDto {
    private int pageNo = 0;
    private int pageSize = 10;
    private List<SortDto> sort;

    public Pageable getPageable(PageRequestDto dto) {
        return PageRequest.of(dto.getPageNo(), dto.getPageSize(),getSortOrder(dto.getSort()));
    }

    public Sort getSortOrder(List<SortDto> sortDtoList) {
        List<Sort.Order> orderList = new ArrayList<>();
        if (sortDtoList == null || sortDtoList.isEmpty()) {
            return Sort.by(Sort.Order.desc("id"));
        } else {
            for (SortDto sortDto : sortDtoList) {
                orderList.add(new Sort.Order(getSortDirection(sortDto.getSortBy()), sortDto.getSortByColumns()));
            }
            return Sort.by(orderList);
        }
    }

    private Sort.Direction getSortDirection(Sort.Direction direction) {
        if (direction.isDescending()) {
            return Sort.Direction.DESC;
        } else {
            return Sort.Direction.ASC;
        }
    }
}
