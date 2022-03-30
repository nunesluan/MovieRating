package com.movie.movierating

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.movie.movierating.adapter.MoviesAdapter
import com.movie.movierating.dao.AppDataBase
import com.movie.movierating.databinding.ActivityMainBinding
import com.movie.movierating.model.Movies

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var movies = arrayListOf<Movies>()
    private lateinit var adapter: MoviesAdapter
    private var database: AppDataBase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()

        database = AppDataBase.openDataBase(this@MainActivity)

        movies.addAll(database!!.movieDAO().fetch() as ArrayList<Movies>)


        //(database!!.movieDAO().fetch() as ArrayList<Movies>).forEach {
          //  movies.add(it)
            //Log.d("TAG", "Passou aqui ao entrar na tela")
        //}
        adapter.notifyDataSetChanged()

        binding.button.setOnClickListener {
            if(binding.editTextName.text.isNotEmpty() &&
                binding.editTextDuration.text.toString().isNotEmpty() &&
                binding.SpinnerCategory.selectedItemPosition != 0 &&
                binding.SpinnerEvaluation.selectedItemPosition != 0) {
                val movie = Movies(null, binding.editTextName.text.toString(),
                    binding.editTextDuration.text.toString(),
                    binding.SpinnerCategory.selectedItem.toString(),
                    binding.SpinnerEvaluation.selectedItem.toString())

                database!!.movieDAO().store(movie)
                movies.clear()
                movies.addAll(database!!.movieDAO().fetch() as ArrayList<Movies>)

                adapter.notifyItemInserted(movies.size-1)
                alert(this, R.string.alert_title, R.string.alert_text, R.string.alert_button)
                binding.editTextName.text.clear()
                binding.editTextDuration.text.clear()
                binding.SpinnerCategory.setSelection(0)
                binding.SpinnerEvaluation.setSelection(0)

                } else {
                    alert(this, R.string.alert_error_title, R.string.alert_error_text, R.string.alert_button)
            }

        }
    }



    override fun onPause() {
        super.onPause()
        AppDataBase.closeDataBase()
    }

    override fun onRestart() {
        super.onRestart()
        AppDataBase.openDataBase(this@MainActivity)
    }

    fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MoviesAdapter(movies, this@MainActivity) { movie: Movies ->
            resultItemClicked(
                movie
            )
        }

        binding.recyclerViewMovies.adapter = adapter
        binding.recyclerViewMovies.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    fun resultItemClicked(movie: Movies) {
        database!!.movieDAO().delete(movie)
        movies.clear()
        movies.addAll(database!!.movieDAO().fetch() as ArrayList<Movies>)
        adapter.notifyDataSetChanged()
        alert(this@MainActivity, R.string.alert_title2, R.string.alert_text2, R.string.alert_button)
    }
}