package com.nhh203.promotionservice.model.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ElementCollection;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

// promotion request
public class PromotionDTO {
    private Long id;
    private String name;
    private String description;
    private String code; // Mã giảm giá
    private Boolean isActive;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private double discountValue; // Giá trị ưu đãi
    private Long idUser;

    @ElementCollection
    private List<String> idProducts;
}
