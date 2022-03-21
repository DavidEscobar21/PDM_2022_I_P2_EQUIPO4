package hn.edu.ujcv.pdm_2022_i_p2_equipo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_clientes.*
import java.util.regex.Pattern

class Clientes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clientes)

        btnGuardarCliente.setOnClickListener {
            GuardarCliente()

        }

    }


    private fun GuardarCliente() {

        val nombre = "^([A-Z][a-záéíóú]* )(([A-Z][a-záéíóú]* [A-Z][a-záéíóú]*)|([A-Z][a-záéíóú]*)|([A-Z][a-záéíóú]* [A-Z][a-záéíóú]* [A-Z][a-záéíóú]*))\$"
        val pattern = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})\$"

        if(txtIDClientes.text.trim().length != 6){
            txtIDClientes.error ="El ID del cliente debe contener 6 números"

        }else if(!(Pattern.matches(nombre,txtNombreClientes.text.toString()))){
            txtNombreClientes.error ="Nombre inválido"

        }else if(!(Pattern.matches(pattern,txtCorreo.text.toString()))){
            txtCorreo.error ="Correo inválido"
        }else{
            guardar()
        }
    }

    private fun guardar(){
        val cliente = DatosClientes(txtIDClientes.text.toString().toInt(),txtNombreClientes.text.toString(),
            txtCorreo.text.toString())
        Singleton.listaClientes.add(cliente)
        Toast.makeText(this, "Se guardó correctamente", Toast.LENGTH_SHORT).show()
        limpiarClase()
        val intent: Intent = Intent(this, MostrarClientes::class.java)
        startActivity(intent)
    }


    private fun limpiarClase() {
        txtIDClientes.setText("")
        txtNombreClientes.setText("")
        txtCorreo.setText("")


    }
}