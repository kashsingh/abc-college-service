package org.abc.data.repository;

import org.abc.data.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findRoleByRoleName(String roleName);

}
