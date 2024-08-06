package com.nhh203.productservice.controller;

import com.nhh203.productservice.Utils.Constants;
import com.nhh203.productservice.exception.wrapper.BadRequestException;
import com.nhh203.productservice.exception.wrapper.NotFoundException;
import com.nhh203.productservice.model.Product;
import com.nhh203.productservice.model.attribute.ProductAttribute;
import com.nhh203.productservice.model.attribute.ProductAttributeValue;
import com.nhh203.productservice.repository.ProductAttributeRepository;
import com.nhh203.productservice.repository.ProductAttributeValueRepository;
import com.nhh203.productservice.repository.ProductRepository;
import com.nhh203.productservice.viewmodel.error.ErrorVm;
import com.nhh203.productservice.viewmodel.productattribute.ProductAttributeValueGetVm;
import com.nhh203.productservice.viewmodel.productattribute.ProductAttributeValuePostVm;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductAttributeValueController {
	private final ProductAttributeValueRepository productAttributeValueRepository;
	private final ProductAttributeRepository productAttributeRepository;
	private final ProductRepository productRepository;

	public ProductAttributeValueController(ProductAttributeValueRepository productAttributeValueRepository, ProductAttributeRepository productAttributeRepository, ProductRepository productRepository) {
		this.productAttributeValueRepository = productAttributeValueRepository;
		this.productAttributeRepository = productAttributeRepository;
		this.productRepository = productRepository;
	}

	@GetMapping({"/backoffice/product-attribute-value/{productId}"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ProductAttributeValueGetVm.class))),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorVm.class))),
	})
	public ResponseEntity<List<ProductAttributeValueGetVm>> listProductAttributeValuesByProductId(@PathVariable("productId") String productId) {
		Product product = productRepository
				.findById(productId)
				.orElseThrow(() -> new BadRequestException(Constants.ERROR_CODE.PRODUCT_NOT_FOUND, productId));
		List<ProductAttributeValueGetVm> productAttributeValueGetVms = productAttributeValueRepository
				.findAllByProduct(product).stream()
				.map(ProductAttributeValueGetVm::fromModel)
				.toList();
		return ResponseEntity.ok(productAttributeValueGetVms);
	}

	@PutMapping("/backoffice/product-attribute-value/{id}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "No content"),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorVm.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorVm.class)))})
	public ResponseEntity<Void> updateProductAttributeValue(@PathVariable Long id, @Valid @RequestBody final ProductAttributeValuePostVm productAttributeValuePostVm) {
		ProductAttributeValue productAttributeValue = productAttributeValueRepository
				.findById(id)
				.orElseThrow(() -> new NotFoundException(Constants.ERROR_CODE.PRODUCT_ATTRIBUTE_VALUE_IS_NOT_FOUND, id));
		productAttributeValue.setValue(productAttributeValuePostVm.value());
		productAttributeValueRepository.saveAndFlush(productAttributeValue);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/backoffice/product-attribute-value")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = ProductAttributeValueGetVm.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorVm.class)))})
	public ResponseEntity<ProductAttributeValueGetVm> createProductAttributeValue(@Valid @RequestBody ProductAttributeValuePostVm productAttributeValuePostVm, UriComponentsBuilder uriComponentsBuilder) {
		ProductAttributeValue productAttributeValue = new ProductAttributeValue();
		if (productAttributeValuePostVm.ProductId() != null) {
			Product product = productRepository
					.findById(productAttributeValuePostVm.ProductId())
					.orElseThrow(() -> new BadRequestException(Constants.ERROR_CODE.PRODUCT_NOT_FOUND, productAttributeValuePostVm.ProductId()));
			productAttributeValue.setProduct(product);
		}
		if (productAttributeValuePostVm.productAttributeId() != null) {
			ProductAttribute productAttribute = productAttributeRepository
					.findById(productAttributeValuePostVm.productAttributeId())
					.orElseThrow(() -> new BadRequestException(Constants.ERROR_CODE.PRODUCT_ATTRIBUTE_IS_NOT_FOUND, productAttributeValuePostVm.productAttributeId()));
			productAttributeValue.setProductAttribute(productAttribute);
		}
		productAttributeValue.setValue(productAttributeValuePostVm.value());
		ProductAttributeValue savedProductAttributeValue = productAttributeValueRepository.saveAndFlush(productAttributeValue);
		ProductAttributeValueGetVm productAttributeValueGetVm = ProductAttributeValueGetVm.fromModel(savedProductAttributeValue);
		return ResponseEntity.created(uriComponentsBuilder.replacePath("/product-attribute-value/{id}").buildAndExpand(savedProductAttributeValue.getId()).toUri())
				.body(productAttributeValueGetVm);
	}

	@DeleteMapping("/backoffice/product-attribute-value/{id}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "No content"),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorVm.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorVm.class)))})
	public ResponseEntity<Void> deleteProductAttributeValueById(@PathVariable Long id) {
		Optional<ProductAttributeValue> productAttributeValue = productAttributeValueRepository.findById(id);
		if (productAttributeValue.isEmpty())
			throw new NotFoundException(Constants.ERROR_CODE.PRODUCT_ATTRIBUTE_VALUE_IS_NOT_FOUND, id);
		productAttributeValueRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
