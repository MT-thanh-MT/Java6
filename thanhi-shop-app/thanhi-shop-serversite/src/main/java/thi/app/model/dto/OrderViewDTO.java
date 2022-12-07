package thi.app.model.dto;

import lombok.*;
import thi.app.model.entity.Status;

import java.io.Serializable;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderViewDTO extends AbstractDTOEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long accountId;

    private String payer;

    private String address;

    private String status;

    private Set<OrderDetailViewDTO> orderDetailView;
}
