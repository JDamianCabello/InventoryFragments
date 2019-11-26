package es.jdamiancabello.inventory.ui.base;

public interface BaseView<T>{
    void onSucess();
    void setPresenter(T presenter);
    void showGenericError(String s);
}
