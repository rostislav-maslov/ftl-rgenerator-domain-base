package {{path}}.{{entity.name}}.repository;

import {{path}}.{{entity.name}}.model.{{enity.nameUpper}}Doc;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface {{enity.nameUpper}}Repository extends MongoRepository<{{enity.nameUpper}}Doc, ObjectId> {
}
