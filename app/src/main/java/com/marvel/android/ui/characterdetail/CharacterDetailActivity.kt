package com.marvel.android.ui.characterdetail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.marvel.android.base.GlideApp
import com.marvel.android.data.character.model.CharacterEntity
import com.marvel.android.databinding.ActivityCharacterDetailBinding

class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {

    }

    companion object{
        lateinit var character: CharacterEntity
        fun newInstance(context: Context, character: CharacterEntity): Intent {
            Companion.character = character
            return Intent(context, CharacterDetailActivity::class.java)
        }
    }
}