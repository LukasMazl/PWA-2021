package cz.mazl.tul.repository;

import cz.mazl.tul.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUserId(String userId);

    List<UserEntity> findAllByUserIdIn(List<String> userIds);
}
