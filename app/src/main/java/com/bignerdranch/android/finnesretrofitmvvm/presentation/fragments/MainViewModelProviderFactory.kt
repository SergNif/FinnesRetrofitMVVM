package com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.finnesretrofitmvvm.data.repository.MainRepository
import com.bignerdranch.android.finnesretrofitmvvm.data.repository.UserRepositoryImpl
import com.bignerdranch.android.finnesretrofitmvvm.data.storage.SharedPrefUserStorage
import com.bignerdranch.android.finnesretrofitmvvm.domain.usecase.GetUserNameUseCase
import com.bignerdranch.android.finnesretrofitmvvm.domain.usecase.SaveUserNameUseCase


class MainViewModelProviderFactory(
    context: Context,
    val app: Application,
    private val mainRepository: MainRepository
//    val getUserNameUseCase: GetUserNameUseCase,
//    val saveUserNameUseCase: SaveUserNameUseCase
////    val sharedPreferences: SharedPrefUserStorage
) :
    ViewModelProvider.Factory {
    private val userRepository by lazy(LazyThreadSafetyMode.NONE) {
        UserRepositoryImpl(userStorage = SharedPrefUserStorage(context = context))
    }
    private val getUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetUserNameUseCase(userRepository = userRepository)
    }
    private val saveUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) {
        SaveUserNameUseCase(userRepository = userRepository)
    }
//    private val mainRepositoryVal by lazy(LazyThreadSafetyMode.NONE) {
//        MainRepository(db = UserdataDatabase)    //(userRepository = userRepository)
//    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            app,
            mainRepository,
            getUserNameUseCase = getUserNameUseCase,
            saveUserNameUseCase = saveUserNameUseCase,
        ) as T
    }
}
