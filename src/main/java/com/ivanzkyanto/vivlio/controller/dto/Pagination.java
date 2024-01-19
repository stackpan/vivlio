package com.ivanzkyanto.vivlio.controller.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Pagination extends RepresentationModel<Pagination> {

    private Integer currentPage;

    private Integer totalPages;

    private Long totalItems;

    private Integer pagePerItem;

    private Integer totalCurrentPageItems;

}
