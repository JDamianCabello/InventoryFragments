package es.jdamiancabello.inventory.ui.dependency;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.jdamiancabello.inventory.R;
import es.jdamiancabello.inventory.adapter.DependencyAdapter;

public class DependencyListFragment extends Fragment {

    private FloatingActionButton fabAdd;
    private OnAddDependencyListener onAddDependencyListener;
    interface OnAddDependencyListener{
        void onAddDependency();
    }



    private RecyclerView rvDependencies;
    private DependencyAdapter dependencyAdapter;
    public static final String TAG = "dependencyListFragment";

    public static Fragment newInstance() {
        return new DependencyListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_dependency_list,container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dependencyAdapter = new DependencyAdapter();

        rvDependencies = view.findViewById(R.id.rvDependency);
        rvDependencies.setAdapter(dependencyAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvDependencies.setLayoutManager(linearLayoutManager);

        fabAdd = view.findViewById(R.id.fabAddDependency);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddDependencyListener.onAddDependency();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            onAddDependencyListener=(OnAddDependencyListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implements OnAddDependencyListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onAddDependencyListener=null;
    }
}
