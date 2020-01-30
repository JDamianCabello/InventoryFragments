package es.jdamiancabello.inventory.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.jdamiancabello.inventory.data.model.Sector;

@Dao
public interface SectorDAO {
    @Insert
    long insert(Sector sector);

    @Delete
    void delete(Sector sector);

    @Update
    void update(Sector sector);

    @Query("Select * from sector order by name asc")
    List<Sector> getAll();

    @Query("Select * from sector where shortName=:shortname")
    Sector findbyShortName(String shortname);
}
