package com.caiosilva.recycler2tdspk

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.caiosilva.recycler2tdspk.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        val description = intent.getStringExtra("EXTRA_DESCRIPTION").orEmpty()
        val url = intent.getStringExtra("EXTRA_URL").orEmpty()

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        val intentSend = Intent(Intent.ACTION_SEND)
        intentSend.setType("text/plain")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Descrição do herói")
        intent.putExtra(Intent.EXTRA_TEXT, description)

        binding.textViewDescription.text = description

        binding.textViewDescription.setOnClickListener {
            startActivity(Intent.createChooser(intentSend, "Enviar"))
        }

        binding.textViewDescription.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                startActivity(intent)

                return true
            }
        })
    }
}