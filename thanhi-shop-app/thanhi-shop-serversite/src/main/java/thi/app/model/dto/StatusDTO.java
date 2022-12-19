package thi.app.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import thi.app.model.entity.Product;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StatusDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private Set<Product> productList;
}
