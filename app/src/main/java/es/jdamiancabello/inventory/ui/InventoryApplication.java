package es.jdamiancabello.inventory.ui;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import es.jdamiancabello.inventory.R;
import es.jdamiancabello.inventory.data.InventoryDatabase;
import es.jdamiancabello.inventory.data.model.User;

public class InventoryApplication extends Application {
    public static final String CHANNEL_ID = "1";
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
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
