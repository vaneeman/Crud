package mx.edu.utng.crud_peliculas

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import mx.edu.utng.crud_peliculas.models.Pelicula

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "peliculas.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_PELICULAS = "peliculas"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITULO = "titulo"
        private const val COLUMN_DIRECTOR = "director"
        private const val COLUMN_ANIO = "anio"
        private const val COLUMN_GENERO = "genero"
        private const val COLUMN_DURACION = "duracion"
        private const val COLUMN_RATING = "rating"
        private const val COLUMN_IMAGEN = "imagenUrl"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_PELICULAS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TITULO TEXT,
                $COLUMN_DIRECTOR TEXT,
                $COLUMN_ANIO INTEGER,
                $COLUMN_GENERO TEXT,
                $COLUMN_DURACION INTEGER,
                $COLUMN_RATING REAL,
                $COLUMN_IMAGEN TEXT
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PELICULAS")
        onCreate(db)
    }

    fun insertarPelicula(pelicula: Pelicula): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITULO, pelicula.titulo)
            put(COLUMN_DIRECTOR, pelicula.director)
            put(COLUMN_ANIO, pelicula.anio)
            put(COLUMN_GENERO, pelicula.genero)
            put(COLUMN_DURACION, pelicula.duracion)
            put(COLUMN_RATING, pelicula.rating)
            put(COLUMN_IMAGEN, pelicula.imagenUrl)
        }
        return db.insert(TABLE_PELICULAS, null, values)
    }

    fun obtenerPeliculas(): List<Pelicula> {
        val listaPeliculas = mutableListOf<Pelicula>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_PELICULAS", null)
        while (cursor.moveToNext()) {
            listaPeliculas.add(
                Pelicula(
                    id = cursor.getInt(0),
                    titulo = cursor.getString(1),
                    director = cursor.getString(2),
                    anio = cursor.getInt(3),
                    genero = cursor.getString(4),
                    duracion = cursor.getInt(5),
                    rating = cursor.getFloat(6),
                    imagenUrl = cursor.getString(7)
                )
            )
        }
        cursor.close()
        return listaPeliculas
    }

    fun eliminarPelicula(id: Int): Int {
        val db = writableDatabase
        return db.delete(TABLE_PELICULAS, "$COLUMN_ID=?", arrayOf(id.toString()))
    }
}
