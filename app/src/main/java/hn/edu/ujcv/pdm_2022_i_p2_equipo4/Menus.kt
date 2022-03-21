package hn.edu.ujcv.pdm_2022_i_p2_equipo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_clientes.*
import kotlinx.android.synthetic.main.activity_menus.*

class Menus : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menus)

        btnGuardarMenus.setOnClickListener {GuardarMenu()}

    }

    private fun GuardarMenu() {

        if(txtIDMenu.text.trim().length != 6){
            txtIDMenu.error ="El ID del menú debe contener 6 números"

        }else if(txtNombreMenu.text.trim().isEmpty()){
            txtNombreMenu.error ="La nombre del menú no puede estar vacía"

        }else if(txtNombreMenu.text.length < 3){
            txtNombreMenu.error ="La nombre del menú no puede contener menos de 3 letras"

        }else if(txtPrecio.text.trim().isEmpty()){
            txtPrecio.error ="El precio no puede estar vacío"

        }else if(txtPrecio.text.toString().toDouble() <= 0){
            txtPrecio.error ="El precio no puede ser cero"

        }else if(txtDescripcion.text.trim().isEmpty()){
            txtDescripcion.error ="La descripción no puede estar vacía"

        }else{
            val menu = DatosMenu(txtIDMenu.text.toString().toInt(),txtNombreMenu.text.toString(),
                txtPrecio.text.toString().toDouble(),txtDescripcion.text.toString())
            Singleton.listaMenus.add(menu)
            Toast.makeText(this, "Se guardó correctamente", Toast.LENGTH_SHORT).show()
            limpiarClase()
            val intent: Intent = Intent(this, MostrarMenu::class.java)
            startActivity(intent)
        }
    }


    private fun limpiarClase() {
        txtIDMenu.setText("")
        txtNombreMenu.setText("")
        txtPrecio.setText("")
        txtDescripcion.setText("")

    }



}