package com.nhh203.orderservice.viewmodel.checkout;

import com.nhh203.orderservice.model.Checkout;
import lombok.Builder;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record CheckoutVm(String id,
                         String email,
                         String note,
                         String couponCode,
                         Set<CheckoutItemVm> checkoutItemVms) {
	public static CheckoutVm fromModel(Checkout checkout) {
		Set<CheckoutItemVm> checkoutItemVms = checkout.getCheckoutItem().stream()
				.map(CheckoutItemVm::fromModel)
				.collect(Collectors.toSet());
		return CheckoutVm.builder()
				.id(checkout.getId())
				.email(checkout.getEmail())
				.note(checkout.getNote())
				.couponCode(checkout.getCouponCode())
				.checkoutItemVms(checkoutItemVms)
				.build();
	}
}
