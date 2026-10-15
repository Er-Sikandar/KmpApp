package `in`.kmp.demoapp.notifications.psn

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import `in`.kmp.demoapp.kmp.KmpTopBar


@Composable
fun NotyScreen(onBack: () -> Unit, viewModel: NotyViewModel = viewModel { NotyViewModel() }){
    val notyData by viewModel.notyList.collectAsState()
    val isLoad by viewModel.isLoading.collectAsState()

    Scaffold(topBar = {
        KmpTopBar(
            title = "Notifications",
            modifier= Modifier,
            onNavigationClick = {onBack()},
            actions=emptyList()
        )
    }) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp)) {
                if (isLoad){
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }else {
                    if (notyData.isNotEmpty()) {
                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                            items(notyData) { noty ->
                                Column(Modifier.padding(vertical = 5.dp)) {
                                    Text(
                                        noty.title ?: "N/A",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
                                        noty.description ?: "N/A",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    } else {
                        Text(
                            "No Data Found!",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            "<----------------->",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}