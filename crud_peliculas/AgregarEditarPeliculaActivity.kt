package mx.edu.utng.crud_peliculas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AgregarEditarPeliculaActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private var idPelicula: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_editar_pelicula)

        dbHelper = DatabaseHelper(this)

        val etTitulo: EditText = findViewById(R.id.etTitulo)
        val etDirector: EditText = findViewById(R.id.etDirector)
        val etAnio: EditText = findViewById(R.id.etAnio)
        val etGenero: EditText = findViewById(R.id.etGenero)
        val etDuracion: EditText = findViewById(R.id.etDuracion)
        val etSinopsis: EditText = findViewById(R.id.etSinopsis)
        val etImagenUrl: EditText = findViewById(R.id.etImagenUrl)
        val btnGuardar: Button = findViewById(R.id.btnGuardar)

        idPelicula = intent.getIntExtra("ID_PELICULA", -1)
        if (idPelicula != -1) {
            val pelicula = dbHelper.obtenerPeliculas().first { it.id == idPelicula }
            etTitulo.setText(pelicula.titulo)
            etDirector.setText(pelicula.director)
            etAnio.setText(pelicula.anio.toString())
            etGenero.setText(pelicula.genero)
            etDuracion.setText(pelicula.duracion.toString())
            etSinopsis.setText(pelicula.sinopsis)
            etImagenUrl.setText(pelicula.imagenUrl)
        }

        btnGuardar.setOnClickListener {
            val pelicula = Pelicula(
                idPelicula ?: 0,
                etTitulo.text.toString(),
                etDirector.text.toString(),
                etAnio.text.toString().toInt(),
                etGenero.text.toString(),
                etDuracion.text.toString().toInt(),
                etSinopsis.text.toString(),
                etImagenUrl.text.toString()
            )
            if (idPelicula == -1) {
                dbHelper.insertarPelicula(pelicula)
            } else {
                dbHelper.actualizarPelicula(pelicula)
            }
            finish()
        }
    }
}
