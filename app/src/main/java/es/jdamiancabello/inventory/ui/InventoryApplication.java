package es.jdamiancabello.inventory.ui;

import android.app.Application;
import android.content.Context;

import es.jdamiancabello.inventory.data.InventoryDatabase;
import es.jdamiancabello.inventory.data.model.User;

public class InventoryApplication extends Application {
    private static Context context;
    public static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        InventoryApplication.user = user;
    }

    public static Context getAppContext() {
        return InventoryApplication.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        InventoryDatabase.getDatabase();
    }
}
