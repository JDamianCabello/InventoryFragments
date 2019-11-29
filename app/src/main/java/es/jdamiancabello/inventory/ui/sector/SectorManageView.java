package es.jdamiancabello.inventory.ui.sector;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import es.jdamiancabello.inventory.R;
import es.jdamiancabello.inventory.data.model.Dependency;
import es.jdamiancabello.inventory.data.model.Sector;


public class SectorManageView extends Fragment implements SectorManageContract.View, SectorSpinnerContentContract.Spinner{

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

        if (getArguments() != null){
            Sector sector = getArguments().getParcelable("sectpr");
            ednombreCorto.setText(sector.getShortName());
            ednombre.setText(sector.getName());
            eddescripcion.setText(sector.getSectorDescription());
            spinner.setSelection(getIndex(spinner,sector.getDependency().toString()));
            ednombreCorto.setEnabled(false);
        }

        fbSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getArguments()!= null){
                    presenter.onValidateModify(ednombre.getText().toString(),ednombreCorto.getText().toString(),eddescripcion.getText().toString());
                }else {
                    presenter.onValidate(ednombre.getText().toString(),ednombreCorto.getText().toString(),eddescripcion.getText().toString(),(Dependency)spinner.getSelectedItem());
                }
            }
        });

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

    private int getIndex(Spinner spinner, String dependencyName) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if(spinner.getItemAtPosition(i).toString().equalsIgnoreCase(dependencyName))
                return i;
        }
        return 0;
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
    public Dependency getSelectedDependency(Dependency selectedDependency) {
        return null;
    }


    public interface OnSaveSectorManageView {
        void onSaveSectorManageView();
    }
}
