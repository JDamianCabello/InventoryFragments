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
    public void onValidate(String name, String shortName, String description, Dependency dependency) {
        if(validateName(name) & validateShortName(shortName) & validateDescription(description)){
            if(SectorRepository.getInstance().addDependency(new Sector(name,shortName,dependency,description,"")))
                view.onSucess();
        }

    }

    @Override
    public void onValidateModify(String name, String shortName, String description) {

    }

    @Override
    public void onViewCreated() {
        view.setupContentList((ArrayList<Dependency>) DependencyRepository.getInstance().getDependencyList());
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
