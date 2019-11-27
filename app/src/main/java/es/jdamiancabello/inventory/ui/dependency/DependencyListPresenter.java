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
        if(DependencyRepository.getInstance().deleteDependency(dependency)) {
            view.refresh((ArrayList<Dependency>) DependencyRepository.getInstance().getDependencyList());
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
