package com.nhh203.productservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_option_value")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOptionValue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "product_option_id", nullable = false)
	private ProductOption productOption;

	private String displayType;

	private int displayOrder;

	private String value;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ProductOptionValue)) {
			return false;
		}
		return id != null && id.equals(((ProductOptionValue) o).id);
	}

	@Override
	public int hashCode() {
		// see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
		return getClass().hashCode();
	}
}
