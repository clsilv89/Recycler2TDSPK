package com.caiosilva.recycler2tdspk

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.caiosilva.recycler2tdspk.databinding.ListItemBinding

class PersonagensAdapter :
    ListAdapter<Personagem, PersonagensAdapter.MyViewHolder>(AsyncCallback()) {

    inner class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(personagem: Personagem) {
            binding.textViewName.text = personagem.name
            binding.textViewDescription.text = personagem.description
            binding.textViewModified.text = personagem.modified

            binding.root.setOnClickListener {
                println(personagem)
            }
        }
    }

    class AsyncCallback : DiffUtil.ItemCallback<Personagem>() {
        override fun areItemsTheSame(oldItem: Personagem, newItem: Personagem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Personagem, newItem: Personagem): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = ListItemBinding.inflate(layoutInflater)
        val myViewHolder = MyViewHolder(binding)

        return myViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}