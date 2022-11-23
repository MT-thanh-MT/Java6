package thi.app.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_status")
public class Status implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @Column(length = 50)
    private String name;

    @OneToMany(mappedBy = "status")
    @JsonIgnore
    private Set<Product> productList;
}
