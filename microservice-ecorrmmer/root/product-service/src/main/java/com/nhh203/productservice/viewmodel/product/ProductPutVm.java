package com.nhh203.productservice.viewmodel.product;

import com.nhh203.productservice.validation.ValidateProductPrice;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ProductPutVm(
		@NotEmpty String name,
		String slug,
		@ValidateProductPrice Double price,
		Boolean isAllowedToOrder,
		Boolean isPublished,
		Boolean isFeatured,
		Boolean isVisibleIndividually,
		Boolean stockTrackingEnabled,
		Long brandId,
		List<Integer> categoryIds,
		String shortDescription,
		String description,
		String specification,
		String sku,
		String gtin,
		String metaTitle,
		String metaKeyword,
		String metaDescription,
		Long thumbnailMediaId,
		List<Long> productImageIds,
		List<ProductVariationPutVm> variations,
		List<ProductOptionValuePutVm> productOptionValues,
		List<String> relatedProductIds,
		Long taxClassId
) {
}
