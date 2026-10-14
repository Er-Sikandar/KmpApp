package `in`.kmp.demoapp.notifications.psn

import androidx.lifecycle.ViewModel
import `in`.kmp.demoapp.notifications.data.NotyModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotyViewModel : ViewModel(){
    private val _notyList = MutableStateFlow(emptyList<NotyModel>())
    val notyList = _notyList.asStateFlow()

    init {
        loadNoty()
    }

    fun loadNoty() {
        _notyList.value = listOf(
            NotyModel("1", "Buy groceries", "Milk, eggs, bread"),
            NotyModel("2", "Team meeting", "Sprint planning at 11 AM"),
            NotyModel("3", "Gym", "Leg day"),
            NotyModel("4", "Coding", "Must be follow Solid Principles"),
            NotyModel("5", "Read", "How to talk to anyone"),
            NotyModel("6", "Read", "Finish Clean Architecture chapter 5"),
            NotyModel("7", "Read", "Finish Clean Architecture chapter 5"),
            NotyModel("8", "Read", "Finish Clean Architecture chapter 5"),
            NotyModel("9", "Read", "Finish Clean Architecture chapter 5"),
            NotyModel("10", "Read", "Finish Clean Architecture chapter 5"),
            NotyModel("11", "Read", "Finish Clean Architecture chapter 5"),
            NotyModel("12", "Read", "Finish Clean Architecture chapter 5"),
            NotyModel("13", "Read", "Finish Clean Architecture chapter 5"),
            NotyModel("14", "Read", "Finish Clean Architecture chapter 5"),
            NotyModel("15", "Read", "Finish Clean Architecture chapter 5"),
        )
    }
}