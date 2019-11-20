package es.jdamiancabello.inventory.ui.dependency;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import es.jdamiancabello.inventory.R;
import es.jdamiancabello.inventory.adapter.DependencyAdapter;

public class DependencyActivity extends AppCompatActivity implements DependencyListFragment.OnAddDependencyListener{
    private Fragment dependencyListFragment;
    private Fragment dependencyAddFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependencies);

        showListFragment();
    }

    private void showListFragment(){
        dependencyListFragment = getSupportFragmentManager().findFragmentByTag(DependencyListFragment.TAG);

        if(dependencyListFragment == null){
            dependencyListFragment = DependencyListFragment.newInstance();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(android.R.id.content,dependencyListFragment,DependencyListFragment.TAG);
            fragmentTransaction.commit();
        }
    }

    /**
     * Este método muestra el fragment DependencyAddFragment
     */
    @Override
    public void onAddDependency() {
        showListFragment();
    }

    private void showAddFragment(){
        dependencyAddFragment = getSupportFragmentManager().findFragmentByTag(DependencyListFragment.TAG);

        if(dependencyAddFragment == null){
            dependencyAddFragment = DependencyAddFragment.newInstance();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(android.R.id.content,dependencyAddFragment,DependencyAddFragment.TAG);
            fragmentTransaction.addToBackStack("DependencyListToAddDepencency");
            fragmentTransaction.commit();
        }
    }

}
