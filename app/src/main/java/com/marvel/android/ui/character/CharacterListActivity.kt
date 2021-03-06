package com.marvel.android.ui.character

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.marvel.android.base.Constants
import com.marvel.android.base.EndlessScrollViewListener
import com.marvel.android.base.Utils.Companion.hideKeyboard
import com.marvel.android.databinding.ActivityMainBinding
import com.marvel.android.ui.character.adapter.CharacterListAdapter
import com.marvel.android.ui.character.utils.Utils
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CharacterListAdapter
    private val characterViewModel: CharacterViewModel by viewModel()
    private lateinit var endlessScrollListener: EndlessScrollViewListener
    private var limit = Constants.MAX_LIMIT
    private var offset = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupObservers()
    }

    private fun setupView() {
        characterViewModel.getCharactersList(null, limit, offset, Utils.isConnectedToInternet(this))
        binding.includeToolbar.startSearch.setOnClickListener {
            searchToolState(true)
        }
        binding.includeSearch.btnCloseDelete.setOnClickListener {
            if(binding.includeSearch.editText.text.isNotEmpty()){
                binding.includeSearch.editText.text = null
                adapter.clearList()
                characterViewModel.getCharactersList(
                    null,
                    null, null,
                    Utils.isConnectedToInternet(this@CharacterListActivity))
                it.hideKeyboard()
            }else{
                searchToolState(false)
            }
        }
        binding.includeSearch.btnSearch.setOnClickListener {
            characterViewModel.getCharactersList(
                binding.includeSearch.editText.text.toString(),
                null, null,
                Utils.isConnectedToInternet(this@CharacterListActivity))
        }

        setupCharacterAdapter()
    }

    private fun setupObservers() {
        characterViewModel.characterList.observe(this, {
            if(offset == 0){
                adapter.updateList(it)
            }else{
                adapter.addToList(it)
            }
        })

        characterViewModel.progressLoading.observe(this, {
            binding.characterProgress.visibility = if(it) View.VISIBLE else View.GONE
        })

        characterViewModel.viewEmptyList.observe(this, {
            binding.characterRecyclerview.visibility = if (it) View.GONE else View.VISIBLE
            binding.partialCharacterEmptyList.root.visibility = if(it) View.VISIBLE else View.GONE
        })

        characterViewModel.errorMessage.observe(this, {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        })

        characterViewModel.searchCharacterList.observe(this, {
            adapter.updateList(it)
        })
    }

    private fun setupCharacterAdapter() {
        adapter = CharacterListAdapter(arrayListOf()){
            val intent = CharacterDetailActivity.newInstance(this, it)
            startActivity(intent)
        }
        val layoutManager = LinearLayoutManager(this@CharacterListActivity)
        endlessScrollListener = object : EndlessScrollViewListener(layoutManager) {
            override fun onLoadMore(totalItemsCount: Int, view: RecyclerView?) {
                if(totalItemsCount < characterViewModel.totalCharacters.value!!){
                    offset += limit
                    characterViewModel.getCharactersList(null, limit, offset, Utils.isConnectedToInternet(this@CharacterListActivity))
                }
            }
        }
        binding.characterRecyclerview.apply {
            this.layoutManager = layoutManager
            itemAnimator = DefaultItemAnimator()
            addOnScrollListener(endlessScrollListener)
            adapter = this@CharacterListActivity.adapter
        }
    }

    private fun searchToolState(visible: Boolean){
        if(visible){
            binding.includeToolbar.root.visibility = View.GONE
            binding.includeSearch.root.visibility = View.VISIBLE
        }else{
            binding.includeToolbar.root.visibility = View.VISIBLE
            binding.includeSearch.root.visibility = View.GONE
        }
    }
}