package es.jdamiancabello.inventory.ui.sector;

import es.jdamiancabello.inventory.data.model.Dependency;

public interface SectorSpinnerContentContract {
    interface Spinner{
        Dependency getSelectedDependency(Dependency selectedDependency);
    }

    interface Presenter{
        void loadSpinnerData();
    }
}
