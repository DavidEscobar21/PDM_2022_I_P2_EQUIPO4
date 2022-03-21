package hn.edu.ujcv.pdm_2022_i_p2_equipo4.pedidos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import hn.edu.ujcv.pdm_2022_i_p2_equipo4.R
import kotlinx.android.synthetic.main.activity_mostrar_pedidos.*
import kotlinx.android.synthetic.main.content_mostrar_pedidos.*

class MostrarPedidos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_pedidos)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListaPedidosFragment.newInstance())
                .commitNow()
        }
    }

    fun ingresarPedido(view: View){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, IngresarPedidoFragment.newInstance())
            .commitNow()
    }



}