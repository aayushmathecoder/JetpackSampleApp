package com.sample.jetpacksampleapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sample.jetpacksampleapp.ui.theme.JetpackSampleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackSampleAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Transparent
                ) {
                    Column {
                        ShowToolbar()
                        ShowVoteSection()
                        ListComponent(list = getItemsList())
                    }
                }
            }
        }
    }
}

private const val argentinaImageUrl = "https://i.ibb.co/TPMpD3f/argentina.png"
private const val brazilImageUrl = "https://i.ibb.co/4m15rgZ/brazil.png"
private const val croatiaImageUrl = "https://i.ibb.co/N3LZyPq/croatia.png"
private const val englandImageUrl = "https://i.ibb.co/fG0T4cm/england.png"
private const val moroccoImageUrl = "https://i.ibb.co/LP9qm61/morocco.png"
private const val netherlandsImageUrl = "https://i.ibb.co/pR0S0tG/netherlands.png"
private const val portugalImageUrl = "https://i.ibb.co/txVDRmQ/portugal.png"
private const val franceImageUrl = "https://i.ibb.co/zPRgm4C/france.png"


private fun getItemsList(): List<FIFATeam> {
    return listOf(
        FIFATeam(title = "Argentina", subtitle = "South America", argentinaImageUrl),
        FIFATeam(title = "Brazil", subtitle = "South America", brazilImageUrl),
        FIFATeam(title = "Croatia", subtitle = "Europe", croatiaImageUrl),
        FIFATeam(title = "England", subtitle = "Europe", englandImageUrl),
        FIFATeam(title = "France", subtitle = "Europe", franceImageUrl),
        FIFATeam(title = "Morocco", subtitle = "Africa", moroccoImageUrl),
        FIFATeam(title = "Netherlands", subtitle = "Europe", netherlandsImageUrl),
        FIFATeam(title = "Portugal", subtitle = "Europe", portugalImageUrl)
    )
}

@Composable
fun ShowVoteSection() {
    Row(modifier = Modifier.padding(12.dp)) {
        ShowEditText()
        ShowButton()
    }
}

@Composable
fun ShowEditText() {
    var text by rememberSaveable { mutableStateOf("") }
    val lb = Color(0xff76a9ff)
    TextField(
        value = text,
        onValueChange = {
            text = it
        },
//        modifier = Modifier.background(color = lb), // you can colour of Edittext like this
        label = { Text("Who will take the cup?") }
    )
}

@Composable
fun ShowButton() {
    val context = GetActivity()
    Button(
        onClick = {
            Toast.makeText(context, "Thanks for voting", Toast.LENGTH_SHORT).show()
        },
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = "Vote")
    }
}

@Composable
fun ShowToolbar() {
    TopAppBar(
        title = {
            Text(text = "FIFA 2022 Top 8")
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu Btn")
            }
        },
        backgroundColor = Color.White,
        contentColor = Color.Black,
        elevation = 2.dp
    )
}


@Composable
fun LoadFIFATeam(
    title: String,
    subtitle: String,
    imageUrl: String
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .fillMaxWidth()
//            .fillMaxHeight()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        border = BorderStroke(1.dp, Color.Blue)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
//                .background(color = Color.Green)
        ) {
            AsyncImage(
                modifier = Modifier.size(width = 50.dp, height = 50.dp),
                model = imageUrl, contentDescription = "Image"
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
//                    .background(color = Color.Yellow)
            ) {
                Text(title, fontSize = 20.sp)
                Text(subtitle, fontSize = 14.sp)
            }
        }
    }
}

data class FIFATeam(
    val title: String,
    val subtitle: String,
    val imageUrl: String,
)

@Composable
fun ListComponent(list: List<FIFATeam>) {
    LazyColumn(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        items(
            items = list,
            itemContent = { it ->
                LoadFIFATeam(
                    title = it.title,
                    subtitle = it.subtitle,
                    imageUrl = it.imageUrl
                )

            }
        )
    }
}

@Composable
private fun GetActivity() = LocalContext.current as ComponentActivity

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackSampleAppTheme {
        Greeting("Android")
    }
}
