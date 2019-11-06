package es.jdamiancabello.inventory.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
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
    private TextInputLayout tilEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btSignUp = findViewById(R.id.btnSignUp);
        tiedUser = findViewById(R.id.tieUserName);
        tiedPassword = findViewById(R.id.tiePassword);
        tiedEmail = findViewById(R.id.tieEmail);


        tilUser = findViewById(R.id.tilUserName);
        tilPassword = findViewById(R.id.tilPassword);
        tilEmail = findViewById(R.id.tilEmail);

        tiedUser.addTextChangedListener(new SignUpWatcher(tiedUser));
        tiedPassword.addTextChangedListener(new SignUpWatcher(tiedPassword));
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
        if (validateUser(tiedUser.getText().toString()) & validatePassword(tiedPassword.getText().toString()) & validateEmail(tiedEmail.getText().toString())) {
            //1.-Se guarda el usuario en la base de datos

            //2.-Envio correo de confirmación al usuario

            //3.-Se pasa a LoginActivity
            finish();
        }
    }


    /**
     * Valida un usuario
     *
     * @param user
     * @return
     */
    private boolean validateUser(String user) {
        if (CommonUtils.patterUser(user)) {
            tilUser.setError(null);
            return true;
        }
        else {
            tilUser.setError(getString(R.string.errUserEmpty));
            displaySoftKeyboard(tiedUser);
            return false;
        }
    }

    /**
     * Este método abre el teclado en caso de que una vista (TextInputEditText) tenga el foco
     */
    private void displaySoftKeyboard(View view){
        if(view.requestFocus()){
            //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view,0);
        }
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
     */
    private boolean validatePassword(String psw) {

        if( !CommonUtils.patterPassword(psw)) {
            tilPassword.setError(getString(R.string.errPassword));
            displaySoftKeyboard(tiedPassword);
            return false;
        }
        else{
            tilPassword.setError(null);
            return true;
        }
    }

    /**
     * Comprueba que lo introducido sea un correo válido además de:
     *      -no esté vacío
     * @return
     * @param s El string que contiene el e-mail
     */
    private boolean validateEmail(String s) {
        if( CommonUtils.patternEmail(s)) {
            tilEmail.setError(null);
            return true;
        }
        else{
            tilEmail.setError(getString(R.string.errEmail));
            displaySoftKeyboard(tiedEmail);
            return false;
        }
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
                case R.id.tieUserName:
                    validateUser(((TextInputEditText)view).getText().toString());
                    break;
                case R.id.tiePassword:
                    validatePassword(((TextInputEditText)view).getText().toString());
                    break;
                case R.id.tieEmail:
                    validateEmail(((TextInputEditText)view).getText().toString());
                    break;
            }
        }
    }
}
