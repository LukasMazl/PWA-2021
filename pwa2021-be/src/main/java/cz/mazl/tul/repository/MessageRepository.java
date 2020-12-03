package cz.mazl.tul.repository;

import cz.mazl.tul.entity.MessageEntity;
import cz.mazl.tul.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, Long> {
    List<MessageEntity> findByAuthorOrderByCreatedDesc(UserEntity author);
}
