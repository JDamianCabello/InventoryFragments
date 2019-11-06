package es.jdamiancabello.inventory.ui.dependency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import es.jdamiancabello.inventory.R;
import es.jdamiancabello.inventory.adapter.DependencyAdapter;

public class DependencyListActivity extends AppCompatActivity {
    private RecyclerView rvDependencies;
    private DependencyAdapter dependencyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependency_list);

        dependencyAdapter = new DependencyAdapter();

        rvDependencies = findViewById(R.id.rvDependency);
        rvDependencies.setAdapter(dependencyAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvDependencies.setLayoutManager(linearLayoutManager);
    }
}
