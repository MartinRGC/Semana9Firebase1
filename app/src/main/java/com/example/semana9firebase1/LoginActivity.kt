package com.example.semana9firebase1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val auth = FirebaseAuth.getInstance()

        val txtemail: EditText = findViewById(R.id.txtemail)
        val txtpassword: EditText = findViewById(R.id.txtpassword)
        val btnlogin: Button = findViewById(R.id.btnlogin)
        val btngregister: Button = findViewById(R.id.btnregister)


        btnlogin.setOnClickListener {
            val correo = txtemail.text.toString()
            val clave = txtpassword.text.toString()

            auth.signInWithEmailAndPassword(correo,clave) // autenticación Firebase
                .addOnCompleteListener(this){task->
                    if(task.isSuccessful){
                        Snackbar
                            .make(findViewById(android.R.id.content),
                                "Inicio de sesion exitoso",
                                Snackbar.LENGTH_LONG
                            ).show()
                        startActivity(Intent(this,MainActivity::class.java))
                    }else{
                        Snackbar
                            .make(findViewById(android.R.id.content),
                                "Credenciales invalidas",
                                Snackbar.LENGTH_LONG
                            ).show()
                    }
                }
        }
    }
}