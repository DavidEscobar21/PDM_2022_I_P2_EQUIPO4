package hn.edu.ujcv.pdm_2022_i_p2_equipo4

class Factura {

    var numPedido = ""
    var cliente = ""
    var tipoPago = ""
    var tarjeta = ""
    var efectivo = ""
    var empleado = ""
    var total = ""

    constructor()
    constructor(
        numPedido: String,
        cliente: String,
        tipoPago: String,
        tarjeta: String,
        efectivo: String,
        empleado: String,
        total: String
    ) {
        this.numPedido = numPedido
        this.cliente = cliente
        this.tipoPago = tipoPago
        this.tarjeta = tarjeta
        this.efectivo = efectivo
        this.empleado = empleado
        this.total = total
    }


}