package com.nhh203.orderservice.service;
import com.nhh203.orderservice.config.ServiceUrlConfig;
import com.nhh203.orderservice.viewmodel.order.OrderItemVm;
import com.nhh203.orderservice.viewmodel.order.OrderVm;
import com.nhh203.orderservice.viewmodel.product.ProductQuantityItem;
import com.nhh203.orderservice.viewmodel.product.ProductVariationVM;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;
@Service
@RequiredArgsConstructor
public class ProductService {
    private final RestClient restClient;
    private final ServiceUrlConfig serviceUrlConfig;

    public List<ProductVariationVM> getProductVariations(String productId) {
        final String jwt = ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTokenValue();
        final URI url = UriComponentsBuilder
                .fromHttpUrl(serviceUrlConfig.product())
                .path("/backoffice/product-variations/" + productId)
                .buildAndExpand()
                .toUri();

        return restClient.get()
                .uri(url)
                .headers(h->h.setBearerAuth(jwt))
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<ProductVariationVM>>(){})
                .getBody();
    }

    public void subtractProductStockQuantity(OrderVm orderVm) {

        final String jwt = ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTokenValue();
        final URI url = UriComponentsBuilder
                .fromHttpUrl(serviceUrlConfig.product())
                .path("/backoffice/products/subtract-quantity")
                .buildAndExpand()
                .toUri();

        restClient.put()
                .uri(url)
                .headers(h -> h.setBearerAuth(jwt))
                .body(buildProductQuantityItems(orderVm.orderItemVms()))
                .retrieve();
    }

    private List<ProductQuantityItem> buildProductQuantityItems(Set<OrderItemVm> orderItems) {
        return orderItems.stream()
                .map(orderItem ->
                        ProductQuantityItem
                                .builder()
                                .productId(orderItem.productId())
                                .quantity(Long.valueOf(orderItem.quantity()))
                                .build()
                ).toList();
    }
}
