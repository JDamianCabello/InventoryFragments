package es.jdamiancabello.inventory.base;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.jdamiancabello.inventory.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_basey,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg = new String();
        switch (item.getItemId()){
            case R.id.dependencyMenu_actionSearch:
                msg = "Search Icon";
                break;
            case R.id.dependencyMenu_HelpOption:
                msg = "Help option";
                break;
            case R.id.dependencyMenu_AbouusOption:
                msg = "About us option";
                break;
            case R.id.dependencyMenu_SettingsOption:
                msg = "Settings option";
                break;
        }

        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

}
