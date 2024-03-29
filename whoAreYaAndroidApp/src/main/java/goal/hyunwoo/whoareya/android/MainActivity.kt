package goal.hyunwoo.whoareya.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import goal.hyunwoo.whoareya.Greeting
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    GreetingView(Greeting().greet())
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Column {
        Text(text = text)
        ClosthMap()
    }
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun ClosthMap(viewModel: MainViewModel = koinViewModel()) {
    val uiState = viewModel.uiState.collectAsState().value
    NaverMap(onMapLoaded = {
        viewModel.setMarker()
    }) {
        when (uiState) {
            is UiState.Error -> TODO()
            UiState.Loading -> {}
            is UiState.Success -> {
                uiState.data.forEach {
                    Marker(
                        MarkerState(
                            position = LatLng(it.second.toDouble(), it.first.toDouble())
                        )
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
