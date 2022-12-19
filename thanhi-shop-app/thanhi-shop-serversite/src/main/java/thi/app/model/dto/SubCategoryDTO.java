package thi.app.model.dto;

import lombok.*;
import thi.app.model.entity.Product;

import java.io.Serializable;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubCategoryDTO extends AbstractDTOEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String imageUrl;

    private Long cateId;

    private Set<Product> productList;
}
