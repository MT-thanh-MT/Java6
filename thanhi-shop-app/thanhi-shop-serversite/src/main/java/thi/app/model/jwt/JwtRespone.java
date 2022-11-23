package thi.app.model.jwt;

import lombok.*;
import thi.app.model.dto.AccountDTO;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtRespone {

    private AccountDTO account;
    private String jwtToken;
}
