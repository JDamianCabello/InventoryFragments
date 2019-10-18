package es.jdamiancabello.inventory.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import es.jdamiancabello.inventory.R;

public class SplashActivity extends AppCompatActivity {

    private static final long WAIT_TIME = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    /**
     * Método que ejecuta el run fuera del hilo de la IU
     */
    @Override
    protected void onStart() {
        super.onStart();
        Handler handler = new Handler();
        //Esto es lo que viene a ser un hilo, fuera de la interfaz gráfica
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                    InitialLogin();
            }
        };
        handler.postDelayed(runnable,WAIT_TIME);
    }



    private void InitialLogin() {
        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
        finish(); //Llama a su método OnDestroy() no se podrá volver atras.
    }
}
