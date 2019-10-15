package es.jdamiancabello.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

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
        //Esto es lo que viene a ser un hilo, fuera de la interfaz gráfica
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(WAIT_TIME);
                    InitialLogin();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
    }



    private void InitialLogin() {
        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
        finish(); //Llama a su método OnDestroy() no se podrá volver atras.
    }
}
