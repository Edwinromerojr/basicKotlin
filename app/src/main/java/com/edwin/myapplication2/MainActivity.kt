package com.edwin.myapplication2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.edwin.myapplication2.ui.theme.MyApplication2Theme
import com.edwin.myapplication2.ExpandableCard
import com.edwin.myapplication2.GoogleButton
import com.edwin.myapplication2.repository.PersonRepository
import androidx.compose.foundation.ExperimentalFoundationApi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplication2Theme {
                Person()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    LazyRow(modifier = Modifier.fillMaxSize()) {
        items(count = 10) { i ->
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Text(text = stringResource(id = R.string.app_name))
        }
    }
}

@Composable
fun SampleText1() {
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("A")
            }
            append("B")
            append("C")
            append("D")
        }, modifier = Modifier.width(200.dp)
    )
}

@Composable
fun SampleText2() {
    SelectionContainer {
        Column {
            Text(text = "Hello World!")
            DisableSelection {
                Text(text = "Hello World1!")
            }
            Text(text = "Hello World2!")
        }
    }
}

@Composable
fun SuperScriptText(
    normalText: String,
    normalFontSize: TextUnit = MaterialTheme.typography.bodyMedium.fontSize,
    superText: String,
    superTextFontSize: TextUnit = MaterialTheme.typography.labelSmall.fontSize,
    superTextFontWeight: FontWeight = FontWeight.Normal
) {
    Text(buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = normalFontSize
            )
        ) {
            append(normalText)
        }
        withStyle(
            style = SpanStyle(
                fontSize = superTextFontSize,
                fontWeight = superTextFontWeight,
                baselineShift = BaselineShift.Superscript
            )
        ) {
            append(superText)
        }
    })
}

@Composable
fun TextFieldText() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var text by remember { mutableStateOf("Type here...") }
        OutlinedTextField(
            value = text, onValueChange = { newText ->
                text = newText
            },
            label = {
                Text(text = "Title")
            },
            leadingIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "Email Icon"
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    Log.d("Trailing Icon","Clicked")
                }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Email Icon"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    Log.d("ImeAction", "Clicked2")
                }
            )
        )
    }
}

@Composable
fun CoilImage(){
    Box(
        modifier = Modifier
            .height(150.dp)
            .width(150.dp)
            .background(color = Color.Gray),
        contentAlignment = Alignment.Center,
    ){
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://avatars.githubusercontent.com/u/14994036?v=4")
                .placeholder(R.drawable.ic_google_logo)
                .error(R.drawable.ic_launcher_background)
                .crossfade(true)
                .transformations(
                    CircleCropTransformation()
                )
                .build()
        )
        val painterState = painter.state
        Image(
            painter = painter,
            contentDescription = "Logo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        if(painterState is AsyncImagePainter.State.Loading){
            CircularProgressIndicator()
        }
    }
}

@Composable
fun PasswordText(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var password by rememberSaveable { mutableStateOf("") }
        var passwordVisibility by remember { mutableStateOf(false) }
        val icon = if(passwordVisibility)
            painterResource(id = R.drawable.ic_launcher_foreground)
        else
            painterResource(id = R.drawable.ic_google_logo)

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            placeholder = { Text(text = "Password")},
            label = { Text(text = "Password")},
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(
                        painter = icon,
                        contentDescription = "Visibility Icon"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType =KeyboardType.Password
            ),
            visualTransformation = if(passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation()
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Person(){
//    val personRepository = PersonRepository()
//    val getAllData = personRepository.getAllData()
//
//    LazyColumn(
//        contentPadding = PaddingValues(12.dp),
//        verticalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//        itemsIndexed(
//            items = getAllData,
//            key = { index, person ->
//                person.id
//            }
//        ){ index, person ->
//            Log.d("MainActivity1", index.toString())
//            CustomItem(person = person)
//        }
//    }

    val sections = listOf("A", "B", "C", "D", "E", "F", "G")

    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
        sections.forEach { section ->
            stickyHeader {
                Text(
                    text = "Section $section",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(12.dp)
                )
            }
            items(10){
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = "Item $it from the section $section"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplication2Theme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Person()
        }
    }
}