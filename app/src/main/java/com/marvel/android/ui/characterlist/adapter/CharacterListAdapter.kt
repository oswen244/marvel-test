package com.marvel.android.ui.characterlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.marvel.android.base.GlideApp
import com.marvel.android.data.character.model.CharacterEntity
import com.marvel.android.databinding.ItemCharacterBinding

class CharacterListAdapter(
    private var list: List<CharacterEntity>,
    private val listener: (CharacterEntity) -> Unit):
        RecyclerView.Adapter<CharacterListAdapter.CharacterListViewHolder>() {

    class CharacterListViewHolder(binding: ItemCharacterBinding): RecyclerView.ViewHolder(binding.root) {
        private val characterImage: ImageView = binding.characterImageView
        private val characterName: TextView = binding.characterNameTextView

        fun bind(character: CharacterEntity, listener: (CharacterEntity) -> Unit) = with(itemView){
            GlideApp.with(itemView.context)
                    .load(character.image)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(characterImage)

            characterName.text = character.name

            setOnClickListener {
                listener(character)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    override fun getItemCount(): Int = list.size

    fun updateList(newList: List<CharacterEntity>){
        this.list = newList
        notifyDataSetChanged()
    }
}