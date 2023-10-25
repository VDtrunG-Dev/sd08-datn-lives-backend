package com.poly.datn.service;

import com.poly.datn.model.TRole;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<TRole> getAllRoles();

    Optional<TRole> getRoleById(Long id);

    TRole createRole(TRole role);

    TRole updateRole(Long id, TRole updatedRole);

    void deleteRole(Long id);
}
