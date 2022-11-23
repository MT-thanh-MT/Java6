package thi.app.model.jwt;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtRequest {

    private String username;
    private String password;

}
