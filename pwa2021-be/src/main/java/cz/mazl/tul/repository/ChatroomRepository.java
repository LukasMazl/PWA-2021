package cz.mazl.tul.repository;

import cz.mazl.tul.entity.ChatRoomEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomRepository extends CrudRepository<ChatRoomEntity, Long> {
    ChatRoomEntity findByChatRoomId(String roomId);
}
