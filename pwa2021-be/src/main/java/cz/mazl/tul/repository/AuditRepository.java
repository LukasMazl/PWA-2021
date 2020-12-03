package cz.mazl.tul.repository;

import cz.mazl.tul.entity.AuditEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditRepository extends CrudRepository<AuditEntity, Long> {
    void deleteByUserIdAndSessionId(String userId, String sessionId);

    List<AuditEntity> findAll();
}
