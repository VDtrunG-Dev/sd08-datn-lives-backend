package com.poly.datn.service.impl;

import com.poly.datn.model.TRole;
import com.poly.datn.repository.IRoleRepository;
import com.poly.datn.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public List<TRole> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Page<TRole> getAll(Integer status, Pageable pageable) {
        return roleRepository.findByStatus(status, pageable);
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

    @Override
    public Page<TRole> getInActiveRoles(Integer status, Pageable pageable) {
        return roleRepository.findByStatus(status, pageable);
    }

    @Override
    public Page<TRole> getAllStatus(Integer page) {
        Pageable pageable = PageRequest.of(page, 5);
        return roleRepository.findAll(pageable);
    }

    @Override
    public Page<TRole> searchAll(String nameRole, String description, Long id, String createdBy, Pageable pageable) {
        List<TRole> filteredRoles = roleRepository.findAll(pageable)
                .stream()
                .filter(role -> role.getName().contains(nameRole))
                .filter(role -> role.getDescription().contains(description))
                .filter(role -> role.getCreatedBy().contains(createdBy))
                .filter(role -> role.getId().equals(id))
                .collect(Collectors.toList());

        return new PageImpl<>(filteredRoles, pageable, filteredRoles.size());

    }





    @Override
    public Page<TRole> searchByKeyword(String keyword, Pageable pageable) {
        List<TRole> filteredRoles = roleRepository.findAll(pageable)
                .stream()
                .filter(role -> role.getName().contains(keyword)
                        || role.getDescription().contains(keyword)
                        || role.getRoleCode().contains(keyword))
                .collect(Collectors.toList());

        return new PageImpl<>(filteredRoles, pageable, filteredRoles.size());
    }
}
