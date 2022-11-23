package thi.app.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_roles")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @Column(length = 50)
    private String name;

}
