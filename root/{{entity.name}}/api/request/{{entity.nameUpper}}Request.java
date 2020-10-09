package {{path}}.{{entity.name}}.api.request;

import {{path}}.{{entity.name}}.model.Address;
import {{path}}.{{entity.name}}.model.Company;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@ApiModel(value = "{{enity.nameUpper}}Request", description = "Model for update {{enity.name}}")
public class {{enity.nameUpper}}Request {
    {{#entityProperties}}
        {{level}} {{type}} {{name}};
    {{/entityProperties}}
}
