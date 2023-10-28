package com.example.semana9firebase1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.semana9firebase1.model.UserModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        val txtfullname: EditText = findViewById(R.id.txtfullname)
        val txtcountry: EditText = findViewById(R.id.txtcountry)
        val txtemailreg: EditText = findViewById(R.id.txtemailreg)
        val txtpasswordregister: EditText = findViewById(R.id.txtpasswordregister)
        val btnreg: Button = findViewById(R.id.btnreg)
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("users")

        btnreg.setOnClickListener {
            val correo = txtemailreg.text.toString()
            val clave = txtpasswordregister.text.toString()
            val nombrecompleto = txtfullname.text.toString()
            val pais = txtcountry.text.toString()

            auth.createUserWithEmailAndPassword(correo,clave)
                .addOnCompleteListener(this){task->
                    if (task.isSuccessful){
                        //registrar en la colección
                        val user:FirebaseUser? = auth.currentUser
                        val uid = user?.uid

                        val userModel = UserModel(correo,clave,nombrecompleto,pais,uid.toString())
                        collectionRef.add(userModel)
                            .addOnCompleteListener{ }.addOnFailureListener{ error ->
                                Snackbar
                                    .make(findViewById(android.R.id.content),
                                        "Ocurrio un error al registrar el modelo",
                                        Snackbar.LENGTH_LONG
                                    ).show()
                            }
                        Snackbar
                            .make(
                                findViewById(android.R.id.content)
                                ,"Registro exitoso del usuario"
                                , Snackbar.LENGTH_LONG
                            ).show()
                    }else{
                        Snackbar
                            .make(findViewById(android.R.id.content),
                                "Ocurrio un error al registrarse",
                                Snackbar.LENGTH_LONG
                            ).show()
                    }
                }
        }

        }
    }