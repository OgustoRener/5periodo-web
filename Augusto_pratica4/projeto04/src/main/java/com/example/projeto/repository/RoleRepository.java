// src/main/java/com/example/projeto/repository/RoleRepository.java
package com.example.projeto.repository;
import com.example.projeto.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
