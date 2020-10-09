package {{path}}.{{entity.name}}.service;

import com.rmaslov.blog.base.api.request.SearchRequest;
import com.rmaslov.blog.base.api.response.SearchResponse;
import {{path}}.{{entity.name}}.api.request.RegistrationRequest;
import {{path}}.{{entity.name}}.api.request.{{enity.nameUpper}}Request;
import {{path}}.{{entity.name}}.exception.{{enity.nameUpper}}ExistException;
import {{path}}.{{entity.name}}.exception.{{enity.nameUpper}}NotExistException;
import {{path}}.{{entity.name}}.model.{{enity.nameUpper}}Doc;
import {{path}}.{{entity.name}}.repository.{{enity.nameUpper}}Repository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.{{enity.nameUpper}};
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class {{enity.nameUpper}}ApiService {
    private final {{enity.nameUpper}}Repository {{enity.name}}Repository;
    private final MongoTemplate mongoTemplate;

    public {{enity.nameUpper}}Doc create({{enity.nameUpper}}Request request) throws {{enity.nameUpper}}ExistException {
        {{enity.nameUpper}}Doc {{enity.name}}Doc = {{enity.nameUpper}}Mapping.getInstance().getRequest().convert(request);
        return {{enity.name}}Doc;
    }

    public Optional<{{enity.nameUpper}}Doc> findById(ObjectId id){
        return  {{enity.name}}Repository.findById(id);
    }

    public SearchResponse<{{enity.nameUpper}}Doc> search(
             SearchRequest request
    ){
        Criteria criteria = new Criteria();
        if(request.getQuery() != null && request.getQuery()  != ""){
            criteria = criteria.orOperator(
                    // TODO: Add Criteria
                    //Criteria.where("firstName").regex(request.getQuery() , "i"),
            );
        }

        Query query = new Query(criteria);
        Long count = mongoTemplate.count(query, {{enity.nameUpper}}Doc.class);

        query.limit(request.getSize());
        query.skip(request.getSkip());

        List<{{enity.nameUpper}}Doc> {{enity.name}}Docs = mongoTemplate.find(query, {{enity.nameUpper}}Doc.class);
        return SearchResponse.of({{enity.name}}Docs, count);
    }

    public {{enity.nameUpper}}Doc update({{enity.nameUpper}}Request request) throws {{enity.nameUpper}}NotExistException {
        Optional<{{enity.nameUpper}}Doc> {{enity.name}}DocOptional = {{enity.name}}Repository.findById(request.getId());
        if({{enity.name}}DocOptional.isPresent() == false){
            throw new {{enity.nameUpper}}NotExistException();
        }

        {{enity.nameUpper}}Doc {{enity.name}}Doc = {{enity.nameUpper}}Mapping.getInstance().getRequest().convert(request);
        {{enity.name}}Doc.setId(request.getId());
        {{enity.name}}Repository.save({{enity.name}}Doc);

        return {{enity.name}}Doc;
    }

    public void delete(ObjectId id){
        {{enity.name}}Repository.deleteById(id);
    }
}
