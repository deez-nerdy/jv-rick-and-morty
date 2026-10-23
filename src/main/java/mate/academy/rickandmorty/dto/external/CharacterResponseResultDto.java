package mate.academy.rickandmorty.dto.external;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class CharacterResponseResultDto {
    private Long id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private Map<String, String> origin;
    private Map<String, String> location;
    private String image;
    private List<String> episode;
    private String url;
    private String created;
}
