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
import es.jdamiancabello.inventory.data.model.Dependency;

public class DependencyActivity extends AppCompatActivity implements DependencyListFragment.showAddFragmentListener, DependencyAddFragment.onSaveFragmentListener{
    private Fragment dependencyListFragment;
    private Fragment dependencyAddFragment;
    private DependencyAddPresenter dependencyAddPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependencies);

        showListFragment();
    }

    private void showListFragment(){
        dependencyListFragment = getSupportFragmentManager().findFragmentByTag(DependencyListFragment.TAG);

        if(dependencyListFragment == null){
            dependencyListFragment = DependencyListFragment.newInstance(null);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(android.R.id.content,dependencyListFragment,DependencyListFragment.TAG);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void showAddFragment(Dependency dependency){
        dependencyAddFragment = getSupportFragmentManager().findFragmentByTag(DependencyAddFragment.TAG);

        if(dependencyAddFragment == null){
            Bundle b = null;
            if (dependency != null){
                b = new Bundle();
                b.putParcelable("dependecy", dependency);
            }
            dependencyAddFragment = DependencyAddFragment.newInstance(b);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(android.R.id.content,dependencyAddFragment,DependencyAddFragment.TAG);
            fragmentTransaction.addToBackStack("DependencyListToAddDepencency");
            fragmentTransaction.commit();
        }

        dependencyAddPresenter = new DependencyAddPresenter((DependencyAddFragment)dependencyAddFragment);
        ((DependencyAddFragment) dependencyAddFragment).setPresenter(dependencyAddPresenter);

    }

    @Override
    public void onSaveFragment() {
        getSupportFragmentManager().popBackStack();
    }

}
