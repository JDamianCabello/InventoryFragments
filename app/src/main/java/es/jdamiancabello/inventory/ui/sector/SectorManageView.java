package es.jdamiancabello.inventory.ui.sector;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import es.jdamiancabello.inventory.R;
import es.jdamiancabello.inventory.data.model.Dependency;
import es.jdamiancabello.inventory.data.model.Sector;


public class SectorManageView extends Fragment implements SectorManageContract.View{

    public static final String TAG = "SectorManageView";
    private TextInputLayout nombreCorto, nombre, descripcion;
    private TextInputEditText ednombreCorto, ednombre, eddescripcion;
    private Spinner spinner;
    private FloatingActionButton fbSave;
    private OnSaveSectorManageView viewListener;
    private SectorManageContract.Presenter presenter;


    public static Fragment newInstance(Bundle b) {
        SectorManageView fragment = new SectorManageView();
        if (b != null){
            fragment.setArguments(b);
        }
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_modify_sector, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.onViewCreated(view, savedInstanceState);
        nombre = view.findViewById(R.id.tilSectorManageName);
        nombreCorto = view.findViewById(R.id.tilSectorManageShort);
        descripcion = view.findViewById(R.id.tilSectorManageDescription);

        eddescripcion = view.findViewById(R.id.tiledSectorManageDescription);
        ednombre = view.findViewById(R.id.tiledSectorManageName);
        ednombreCorto = view.findViewById(R.id.tiledSectorManageShort);


        spinner = view.findViewById(R.id.spinnerSectorManageInventory);
        fbSave = view.findViewById(R.id.fabSectorManageSave);

        presenter.onViewCreated();

        if (getArguments() != null){
            Sector sector = getArguments().getParcelable("sector");
            ednombreCorto.setText(sector.getShortName());
            ednombre.setText(sector.getName());
            eddescripcion.setText(sector.getSectorDescription());
            spinner.setSelection(getPosition(sector.getDependency()),false);
            ednombreCorto.setEnabled(false);
        }

        fbSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getArguments()!= null){
                    presenter.onModifySector(createSector());
                }else {
                    presenter.onAddSector(createSector());

                }
            }
        });
    }

    private int getPosition(Dependency dependency) {
        return 0;
    }

    private Sector createSector() {
        Sector mySector = new Sector();

        mySector.setName(ednombre.getText().toString());
        mySector.setShortName(ednombreCorto.getText().toString());
        mySector.setSectorDescription(eddescripcion.getText().toString());
        mySector.setDependency((Dependency) spinner.getSelectedItem());
        mySector.setUrlImage(null);


        return mySector;
    }

    @Override
    public void onSucess() {
        viewListener.onSaveSectorManageView();

    }

    public void setPresenter(SectorManageContract.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void showGenericError(String s) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSaveSectorManageView) {
            viewListener = (OnSaveSectorManageView) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        viewListener = null;
    }


    @Override
    public void setupContentList(ArrayList<Dependency> dependencies) {
        ArrayAdapter<Dependency> spinnerAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,dependencies);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setAdapter(spinnerAdapter);
    }

    @Override
    public void onShortNameEmpty(String error) {
        nombreCorto.setError(error);

    }

    @Override
    public void onShortNameShort(String error) {
        nombre.setError(error);
    }

    @Override
    public void onNameEmpty(String error) {
        nombre.setError(error);
    }

    @Override
    public void onDescriptionEmpty(String error) {
        descripcion.setError(error);
    }

    @Override
    public void onContainsEspecialChar(String error) {
        nombreCorto.setError(error);
    }

    @Override
    public void onClearErrorShortNameEmpty() {
        nombreCorto.setError(null);
    }

    @Override
    public void onClearErrorShortNameShort() {
        nombreCorto.setError(null);
    }

    @Override
    public void onClearErrorNameEmpty() {
        nombre.setError(null);
    }

    @Override
    public void onClearErrorDescriptionEmpty() {
        descripcion.setError(null);
    }


    @Override
    public void onClearErrorContainsEspecialChar() {
        nombreCorto.setError(null);
    }


    public interface OnSaveSectorManageView {
        void onSaveSectorManageView();
    }
}
