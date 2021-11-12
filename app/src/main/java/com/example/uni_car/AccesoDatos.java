package com.example.uni_car;

import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.Executor;

public class AccesoDatos {
    public static FirebaseAuth firebaseAuth ;

    public static void registrarusuario (String email, String password, RegisterActivity r) {


        firebaseAuth = FirebaseAuth.getInstance();


            firebaseAuth.createUserWithEmailAndPassword(email, password)

                    .addOnCompleteListener(r, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //checking if success
                            if (task.isSuccessful()) {

                                Toast.makeText(r, "Se ha registrado el usuario con el email: " + email, Toast.LENGTH_LONG).show();
                                Intent intent= new Intent (r, Login.class);
                                r.startActivity(intent);
                            } else {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisiÛn
                                    Toast.makeText(r, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(r, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                                }
                            }

                        }
                    });
        }

    public static void logUser(String mail, String passwd, Login l) {
        //Obtenemos el email y la contraseÒa desde las cajas de texto

        final String email = "nico@nico.com";
        String password = "niconico";
        //String email = mail;
        //String password = passwd;

        //Verificamos que las cajas de texto no esten vacÌas
        if (TextUtils.isEmpty(email)) {//(precio.equals(""))
            Toast.makeText(l, "Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(l, "Falta ingresar la contraseÒa", Toast.LENGTH_LONG).show();
            return;
        }

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener( l, new
                        OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //checking if success
                                if (task.isSuccessful()) {



                                    Toast.makeText(l, "Bienvenido: " + email, Toast.LENGTH_LONG).show();
                                    Intent intencion = new Intent(l.getApplication(), MainActivity.class);

                                    l.startActivity(intencion);


                                } else {
                                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisiÛn
                                        Toast.makeText(l, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(l, "No se pudo iniciar sesion ", Toast.LENGTH_LONG).show();
                                    }
                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("error", e.getLocalizedMessage().toString());
            }
        });
    }


    }

