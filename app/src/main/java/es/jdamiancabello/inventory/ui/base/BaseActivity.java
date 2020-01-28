package es.jdamiancabello.inventory.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import es.jdamiancabello.inventory.R;
import es.jdamiancabello.inventory.ui.dependency.DependencyActivity;
import es.jdamiancabello.inventory.ui.product.ProductActivity;
import es.jdamiancabello.inventory.ui.sector.SectorActivity;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    protected DrawerLayout drawerLayout;
    protected NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this,drawerLayout,toolbar, R.string.openNavigation,R.string.closeNavigation);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if(!menuItem.isChecked()){
            navigationView.getCheckedItem().setChecked(false);
            navigationView.setCheckedItem(menuItem);

            switch (menuItem.getItemId()){
                case R.id.item_menu_dependency:
                    startActivity(new Intent(this, DependencyActivity.class));
                    break;
                case R.id.item_menu_product:
                    startActivity(new Intent(this, ProductActivity.class));
                    break;
                case R.id.item_menu_section:
                    startActivity(new Intent(this, SectorActivity.class));
                    break;
                case R.id.item_submenu_Settings:
                    showToast("Settings");
                    break;
                case R.id.item_submenu_help:
                    showToast("help");
                    break;
                case R.id.item_submenu_aboutUs:
                    showToast("about us");
                    break;
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();

    }
}
