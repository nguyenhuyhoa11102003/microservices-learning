package com.nhh203.productservice.model.attribute;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nhh203.productservice.model.AbstractMappedEntity;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_attribute")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAttribute extends AbstractMappedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "product_attribute_group_id")
    private ProductAttributeGroup productAttributeGroup;

    @OneToMany(mappedBy = "productAttribute")
    @JsonIgnore
    private List<ProductAttributeTemplate> productAttributeTemplates = new ArrayList<>();

    @OneToMany(mappedBy = "productAttribute")
    private List<ProductAttributeValue> attributeValues = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductAttribute)) {
            return false;
        }
        return id != null && id.equals(((ProductAttribute) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }
}
