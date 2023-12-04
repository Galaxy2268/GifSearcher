package com.task.testchili


import android.os.Bundle
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.task.testchili.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.giphy.com/v1/"

class MainActivity : AppCompatActivity() {
    //Initializing variables for methods
    private lateinit var adapter: GifsAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var textChangeJob: Job
    private var offset = 0
    private var input = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Calling all necessary methods and creating retrofit instance and giphyReceiver
        initUI()

        val retrofit = createRetrofit()

        val giphyReceiver = retrofit.create(GiphyReceiver::class.java)

        setupSearchView(giphyReceiver)

        setupRecyclerView(giphyReceiver)
    }





    private fun initUI() {
        //setting binding, adapter instance, setting recyclerView to linear style
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = GifsAdapter(this, mutableListOf())
        binding.recView.layoutManager = LinearLayoutManager(this)
        binding.recView.adapter = adapter
    }



    private fun createRetrofit(): Retrofit {
        //creating retrofit instance
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



    private fun setupSearchView(giphyReceiver: GiphyReceiver) {
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            //Method is called when user change searchView field
            override fun onQueryTextChange(newText: String?): Boolean {
                //Method loads gifs depending on user input
                handleTextChange(newText, giphyReceiver)
                return true
            }
        })
    }



    private fun setupRecyclerView(giphyReceiver: GiphyReceiver) {
        binding.recView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            //onScrolled method is called whe user ends scrolling recyclerView
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                //Then we check if we need to load more gifs and load if so
                handleRecyclerView(giphyReceiver)
            }
        })
    }



    private fun handleTextChange(newText: String?, giphyReceiver: GiphyReceiver) {
        //Check if Coroutine is still working and cancel it if so
        if (::textChangeJob.isInitialized)
            textChangeJob.cancel()
        //Launching coroutine because we need to implement delay and api requests should be called in IO thread
        textChangeJob = CoroutineScope(Dispatchers.IO).launch {
            //Making delay to avoid necessary loading of gifs while user typing
            delay(750)
            newText?.let { input = it }

            val list = newText?.let { giphyReceiver.searchGifs(it, 0) }
            //Getting gifs by giphyReceiver
            withContext(Dispatchers.Main) {
                //using withContext because all work with ui must be done in main thread
                binding.apply {
                    if (list != null) {
                        //updating list of gifs
                        adapter.update(list.res)
                    }
                }
            }
        }
    }



    private fun handleRecyclerView(giphyReceiver: GiphyReceiver) {
        val layoutManager = binding.recView.layoutManager as LinearLayoutManager
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        val totalItemCount = layoutManager.itemCount
        //checking if user is in the end of recyclerView
        if (lastVisibleItemPosition == totalItemCount - 1) {
            //If so add to the offset 50, because by default giphy returns me 50 gifs
            offset += 50

            CoroutineScope(Dispatchers.IO).launch {
                val list = input.let { giphyReceiver.searchGifs(it, offset) }

                withContext(Dispatchers.Main) {
                    binding.apply {
                        //add new gifs to the list

                        adapter.add(list.res)
                    }
                }
            }
        }
    }
}

