package es.jdamiancabello.inventory.ui.dependency;

import es.jdamiancabello.inventory.data.model.Dependency;
import es.jdamiancabello.inventory.data.repository.DependencyRepository;

public class DependencyManagePresenter implements DependencyManageContract.Presenter {
    private DependencyManageContract.View view;
    public DependencyManagePresenter(DependencyManageContract.View view) {
        this.view = view;
    }

    @Override
    public void onValidate(String name, String shortName, String description, String spinner) {
        if(checktName(name) & checkShortName(shortName) & checkDescription(description)){
                if(DependencyRepository.getInstance().addDependency(new Dependency(name,shortName,description,spinner)))
                view.onSucess();
            else
                view.showGenericError("No se ha podido añadir");
        }
    }

    @Override
    public void onValidateModify(String name, String shortName, String description, String spinner) {
        if(checktName(name) & checkDescription(description)) {
            if (DependencyRepository.getInstance().modifyDependency(new Dependency(name,shortName,description,spinner)))
                view.onSucess();
            else
                view.showGenericError("No se ha podido modificar");
        }
    }

    private boolean checkShortName(String s){
        if(s.isEmpty()){
            view.onShortNameEmpty("El nombre corto está vacío");
            return false;
        }
        view.onClearErrorShortNameEmpty();
        return true;
    }

    private boolean checktName(String s){
        if(s.isEmpty()){
            view.onNameEmpty("El nombre está vacío");
            return false;
        }
        view.onlearErrorNameEmpty();
        return true;
    }

    private boolean checkDescription(String s){
        if(s.isEmpty()){
            view.onDescriptionEmpty("La descripción está vacía");
            return false;
        }
        view.onlearErrorDescriptionEmpty();
        return true;
    }
}
