package es.jdamiancabello.inventory.ui.sector;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.inventory.data.model.Dependency;
import es.jdamiancabello.inventory.data.model.Sector;
import es.jdamiancabello.inventory.ui.base.BaseView;

public interface SectorManageContract {
    interface View extends BaseView<Presenter> {
        void setupContentList();

        void onShortNameEmpty(String error);
        void onShortNameShort(String error);
        void onNameEmpty(String error);
        void onDescriptionEmpty(String error);
        void onContainsEspecialChar(String error);

        void onClearErrorShortNameEmpty();
        void onClearErrorShortNameShort();
        void onClearErrorNameEmpty();
        void onClearErrorDescriptionEmpty();
        void onClearErrorContainsEspecialChar();
    }

    interface Presenter{
        void onViewCreated();
        void onAddSector(Sector sector);
        void onModifySector(Sector sector);
        List<String> getDependecysToString();
    }
}
