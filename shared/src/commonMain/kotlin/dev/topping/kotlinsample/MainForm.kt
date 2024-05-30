package dev.topping.kotlinsample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
                Text("A Screen")
            }
        }
    }

    @Composable
    fun BScreen(navController: PlatformNavHostController) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Button(
                onClick = {
                    navController.navigateUp()
                }
            ) {
                Text("B Screen")
            }
        }
    }

    @Composable
    fun App(mainViewModel: MainViewModel = viewModel(key = "MainViewModel") {
        MainViewModel()
    }) {
        //Text(modifier = Modifier.wrapContentSize(), text = "asd")
        /*Column {
            Row(modifier = Modifier.size(60.dp).background(Color.Red)) {
                Text(modifier = Modifier.wrapContentSize(), text = "asd")
            }
        }*/
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
                modifier = Modifier.background(Color.Red),
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
            /*val range = 1..100

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(range.count()) { index ->
                    Button(onClick = {  }) {
                        Text("Filled ${index + 1}")
                    }
                    //Text(text = "- List item number ${index + 1}")
                }
            }*/
        }
    }

    override fun onCreate() {
        val cc = PlatformContext(getForm().getContext()!!.getNativeContext(), null)
        PlatformComposeView(cc).addThis(getForm().getNativeForm()) {
            App()
        }

        /*val navController = getForm().getFragmentManager()?.findFragmentById(LR.id.nav_host_fragment)
            ?.getNavController()
        val toolbar: LGToolbar? = getForm().getViewById(LR.id.ToolbarTest)
        LuaNavigationUI.setupWithNavController(toolbar!!, navController!!)*/
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onDestroy() {
    }
}
