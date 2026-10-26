package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Characters management", description = "Endpoint for managing chars")
@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping
    @Operation(summary = "Search by name",
            description = "Search character by name or part of it")
    public List<CharacterDto> getCharactersByName(@RequestParam @Valid String name) {
        return characterService.getCharactersByName(name);
    }

    @GetMapping("/random")
    @Operation(summary = "Get random char",
            description = "Return random character from character list")
    public CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }
}
