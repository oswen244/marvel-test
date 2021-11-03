package com.marvel.android.ui.character.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marvel.android.base.GlideApp
import com.marvel.android.base.ImageRepresentation
import com.marvel.android.data.comics.model.ComicEntity
import com.marvel.android.databinding.ItemComicBinding

class ComicsAdapter(private var comicList: List<ComicEntity>):
    RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>() {

    inner class ComicsViewHolder(binding: ItemComicBinding): RecyclerView.ViewHolder(binding.root){
        private val comicName: TextView = binding.textViewComicName
        private val comicImage: ImageView = binding.imageViewComic
        fun bind(comic: ComicEntity){
            comicName.text = comic.title
            GlideApp.with(itemView.context)
                .load("${comic.thumbnail.path}/${ImageRepresentation.PORTRAIT_MEDIUM.value}.${comic.thumbnail.extension}")
                .into(comicImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val binding = ItemComicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComicsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        holder.bind(comicList[position])
    }

    override fun getItemCount(): Int {
        return comicList.size
    }

    fun updateList(comics: List<ComicEntity>){
        this.comicList = comics
        notifyDataSetChanged()
    }
}