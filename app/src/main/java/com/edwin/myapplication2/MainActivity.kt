package com.edwin.myapplication2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edwin.myapplication2.ui.theme.MyApplication2Theme
import com.edwin.myapplication2.ExpandableCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplication2Theme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(24.dp)
                ) {
                    ExpandableCard(
                        title = "HEHE",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam" +
                                "ultricies augue nec convallis iaculis. Integer et facilisis justo," +
                                "et iaculis sapien. Pellentesque ac semper ante, nec elementum mi." +
                                "Duis imperdiet massa eu ligula ultrices, sed sagittis lacus luctus." +
                                "Pellentesque scelerisque nulla sed nunc egestas consectetur."
                    )
                }
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
        },
        modifier = Modifier.width(200.dp)
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
){
    Text(buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = normalFontSize
            )
        ){
            append(normalText)
        }
        withStyle(
            style = SpanStyle(
                fontSize = superTextFontSize,
                fontWeight = superTextFontWeight,
                baselineShift = BaselineShift.Superscript
            )
        ){
            append(superText)
        }
    })
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
            ExpandableCard(
                title = "HEHE",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam" +
                        "ultricies augue nec convallis iaculis. Integer et facilisis justo," +
                        "et iaculis sapien. Pellentesque ac semper ante, nec elementum mi." +
                        "Duis imperdiet massa eu ligula ultrices, sed sagittis lacus luctus." +
                        "Pellentesque scelerisque nulla sed nunc egestas consectetur."
            )
        }
    }
}