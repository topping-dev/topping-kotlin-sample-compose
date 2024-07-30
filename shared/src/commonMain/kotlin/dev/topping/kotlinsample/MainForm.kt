package dev.topping.kotlinsample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.navigation.PlatformNavHost
import androidx.compose.ui.navigation.PlatformNavHostController
import androidx.compose.ui.navigation.composable
import androidx.compose.ui.navigation.rememberNavController
import androidx.compose.ui.platform.PlatformComposeView
import androidx.compose.ui.platform.PlatformContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.platform.viewModel
import dev.topping.kotlin.ILuaForm
import dev.topping.kotlin.LR

@OptIn(ExperimentalMaterial3Api::class)
class MainForm(form: Any) : ILuaForm(form) {

    @Composable
    fun AScreen(navController: PlatformNavHostController) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate("BScreen") {

                    }
                }
            ) {
                Text(stringResource(LR.string.ascreen.compose))
            }
        }
    }

    @Composable
    fun BScreen(navController: PlatformNavHostController) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Row {
                val range = 1..100

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(range.count()) { index ->
                        Button(onClick = {  }) {
                            Text("Filled ${index + 1}")
                        }
                    }
                }
            }
            Button(
                onClick = {
                    navController.navigateUp()
                }
            ) {
                Text(stringResource(LR.string.bscreen.compose))
            }
        }
    }

    @Composable
    fun App(mainViewModel: MainViewModel = viewModel(key = "MainViewModel") {
        MainViewModel()
    }) {
        val navController = rememberNavController()
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(text = stringResource(LR.string.receive.compose))
                    }
                )
            },
        ) { innerPadding ->
            PlatformNavHost(
                modifier = Modifier.background(Color.Red)
                    .padding(innerPadding),
                navController = navController,
                startDestination = "AScreen"
            ) {
                composable("AScreen") {
                    AScreen(navController)
                }
                composable("BScreen") {
                    BScreen(navController)
                }
            }
        }
    }

    override fun onCreate() {
        val cc = PlatformContext(getForm().getContext()!!.getNativeContext(), null)
        PlatformComposeView(cc).addThis(getForm().getNativeForm()) {
            App()
        }
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onDestroy() {
    }
}
