package es.jdamiancabello.inventory.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import es.jdamiancabello.inventory.R;
import es.jdamiancabello.inventory.ui.dependency.DependencyActivity;
import es.jdamiancabello.inventory.ui.sector.SectorActivity;

public class DashBoardActivity extends AppCompatActivity {

    ImageButton btDependency;
    ImageButton btSectors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        btDependency = findViewById(R.id.btDependency);

        btDependency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoardActivity.this, DependencyActivity.class));
            }
        });

        btSectors = findViewById(R.id.btSectors);

        btSectors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity((new Intent(DashBoardActivity.this, SectorActivity.class)));
            }
        });
    }
}
