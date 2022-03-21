package hn.edu.ujcv.pdm_2022_i_p2_equipo4.pedidos

import android.annotation.SuppressLint
import android.os.Bundle
import android.telephony.TelephonyCallback
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import hn.edu.ujcv.pdm_2022_i_p2_equipo4.*
import kotlinx.android.synthetic.main.fragment_ingresar_pedido.*
import kotlinx.android.synthetic.main.fragment_ingresar_pedido.view.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match

/**
 * A simple [Fragment] subclass.
 * Use the [IngresarPedidoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IngresarPedidoFragment : Fragment() {

    var cantidades = ArrayList<Int>()
    var menus = ArrayList<DatosMenu>()
    var menusAcumulados = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ingresar_pedido,container,false)

        view.totalPagarTxt.text = "0.00"

        //llenar spinner de clientes
        val spinner: Spinner = view.findViewById(R.id.spinnerClientes)
        val adapter = ArrayAdapter(context!!,android.R.layout.simple_spinner_item,Singleton.listaClientes)
        spinner.adapter = adapter

        //llenar spinner de menus
        val spinnerMenus: Spinner = view.findViewById(R.id.spinnerMenus)
        val adapterMenus = ArrayAdapter(context!!,android.R.layout.simple_spinner_item,Singleton.listaMenus)
        spinnerMenus.adapter = adapterMenus

        //llenar spinner de meseros
        var listaMeseros = ArrayList<DatosEmpleados>()
        for (item in Singleton.listaEmpleados){
            if(item.puesto.equals("Mesero") || item.puesto.equals("mesero")){
                listaMeseros.add(item)
            }
        }
        val spinnerMeseros: Spinner = view.findViewById(R.id.spinnerMeseros)
        //array adapter para listview
        val adapterMeseros = ArrayAdapter(context!!,android.R.layout.simple_spinner_item,listaMeseros)
        spinnerMeseros.adapter = adapterMeseros

        view.anadirMenuBtn.setOnClickListener {
            agregarMenu(view)
        }

        view.btnGuardarPedido.setOnClickListener{
            guardar(view)
        }
        return view
    }

    companion object{
        fun newInstance() = IngresarPedidoFragment()
    }

    private fun agregarMenu(view : View){
        val cantidadTxt = view.findViewById<EditText>(R.id.cantidadTxt)
        var lista = view.findViewById<ListView>(R.id.menusPedidosList)
        var spinnerMenu = view.findViewById<Spinner>(R.id.spinnerMenus)
        val menuSeleccionadoPos = spinnerMenu.selectedItemPosition

        //validaciones de cantidad, mayor a 0 o campo vacio
        if (cantidadTxt.text.toString() == ""){
            Snackbar.make(view,"Debes seleccionar una cantidad.",Snackbar.LENGTH_SHORT).show()
            return
        }else{
            if (Integer.parseInt(cantidadTxt.text.toString()) < 1){
                Snackbar.make(view,"La cantidad debe ser mayor a 0.",Snackbar.LENGTH_SHORT).show()
                return
            }
        }

        //guardar menu seleccionado
        menus.add(Singleton.listaMenus.get(menuSeleccionadoPos))

        //encabezado
        if (menusAcumulados.size < 1){

            menusAcumulados.add("ID \t Nombre \t Cantidad \t Precio \t Subtotal")
            var cantidad = Integer.parseInt(cantidadTxt.text.toString())
            cantidades.add(cantidad)
            var menuSelec = menus[0]
            var subtotal = cantidad * menuSelec.precio
            menusAcumulados.add("${menuSelec.id} \t  ${menuSelec.nombre} \t          $cantidad \t          ${menuSelec.precio} \t          $subtotal")

        }else{
            var cantidad = Integer.parseInt(cantidadTxt.text.toString())
            cantidades.add(cantidad)
            var ultimoMenuAgregado = menus[menus.size-1]
            var subtotal = cantidad * ultimoMenuAgregado.precio
            menusAcumulados.add("${ultimoMenuAgregado.id} \t  ${ultimoMenuAgregado.nombre} \t          $cantidad \t          ${ultimoMenuAgregado.precio} \t          $subtotal")
        }
        //list adapter
        var adapterListView = ArrayAdapter(context!!,android.R.layout.simple_list_item_1, menusAcumulados)
        lista.adapter = adapterListView

        //calcular total automaticamente
        val txtTotal = view.findViewById<TextView>(R.id.totalPagarTxt)
        txtTotal.text = calcularTotal().toString() + " Lps"
    }

    private fun calcularTotal() : Double{
        var total = 0.0
        for (i in 1 until menusAcumulados.size){
            var detalle = menusAcumulados[i]
            var subtotal : Double = detalle.split("\t")[4].toString().trim().toDouble()
            total += subtotal
        }
        return total
    }

    private fun guardar(view : View){
        if (!verificarCamposVacios()){
            Snackbar.make(view,"Debes llenar todos los campos",Snackbar.LENGTH_SHORT).show()
            return
        }
        //clienteSeleccionado
        var spinnerClientes = view.findViewById<Spinner>(R.id.spinnerClientes)
        var cliente = Singleton.listaClientes.get(spinnerClientes.selectedItemPosition)

        //mesero seleccionado
        var spinnerMesero = view.findViewById<Spinner>(R.id.spinnerMeseros)
        var mesero = DatosEmpleados()
        for (item in Singleton.listaEmpleados){
            if (item.ID == Integer.parseInt(spinnerMesero.selectedItem.toString().split(" ")[0])){
                mesero = item
            }
        }

        //lista de menus
        val array = arrayOfNulls<DatosMenu>(menus.size)

        val pedidoGuardar = DatosPedidos(cliente,menus.toArray(array),cantidades,mesero,calcularTotal().toFloat())
        Singleton.listaPedidos.add(pedidoGuardar)
        Snackbar.make(view,"Pedido almacenado correctamente",Snackbar.LENGTH_SHORT).show()
        regresarLista()
    }

    private fun verificarCamposVacios() : Boolean{
        //meseros
        var listaMeseros = ArrayList<DatosEmpleados>()
        for (item in Singleton.listaEmpleados){
            if(item.puesto.equals("Mesero") || item.puesto.equals("mesero")){
                listaMeseros.add(item)
            }
        }

        if (Singleton.listaClientes.size == 0){
            return false
        }else if (listaMeseros.isEmpty()){
            return false
        }else if (menus.isEmpty()){
            return false
        }
        return true
    }

    private fun regresarLista(){
        activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListaPedidosFragment.newInstance())
                .commitNow()
    }
}