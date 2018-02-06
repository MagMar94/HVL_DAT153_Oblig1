package dat153.hvl.no.thenameapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Magnus on 05.02.2018.
 */
@Dao
public interface PersonDao {
    @Query("SELECT * FROM person")
    List<Person> getAll();

    @Query("SELECT * FROM person WHERE name LIKE :name LIMIT 1")
    Person findByName(String name);

    @Insert
    void insertAll(Person... users);

    @Insert
    void insertUser(Person person);
}
