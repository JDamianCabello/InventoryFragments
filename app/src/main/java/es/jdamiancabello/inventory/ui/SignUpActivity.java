package es.jdamiancabello.inventory.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import es.jdamiancabello.inventory.R;
import es.jdamiancabello.inventory.utils.CommonUtils;

public class SignUpActivity extends AppCompatActivity {

    private Button btSignUp;

    private TextInputEditText tiedUser;
    private TextInputEditText tiedPassword;
    private TextInputEditText tiedRepeatPassword;
    private TextInputEditText tiedEmail;

    private TextInputLayout tilUser;
    private TextInputLayout tilPassword;
    private TextInputLayout tilConfirmPassword;
    private TextInputLayout tilEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btSignUp = findViewById(R.id.btnSignUp);
        tiedUser = findViewById(R.id.tieUserName);
        tiedPassword = findViewById(R.id.tiePassword);
        tiedRepeatPassword = findViewById(R.id.tieConfirmPassword);
        tiedEmail = findViewById(R.id.tieEmail);


        tilUser = findViewById(R.id.tilUserName);
        tilPassword = findViewById(R.id.tilPassword);
        tilConfirmPassword = findViewById(R.id.tilConfirmPassword);
        tilEmail = findViewById(R.id.tilEmail);

        tiedUser.addTextChangedListener(new SignUpWatcher(tiedUser));
        tiedPassword.addTextChangedListener(new SignUpWatcher(tiedPassword));
        tiedRepeatPassword.addTextChangedListener(new SignUpWatcher(tiedRepeatPassword));
        tiedEmail.addTextChangedListener(new SignUpWatcher(tiedEmail));

        //Creamos un objeto de la clase SignUpWatcher

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
            startActivity(new Intent(SignUpActivity.this,LoginActivity.class));//.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
            finish();
        }
    }


    /**
     * Valida un usuario
     * @return
     * @param user
     */
    private boolean validateUser(String user) {
        if(TextUtils.isEmpty(user))
            tiedUser.setError(getString(R.string.errUserEmpty));
        requestFocus(tilUser);
    }

    private void requestFocus(TextInputLayout textInputLayout) {
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

        return (!psw.equals(confirmPsw) && !CommonUtils.patterPassword(psw)) ? true : false;

    }

    /**
     * Comprueba que lo introducido sea un correo válido además de:
     *      -no esté vacío
     * @return
     * @param s El string que contiene el e-mail
     */
    private boolean validateEmail(String s) {

        return  CommonUtils.patternEmail(s);
    }

    class SignUpWatcher implements TextWatcher{
        private View view;

        private SignUpWatcher(View view){
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()){
                case R.id.edUser:
                    validateUser(((EditText)view).getText().toString());
            }
        }
    }
}
