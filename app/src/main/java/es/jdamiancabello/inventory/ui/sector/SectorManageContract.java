package es.jdamiancabello.inventory.ui.sector;

import java.util.ArrayList;

import es.jdamiancabello.inventory.data.model.Dependency;
import es.jdamiancabello.inventory.data.model.Sector;
import es.jdamiancabello.inventory.ui.base.BaseView;

public interface SectorManageContract {
    interface View extends BaseView<Presenter> {
        void setupContentList(ArrayList<Dependency> dependencies);
    }

    interface Presenter{
        void onAddSector(String name, String shortName, String description, Dependency dependency);
        void onModifySector(String name, String shortName, String description, Dependency dependency);
        void onViewCreated();

        void onAddSector(Sector sector);

        void onModifySector(Sector sector);
        int getPosition(Dependency dependency);
    }
}
