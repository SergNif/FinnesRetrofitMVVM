package com.bignerdranch.android.finnesretrofitmvvm.domain.usecase

import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.User
import com.bignerdranch.android.finnesretrofitmvvm.domain.repository.UserRepository

class GetUserNameUseCase (private val userRepository: UserRepository){
    fun execute(): User {
        return userRepository.getName()

    }

    fun executeGetData(): List<String> {
        return userRepository.getStartEndData()

    }

}
