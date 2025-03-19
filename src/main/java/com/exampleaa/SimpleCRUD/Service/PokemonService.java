package com.exampleaa.SimpleCRUD.Service;

import com.exampleaa.SimpleCRUD.DTO.PokemonDTO;
import com.exampleaa.SimpleCRUD.Entity.Pokemon;
import com.exampleaa.SimpleCRUD.Mapper.PokemonMapper;
import com.exampleaa.SimpleCRUD.Repository.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;
    private final PokemonMapper pokemonMapper;

    public PokemonService(PokemonRepository pokemonRepository, PokemonMapper pokemonMapper) {
        this.pokemonRepository = pokemonRepository;
        this.pokemonMapper = pokemonMapper;
    }

    public List<PokemonDTO> getAllPokemons() {
        return pokemonRepository.findAll().stream()
                .map(pokemonMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PokemonDTO getPokemonById(int id) {
        return pokemonRepository.findById(id)
                .map(pokemonMapper::toDTO)
                .orElse(null);
    }

    public PokemonDTO createPokemon(PokemonDTO pokemonDTO) {
        Pokemon pokemon = pokemonMapper.toEntity(pokemonDTO);
        Pokemon savedPokemon = pokemonRepository.save(pokemon);
        return pokemonMapper.toDTO(savedPokemon);
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
            return pokemonMapper.toDTO(updatedPokemon);
        }).orElse(null);
    }

    public boolean deletePokemon(int id) {
        if (pokemonRepository.existsById(id)) {
            pokemonRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
