package thi.app.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import thi.app.model.entity.AbstractEntity;
import thi.app.model.entity.SubCategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO extends AbstractDTOEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty
    private String name;

    @Size(max = 256)
    private String imageUrl;

    private Set<SubCategory> subCategories;
}
