package hn.edu.ujcv.pdm_2022_i_p2_equipo4

class DatosClientes {

    var id: Int = 0
    var nombre: String = ""
    var correo: String = ""


    constructor()
    constructor(id: Int, nombre: String, correo: String) {
        this.id = id
        this.nombre = nombre
        this.correo = correo
    }

    override fun toString(): String {
        return "$id  $nombre"
    }
}