package thi.app.model.dto;

import lombok.*;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailDTO extends AbstractDTOEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long orderId;

    private Long productId;

    @Min(0)
    private BigDecimal price;

    @Min(0)
    private Integer quantity;
}
