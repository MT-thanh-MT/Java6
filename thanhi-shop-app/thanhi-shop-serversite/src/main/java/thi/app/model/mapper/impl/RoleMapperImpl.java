package thi.app.model.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thi.app.model.dto.RoleDTO;
import thi.app.model.entity.Role;
import thi.app.model.mapper.RoleMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RoleMapperImpl implements RoleMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Role toEntity(RoleDTO dto) {
        Role role = modelMapper.map(dto, Role.class);
        return role;
    }

    @Override
    public RoleDTO toDto(Role entity) {
        RoleDTO roleDTO = modelMapper.map(entity, RoleDTO.class);
        return roleDTO;
    }

    @Override
    public List<Role> toEntity(List<RoleDTO> dtoList) {
        return dtoList.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoleDTO> toDto(List<Role> entityList) {
        return entityList.stream()
                .filter(Objects::nonNull)
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
