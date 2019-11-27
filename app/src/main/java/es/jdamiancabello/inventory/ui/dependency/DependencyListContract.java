package es.jdamiancabello.inventory.ui.dependency;

import java.util.ArrayList;

import es.jdamiancabello.inventory.data.model.Dependency;
import es.jdamiancabello.inventory.ui.base.BaseView;

public interface DependencyListContract {
    interface View extends BaseView<Presenter> {
        void refresh(ArrayList<Dependency> dependencies);
        void noDependencies();
        void setListAdapter(ArrayList<Dependency> dependencies);
    }

    interface Presenter{
        void modifyDependency(Dependency dependency);
        void deleteDependency(Dependency dependency);
        void loadData();
    }
}
