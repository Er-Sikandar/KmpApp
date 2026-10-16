package `in`.kmp.demoapp.home.psn

import androidx.lifecycle.ViewModel
import `in`.kmp.demoapp.home.domain.HomeRepository
import `in`.kmp.demoapp.networks.LocalStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(private val repo: HomeRepository,
                     private val tokens: LocalStorage) : ViewModel() {
    private val _isAccess = MutableStateFlow(false)
    val isAccess = _isAccess.asStateFlow()


}