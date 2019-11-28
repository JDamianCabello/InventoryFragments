package es.jdamiancabello.inventory.ui.dependency;

import java.util.ArrayList;


import es.jdamiancabello.inventory.data.model.Dependency;
import es.jdamiancabello.inventory.ui.base.BaseView;

public interface DependencyListContract {
    interface View extends BaseView<Presenter> {
        void refresh(ArrayList<Dependency> dependencies);
        void noDependencies();
        void showProgressBar();
        void hideProgressBar();
        void onSucessDelete();
        void onSucessUndo(Dependency d);
    }

    interface Presenter{
        void deleteDependency(Dependency dependency);
        void loadData();
        void undoDelete(Dependency dependency);
    }
}
