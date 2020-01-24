package es.jdamiancabello.inventory.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.jdamiancabello.inventory.data.model.Dependency;

@Dao
public interface DependencyDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Dependency dependency);

    @Delete
    void delete(Dependency dependency);

    @Update
    void update(Dependency dependency);

    @Query("Select * from dependency order by name asc")
    List<Dependency> getAll();

    @Query("Select * from dependency where shortName=:shortname")
    Dependency findbyShortName(String shortname);
}
