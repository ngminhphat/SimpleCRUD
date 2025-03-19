package com.exampleaa.SimpleCRUD.Service;

import com.exampleaa.SimpleCRUD.DTO.PokemonDTO;
import com.exampleaa.SimpleCRUD.Entity.Pokemon;
import com.exampleaa.SimpleCRUD.Repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    public List<PokemonDTO> getAllPokemons() {
        return pokemonRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PokemonDTO getPokemonById(int id) {
        return pokemonRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public PokemonDTO createPokemon(PokemonDTO pokemonDTO) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDTO.getName());
        pokemon.setType(pokemonDTO.getType());
        pokemon.setLevel(pokemonDTO.getLevel());
        pokemon.setGender(pokemonDTO.getGender());
        pokemon.setHeight(pokemonDTO.getHeight());
        pokemon.setWeight(pokemonDTO.getWeight());
        pokemon.setLegendary(pokemonDTO.isLegendary());

        Pokemon savedPokemon = pokemonRepository.save(pokemon);
        return convertToDTO(savedPokemon);
    }

    public PokemonDTO updatePokemon(int id, PokemonDTO pokemonDTO) {
        return pokemonRepository.findById(id).map(pokemon -> {
            pokemon.setName(pokemonDTO.getName());
            pokemon.setType(pokemonDTO.getType());
            pokemon.setLevel(pokemonDTO.getLevel());
            pokemon.setGender(pokemonDTO.getGender());
            pokemon.setHeight(pokemonDTO.getHeight());
            pokemon.setWeight(pokemonDTO.getWeight());
            pokemon.setLegendary(pokemonDTO.isLegendary());
            Pokemon updatedPokemon = pokemonRepository.save(pokemon);
            return convertToDTO(updatedPokemon);
        }).orElse(null);
    }

    public boolean deletePokemon(int id) {
        if (pokemonRepository.existsById(id)) {
            pokemonRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private PokemonDTO convertToDTO(Pokemon pokemon) {
        return new PokemonDTO(pokemon.getId(), pokemon.getName(), pokemon.getType(),
                pokemon.getLevel(), pokemon.getGender(), pokemon.getHeight(), pokemon.getWeight(), pokemon.isLegendary());
    }
}
