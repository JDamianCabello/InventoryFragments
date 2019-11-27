package es.jdamiancabello.inventory.ui.dependency;

import java.util.ArrayList;

import es.jdamiancabello.inventory.data.model.Dependency;
import es.jdamiancabello.inventory.data.repository.DependencyRepository;

public class DependencyListPresenter implements DependencyListContract.Presenter {
    public DependencyListContract.View view;

    public DependencyListPresenter(final DependencyListContract.View view){
        this.view = view;
    }

    @Override
    public void modifyDependency(Dependency dependency) {

        if(DependencyRepository.getInstance().modifyDependency(dependency))
            view.refresh((ArrayList<Dependency>) DependencyRepository.getInstance().getDependencyList());
    }

    @Override
    public void deleteDependency(Dependency dependency) {
        if(DependencyRepository.getInstance().deleteDependency(dependency))
            view.refresh((ArrayList<Dependency>) DependencyRepository.getInstance().getDependencyList());
    }

    @Override
    public void loadData() {
        ArrayList<Dependency> dependencies = (ArrayList<Dependency>) DependencyRepository.getInstance().getDependencyList();
        if (dependencies.size() == 0){
            view.noDependencies();
        }else {
            view.setListAdapter(dependencies);
        }

    }
}
