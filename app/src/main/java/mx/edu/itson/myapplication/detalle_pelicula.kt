package mx.edu.itson.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class detalle_pelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_pelicula)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val iv_pelicula_image: ImageView = findViewById<ImageView>(R.id.iv_pelicula_imagen)
        val tv_nombre_pelicula: TextView = findViewById<TextView>(R.id.tv_nombre_pelicula)
        val tv_pelicula_desc: TextView = findViewById<TextView>(R.id.tv_pelicula_desc)

        val buy_button: Button = findViewById<Button>(R.id.buyTickets)

        val bundle = intent.extras

        if (bundle != null) {
            iv_pelicula_image.setImageResource(bundle.getInt("header"))
            tv_nombre_pelicula.setText(bundle.getString("titulo"))
            tv_pelicula_desc.setText(bundle.getString("sinopsis"))
        }

        updateSeats()

        buy_button.setOnClickListener {
            val intent = Intent(this, SeatSelection::class.java)
            intent.putExtra("name", bundle?.getString("titulo"))
            intent.putExtra("id", bundle?.getInt("id"))
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        updateSeats()
    }

    private fun updateSeats() {
        val seats_avail: TextView = findViewById<TextView>(R.id.seatLeft)
        val id = intent.getIntExtra("id", -1)
        val movie = Catalogo.peliculas.find { it.id == id } ?: Catalogo.series.find { it.id == id }
        movie?.let {
            val count = 20 - it.seats.size
            seats_avail.text = "$count seats available"
        }
    }
}