package com.example.jp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun SecondScreen(
    onGoingtoPrevScreen:  ()->Unit

) {
    //by default NAVHOST isko topstart main le jayga
    // we can align center in mainactivity also
    // jo abhi ka code hain

    Box(
        modifier = Modifier.fillMaxSize().background(
            Color.Magenta
        ).statusBarsPadding(),
        contentAlignment = Alignment.Center

        )  {
        Button(
            onClick = {
                onGoingtoPrevScreen()
            }
        ) {

            Text(text = "Go to previous screen ma")
        }
    }
}