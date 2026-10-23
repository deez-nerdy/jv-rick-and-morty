package mate.academy.rickandmorty.startup;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.CharactersClient;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExternalApiSyncStartUp implements CommandLineRunner {
    private final CharactersClient charactersClient;
    private final CharacterService characterService;

    @Override
    public void run(String... args) throws Exception {
        try {
            List<CharacterDto> characters = charactersClient.getCharacters();
            characterService.saveAllCharacters(characters);
            System.out.println("Data sync completed successfully!");
        } catch (Exception e) {
            throw new RuntimeException("Failed to sync data on startup", e);
        }
    }
}
