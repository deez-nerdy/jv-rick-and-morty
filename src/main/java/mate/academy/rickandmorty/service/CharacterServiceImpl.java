package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;

    @Transactional
    @Override
    public void saveAllCharacters(List<CharacterDto> characters) {
        if (characters.isEmpty()) {
            throw new IllegalArgumentException("List of characters is empty");
        }
        List<Character> entities = characters.stream()
                .map(characterMapper::characterDtoToCharacter).toList();
        characterRepository.saveAll(entities);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CharacterDto> getCharactersByName(String name) {
        return characterRepository.findByNameContainingIgnoreCase(name).stream()
                .map(characterMapper::characterToCharacterDto).toList();
    }

    @Override
    public CharacterDto getRandomCharacter() {
        List<Character> characters = characterRepository.findAll();
        if (characters.isEmpty()) {
            throw new NoSuchElementException("List of characters is empty");
        }
        return characterMapper.characterToCharacterDto(
                characters.get(new Random().nextInt(characters.size())));
    }
}
