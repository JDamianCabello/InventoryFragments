package es.jdamiancabello.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    private Button btSignUp;

    private TextInputEditText tiedUser;
    private TextInputEditText tiedPassword;
    private TextInputEditText tiedRepeatPassword;
    private TextInputEditText tiedEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btSignUp = findViewById(R.id.btSignUp);
        tiedUser = findViewById(R.id.tilUserName);
        tiedPassword = findViewById(R.id.tilPassword);
        tiedRepeatPassword = findViewById(R.id.tilConfirmPassword);
        tiedEmail = findViewById(R.id.tilEmail);

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }

    /*
    Método que comprueba la valided de todos los campos de TextImputLayout
     */
    private void validate() {
        if(validateUser(tiedUser.getText().toString()) && validatePassword(tiedPassword.getText().toString(),tiedRepeatPassword.getText().toString()) && validateEmail(tiedEmail.getText().toString())){
            //1.-Se guarda el usuario en la base de datos

            //2.-Envio correo de confirmación al usuario

            //3.-Se pasa a LoginActivity
            startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            finish();
        }
    }


    /**
     * Valida un usuario
     * @return
     * @param s
     */
    private boolean validateUser(String s) {
        return false;
    }

    /**
     * Comprueba la contraseña:
     *      -tenga entre 8-12 de longitud
     *      -tenga un número
     *      -tenga una mayúscula
     *      -las contraseñas son iguales
     *      -no puede ser nulo (arreglado don el primer control)
     * @return
     * @param psw la contraseña a validar
     * @param confirmPsw la contraseña para comprobar que coinciden
     */
    private boolean validatePassword(String psw, String confirmPsw) {
        return false;
    }

    /**
     * Comprueba que lo introducido sea un correo válido además de:
     *      -no esté vacío
     * @return
     * @param s El string que contiene el e-mail
     */
    private boolean validateEmail(String s) {
        return false;
    }
}
