package com.marvel.android

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.marvel.android.repository.FakeCharacterRepository
import com.marvel.android.repository.FakeEmptyCharacterRepository
import com.marvel.android.repository.FakeErrorCharacterRepository
import com.marvel.android.utils.getOrAwaitValue
import com.marvel.android.base.OperationResult
import com.marvel.android.data.character.model.CharacterEntity
import com.marvel.android.data.character.model.ThumbnailEntity
import com.marvel.android.data.character.repository.CharactersRepository
import com.marvel.android.data.comics.model.ComicURIEntity
import com.marvel.android.domain.GetCharacterComicsUseCase
import com.marvel.android.domain.GetCharactersUseCase
import com.marvel.android.ui.character.CharacterViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CharacterViewModelUnitTest {
    @Mock
    private lateinit var context: Application

    private lateinit var mockCharactersEmptyList: List<CharacterEntity>
    private lateinit var mockCharacterList: List<CharacterEntity>

    private lateinit var characterRepository: CharactersRepository

    private lateinit var characterViewModel: CharacterViewModel
    private lateinit var getCharacterListUseCase: GetCharactersUseCase
    lateinit var getCharacterComicsUseCase: GetCharacterComicsUseCase

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        `when`<Context>(context.applicationContext).thenReturn(context)
        Dispatchers.setMain(testDispatcher)

        mockData()
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `retrieve characters repository returns empty list`(){
        characterRepository = FakeEmptyCharacterRepository()
        getCharacterListUseCase = GetCharactersUseCase(characterRepository)
        getCharacterComicsUseCase = GetCharacterComicsUseCase(characterRepository)
        characterViewModel = CharacterViewModel(getCharacterListUseCase, getCharacterComicsUseCase)

        runBlockingTest {
            characterViewModel.getCharactersList(null, null, null, true)
            val result = characterViewModel.viewEmptyList.getOrAwaitValue()
            Assert.assertTrue(result)
        }

        runBlockingTest {
            val response = characterRepository.getCharacters(null, 0, 0 , 28391382, "76asd8as8", true)
            Assert.assertTrue(response is OperationResult.Success)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `retrieve characters repository return data list`(){
        characterRepository = FakeCharacterRepository()
        getCharacterListUseCase = GetCharactersUseCase(characterRepository)
        getCharacterComicsUseCase = GetCharacterComicsUseCase(characterRepository)
        characterViewModel = CharacterViewModel(getCharacterListUseCase, getCharacterComicsUseCase)

       runBlockingTest {
           characterViewModel.getCharactersList(null, null, null, true)
            val result = characterViewModel.characterList.getOrAwaitValue()
           Assert.assertTrue(result.size == mockCharacterList.size)
        }

        runBlockingTest {
            val response = characterRepository.getCharacters(null, 0, 0 , 28391382, "76asd8as8", true)
            Assert.assertTrue(response is OperationResult.Success)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `retrieve characters repository return error`(){
        characterRepository = FakeErrorCharacterRepository()
        getCharacterListUseCase = GetCharactersUseCase(characterRepository)
        getCharacterComicsUseCase = GetCharacterComicsUseCase(characterRepository)
        characterViewModel = CharacterViewModel(getCharacterListUseCase, getCharacterComicsUseCase)

        runBlockingTest {
            characterViewModel.getCharactersList(null, null, null, true)
            val result = characterViewModel.errorMessage.getOrAwaitValue()
            Assert.assertTrue(characterViewModel.errorMessage.value == "Error")
        }

        runBlockingTest {
            val response = characterRepository.getCharacters(null, 0, 0 , 28391382, "76asd8as8", true)
            Assert.assertTrue(response is OperationResult.Error)
        }
    }

    private fun mockData(){
        mockCharactersEmptyList = emptyList()
        val mockList:MutableList<CharacterEntity>  = mutableListOf()
        mockList.add(CharacterEntity(0, null, "desc", ThumbnailEntity("url", "ext"), ComicURIEntity(
            "uri", 1)))
        mockList.add(CharacterEntity(1, null, "desc", ThumbnailEntity("url", "ext"), ComicURIEntity(
            "uri", 1)))
        mockList.add(CharacterEntity(2, null, "desc", ThumbnailEntity("url", "ext"), ComicURIEntity(
            "uri", 1)))
        mockCharacterList = mockList.toList()
    }
}