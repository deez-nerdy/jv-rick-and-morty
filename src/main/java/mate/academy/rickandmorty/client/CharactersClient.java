package mate.academy.rickandmorty.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharactersClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/";
    private static final String CHARACTERS_PATH = "character";
    private static final String PAGE_PATH = "?page=";
    private static final Long SLEEP_TIME = 500L;
    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;

    public List<CharacterDto> getCharacters() {
        HttpClient client = HttpClient.newHttpClient();
        List<CharacterDto> characters = new ArrayList<>();
        int page = 1;
        boolean hasNext = true;

        while (hasNext) {
            String url = BASE_URL + CHARACTERS_PATH + PAGE_PATH + page;
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();

            try {
                HttpResponse<String> response = client.send(request,
                        HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == HttpStatus.TOO_MANY_REQUESTS.value()) {
                    Thread.sleep(1000L);
                    response = client.send(request,
                            HttpResponse.BodyHandlers.ofString());

                    if (response.statusCode() == HttpStatus.TOO_MANY_REQUESTS.value()) {
                        throw new RuntimeException("Too many requests after retry");
                    }
                }

                CharacterResponseDataDto responseDataDto = objectMapper.readValue(response.body(),
                        CharacterResponseDataDto.class);
                hasNext = responseDataDto.getInfo().getNext() != null;
                page++;
                characters.addAll(responseDataDto.getResults().stream()
                        .map(characterMapper::characterResponseResultDtoToCharacterDto)
                        .toList());

                Thread.sleep(SLEEP_TIME);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return characters;
    }
}
