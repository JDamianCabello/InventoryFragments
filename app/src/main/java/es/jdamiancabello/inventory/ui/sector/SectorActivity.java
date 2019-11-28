package es.jdamiancabello.inventory.ui.sector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import es.jdamiancabello.inventory.R;
import es.jdamiancabello.inventory.adapter.SectorAdapter;
import es.jdamiancabello.inventory.data.model.Sector;

public class SectorActivity extends AppCompatActivity implements SectorListView.SectorListViewListener{
    private Fragment sectorListFragment;
    private SectorAdapter sectorAdapter;
    private SectorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sector);

        sectorListFragment = getSupportFragmentManager().findFragmentByTag(SectorListView.TAG);
        if(sectorListFragment == null){
            sectorListFragment = SectorListView.newInstance(null);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(android.R.id.content,sectorListFragment,SectorListView.TAG);
            fragmentTransaction.commit();
        }
        presenter = new SectorPresenter((SectorListContract.View) sectorListFragment);
        ((SectorListContract.View) sectorListFragment).setPresenter(presenter);

    }

    @Override
    public void sectorAddEditFragmentShow(Sector sector) {

    }
}
