package es.jdamiancabello.inventory.ui.sector;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.inventory.data.model.Dependency;
import es.jdamiancabello.inventory.data.model.Sector;
import es.jdamiancabello.inventory.data.repository.DependencyRepository;
import es.jdamiancabello.inventory.data.repository.SectorRepository;

public class SectorManagePresenter implements SectorManageContract.Presenter  {
    SectorManageContract.View view;

    public SectorManagePresenter(SectorManageContract.View view){
        this.view = view;
    }


    @Override
    public void onAddSector(String name, String shortName, String description, Dependency dependency) {


    }

    @Override
    public void onModifySector(String name, String shortName, String description, Dependency dependency) {

    }

    @Override
    public void onViewCreated() {
        view.setupContentList((ArrayList<Dependency>) DependencyRepository.getInstance().getDependencyList());
    }

    @Override
    public void onAddSector(Sector sector) {
        if(validateSector(sector) ){
            if(SectorRepository.getInstance().addDependency(sector))
                view.onSucess();
        }
    }

    private boolean validateSector(Sector sector) {
        return true;
    }

    @Override
    public void onModifySector(Sector sector) {
        if(validateSector(sector)) {
            if (SectorRepository.getInstance().modifyDependency(sector))
                view.onSucess();
            else
                view.showGenericError("No se ha podido modificar");
        }
    }

    @Override
    public int getPosition(Dependency dependency) {
        return DependencyRepository.getInstance().getPosition(dependency);
    }

    public boolean validateName(String s){
        if(s.isEmpty())
            return false;
        return true;
    }

    public boolean validateShortName(String s){
        if(s.isEmpty())
            return false;
        return true;
    }

    public boolean validateDescription(String s){
        if(s.isEmpty())
            return false;
        return true;
    }
}
