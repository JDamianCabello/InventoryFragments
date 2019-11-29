package es.jdamiancabello.inventory.ui.sector;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.inventory.data.model.Sector;
import es.jdamiancabello.inventory.ui.base.BaseView;

public interface SectorListContract {
    interface View extends BaseView<Presenter> {
        void showProgress();
        void hideProgress();
        void noSectors();
        void refresh(ArrayList<Sector> sectors);
        void onSuccessDeleted();
        void onSucessUndo(Sector sector);
    }

    interface Presenter{
        void delete(Sector sector);
        void load();
        void undo(Sector sector);
    }
}
