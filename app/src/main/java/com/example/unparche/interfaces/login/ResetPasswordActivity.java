package com.example.unparche.interfaces.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.unparche.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText mUsername;
    Button mReset;

    FirebaseAuth firebaseAuth;
    AwesomeValidation awesomeValidation;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        mUsername = findViewById(R.id.etUsername);
        mReset = findViewById(R.id.btnReset);

        firebaseAuth = FirebaseAuth.getInstance();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.etUsername, Patterns.EMAIL_ADDRESS, R.string.invalid_username);

        progressDialog = new ProgressDialog(this);

        mReset.setEnabled(true);

        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = mUsername.getText().toString();
                if(awesomeValidation.validate()){
                    progressDialog.setMessage("Espere un momento...");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    firebaseAuth.setLanguageCode("es");
                    firebaseAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ResetPasswordActivity.this, "Se ha enviado un correo para reestablecer tu contrase??a :)", Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                                dameToastDeError(errorCode);
                            }
                            progressDialog.dismiss();
                        }
                    });
                }else{
                    Toast.makeText(ResetPasswordActivity.this, R.string.completar_campos, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void dameToastDeError(String error) {

        switch (error) {

            case "ERROR_INVALID_CUSTOM_TOKEN":
                Toast.makeText(ResetPasswordActivity.this, "El formato del token personalizado es incorrecto. Por favor revise la documentaci??n", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_CUSTOM_TOKEN_MISMATCH":
                Toast.makeText(ResetPasswordActivity.this, "El token personalizado corresponde a una audiencia diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_CREDENTIAL":
                Toast.makeText(ResetPasswordActivity.this, "La credencial de autenticaci??n proporcionada tiene un formato incorrecto o ha caducado.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_EMAIL":
                Toast.makeText(ResetPasswordActivity.this, "La direcci??n de correo electr??nico est?? mal formateada.", Toast.LENGTH_LONG).show();
                mUsername.setError("La direcci??n de correo electr??nico est?? mal formateada.");
                mUsername.requestFocus();
                break;

            case "ERROR_USER_MISMATCH":
                Toast.makeText(ResetPasswordActivity.this, "Las credenciales proporcionadas no corresponden al usuario que inici?? sesi??n anteriormente..", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_REQUIRES_RECENT_LOGIN":
                Toast.makeText(ResetPasswordActivity.this,"Esta operaci??n es sensible y requiere autenticaci??n reciente. Inicie sesi??n nuevamente antes de volver a intentar esta solicitud.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                Toast.makeText(ResetPasswordActivity.this, "Ya existe una cuenta con la misma direcci??n de correo electr??nico pero diferentes credenciales de inicio de sesi??n. Inicie sesi??n con un proveedor asociado a esta direcci??n de correo electr??nico.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_EMAIL_ALREADY_IN_USE":
                Toast.makeText(ResetPasswordActivity.this, "La direcci??n de correo electr??nico ya est?? siendo utilizada por otra cuenta..   ", Toast.LENGTH_LONG).show();
                mUsername.setError("La direcci??n de correo electr??nico ya est?? siendo utilizada por otra cuenta.");
                mUsername.requestFocus();
                break;

            case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                Toast.makeText(ResetPasswordActivity.this, "Esta credencial ya est?? asociada con una cuenta de usuario diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_DISABLED":
                Toast.makeText(ResetPasswordActivity.this, "La cuenta de usuario ha sido inhabilitada por un administrador..", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_TOKEN_EXPIRED":
                Toast.makeText(ResetPasswordActivity.this, "La credencial del usuario ya no es v??lida. El usuario debe iniciar sesi??n nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_NOT_FOUND":
                Toast.makeText(ResetPasswordActivity.this, "No hay ning??n registro de usuario que corresponda a este identificador. Es posible que se haya eliminado al usuario.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_USER_TOKEN":
                Toast.makeText(ResetPasswordActivity.this, "La credencial del usuario ya no es v??lida. El usuario debe iniciar sesi??n nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_OPERATION_NOT_ALLOWED":
                Toast.makeText(ResetPasswordActivity.this, "Esta operaci??n no est?? permitida. Debes habilitar este servicio en la consola.", Toast.LENGTH_LONG).show();
                break;
        }

    }
}