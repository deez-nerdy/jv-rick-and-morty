package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.external.CharacterResponseResultDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    CharacterDto characterResponseResultDtoToCharacterDto(
            CharacterResponseResultDto characterResponseResultDto);

    @Mapping(source = "id", target = "externalId")
    Character characterDtoToCharacter(CharacterDto characterDto);

    CharacterDto characterToCharacterDto(Character character);
}
