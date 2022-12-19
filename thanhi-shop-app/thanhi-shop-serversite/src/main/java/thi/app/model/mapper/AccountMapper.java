package thi.app.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import thi.app.model.dto.AccountDTO;
import thi.app.model.entity.Account;

@Mapper(componentModel = "spring", uses = {})
public interface AccountMapper extends EntityMapper<AccountDTO, Account>{
    @Mapping(target="roles", expression="java(account.getRoles().stream().map(role -> role.getName()).collect(java.util.stream.Collectors.toSet()))")
    AccountDTO toDto(Account account);

    @Mapping(target="roles", expression="java(accountDTO.getRoles().stream().map(name -> {return new thi.app.model.entity.Role(name);}).collect(java.util.stream.Collectors.toSet()))")
    Account toEntity(AccountDTO accountDTO);
    default Account fromId(Long id) {
        if (id == null) {
            return null;
        }
        Account acc = new Account();
        acc.setId(id);
        return acc;
    }

}
