package hn.edu.ujcv.pdm_2022_i_p2_equipo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_clientes.*
import kotlinx.android.synthetic.main.activity_empleados.*
import java.util.regex.Pattern

class Empleados : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empleados)

        btnGuardarEmpleado.setOnClickListener {GuardarEmpleado()}

    }

    private fun GuardarEmpleado() {

        val nombre = "^([A-Z][a-záéíóú]* )(([A-Z][a-záéíóú]* [A-Z][a-záéíóú]*)|([A-Z][a-záéíóú]*)|([A-Z][a-záéíóú]* [A-Z][a-záéíóú]* [A-Z][a-záéíóú]*))\$"

        if(txtIDEmpleado.text.trim().length != 6){
            txtIDEmpleado.error ="El ID del Empleado debe contener 6 números"

        }else if(!(Pattern.matches(nombre,txtNombreEmpleado.text.toString()))){
            txtNombreEmpleado.error ="Nombre inválido"

        }else if(txtPuesto.text.trim().isEmpty()) {
            txtPuesto.error = "El puesto no debe de estar vacío"

        }else if(txtPuesto.text.length < 3){
            txtPuesto.error ="El puesto no puede contener menos de 3 letras"

        }else{
            val empleado = DatosEmpleados(txtIDEmpleado.text.toString().toInt(),txtNombreEmpleado.text.toString(),
                txtPuesto.text.toString())
            Singleton.listaEmpleados.add(empleado)
            Toast.makeText(this, "Se guardó correctamente", Toast.LENGTH_SHORT).show()
            limpiarClase()
            val intent: Intent = Intent(this, MostrarEmpleados::class.java)
            startActivity(intent)
        }
    }





    private fun limpiarClase() {
        txtIDEmpleado.setText("")
        txtNombreEmpleado.setText("")
        txtPuesto.setText("")

    }

}