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

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.inventory.R;
import es.jdamiancabello.inventory.adapter.SectorAdapter;
import es.jdamiancabello.inventory.data.model.Sector;


public class SectorListView extends Fragment implements SectorListContract.View{

    public static final String TAG = "SectorListView";
    private SectorListContract.Presenter presenter;
    private RecyclerView rvSectors;
    private FloatingActionButton fabButton;
    private ProgressBar progressBar;
    private SectorListViewListener viewListener;

    private SectorAdapter adapter;
    private SectorAdapter.OnManageSectorListener adapterOManageSectorListener;



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
        adapter = new SectorAdapter();
        adapterOManageSectorListener = new SectorAdapter.OnManageSectorListener() {
            @Override
            public void onDeleteSectorListener(Sector sector) {
                Toast.makeText(getContext(),"Hola Edit",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAddorEditSectorListener(Sector sector) {
                Toast.makeText(getContext(),"Hola rm",Toast.LENGTH_SHORT).show();

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
    public void showImgNoData() {

    }

    @Override
    public void noSectors() {
        Toast.makeText(getContext(),"No hay sectores",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccessDelete(ArrayList<Sector> sectors) {

    }

    @Override
    public void refresh(ArrayList<Sector> sectors) {
        adapter.clear();
        adapter.addAll(sectors);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccessDeleted() {

    }

    @Override
    public boolean isVisibleImgNoData() {
        return false;
    }

    @Override
    public void hideImgNoData() {

    }

    @Override
    public void onSuccess(List<Sector> sectorList) {

    }

    @Override
    public void OnSuccessUndo(Sector sector) {

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


    public interface SectorListViewListener {
        void sectorAddEditFragmentShow(Sector sector);
    }
}
