package com.caiosilva.recycler2tdspk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.caiosilva.recycler2tdspk.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = PersonagensAdapter()
        adapter.submitList(listaPersonagens())

        binding.recyclerViewPersonagens.adapter = adapter
        binding.recyclerViewPersonagens.layoutManager = LinearLayoutManager(this)
    }

    private fun listaPersonagens(): List<Personagem> {
        return listOf(
            Personagem(
                1,
                "Homem Aranha",
                "Menino mordido por aranha radioativa prefere lutar com marginais a pagar conta do hospistal",
                ""
            ),
            Personagem(
                2,
                "Homem de Ferro",
                "Gênio, bilionário, playboy, filântropo",
                ""
            ),
            Personagem(
                3,
                "Capitão América",
                "Soldado toma bomba para socar nazista mais forte",
                ""
            ),
            Personagem(
                4,
                "Thor",
                "Deus nórdico vai a terra para bater em mortais e tomar café",
                ""
            ),
            Personagem(
                5,
                "Homem Formiga",
                "Ex ladrão ajuda cientistas a roubar de empresa multibilionária",
                ""
            ),
            Personagem(
                6,
                "Hawakeye",
                "Ex soldado acha que faz sentido lançar flechas em literais deuses. E ele tá certo.",
                ""
            ),
            Personagem(
                7,
                "Viuva Negra",
                "Scarlett Johansonn de roupas apertadas",
                ""
            ),
        )
    }
}
