package es.jdamiancabello.inventory.ui.sector;

import java.util.ArrayList;

import es.jdamiancabello.inventory.data.model.Dependency;
import es.jdamiancabello.inventory.ui.base.BaseView;

public interface SectorManageContract {
    interface View extends BaseView<Presenter> {
        void setupContentList(ArrayList<Dependency> dependencies);
    }

    interface Presenter{
        void onValidate(String name, String shortName, String description, Dependency dependency);
        void onValidateModify(String name, String shortName, String description);
        void onViewCreated();
    }
}
