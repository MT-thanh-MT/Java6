package thi.app.model.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thi.app.model.dto.AccountDTO;
import thi.app.model.entity.Account;
import thi.app.model.entity.Role;
import thi.app.model.mapper.AccountMapper;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

//@Service
public class AccountMapperImpl implements AccountMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Account toEntity(AccountDTO dto) {
        Account acc = new Account();
        acc.setId(dto.getId());
        acc.setPassword(dto.getPassword());
        acc.setEmail(dto.getEmail());
        acc.setActivated(dto.isActivated());
        acc.setUsername(dto.getUsername());
//        acc.setRoles();
        if (!dto.getRoles().isEmpty()) {
            Set<Role> roles = dto.getRoles().stream().map(name -> {
                return new Role(name);
            }).collect(Collectors.toSet());
            acc.setRoles(roles);
        }
        return acc;
    }

    @Override
    public AccountDTO toDto(Account entity) {
        AccountDTO accountDTO = modelMapper.map(entity, AccountDTO.class);
        if (!entity.getRoles().isEmpty()) {
            Set<String> roles = entity.getRoles().stream()
                    .map(role -> role.getName())
                    .collect(Collectors.toSet());
            accountDTO.setRoles(roles);
        }
        return accountDTO;
    }

    @Override
    public List<Account> toEntity(List<AccountDTO> dtoList) {
        return dtoList.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountDTO> toDto(List<Account> entityList) {
        return entityList.stream()
                .filter(Objects::nonNull)
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
