package {{path}}.{{entity.name}}.api.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@ApiModel(value = "{{enity.nameUpper}}Response", description = "{{enity.nameUpper}} data(for search and list)")
public class {{enity.nameUpper}}Response {
    {{#entityProperties}}
        protected {{type}} {{name}};
    {{/entityProperties}}
}
