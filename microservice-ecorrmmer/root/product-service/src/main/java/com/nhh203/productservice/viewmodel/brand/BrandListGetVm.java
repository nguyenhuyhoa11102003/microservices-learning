package com.nhh203.productservice.viewmodel.brand;

import java.util.List;

public record BrandListGetVm(List<BrandVm> brandContent,
                             int pageNo,
                             int pageSize,
                             int totalElements,
                             int totalPages,
                             boolean isLast) {
}
