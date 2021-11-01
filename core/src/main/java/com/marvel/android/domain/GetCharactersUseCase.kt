package com.marvel.android.domain

import com.marvel.android.base.OperationResult
import com.marvel.android.data.character.model.CharacterEntity
import com.marvel.android.data.character.repository.CharactersRepository

class GetCharactersUseCase(private val charactersRepository: CharactersRepository) {
    suspend fun execute(): OperationResult<List<CharacterEntity>> {
        return charactersRepository.getCharacters()
    }
}