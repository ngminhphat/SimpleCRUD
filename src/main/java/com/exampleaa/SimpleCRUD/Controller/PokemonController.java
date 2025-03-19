package com.exampleaa.SimpleCRUD.Controller;

import com.exampleaa.SimpleCRUD.DTO.PokemonDTO;
import com.exampleaa.SimpleCRUD.Service.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @Operation(summary = "Get all Pokemons")
    @ApiResponse(responseCode = "200", description = "List of all Pokemons")
    @GetMapping
    public List<PokemonDTO> getAllPokemons() {
        return pokemonService.getAllPokemons();
    }

    @Operation(summary = "Get Pokemon by ID")
    @ApiResponse(responseCode = "200", description = "Pokemon found")
    @ApiResponse(responseCode = "404", description = "Pokemon not found")
    @GetMapping("/{id}")
    public ResponseEntity<PokemonDTO> getPokemonById(@PathVariable int id) {
        PokemonDTO pokemon = pokemonService.getPokemonById(id);
        return pokemon != null ? ResponseEntity.ok(pokemon) : ResponseEntity.notFound().build();
    }
    @Operation(summary = "Create a new Pokemon")
    @ApiResponse(responseCode = "201", description = "Pokemon created successfully")
    @PostMapping
    public ResponseEntity<PokemonDTO> createPokemon(@RequestBody PokemonDTO pokemonDTO) {
        PokemonDTO createdPokemon = pokemonService.createPokemon(pokemonDTO);
        return ResponseEntity.status(201).body(createdPokemon);
    }

    @Operation(summary = "Update an existing Pokemon")
    @ApiResponse(responseCode = "200", description = "Pokemon updated successfully")
    @PutMapping("/{id}")
    public ResponseEntity<PokemonDTO> updatePokemon(@PathVariable int id, @RequestBody PokemonDTO pokemonDTO) {
        PokemonDTO updatedPokemon = pokemonService.updatePokemon(id, pokemonDTO);
        return updatedPokemon != null ? ResponseEntity.ok(updatedPokemon) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete a Pokemon by ID")
    @ApiResponse(responseCode = "204", description = "Pokemon deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable int id) {
        if (pokemonService.deletePokemon(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
