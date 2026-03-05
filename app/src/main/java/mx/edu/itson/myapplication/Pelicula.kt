package mx.edu.itson.myapplication

data class Pelicula(var id: Int,
                    var titulo:String,
                    var image:Int,
                    var header:Int,
                    var sinopsis:String,
                    var seats: ArrayList<Cliente>)