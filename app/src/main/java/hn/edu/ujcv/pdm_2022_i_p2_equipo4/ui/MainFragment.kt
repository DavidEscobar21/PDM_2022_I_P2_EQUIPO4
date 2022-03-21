package hn.edu.ujcv.pdm_2022_i_p2_equipo4.ui

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hn.edu.ujcv.pdm_2022_i_p2_equipo4.*
import hn.edu.ujcv.pdm_2022_i_p2_equipo4.pedidos.MostrarPedidos
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel


        btnClientes.setOnClickListener {
            val intent: Intent = Intent(this.context, MostrarClientes::class.java)
            startActivity(intent)
        }

        btnEmpleados.setOnClickListener {
            val intent: Intent = Intent(this.context, MostrarEmpleados::class.java)
            startActivity(intent)
        }

        btnMenu.setOnClickListener{
            val intent: Intent = Intent(this.context, MostrarMenu::class.java)
            startActivity(intent)
        }

        btnPedidos.setOnClickListener{
            val intent = Intent(this.context, MostrarPedidos::class.java )
            startActivity(intent)
        }

        btnFactura.setOnClickListener {
            val intent: Intent = Intent(this.context, MostrarFacturas::class.java)
            startActivity(intent) }
        }

    }

