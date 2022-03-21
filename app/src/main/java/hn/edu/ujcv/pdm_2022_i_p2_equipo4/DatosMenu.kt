package hn.edu.ujcv.pdm_2022_i_p2_equipo4

class DatosMenu {

    var id: Int = 0
    var nombre: String = ""
    var precio: Double = 0.00
    var descripcion: String = ""


    constructor()
    constructor(id: Int, nombre: String, precio: Double, descripcion: String) {
        this.id = id
        this.nombre = nombre
        this.precio = precio
        this.descripcion = descripcion
    }

    override fun toString(): String {
        return "$id  $nombre"
    }
}