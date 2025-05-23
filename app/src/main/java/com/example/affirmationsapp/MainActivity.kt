package com.example.affirmationsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmationsapp.data.DataSource
import com.example.affirmationsapp.model.Affirmation
import com.example.affirmationsapp.ui.theme.AffirmationsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AffirmationsAppTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    AffirmationApp()
                }
            }
        }
    }
}

@Composable
fun AffirmationApp() {
    val layoutDirection = LocalLayoutDirection.current
    val paddingValues = WindowInsets.safeDrawing.asPaddingValues()
    Surface(
        modifier = Modifier.fillMaxWidth().
        statusBarsPadding().padding(
            start = paddingValues.calculateStartPadding(layoutDirection),
            end = paddingValues.calculateEndPadding(layoutDirection),
            bottom = paddingValues.calculateBottomPadding()
        )
    ) {
        AffirmationList(
            affirmationlist = DataSource().loadAffirmations())
    }

}

@Composable
fun AffirmationCard(
    affirmation: Affirmation,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId),
                modifier = Modifier.fillMaxWidth().
                height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall

                )
        }
    }

    }
@Composable
fun AffirmationList(affirmationlist: List<Affirmation>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        items(affirmationlist) { affirmation ->
            AffirmationCard(
                affirmation = affirmation,
                modifier = Modifier
                    .padding(16.dp)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun AffirmationPreview() {
    AffirmationsAppTheme {
        AffirmationApp()
    }
}