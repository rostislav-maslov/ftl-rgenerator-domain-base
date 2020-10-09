package {{path}}.{{entity.name}}.controller;

import com.rmaslov.blog.base.api.request.SearchRequest;
import com.rmaslov.blog.base.api.response.OkResponse;
import com.rmaslov.blog.base.api.response.SearchResponse;
import {{path}}.{{entity.name}}.api.request.RegistrationRequest;
import {{path}}.{{entity.name}}.api.request.{{enity.nameUpper}}Request;
import {{path}}.{{entity.name}}.api.response.{{enity.nameUpper}}FullResponse;
import {{path}}.{{entity.name}}.api.response.{{enity.nameUpper}}Response;
import {{path}}.{{entity.name}}.exception.{{enity.nameUpper}}ExistException;
import {{path}}.{{entity.name}}.exception.{{enity.nameUpper}}NotExistException;
import {{path}}.{{entity.name}}.mapping.{{enity.nameUpper}}Mapping;
import {{path}}.{{entity.name}}.routes.{{enity.nameUpper}}ApiRoutes;
import {{path}}.{{entity.name}}.service.{{enity.nameUpper}}ApiService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Api(value = "{{enity.nameUpper}} API")
public class {{enity.nameUpper}}ApiController {
    private final {{enity.nameUpper}}ApiService {{enity.name}}ApiService;

    @PostMapping({{enity.nameUpper}}ApiRoutes.ROOT)
    @ApiOperation(value = "Create", notes = "Use this when you need create new {{enity.name}}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "{{enity.nameUpper}} already exist")
    })
    public OkResponse<{{enity.nameUpper}}FullResponse> registration(@RequestBody RegistrationRequest request) throws {{enity.nameUpper}}ExistException {
        return OkResponse.of({{enity.nameUpper}}Mapping.getInstance().getResponseFull().convert({{enity.name}}ApiService.create(request)));
    }

    @GetMapping({{enity.nameUpper}}ApiRoutes.BY_ID)
    @ApiOperation(value = "Find {{enity.name}} by ID", notes = "Use this when you need full info about {{enity.name}}")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 404, message = "{{enity.nameUpper}} not found")
            }
    )
    public OkResponse<{{enity.nameUpper}}Response> byId(
            @ApiParam(value = "{{enity.nameUpper}} id") @PathVariable ObjectId id
    ) throws ChangeSetPersister.NotFoundException {
        return OkResponse.of({{enity.nameUpper}}Mapping.getInstance().getResponseFull().convert(
                {{enity.name}}ApiService.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new)
        ));
    }

    @GetMapping({{enity.nameUpper}}ApiRoutes.ROOT)
    @ApiOperation(value = "Search {{enity.name}}", notes = "Use this when you need find {{enity.name}} by ?????")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success")
            }
    )
    public OkResponse<SearchResponse<{{enity.nameUpper}}Response>> search(
            @ModelAttribute SearchRequest request
            ){
        return OkResponse.of({{enity.nameUpper}}Mapping.getInstance().getSearch().convert(
                {{enity.name}}ApiService.search(request)
        ));
    }

    @PutMapping({{enity.nameUpper}}ApiRoutes.BY_ID)
    @ApiOperation(value = "Update {{enity.name}}", notes = "Use this when you need update {{enity.name}} info")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success"),
                    @ApiResponse(code = 400, message = "{{enity.nameUpper}} ID invalid")
            }
    )
    public OkResponse<{{enity.nameUpper}}FullResponse> updateById(
            @ApiParam(value = "{{enity.nameUpper}} id")  @PathVariable String id,
            @RequestBody {{enity.nameUpper}}Request {{enity.name}}Request
            ) throws {{enity.nameUpper}}NotExistException {
        return OkResponse.of({{enity.nameUpper}}Mapping.getInstance().getResponseFull().convert(
                {{enity.name}}ApiService.update({{enity.name}}Request)
        ));
    }

    @DeleteMapping({{enity.nameUpper}}ApiRoutes.BY_ID)
    @ApiOperation(value = "Delete {{enity.name}}", notes = "Use this when you need delete {{enity.name}}")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success")
            }
    )
    public OkResponse<String> deleteById(
            @ApiParam(value = "{{enity.nameUpper}} id") @PathVariable ObjectId id
    ){
        {{enity.name}}ApiService.delete(id);
        return OkResponse.of(HttpStatus.OK.toString());
    }
}
