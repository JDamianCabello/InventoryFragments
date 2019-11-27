package es.jdamiancabello.inventory.ui.dependency;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import es.jdamiancabello.inventory.R;
import es.jdamiancabello.inventory.data.model.Dependency;

public class DependencyActivity extends AppCompatActivity implements DependencyListView.showAddFragmentListener, DependencyAddFragment.onSaveFragmentListener{
    private Fragment dependencyListFragment;
    private Fragment dependencyAddFragment;
    private DependencyAddPresenter dependencyAddPresenter;
    private DependencyListPresenter dependencyListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependencies);

        showListFragment();
    }

    private void showListFragment(){
        dependencyListFragment = getSupportFragmentManager().findFragmentByTag(DependencyListView.TAG);

        if(dependencyListFragment == null){
            dependencyListFragment = DependencyListView.newInstance(null);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(android.R.id.content,dependencyListFragment, DependencyListView.TAG);
            fragmentTransaction.commit();
        }

        dependencyListPresenter = new DependencyListPresenter((DependencyListView)dependencyListFragment);
        ((DependencyListView) dependencyListFragment).setPresenter(dependencyListPresenter);
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
