package es.jdamiancabello.inventory.data;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import es.jdamiancabello.inventory.data.model.Dependency;
import es.jdamiancabello.inventory.data.model.Sector;
import es.jdamiancabello.inventory.ui.InventoryApplication;

@Database(entities = {Dependency.class, Sector.class}, version = 2, exportSchema = false)
public abstract class InventoryDatabase extends RoomDatabase {

    public abstract DependencyDAO dependencyDao();
    public abstract SectorDAO sectorDAO();

    private static volatile InventoryDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static InventoryDatabase getDatabase() {
        if (INSTANCE == null) {
            synchronized (InventoryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(InventoryApplication.getAppContext(),
                            InventoryDatabase.class, "inventory_data")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
