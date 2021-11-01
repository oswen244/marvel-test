package com.marvel.android.ui.characterlist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.marvel.android.databinding.ActivityMainBinding
import com.marvel.android.ui.characterdetail.CharacterDetailActivity
import com.marvel.android.ui.characterlist.adapter.CharacterListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CharacterListAdapter
    private val characterListViewModel: CharacterListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupObservers()
        characterListViewModel.getCharactersList()
    }

    private fun setupView() {
        adapter = CharacterListAdapter(characterListViewModel.characterList.value!!){
            val intent = CharacterDetailActivity.newInstance(this, it)
            startActivity(intent)
        }
        binding.characterRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.characterRecyclerview.adapter = adapter
    }

    private fun setupObservers() {
        characterListViewModel.characterList.observe(this, {
            if(it.isNotEmpty()){
                adapter.updateList(it)
                binding.characterRecyclerview.visibility = View.VISIBLE
            }
        })

        characterListViewModel.progressLoading.observe(this, {
            if(it){
                binding.characterProgress.visibility = View.VISIBLE
            }else{
                binding.characterProgress.visibility = View.GONE
            }
        })

        characterListViewModel.viewEmptyList.observe(this, {
            if(it){
                binding.partialCharacterEmptyList.root.visibility = View.VISIBLE
            }else{
                binding.partialCharacterEmptyList.root.visibility = View.GONE
            }
        })
    }
}