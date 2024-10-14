package br.com.mizaeldouglas.appcat

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import br.com.mizaeldouglas.appcat.adpter.ImageAdapter
import br.com.mizaeldouglas.appcat.databinding.ActivityMainBinding
import br.com.mizaeldouglas.appcat.model.ImgurResponse
import br.com.mizaeldouglas.appcat.service.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val api by lazy {
        RetrofitInstance.api
    }

    private var jobImage: Job? = null
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViews()
    }




    private fun initViews() {
        imageAdapter = ImageAdapter(){
            images ->
            Log.i("initViews", "initViews: ${images.link}")
        }
        binding.recyclerView.adapter = imageAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)

    }
    override fun onStart() {
        super.onStart()
        fetchCatImages()
    }

    private fun fetchCatImages() {

        jobImage = CoroutineScope(Dispatchers.IO).launch {
            var response: Response<ImgurResponse>? = null
            try {
                response = api.searchCats()
            } catch (e: Exception) {
                Log.i("apiError", "fetchCatImages: erro ao inicializar a api")
            }

            if (response != null && response.isSuccessful) {
                val images = response.body()?.data ?: emptyList()

                withContext(Dispatchers.Main){
                    imageAdapter.addList(images)
                }
            }


        }
    }

    override fun onStop() {
        super.onStop()
        jobImage?.cancel()
    }
}