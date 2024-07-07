package com.akilesh.socialmedia.dto;

import org.springframework.data.domain.Sort;

import lombok.Getter;
import lombok.Setter;

/**
 * @author AkileshVasudevan
 */
@Getter
@Setter
public class SortDto {
    private Sort.Direction sortBy;
    private String sortByColumns;

}
