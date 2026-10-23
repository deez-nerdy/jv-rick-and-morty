package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CharacterService {
    void saveAllCharacters(List<CharacterDto> characters);

    Page<CharacterDto> getCharactersByName(String name, Pageable pageable);

    CharacterDto getRandomCharacter();
}
