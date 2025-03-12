package mx.edu.utng.crud_peliculas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.edu.utng.crud_peliculas.models.Pelicula
import mx.edu.utng.crud_peliculas.R


class PeliculaAdapter(private var peliculas: List<Pelicula>, private val onItemClick: (Pelicula) -> Unit) :
    RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>() {

    class PeliculaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitulo: TextView = view.findViewById(R.id.tvTitulo)
        val tvDirector: TextView = view.findViewById(R.id.tvDirector)
        val ivPelicula: ImageView = view.findViewById(R.id.ivPelicula)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pelicula, parent, false)
        return PeliculaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        val pelicula = peliculas[position]
        holder.tvTitulo.text = pelicula.titulo
        holder.tvDirector.text = pelicula.director
        Glide.with(holder.itemView.context).load(pelicula.imagenUrl).into(holder.ivPelicula)
        holder.itemView.setOnClickListener { onItemClick(pelicula) }
    }

    override fun getItemCount() = peliculas.size

    fun actualizarLista(nuevaLista: List<Pelicula>) {
        peliculas = nuevaLista
        notifyDataSetChanged()
    }
}
