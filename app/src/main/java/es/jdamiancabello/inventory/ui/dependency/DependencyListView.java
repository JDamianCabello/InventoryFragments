package es.jdamiancabello.inventory.ui.dependency;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.inventory.R;
import es.jdamiancabello.inventory.adapter.DependencyAdapter;
import es.jdamiancabello.inventory.data.model.Dependency;

public class DependencyListView extends Fragment implements DependencyListContract.View{

    private FloatingActionButton fabAdd;
    private showAddFragmentListener activityListener;
    private DependencyAdapter.onManageDependencyListener adapterOnManagerDependency;
    private RecyclerView rvDependencies;
    private ProgressBar progressBar;
    private DependencyAdapter dependencyAdapter;
    private DependencyListContract.Presenter presenterListener;
    public static final String TAG = "dependencyListFragment";

    public static Fragment newInstance(Bundle bundle) {
        DependencyListView dependencyListView = new DependencyListView();
        if (bundle != null){
            dependencyListView.setArguments(bundle);
        }
        return dependencyListView;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_dependency_list, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar= view.findViewById(R.id.progressBar);

        rvDependencies = view.findViewById(R.id.rvDependency);

        dependencyAdapter = new DependencyAdapter();
        DependencyAdapter.onManageDependencyListener adapterOnManagerDependency = new DependencyAdapter.onManageDependencyListener() {
            @Override
            public void onEditDependencyListener(Dependency dependency) {
                activityListener.showAddFragment(dependency);
            }

            @Override
            public void onDeleteDependencyListener(final Dependency dependency) {
                new AlertDialog.Builder(getContext()).setTitle("ELIMINAR").setMessage("¿Seguro que desea elmininar " + dependency.getName() + "?").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenterListener.deleteDependency(dependency);
                    }
                }).setNegativeButton(android.R.string.no,null).show();
            }
        };
        dependencyAdapter.setViewOnManageDependencyListener(adapterOnManagerDependency);
        rvDependencies.setAdapter(dependencyAdapter);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvDependencies.setLayoutManager(linearLayoutManager);

        fabAdd = view.findViewById(R.id.fabAddDependency);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityListener.showAddFragment(null);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activityListener = (showAddFragmentListener) context;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenterListener.loadData();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.activityListener= null;
    }

    @Override
    public void refresh(ArrayList<Dependency> dependencies) {
        dependencyAdapter.clear();
        dependencyAdapter.addAll(dependencies);
        dependencyAdapter.notifyDataSetChanged();
    }


    @Override
    public void noDependencies() {
        Snackbar.make(getView(),"No hay datos para mostrar.",Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }


    public void onSucessDelete(List<Dependency> dependencyList) {
        dependencyAdapter.clear();
        dependencyAdapter.addAll((ArrayList<Dependency>) dependencyList);
        dependencyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSucess() {

    }

    @Override
    public void setPresenter(DependencyListContract.Presenter presenter) {
        this.presenterListener = presenter;
    }

    @Override
    public void showGenericError(String s) {
        Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
    }

    interface showAddFragmentListener{
        void showAddFragment(Dependency d);
    }
}
