package hexlet.code.dto;

import io.javalin.validation.ValidationError;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BuildUrlPage {
    private String name;
    private Map<String, List<ValidationError<Object>>> errors;
}
