package es.jdamiancabello.inventory.ui.dependency;

import es.jdamiancabello.inventory.data.model.Dependency;
import es.jdamiancabello.inventory.data.repository.DependencyRepository;

public class DependencyManagePresenter implements DependencyManageContract.Presenter{
    private DependencyManageContract.View view;
    public DependencyManagePresenter(DependencyManageContract.View view) {
        this.view = view;
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


    @Override
    public void onAdd(Dependency dependency) {
        if(checktName(dependency.getName()) & checkShortName(dependency.getShortName()) & checkDescription(dependency.getDescription())){
            if(DependencyRepository.getInstance().addDependency(dependency) == 1)
                view.onSucess();
            else
                view.showGenericError("No se ha podido añadir");
        }
    }

    @Override
    public void onValidateModify(Dependency dependency) {
        if(checktName(dependency.getName()) & checkDescription(dependency.getDescription())) {
            if (DependencyRepository.getInstance().editDependency(dependency))
                view.onSucess();
            else
                view.showGenericError("No se ha podido modificar");
        }
    }
}
