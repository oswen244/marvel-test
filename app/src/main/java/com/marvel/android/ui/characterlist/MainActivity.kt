package com.marvel.android.ui.characterlist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marvel.android.base.EndlessScrollViewListener
import com.marvel.android.databinding.ActivityMainBinding
import com.marvel.android.ui.characterdetail.CharacterDetailActivity
import com.marvel.android.ui.characterlist.adapter.CharacterListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CharacterListAdapter
    private val characterListViewModel: CharacterListViewModel by viewModel()
    private lateinit var endlessScrollListener: EndlessScrollViewListener
    private var limit = 40
    private var offset = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupObservers()
        characterListViewModel.getCharactersList(limit, offset)
    }

    private fun setupView() {
        adapter = CharacterListAdapter(characterListViewModel.characterList.value!!){
            val intent = CharacterDetailActivity.newInstance(this, it)
            startActivity(intent)
        }
        val layoutManager = LinearLayoutManager(this@MainActivity)
        endlessScrollListener = object : EndlessScrollViewListener(layoutManager) {
            override fun onLoadMore(totalItemsCount: Int, view: RecyclerView?) {
                if(totalItemsCount < characterListViewModel.totalCharacters.value!!){
                    offset += limit
                    characterListViewModel.getCharactersList(limit, offset)
                }
            }
        }
        binding.characterRecyclerview.apply {
            this.layoutManager = layoutManager
            itemAnimator = DefaultItemAnimator()
            addOnScrollListener(endlessScrollListener)
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupObservers() {
        characterListViewModel.characterList.observe(this, {
            if(offset == 0){
                adapter.updateList(it)
            }else{
                adapter.addToList(it)
            }
        })

        characterListViewModel.progressLoading.observe(this, {
            binding.characterProgress.visibility = if(it) View.VISIBLE else View.GONE
        })

        characterListViewModel.viewEmptyList.observe(this, {
            binding.characterRecyclerview.visibility = if (it) View.GONE else View.VISIBLE
            binding.partialCharacterEmptyList.root.visibility = if(it) View.VISIBLE else View.GONE
        })
    }
}