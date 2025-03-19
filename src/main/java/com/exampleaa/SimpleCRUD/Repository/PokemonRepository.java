package com.exampleaa.SimpleCRUD.Repository;

import com.exampleaa.SimpleCRUD.Entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {
}
