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
import hn.edu.ujcv.pdm_2022_i_p2_equipo4.databinding.ActivityMostrarClientesBinding
import kotlinx.android.synthetic.main.fragment_first.*

class MostrarClientes : AppCompatActivity() {

    var listItem = ArrayList<String>()
    var adapter: ArrayAdapter<String>? = null

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMostrarClientesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMostrarClientesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_mostrar_clientes)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        addListItem()

        btnRegistrarCliente.setOnClickListener {
            val intent: Intent = Intent(this, Clientes::class.java)
            startActivity(intent) }

        btnRegresarCliente.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }


    private fun addListItem(){
        for (i in Singleton.listaClientes) {
            listItem.add("ID: "+i.id+",  Nombre: "+i.nombre+ ",  Correo: " + i.correo)
        }
        adapter?.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,listItem)
        listView.adapter = adapter
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_mostrar_clientes)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}