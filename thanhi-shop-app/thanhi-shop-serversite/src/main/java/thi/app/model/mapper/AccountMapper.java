package thi.app.model.mapper;

import org.springframework.stereotype.Service;
import thi.app.model.dto.AccountDTO;
import thi.app.model.entity.Account;

@Service
public interface AccountMapper extends EntityMapper<AccountDTO, Account>{
}
