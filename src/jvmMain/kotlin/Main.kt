// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {

    var mWallLength by remember { mutableStateOf("") }
    var mWallHeight by remember { mutableStateOf("") }
    var mWallArea by remember { mutableStateOf("") }

    MaterialTheme {
        Column(modifier = Modifier
            .background(Color.Gray)
            .fillMaxSize()) {

            Text(modifier = Modifier
                .padding(16.dp),
                text = "Fliesenrechenr",
                textAlign = TextAlign.Center
            )
            Row (modifier = Modifier
                .height(IntrinsicSize.Min))
            {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(16.dp),
                    value = mWallLength,
                    onValueChange = { mWallLength = it },
                    label = { Text(text = "Wandlänge in m")}

                )
                OutlinedTextField(
                    modifier = Modifier
                        .padding(16.dp),
                    value = mWallHeight,
                    onValueChange = { mWallHeight = it },
                    label = { Text(text = "Wandhöhe in m")}
                )
                Button(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxHeight(),
                    onClick = {
                        if (mWallHeight.isNotEmpty() && mWallLength.isNotEmpty()) {
                            var mLengthOk = false
                            var mHeightOk = false
                            if (mWallLength.toDoubleOrNull() == null) {
                                mWallLength = "Eine Zahl, bitte"
                            } else mLengthOk = true
                            if (mWallHeight.toDoubleOrNull() == null) {
                                mWallHeight = "Eine Zahl, bitte"
                            } else mHeightOk = true
                            if (mLengthOk && mHeightOk) mWallArea = (mWallHeight.toDouble() * mWallLength.toDouble()).toString()
                        }

                    }
                ) { Text("berechnen") }
            } // end of row

            Text(modifier = Modifier
                .padding(16.dp),
                text = "Wandfläche: $mWallArea m^2"
            )

        }

    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Kotlin Desktop-Compose") {
        App()
    }
}
