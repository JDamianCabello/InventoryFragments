package es.jdamiancabello.inventory.ui.dependency;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import es.jdamiancabello.inventory.R;
import es.jdamiancabello.inventory.adapter.DependencyAdapter;
import es.jdamiancabello.inventory.ui.base.BaseDialogFragment;
import es.jdamiancabello.inventory.data.model.Dependency;

public class DependencyListView extends Fragment implements DependencyListContract.View, BaseDialogFragment.OnAcceptDialogListener{

    public static final int CODE_DELETE = 300;
    private FloatingActionButton fabAdd;
    private showAddFragmentListener activityListener;
    private DependencyAdapter.onManageDependencyListener adapterOnManagerDependency;
    private RecyclerView rvDependencies;
    private ProgressBar progressBar;
    private DependencyAdapter dependencyAdapter;
    private DependencyListContract.Presenter presenterListener;
    private Dependency deletedDependency;
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

        //Se debe añadir la siguiente línea para que llame a los métodos callback
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar= view.findViewById(R.id.progressBar);

        rvDependencies = view.findViewById(R.id.rvDependency);

        dependencyAdapter = new DependencyAdapter();
        adapterOnManagerDependency = new DependencyAdapter.onManageDependencyListener() {
            @Override
            public void onEditDependencyListener(Dependency dependency) {
                activityListener.showAddFragment(dependency);
            }

            @Override
            public void onDeleteDependencyListener(final Dependency dependency) {
                Bundle b = new Bundle();
                b.putString(BaseDialogFragment.TITTLE,"ELIMINAR");
                b.putString(BaseDialogFragment.MESSAGE, "¿Seguro que desea elmininar " + dependency.getName() + "?");
                BaseDialogFragment baseDialogFragment = (BaseDialogFragment) BaseDialogFragment.newInstance(b);

                baseDialogFragment.setTargetFragment(DependencyListView.this, CODE_DELETE);
                baseDialogFragment.show(getFragmentManager(),baseDialogFragment.TAG);

                deletedDependency = dependency;

            }
        };
        dependencyAdapter.setViewOnManageDependencyListener(adapterOnManagerDependency);
        rvDependencies.setAdapter(dependencyAdapter);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvDependencies.setLayoutManager(linearLayoutManager);

        fabAdd = getActivity().findViewById(R.id.floatingActionButton);
        fabAdd.setImageResource(R.drawable.ic_action_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityListener.showAddFragment(null);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_dependencylist,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg = new String();
        switch (item.getItemId()){
            case R.id.dependencyListMenu_orderbyName:
                msg = "Order by name option";
                break;
        }
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
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
        Toast.makeText(getContext(),"No hay datos para mostrar.",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }


    public void onSucessDelete() {
        dependencyAdapter.delete(deletedDependency);
        dependencyAdapter.notifyDataSetChanged();
        showSnackBarDeleted();
    }

    @Override
    public void onSucessUndo(Dependency d) {
        dependencyAdapter.add(d);
        dependencyAdapter.notifyDataSetChanged();
        Toast.makeText(getContext(),"Se ha restaurado "+d.getName(),Toast.LENGTH_SHORT).show();
    }

    private void showSnackBarDeleted() {
        final Dependency undoDependency = deletedDependency;
        Snackbar.make(getView(),R.string.undoDelete,Snackbar.LENGTH_LONG).setAction(R.string.undo, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                undoDeleted(undoDependency);
            }
        }).show();
    }

    private void undoDeleted(Dependency d) {
        presenterListener.undoDelete(d);
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

    @Override
    public void onAcceptDialog() {
        presenterListener.deleteDependency(deletedDependency);
        dependencyAdapter.notifyDataSetChanged();
        deletedDependency = null;
    }

    interface showAddFragmentListener{
        void showAddFragment(Dependency d);
    }
}
