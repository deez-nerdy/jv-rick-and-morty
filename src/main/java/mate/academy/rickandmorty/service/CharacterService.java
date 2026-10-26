package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

public interface CharacterService {
    void saveAllCharacters(List<CharacterDto> characters);

    List<CharacterDto> getCharactersByName(String name);

    CharacterDto getRandomCharacter();
}
