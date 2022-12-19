package thi.app.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import thi.app.common.Constants;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_accounts")
@Indexed
public class Account extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = Constants.USERNAME_REGEX)
    @Column(length = 50, unique = true, nullable = false)
    @FullTextField
    private String username;

    @JsonIgnore
    @NotNull
    @Column(name = "password_hash",length = 130, nullable = false)
    private String password;

    @Column(name = "first_name",length = 50)
    @FullTextField
    private String firstName;

    @Column(name = "last_name",length = 50)
    @FullTextField
    private String lastName;

    @Email
    @Column(unique = true, length = 100)
    @FullTextField
    private String email;

    @Column
    private boolean activated = false;

    @ManyToMany
    @JoinTable(name = "tb_authorities" ,
            joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "name")})
    private Set<Role> roles = new HashSet<>();
}
