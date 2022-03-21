package hn.edu.ujcv.pdm_2022_i_p2_equipo4

class DatosPedidos {
    var id = 0
    var cliente = DatosClientes()
    var menusPedido = arrayOf<DatosMenu>()
    var cantidad = arrayListOf<Int>()
    var mesero = DatosEmpleados()
    var total = 0.0f

    constructor(){}
    constructor(
        cliente: DatosClientes,
        menusPedido: Array<DatosMenu>,
        cantidad: ArrayList<Int>,
        mesero: DatosEmpleados,
        total: Float
    ) {
        this.id = Singleton.listaPedidos.size + 1
        this.cliente = cliente
        this.menusPedido = menusPedido
        this.cantidad = cantidad
        this.mesero = mesero
        this.total = total
    }


    override fun toString(): String {
        return "Cliente: ${cliente.nombre}  Mesero: ${mesero.nombre} Total: $total"
    }


}