package com.example.semana9firebase1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = FirebaseFirestore.getInstance()  // referencia a la instancia de Firestore
        val tvcurso: TextView = findViewById(R.id.tvcurso)  // Variables en el layout
        val tvnota: TextView = findViewById(R.id.tvnota)  // Variables en el layout
        db.collection("courses")  // agregar la colección a la que se desea acceder
            //.whereEqualTo("state", "CA")
            .addSnapshotListener{ snapshots,e -> // Leer datos en tiempo real // variables snapshots y e (error)
                if ( e != null){
                    Snackbar
                        .make(findViewById(android.R.id.content),
                            "Ocurrió un error al consultar la colecccion",
                            Snackbar.LENGTH_LONG
                        ).show()
                    //Log.w("Firebase","listen:error",e)
                    return@addSnapshotListener
                }
                for (dc in snapshots!!.documentChanges){
                    when (dc.type){
                        DocumentChange.Type.ADDED, DocumentChange.Type.MODIFIED ->{// si en caso se agregue o modifique un documento
                            Snackbar
                                .make(findViewById(android.R.id.content),
                                    "Se agregó un documento",
                                    Snackbar.LENGTH_LONG
                                ).show()
                            tvcurso.text = dc.document.data["description"].toString()
                            tvnota.text = dc.document.data["score"].toString()
                        }
                        DocumentChange.Type.MODIFIED -> {
                            tvcurso.text = dc.document.data["description"].toString()
                            tvnota.text = dc.document.data["score"].toString()
                        }
                        DocumentChange.Type.REMOVED -> {
                            Snackbar
                                .make(findViewById(android.R.id.content),
                                    "Se elimino un documento",
                                    Snackbar.LENGTH_LONG
                                ).show()
                        }
                        else ->{
                            Snackbar
                                .make(findViewById(android.R.id.content),
                                    "Error al consutar la colección",
                                    Snackbar.LENGTH_LONG
                                ).show()

                        }
                    }
                }
            }
    }
}