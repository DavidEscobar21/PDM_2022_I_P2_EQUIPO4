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
import hn.edu.ujcv.pdm_2022_i_p2_equipo4.databinding.ActivityMostrarMenuBinding
import kotlinx.android.synthetic.main.fragment_first3.*

class MostrarMenu : AppCompatActivity() {

    var listMen = ArrayList<String>()
    var adapter: ArrayAdapter<String>? = null

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMostrarMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMostrarMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_mostrar_menu)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        addListItem()

        btnRegistrarMenu.setOnClickListener {
            val intent: Intent = Intent(this, Menus::class.java)
            startActivity(intent) }

        btnRegresarMenu.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun addListItem(){
        for (i in Singleton.listaMenus) {
            listMen.add("ID: " +i.id+ ",  Nombre: " +i.nombre+ ",  Precio: " + i.precio + ", Descripcion: " + i.descripcion)
        }
        adapter?.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,listMen)
        listViewMenu.adapter = adapter
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_mostrar_menu)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}