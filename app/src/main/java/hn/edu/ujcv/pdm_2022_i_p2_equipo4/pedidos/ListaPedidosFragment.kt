package hn.edu.ujcv.pdm_2022_i_p2_equipo4.pedidos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import hn.edu.ujcv.pdm_2022_i_p2_equipo4.R
import hn.edu.ujcv.pdm_2022_i_p2_equipo4.Singleton

class ListaPedidosFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.content_mostrar_pedidos,container,false)
        val listaView = view.findViewById<ListView>(R.id.listaPedidos)

        val listViewAdapter = ArrayAdapter(context!!,android.R.layout.simple_list_item_1,Singleton.listaPedidos)
        listaView.adapter = listViewAdapter
        return view
    }

    companion object{
        fun newInstance() = ListaPedidosFragment()
    }




}