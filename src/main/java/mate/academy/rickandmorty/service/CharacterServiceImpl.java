package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        List<Character> entities = characters.stream()
                .map(characterMapper::characterDtoToCharacter).toList();
        characterRepository.saveAll(entities);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CharacterDto> getCharactersByName(String name, Pageable pageable) {
        return characterRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(characterMapper::characterToCharacterDto);
    }

    @Override
    public CharacterDto getRandomCharacter() {
        List<Character> characters = characterRepository.findAll();
        return characterMapper.characterToCharacterDto(
                characters.get(new Random().nextInt(characters.size())));
    }
}
