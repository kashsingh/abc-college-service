package org.abc.data.repository;

import org.abc.data.entity.security.Authority;
import org.abc.data.entity.security.AuthorityName;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<Authority, Integer>{

    Authority findByName(AuthorityName name);
}
