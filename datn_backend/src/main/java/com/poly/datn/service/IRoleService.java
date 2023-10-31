package com.poly.datn.service;

import com.poly.datn.model.TRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<TRole> getAllRoles();
    Page<TRole> getAll(Integer status, Pageable pageable);

    Optional<TRole> getRoleById(Long id);

    TRole createRole(TRole role);

    TRole updateRole(Long id, TRole updatedRole);

    void deleteRole(Long id);
    Page<TRole> getInActiveRoles(Integer status, Pageable pageable);
    Page<TRole> getAllStatus(Integer page);

    List<TRole> searchAll(String nameRole, String description, String createdBy);
    List<TRole> searchByKeyword(String keyword);

}
