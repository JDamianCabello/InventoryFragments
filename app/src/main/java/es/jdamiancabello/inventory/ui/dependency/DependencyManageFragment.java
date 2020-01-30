package es.jdamiancabello.inventory.ui.dependency;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Random;

import es.jdamiancabello.inventory.R;
import es.jdamiancabello.inventory.data.model.Dependency;
import es.jdamiancabello.inventory.data.model.Sector;
import es.jdamiancabello.inventory.ui.InventoryApplication;


public class DependencyManageFragment extends Fragment implements DependencyManageContract.View{
    private TextInputLayout nombreCorto, nombre, descripcion;
    private TextInputEditText ednombreCorto, ednombre, eddescripcion;
    private Spinner spinner;
    private FloatingActionButton floatingActionButton;
    private DependencyManageContract.Presenter presenter;
    private onSaveFragmentListener activityListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activityListener = (DependencyActivity)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityListener = null;
    }

    public static final String TAG = "dependencyAddFragment";

    public static Fragment newInstance(Bundle bundle) {
        DependencyManageFragment dependencyManageFragment = new DependencyManageFragment();
        if (bundle != null){
            dependencyManageFragment.setArguments(bundle);
        }
        return dependencyManageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_dependency_manage,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nombre = view.findViewById(R.id.tilDependencyManageName);
        nombreCorto = view.findViewById(R.id.tilDependencyManageShort);
        descripcion = view.findViewById(R.id.tilDependencyManageDescription);

        eddescripcion = view.findViewById(R.id.tiledDependencyManageDescription);
        ednombre = view.findViewById(R.id.tiledDependencyManageName);
        ednombreCorto = view.findViewById(R.id.tiledDependencyManageShort);

        spinner = view.findViewById(R.id.spinnerDependencyManageInventory);
        floatingActionButton = getActivity().findViewById(R.id.floatingActionButton);
        floatingActionButton.setImageResource(R.drawable.ic_action_save);
        if (getArguments() != null){
            Dependency dependency = getArguments().getParcelable(Dependency.TAG);
            ednombreCorto.setText(dependency.getShortName());
            ednombre.setText(dependency.getName());
            eddescripcion.setText(dependency.getDescription());
            spinner.setSelection(getIndex(spinner,dependency.getInventory()));
            ednombreCorto.setEnabled(false);
        }
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getArguments()!= null){
                    presenter.onValidateModify(new Dependency(ednombre.getText().toString(),ednombreCorto.getText().toString(),eddescripcion.getText().toString(),spinner.getSelectedItem().toString(),""));
                }else {
                    presenter.onAdd(new Dependency(ednombre.getText().toString(),ednombreCorto.getText().toString(),eddescripcion.getText().toString(),spinner.getSelectedItem().toString(),""));

                }
            }
        });
    }


    private int getIndex(Spinner spinner, String year) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if(spinner.getItemAtPosition(i).toString().equalsIgnoreCase(year))
                return i;
        }
        return 0;
    }


    @Override
    public void onShortNameEmpty(String error) {
        nombreCorto.setError(error);
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
    public void onClearErrorShortNameEmpty() {
        nombreCorto.setError(null);
    }

    @Override
    public void onlearErrorNameEmpty() {
        nombre.setError(null);
    }

    @Override
    public void onlearErrorDescriptionEmpty() {
        descripcion.setError(null);
    }

    @Override
    public void onSucess(Dependency dependency) {
        Intent intent = new Intent(getContext(),DependencyActivity.class);
        intent.putExtra("NOTIFICATION", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Dependency.TAG, dependency);
        intent.putExtras(bundle);

        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),new Random().nextInt(100), intent,PendingIntent.FLAG_UPDATE_CURRENT);


        Notification.Builder builder = new Notification.Builder(
                getContext(),
                InventoryApplication.CHANNEL_ID)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_inventory)
                .setContentTitle("Inventory")
                .setContentText(dependency.getName())
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getContext());

        notificationManagerCompat.notify(new Random().nextInt(1000),builder.build());


        activityListener.onSaveFragment();
    }

    @Override
    public void onSucess() {

    }

    @Override
    public void setPresenter(DependencyManageContract.Presenter presenter) {
        this.presenter = presenter;

    }

    @Override
    public void showGenericError(String s) {
        Snackbar.make(getView(),s, Snackbar.LENGTH_SHORT).show();
    }

    interface onSaveFragmentListener{
        void onSaveFragment();
    }
}