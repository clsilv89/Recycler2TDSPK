package com.caiosilva.recycler2tdspk

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.caiosilva.recycler2tdspk.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private val retrofitClient = RetrofitClient()
        .getRetrofit("https://jsonplaceholder.typicode.com/")
        .create(APIServices::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPosts()
        getPhotos()
    }

    private fun getPosts() {
        val getPosts = retrofitClient.getPosts()

        getPosts.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                println(response.body())
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                println(t.message)
            }
        })
    }

    private fun getPhotos() {
        val getPhotos = retrofitClient.getPhotos()

        getPhotos.enqueue(object : Callback<List<Album>> {
            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                setupRecyclerView(response.body().orEmpty())
            }

            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                println(t.message)
            }
        })
    }

    private fun post(album: Album) {
        val postPost = PostPost(
            title = album.title,
            body = album.thumbnailUrl,
            userId = album.id,
        )
        val post = retrofitClient.postPost(postPost)

        post.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                Toast.makeText(
                    this@MainActivity, response.body()?.body, Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                println(t.message)
            }
        })
    }

    private fun setupRecyclerView(albums: List<Album>) {
        val adapter = PersonagensAdapter()
        adapter.submitList(albums)
        adapter.onClick = {
            post(it)
//            openActvity(it)
        }

        binding.recyclerViewPersonagens.adapter = adapter
        binding.recyclerViewPersonagens.layoutManager = LinearLayoutManager(this)
    }

    private fun openActvity(personagem: Personagem) {
        val intent = Intent(this, SecondActivity().javaClass)
        intent
            .putExtra("EXTRA_DESCRIPTION", personagem.description)
            .putExtra("EXTRA_URL", personagem.url)

        startActivity(intent)
    }

    private fun listaPersonagens(): List<Personagem> {
        return listOf(
            Personagem(
                1,
                "Homem Aranha",
                "Menino mordido por aranha radioativa prefere lutar com marginais a pagar conta do hospistal",
                "",
                "https://www.marvel.com/"
            ),
            Personagem(
                2,
                "Homem de Ferro",
                "Gênio, bilionário, playboy, filântropo",
                "",
                "https://www.marvel.com/"
            ),
            Personagem(
                3,
                "Capitão América",
                "Soldado toma bomba para socar nazista mais forte",
                "",
                "https://www.marvel.com/"
            ),
            Personagem(
                4,
                "Thor",
                "Deus nórdico vai a terra para bater em mortais e tomar café",
                "",
                "https://www.marvel.com/"
            ),
            Personagem(
                5,
                "Homem Formiga",
                "Ex ladrão ajuda cientistas a roubar de empresa multibilionária",
                "",
                "https://www.marvel.com/"
            ),
            Personagem(
                6,
                "Hawakeye",
                "Ex soldado acha que faz sentido lançar flechas em literais deuses. E ele tá certo.",
                "",
                "https://www.marvel.com/"
            ),
            Personagem(
                7,
                "Viuva Negra",
                "Scarlett Johansonn de roupas apertadas",
                "",
                "https://www.marvel.com/"
            ),
        )
    }
}
