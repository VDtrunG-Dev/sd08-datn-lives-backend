package com.poly.datn.service.impl;

import com.poly.datn.model.TRole;
import com.poly.datn.repository.IRoleRepository;
import com.poly.datn.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public List<TRole> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<TRole> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public TRole createRole(TRole role) {
        return roleRepository.save(role);
    }

    @Override
    public TRole updateRole(Long id, TRole updatedRole) {
        return roleRepository.findById(id)
                .map(existingRole -> {
                    existingRole.setRoleCode(updatedRole.getRoleCode());
                    existingRole.setName(updatedRole.getName());
                    existingRole.setDescription(updatedRole.getDescription());
                    existingRole.setUpdatedBy(updatedRole.getUpdatedBy());
                    existingRole.setStatus(updatedRole.getStatus());
                    return roleRepository.save(existingRole);
                })
                .orElse(null); // Handle error or return null if the role doesn't exist
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.findById(id).ifPresent(role -> roleRepository.delete(role));
    }
}
