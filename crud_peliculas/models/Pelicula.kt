package mx.edu.utng.crud_peliculas.models

data class Pelicula(
    var id: Int = 0,
    var titulo: String,
    var director: String,
    var anio: Int,
    var genero: String,
    var duracion: Int,
    var rating: Float,
    var imagenUrl: String
)
