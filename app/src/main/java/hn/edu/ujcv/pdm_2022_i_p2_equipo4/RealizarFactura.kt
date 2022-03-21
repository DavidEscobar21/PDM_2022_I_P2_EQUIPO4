package hn.edu.ujcv.pdm_2022_i_p2_equipo4

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_realizar_factura.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class RealizarFactura : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realizar_factura)
        llenarTipoPago()
        llenarPedido()
        llenarEmpledos()
        btnRegresarMostrarFactura.setOnClickListener {
            val intent: Intent = Intent(this, MostrarFacturas::class.java)
            startActivity(intent)
        }
        btnGenerarFactura.setOnClickListener { generarFactura() }

        spnTipoPago.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (spnTipoPago.selectedItem.toString().equals("Tarjeta")){
                    txtEfectivo.isEnabled = false
                    txtTarjeta.isEnabled = true
                    limpiar()
                }else if (spnTipoPago.selectedItem.toString().equals("Efectivo")){
                    txtEfectivo.isEnabled = true
                    txtTarjeta.isEnabled = false
                    limpiar()
                }else if (spnTipoPago.selectedItem.toString().equals("Mixto")){
                    txtEfectivo.isEnabled = true
                    txtTarjeta.isEnabled = true
                    limpiar()
                }else{
                    inicializar()
                    limpiar()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                inicializar()
            }

        }
    }

    fun inicializar(){
        txtEfectivo.isEnabled = false
        txtTarjeta.isEnabled = false
    }
    fun limpiar(){
        txtTarjeta.setText("")
        txtEfectivo.setText("")
    }

    fun llenarTipoPago(){
        val lista = listOf("Seleccione","Tarjeta","Efectivo","Mixto")
        val adaptador = ArrayAdapter(this,android.R.layout.simple_spinner_item,lista)
        spnTipoPago.adapter = adaptador

    }


    fun llenarPedido(){

        var lista2 = ArrayList<String>()

        lista2.add("Seleccione")
        for (i in Singleton.listaPedidos) {
            lista2.add("Pedido:"+i.id+ " Cliente:"+i.cliente.nombre+" Total:"+i.total)
        }


        val adaptador2 = ArrayAdapter(this,android.R.layout.simple_spinner_item,lista2)
        spnPedidoFactura.adapter = adaptador2


    }

    fun llenarEmpledos(){

        var lista3 = ArrayList<String>()

        lista3.add("Seleccione")
        for (i in Singleton.listaEmpleados) {
            lista3.add("Id:"+i.ID+"   "+"Nombre:"+i.nombre)
        }

        val adaptador3 = ArrayAdapter(this,android.R.layout.simple_spinner_item,lista3)
        spnEmpleadoFactura.adapter = adaptador3


    }


    fun generarFactura(){

        var total = spnPedidoFactura.selectedItem.toString().split(":")


        if (spnPedidoFactura.selectedItem.toString().equals("Seleccione")){
            //debe de seleccionar un pedido
            Toast.makeText(this, "Debe de seleccionar un pedido", Toast.LENGTH_SHORT).show()

        }else if (spnTipoPago.selectedItem.toString().equals("Seleccione")){
            //debe de seleccionar un metodo de pago
            Toast.makeText(this, "Debe de seleccionar un metodo de pago", Toast.LENGTH_SHORT).show()

        }else if(spnTipoPago.selectedItem.toString().equals("Efectivo") && txtEfectivo.text.toString().trim().isEmpty()){
            //el efectivo debe de ser mayor al total a pagar
            Toast.makeText(this, "El efectivo no dede de estar vacío", Toast.LENGTH_SHORT).show()

        }else if(spnTipoPago.selectedItem.toString().equals("Efectivo") && txtEfectivo.text.toString().toDouble() < total[3].toDouble()){
            //el efectivo debe de ser mayor al total a pagar
            Toast.makeText(this, "El efectivo debe de ser mayor o igual al total a pagar", Toast.LENGTH_SHORT).show()

        }else if (spnTipoPago.selectedItem.toString().equals("Tarjeta") && (txtTarjeta.text.toString().length < 15 || txtTarjeta.text.toString().length > 16)){
            //el numero de tarjeta debe de contener de 15 a 16 digitos
            Toast.makeText(this, "El numero de tarjeta debe de contener de 15 a 16 digitos", Toast.LENGTH_SHORT).show()

        }else if(spnTipoPago.selectedItem.toString().equals("Mixto")&& (txtTarjeta.text.toString().length < 15 || txtTarjeta.text.toString().length > 16)){
            //el numero de tarjeta debe de contener de 15 a 16 digitos
            Toast.makeText(this, "El numero de tarjeta debe de contener de 15 a 16 digitos", Toast.LENGTH_SHORT).show()

        }else if(spnTipoPago.selectedItem.toString().equals("Mixto")&& txtEfectivo.text.toString().trim().isEmpty()){
            //el efectivo no puede cubrir el total a pagar
            Toast.makeText(this, "En pago mixto el efectivo no puede estar vacío", Toast.LENGTH_SHORT).show()

        }else if(spnTipoPago.selectedItem.toString().equals("Mixto")&& txtEfectivo.text.toString().toDouble() >= total[3].toDouble()){
            //el efectivo no puede cubrir el total a pagar
            Toast.makeText(this, "En pago mixto el efectivo no puede cubrir el total a pagar", Toast.LENGTH_SHORT).show()

        }else if(spnTipoPago.selectedItem.toString().equals("Mixto")&& txtEfectivo.text.toString().toDouble() <= 0){
            //el efectivo no puede cubrir el total a pagar
            Toast.makeText(this, "En pago mixto el efectivo debe de ser mayor a cero", Toast.LENGTH_SHORT).show()

        }else if (spnEmpleadoFactura.selectedItem.toString().equals("Seleccione")){
            //debe de seleccionar un empleado
            Toast.makeText(this, "Debe de seleccionar un empleado", Toast.LENGTH_SHORT).show()

        }else{
            //Generar Factura
            guardar()
        }
    }

    private fun guardar() {
        var NumPedido = spnPedidoFactura.selectedItem.toString().split(" ")
        var id = NumPedido[0].split(":")
        var total = spnPedidoFactura.selectedItem.toString().split(":")
        var empleado = spnEmpleadoFactura.selectedItem.toString().split(":")
        var cliente = ""

        for (i in Singleton.listaPedidos) {
            if (i.id == id[1].toInt()) {
                cliente = i.cliente.nombre
            }
        }
            val guardarFact = Factura(
                id[1],
                cliente,
                spnTipoPago.selectedItem.toString(),
                txtTarjeta.text.toString(),
                txtEfectivo.text.toString(),
                empleado[2],
                total[3]
            )

            Singleton.listaFactura.add(guardarFact)
            eliminar()
            Toast.makeText(this, "Se guardó correctamente", Toast.LENGTH_SHORT).show()


    }


    fun eliminar(){
        MandarCorreo()
        println("-----------------------------------------------------------------------------")
        println("-----------------------------------------------------------------------------")
        var NumPedido = spnPedidoFactura.selectedItem.toString().split(" ")
        var id = NumPedido[0].split(":")

        for (i in Singleton.listaPedidos) {
            if (i.id == id[1].toInt()) {
                print(" "+i.id+" = "+id[1].toInt())
                Singleton.listaPedidos.remove(i)
            }
        }
    }

    private fun MandarCorreo() {

        val TO = arrayOf(getcorreo()) //Direcciones email  a enviar.

        val emailIntent = Intent(Intent.ACTION_SEND)

        emailIntent.data = Uri.parse("mailto:")
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Factura")
        emailIntent.putExtra(Intent.EXTRA_TEXT, FacturaCliente()) // * configurar email aquí!


        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email."))
            Log.i("EMAIL", "Enviando email...")
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                this,
                "NO existe ningún cliente de email instalado!.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun FacturaCliente():String{

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        var NumPedido = spnPedidoFactura.selectedItem.toString().split(" ")
        var id = NumPedido[0].split(":")

        var correo:String =
                        "              RESTAURANTE DON PEPE \n" +
                        "--------------------------Factura--------------------------\n"+
                        "Fecha y hora:"+currentDate+
                        "\nEmpleado:" +Singleton.listaFactura.get(Singleton.listaFactura.size-1).empleado+
                        "\nCliente:" +Singleton.listaFactura.get(Singleton.listaFactura.size-1).cliente+
                        "\nTipo de Pago:" +Singleton.listaFactura.get(Singleton.listaFactura.size-1).tipoPago+
                        "\n\n                      DESCRIPCIÓN\n" +
                        "------------------------------------------------------------"
        var contador = 0
        for (i in Singleton.listaPedidos){
            if(i.id == id[1].toInt()){
                for (e in i.menusPedido) {
                    correo += "\nId:" + e.id + " Descripción:" + e.nombre + " Cantidad:" + i.cantidad.get(contador) + " Precio:" + e.precio
                    contador++
                }
            }
        }

        correo += "\n------------------------------------------------------------\n" +
                "Total a Pagar: L "+Singleton.listaFactura.get(Singleton.listaFactura.size-1).total+
                "\n--------------------------------------------------------------\n" +
                "                 ¡Gracias por su visita!"
        return correo

    }
    fun getcorreo():String{

        var total = spnPedidoFactura.selectedItem.toString().split(" ")
        var id = total[0].split(":")

        var correo:String = "Davidandrew.de@gmail.com"

        for (i in Singleton.listaPedidos){
            if(i.id == id[1].toInt()){
                correo = i.cliente.correo
            }
        }
        return correo
    }

}