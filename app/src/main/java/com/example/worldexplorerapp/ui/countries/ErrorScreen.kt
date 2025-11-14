package com.example.worldexplorerapp.ui.countries

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.worldexplorerapp.R

@Composable
internal fun ErrorScreen(
    message: String,
    retryAction: () -> Unit
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(stringResource(R.string.error, message), textAlign = TextAlign.Center)
            Spacer(Modifier.height(8.dp))
            Button(onClick = { retryAction() }) {
                Text(stringResource(R.string.retry))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(message = "There is an error") {}
}