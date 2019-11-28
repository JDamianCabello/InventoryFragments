package es.jdamiancabello.inventory.ui.dependency;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

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
            if(DependencyRepository.getInstance().getDependencyList().isEmpty())
                view.noDependencies();
            else
            {
                //Aqui se muestra el toask de si hay datos (o la imagen)
            }
            view.onSucessDelete();
        }
    }

    public void undoDelete(Dependency d){
        if(DependencyRepository.getInstance().addDependency(d)){
            view.onSucessUndo(d);
        }
    }

    @Override
    public void loadData() {
        new AsyncTask<Void,Void, List<Dependency>>(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                view.showProgressBar();
            }

            @Override
            protected void onPostExecute(List<Dependency> dependencies) {
                super.onPostExecute(dependencies);
                if(dependencies.isEmpty())
                    view.noDependencies();
                else
                    view.refresh((ArrayList<Dependency>) dependencies);
                view.hideProgressBar();
            }

            @Override
            protected List<Dependency> doInBackground(Void... voids) {
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                return DependencyRepository.getInstance().getDependencyList();
            }
        }.execute();
    }
}
