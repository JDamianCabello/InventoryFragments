package es.jdamiancabello.inventory.ui.dependency;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import es.jdamiancabello.inventory.data.model.Dependency;
import es.jdamiancabello.inventory.data.repository.DependencyRepository;

public class DependencyListPresenter implements DependencyListContract.Presenter {
    public DependencyListContract.View view;

    public DependencyListPresenter(final DependencyListContract.View view){
        this.view = view;
    }


    @Override
    public void deleteDependency(Dependency dependency) {
        //1. Realizar la operacion en el repo y comprobar el resultado
        if(DependencyRepository.getInstance().deleteDependency(dependency)) {
            //1.2 Comprobar si no hay datos
            if(DependencyRepository.getInstance().getAll().isEmpty())
                view.noDependencies();
            else
            {
                //Aqui se muestra el toask de si hay datos (o la imagen)
            }
            view.onSucessDelete();
        }
    }

    public void undoDelete(Dependency d){
        if(DependencyRepository.getInstance().addDependency(d) == 1)
            view.onSucessUndo(d);
    }

    @Override
    public void loadData() {
        view.refresh((ArrayList<Dependency>) DependencyRepository.getInstance().getAll());
    }
}
