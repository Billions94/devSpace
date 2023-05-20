package com.api.devSpace.space.Repository;

import com.api.devSpace.space.Entity.Space;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceRepository extends JpaRepository<Space, Long> {
    boolean existsByName(String name);
    @Override
    @NotNull
    Space getById(@NotNull Long id);
}
