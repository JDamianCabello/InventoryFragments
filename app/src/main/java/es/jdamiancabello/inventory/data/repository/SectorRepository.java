package es.jdamiancabello.inventory.data.repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

import es.jdamiancabello.inventory.data.InventoryDatabase;
import es.jdamiancabello.inventory.data.model.Sector;
import es.jdamiancabello.inventory.data.SectorDAO;

public class SectorRepository {
    private static SectorRepository sectorRepository;
    private SectorDAO sectorDAO;

    private SectorRepository() {
        this.sectorDAO = InventoryDatabase.getDatabase().sectorDAO();
    }


    static {
        sectorRepository = new SectorRepository();
    }

    public static SectorRepository getInstance(){
        return sectorRepository;
    }

    public List<Sector> getAll(){
        List<Sector> sectors = null;

        try {
            sectors = InventoryDatabase.databaseWriteExecutor.submit(() -> sectorDAO.getAll()).get();
        }catch (ExecutionException | InterruptedException e){
            e.printStackTrace();
        }

        return sectors;
    }

    public long addSector(Sector sector) {
        long rowId= 0;

        try {
            rowId = InventoryDatabase.databaseWriteExecutor.submit(() -> sectorDAO.insert(sector)).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            rowId = -1;
        }
        return rowId;
    }

    public boolean deleteSector(Sector sector){
        InventoryDatabase.databaseWriteExecutor.execute(() -> sectorDAO.delete(sector));
        return true;
    }

    public boolean editSector(Sector sector) {
        InventoryDatabase.databaseWriteExecutor.execute(() -> sectorDAO.update(sector));
        return true;
    }
}
