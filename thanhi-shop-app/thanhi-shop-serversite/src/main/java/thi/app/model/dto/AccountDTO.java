package thi.app.model.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO extends AbstractDTOEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;

    @NotNull
    private String password;

    private String firstName;

    private String lastName;

    @Email
    private String email;

    private boolean activated = false;

    private Set<String> roles = new HashSet<>();
}
