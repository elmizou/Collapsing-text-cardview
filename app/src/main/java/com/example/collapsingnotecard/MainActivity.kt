package com.example.collapsingnotecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.collapsingnotecard.ui.theme.CollapsingNoteCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CollapsingNoteCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {

                        NoteCard(
                            "Sample text",
                            modifier = Modifier
                                .padding(innerPadding)
                        )
                    }


                }
            }
        }
    }

}

@Composable
fun NoteCard(
    text: String,
    modifier: Modifier = Modifier,
) {
    val (isExpanded, setIsExpanded) = rememberSaveable {

        mutableStateOf(true)
    }
    var textContent by remember { mutableStateOf(text) }

    Card(modifier = Modifier.padding(0.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            if (isExpanded) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    IconButton(
                        modifier = Modifier.size(50.dp),
                        onClick = {
                            setIsExpanded(false)
                        }) {
                        Icon(imageVector = Icons.Default.Close, "Click to close")
                    }
                }
                OutlinedTextField(
                    value = text,
                    onValueChange = {
                        textContent = it
                    })
                Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    IconButton(onClick = {
                        setIsExpanded(false)
                    }) {
                        Icon(imageVector = Icons.Default.Done, "Click to close")
                    }
                }
            } else {
                Text(text = text, modifier = Modifier.clickable(onClick = {
                    println("Clicked")
                    setIsExpanded(isExpanded.not())
                }))
            }


        }
    }


}

@Preview
@Composable
private fun NoteCardPreview() {
    NoteCard(text = "Sample Text")

}