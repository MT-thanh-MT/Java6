package thi.app.model.entity;

import lombok.*;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_roles")
@Indexed
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @Column(length = 50)
    @FullTextField
    private String name;

}
