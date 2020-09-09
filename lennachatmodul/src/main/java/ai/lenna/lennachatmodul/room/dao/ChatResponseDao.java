package ai.lenna.lennachatmodul.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ai.lenna.lennachatmodul.room.entity.ChatResponseEntity;


@Dao
public interface ChatResponseDao {

    @Query("SELECT * FROM chat")
    List<ChatResponseEntity> getAll();

//    @Query("SELECT * FROM user where first_name LIKE  :firstName AND last_name LIKE :lastName")
//    User findByName(String firstName, String lastName);

    @Query("SELECT COUNT(*) from chat")
    int countUsers();

    @Insert
    void insertAll(ChatResponseEntity chatResponseEntity);

    @Delete
    void delete(ChatResponseEntity chat);
}
