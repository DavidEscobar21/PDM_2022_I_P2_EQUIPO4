package hn.edu.ujcv.pdm_2022_i_p2_equipo4

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import hn.edu.ujcv.pdm_2022_i_p2_equipo4.databinding.ActivityMostrarEmpleadosBinding
import kotlinx.android.synthetic.main.fragment_first2.*

class MostrarEmpleados : AppCompatActivity() {

    var listEmp = ArrayList<String>()
    var adapter: ArrayAdapter<String>? = null

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMostrarEmpleadosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMostrarEmpleadosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_mostrar_empleados)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        addListItem()

        btnRegistrarEmpleados.setOnClickListener {
            val intent: Intent = Intent(this, Empleados::class.java)
            startActivity(intent) }

        btnRegresarEmpleados.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }


    private fun addListItem(){
        for (i in Singleton.listaEmpleados) {
            listEmp.add("ID: " +i.ID+ ",  Nombre: " +i.nombre+ ",  Puesto: " + i.puesto)
        }
        adapter?.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,listEmp)
        listViewEmpleados.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_mostrar_empleados)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}