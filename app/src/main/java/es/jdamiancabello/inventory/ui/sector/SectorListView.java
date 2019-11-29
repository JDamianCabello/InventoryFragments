package es.jdamiancabello.inventory.ui.sector;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import es.jdamiancabello.inventory.R;
import es.jdamiancabello.inventory.adapter.SectorAdapter;
import es.jdamiancabello.inventory.data.model.Sector;
import es.jdamiancabello.inventory.ui.base.BaseDialogFragment;


public class SectorListView extends Fragment implements SectorListContract.View, BaseDialogFragment.OnAcceptDialogListener{

    public static final String TAG = "SectorListView";
    private SectorListContract.Presenter presenter;
    private RecyclerView rvSectors;
    private FloatingActionButton fabButton;
    private ProgressBar progressBar;
    private SectorListViewListener viewListener;
    private static final int CODE_DELETE = 300;

    private SectorAdapter adapter;
    private SectorAdapter.OnManageSectorListener adapterOManageSectorListener;

    private Sector deletedSector;



    public static Fragment newInstance(Bundle b) {
        SectorListView fragment = new SectorListView();
        if(b != null)
            fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvSectors = view.findViewById(R.id.rvSector);
        progressBar = view.findViewById(R.id.sectorProgressBar);

        fabButton = view.findViewById(R.id.fabAddSector);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewListener.sectorAddEditFragmentShow(null);
            }
        });

        adapter = new SectorAdapter();
        adapterOManageSectorListener = new SectorAdapter.OnManageSectorListener() {
            @Override
            public void onDeleteSectorListener(final Sector sector) {
                Bundle b = new Bundle();
                b.putString(BaseDialogFragment.TITTLE,"ELIMINAR");
                b.putString(BaseDialogFragment.MESSAGE, "¿Seguro que desea elmininar " + sector.toString() + "?");
                BaseDialogFragment baseDialogFragment = (BaseDialogFragment) BaseDialogFragment.newInstance(b);


                baseDialogFragment.setTargetFragment(SectorListView.this, CODE_DELETE);
                baseDialogFragment.show(getFragmentManager(),baseDialogFragment.TAG);

                deletedSector = sector;
            }

            @Override
            public void onAddorEditSectorListener(Sector sector) {
                viewListener.sectorAddEditFragmentShow(sector);
            }
        };

        adapter.setOnManageSectorListener(adapterOManageSectorListener);
        rvSectors.setAdapter(adapter);
        rvSectors.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sector_list, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SectorListViewListener) {
            viewListener = (SectorListViewListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAddSector");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.load();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        viewListener = null;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void noSectors() {
        Toast.makeText(getContext(),"No hay sectores",Toast.LENGTH_LONG).show();
    }

    @Override
    public void refresh(ArrayList<Sector> sectors) {
        adapter.clear();
        adapter.addAll(sectors);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccessDeleted() {
        adapter.delete(deletedSector);
        adapter.notifyDataSetChanged();
        showSnackBarDeleted();
    }

    private void showSnackBarDeleted() {
        final Sector sectorDeleted = deletedSector;
        Snackbar.make(getView(),R.string.undoDelete,Snackbar.LENGTH_LONG).setAction(R.string.undo, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                undoDelete(deletedSector);
            }
        }).show();
    }


    private void undoDelete(Sector sector) {
        presenter.undo(sector);
    }



    @Override
    public void onSucessUndo(Sector sector) {
        adapter.add(sector);
        adapter.notifyDataSetChanged();
        Toast.makeText(getContext(),"Se ha restaurado "+sector.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSucess() {

    }

    @Override
    public void setPresenter(SectorListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showGenericError(String s) {

    }

    @Override
    public void onAcceptDialog() {
        presenter.delete(deletedSector);
        adapter.notifyDataSetChanged();
    }


    public interface SectorListViewListener {
        void sectorAddEditFragmentShow(Sector sector);
    }
}
