package com.portfolioargprg.lns.security.Repository;

import com.portfolioargprg.lns.security.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByNombredeUsuario(String nombredeUsuario);

    boolean existsByNombredeUsuario(String nombredeUsuario);
    boolean existsByEmail(String email);
}
