package thi.app.model.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailViewDTO extends AbstractDTOEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long orderId;

    private Long productId;

    private String productImageUrl;

    private String productName;

    private Long subCategoryID;

    private String subCategoryName;

    private BigDecimal price;

    private Integer quantity;


}
