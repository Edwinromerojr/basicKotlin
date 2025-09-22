package com.edwin.myapplication2

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.alpha
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Shapes
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Composable
fun ExpandableCard(
    title: String,
    titleFontSize: TextUnit = MaterialTheme.typography.labelSmall.fontSize,
    titleFontWeight: FontWeight = FontWeight.Bold,
    description: String,
    descriptionFontSize: TextUnit = MaterialTheme.typography.titleLarge.fontSize,
    descriptionFontWeight: FontWeight = FontWeight.Normal,
    descriptionMaxLines: Int = 4,
    shape: CornerBasedShape = RoundedCornerShape(4.dp),
    padding: Dp = 12.dp
){
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = shape,
        onClick = {
            expandedState = !expandedState
        }
    ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(padding)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically){
                Text(
                    modifier = Modifier
                        .weight(6f),
                    text= title,
                    fontSize = titleFontSize,
                    fontWeight = titleFontWeight,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    modifier = Modifier
                        .alpha(0.75f)
                        .weight(1f)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }
            if(expandedState){
                Text(
                    text = description,
                    fontSize = descriptionFontSize,
                    fontWeight = descriptionFontWeight,
                    maxLines = descriptionMaxLines,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
@Preview
fun ExpandableCardPreview(){
    ExpandableCard(
        title = "HEHE",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam" +
            "ultricies augue nec convallis iaculis. Integer et facilisis justo," +
            "et iaculis sapien. Pellentesque ac semper ante, nec elementum mi." +
            "Duis imperdiet massa eu ligula ultrices, sed sagittis lacus luctus." +
            "Pellentesque scelerisque nulla sed nunc egestas consectetur."
    )
}