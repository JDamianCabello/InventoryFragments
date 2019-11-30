package es.jdamiancabello.inventory.data.repository;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.inventory.data.model.Dependency;
import es.jdamiancabello.inventory.data.model.Sector;

public class SectorRepository {
    private List<Sector> sectorList;
    private static SectorRepository sectorRepository;

    private SectorRepository() {
        this.sectorList = new ArrayList<>();
        initialice();
    }

    private void initialice() {
        sectorList.add(new Sector("Sector 1", "SC1", new Dependency("dependency_1","dp_1", "This re the dependency number: 1", "No image"), "Descripción del Sector 1", ""));
        sectorList.add(new Sector("Sector 2", "SC2", new Dependency("dependency_1","dp_1", "This re the dependency number: 1", "No image"), "Descripción del Sector 2", ""));
        sectorList.add(new Sector("Sector 1", "SC1", new Dependency("dependency_2","dp_2", "This re the dependency number: 2", "No image"), "Descripción del Sector 1", ""));
    }

    static {
        sectorRepository = new SectorRepository();
    }

    public static SectorRepository getInstance(){
        return sectorRepository;
    }

    public List<Sector> getSectorList(){
        return sectorList;
    }

    public boolean deleteSector(Sector sector){
        return sectorList.remove(sector);
    }

    public boolean addDependency(Sector sector) {
        return sectorList.add(sector);
    }

    public boolean modifyDependency(Sector newSector) {
        for (Sector it : sectorList) {
            if (it.getShortName().equals(newSector.getShortName())) {
                it.setName(newSector.getName());
                it.setSectorDescription(newSector.getSectorDescription());
                it.setDependency(newSector.getDependency());
                return true;
            }
        }
        return false;
    }
}
