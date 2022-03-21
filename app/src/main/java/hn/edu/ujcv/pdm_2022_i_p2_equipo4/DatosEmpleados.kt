package hn.edu.ujcv.pdm_2022_i_p2_equipo4

class DatosEmpleados {
    var ID : Int = 0
    var nombre : String = ""
    var puesto : String = ""


    constructor()
    constructor(ID: Int, nombre: String, puesto: String) {
        this.ID = ID
        this.nombre = nombre
        this.puesto = puesto
    }

    override fun toString(): String {
        return "$ID  $nombre"
    }
}