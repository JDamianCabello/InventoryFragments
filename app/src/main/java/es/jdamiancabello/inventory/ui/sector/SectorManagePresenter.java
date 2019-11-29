package es.jdamiancabello.inventory.ui.sector;

import es.jdamiancabello.inventory.data.model.Dependency;

public class SectorManagePresenter implements SectorManageContract.Presenter {
    SectorManageContract.View view;

    public SectorManagePresenter(SectorManageContract.View view){
        this.view = view;
    }


    @Override
    public void onValidate(String name, String shortName, String description, Dependency dependency) {

    }

    @Override
    public void onValidateModify(String name, String shortName, String description) {

    }
}
