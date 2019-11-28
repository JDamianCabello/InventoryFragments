package es.jdamiancabello.inventory.ui.dependency;

import es.jdamiancabello.inventory.data.model.Dependency;
import es.jdamiancabello.inventory.ui.base.BaseView;

public interface DependencyManageContract {

    interface View extends BaseView<Presenter> {
        void onShortNameEmpty(String error);
        void onNameEmpty(String error);
        void onDescriptionEmpty(String error);

        void onClearErrorShortNameEmpty();
        void onlearErrorNameEmpty();
        void onlearErrorDescriptionEmpty();
    }

    interface Presenter{
        void onValidate(String name, String shortName, String description, String spinner);
        void onValidateModify(String string, String toString, String toString1, String toString2);
    }
}
