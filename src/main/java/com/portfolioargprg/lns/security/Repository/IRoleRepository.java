package com.portfolioargprg.lns.security.Repository;

import com.portfolioargprg.lns.security.enouns.rolname;
import com.portfolioargprg.lns.security.entity.role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<role, Integer>{
    Optional<role> findByRolName(rolname rolName);
}
