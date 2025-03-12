package mx.edu.utng.crud_peliculas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var pelicula: Pelicula

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pelicula)

        dbHelper = DatabaseHelper(this)

        val idPelicula = intent.getIntExtra("ID_PELICULA", -1)
        pelicula = dbHelper.obtenerPeliculas().first { it.id == idPelicula }

        val tvTitulo: TextView = findViewById(R.id.tvTituloDetalle)
        val tvDirector: TextView = findViewById(R.id.tvDirectorDetalle)
        val ivPelicula: ImageView = findViewById(R.id.ivPeliculaDetalle)
        val btnEditar: Button = findViewById(R.id.btnEditar)
        val btnEliminar: Button = findViewById(R.id.btnEliminar)

        tvTitulo.text = pelicula.titulo
        tvDirector.text = pelicula.director
        Glide.with(this).load(pelicula.imagenUrl).into(ivPelicula)

        btnEditar.setOnClickListener {
            val intent = Intent(this, AgregarEditarPeliculaActivity::class.java)
            intent.putExtra("ID_PELICULA", pelicula.id)
            startActivity(intent)
        }

        btnEliminar.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Eliminar Película")
                .setMessage("¿Estás seguro de que deseas eliminar esta película?")
                .setPositiveButton("Sí") { _, _ ->
                    dbHelper.eliminarPelicula(pelicula.id)
                    finish()
                }
                .setNegativeButton("No", null)
                .show()
        }
    }
}
