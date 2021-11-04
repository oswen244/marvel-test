package com.marvel.android.ui.character

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.marvel.android.base.GlideApp
import com.marvel.android.base.ImageRepresentation
import com.marvel.android.data.character.model.CharacterEntity
import com.marvel.android.databinding.ActivityCharacterDetailBinding
import com.marvel.android.ui.character.adapter.ComicsAdapter
import com.marvel.android.ui.character.utils.Utils
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailBinding
    private val characterViewModel: CharacterViewModel by viewModel()
    private lateinit var comicsAdapter: ComicsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupObservers()
    }

    private fun setupView() = with(binding) {
        characterViewModel.getCharacterComics(
            character.id,
            character.comics?.available?.let { Utils.getLimit(it) },
            0,
            Utils.isConnectedToInternet(this@CharacterDetailActivity))
        includeToolbar.apply {
            title.text = character.name
            imageViewBack.setOnClickListener {
                this@CharacterDetailActivity.finish()
            }
        }
        GlideApp.with(this@CharacterDetailActivity)
            .load("${character.thumbnail?.path}/${ImageRepresentation.LANDSCAPE_LARGE.value}.${character.thumbnail?.extension}")
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(characterImageView)
        textViewDescription.text = if(character.description.isNotEmpty()) character.description else "Unknown"

        comicsAdapter = ComicsAdapter(characterViewModel.characterComicsList.value!!)
        recyclerViewComics.apply {
            layoutManager = LinearLayoutManager(this@CharacterDetailActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = comicsAdapter
        }
    }

    private fun setupObservers() {
        characterViewModel.characterComicsList.observe(this, {
            comicsAdapter.updateList(it)
        })
        characterViewModel.progressLoading.observe(this, {
            binding.loading.visibility = if(it) View.VISIBLE else View.GONE
        })
        characterViewModel.errorMessage.observe(this, {
            binding.partialEmptyStatus.msgEmptyCharacterList.text = it
            binding.partialEmptyStatus.root.visibility = View.VISIBLE
            binding.constraintCharacterInfo.visibility = View.GONE
        })
    }

    companion object{
        lateinit var character: CharacterEntity
        fun newInstance(context: Context, character: CharacterEntity): Intent {
            Companion.character = character
            return Intent(context, CharacterDetailActivity::class.java)
        }
    }
}