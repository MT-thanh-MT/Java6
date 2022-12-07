package thi.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thi.app.Repository.RoleRepository;
import thi.app.model.dto.RoleDTO;
import thi.app.model.mapper.RoleMapper;
import thi.app.service.IRoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<RoleDTO> getAll() {
        return roleMapper.toDto(roleRepository.findAll());
    }
}
