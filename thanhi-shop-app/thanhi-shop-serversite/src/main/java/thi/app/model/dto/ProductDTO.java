package thi.app.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import thi.app.model.entity.OrderDetail;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO extends AbstractDTOEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 255)
    private String name;

    @Size(max = 256)
    private String imageUrl;

    @Min(1)
    private BigDecimal sellPrice;

    @Min(1)
    private BigDecimal originPrice;

    @JsonProperty("statusId")
    private String status;

    private Long subCateId;

    private Instant hot = Instant.now();

    @Min(0)
    private Integer quantity;

    @Size(min = 1,max = 500)
    private String description;

    @Min(0)
    private Integer sold;

    private Set<OrderDetail> orderDetailList;
}
