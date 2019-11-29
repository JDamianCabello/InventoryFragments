package es.jdamiancabello.inventory.ui.sector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.SpinnerAdapter;

import es.jdamiancabello.inventory.R;
import es.jdamiancabello.inventory.data.model.Sector;

public class SectorActivity extends AppCompatActivity implements SectorListView.SectorListViewListener, SectorManageView.OnSaveSectorManageView{
    private Fragment sectorListFragment;
    private SectorPresenter sectorPresenter;
    private Fragment sectorManageView;
    private SectorManagePresenter sectorManagePresenter;

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
        sectorPresenter = new SectorPresenter((SectorListContract.View) sectorListFragment);
        ((SectorListContract.View) sectorListFragment).setPresenter(sectorPresenter);

    }

    @Override
    public void sectorAddEditFragmentShow(Sector sector) {
        sectorManageView = getSupportFragmentManager().findFragmentByTag(SectorManageView.TAG);

        if(sectorManageView == null){
            Bundle b = null;
            if(sector != null){
                b = new Bundle();
                b.putParcelable("sector",sector);
            }

            sectorManageView = SectorManageView.newInstance(b);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(android.R.id.content,sectorManageView,SectorManageView.TAG);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        sectorManagePresenter = new SectorManagePresenter((SectorManageContract.View) sectorManageView);
        ((SectorManageContract.View) sectorManageView).setPresenter(sectorManagePresenter);
    }

    @Override
    public void onSaveSectorManageView() {
        onBackPressed();
    }
}
