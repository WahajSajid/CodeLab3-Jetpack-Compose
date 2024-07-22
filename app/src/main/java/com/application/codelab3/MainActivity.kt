package com.application.codelab3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.application.codelab3.ui.theme.Codelab3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Codelab3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

}

//Preview function to show MyScreen preview
@Composable
@Preview
private fun MyScreenPreview() {
//    MyScreen()
}


//Composable function to render the screen
@Composable
fun MyScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        TasksList(modifier = Modifier.padding(15.dp))
    }
}


//Preview Function for Column Item
@Preview
@Composable
fun ColumnItemPreview() {
    TaskItem(task = "Task # 1") {

    }
}

//Composable function render the item which is shown in the LazyColumn
@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    task: String,
    onClose: () -> Unit
) {
    var checked by rememberSaveable {
        mutableStateOf(false)
    }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = task, modifier = Modifier
                .padding(start = 10.dp)
                .weight(1f)
        )
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = !checked },
        )
        IconButton(onClick = onClose) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_close_24),
                contentDescription = "Close"
            )
        }
    }
}


//Composable function which render the LazyColumn
@Composable
fun TasksList(
    modifier: Modifier = Modifier,
    viewModelCompose: SharedViewModel = viewModel()
) {
    LazyColumn(modifier = modifier) {
        items(viewModelCompose.tasks) { task ->
            TaskItem(modifier = Modifier.padding(top = 10.dp), task = task.label, onClose = { viewModelCompose.removeTask(task) })
        }
    }
}