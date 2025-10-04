package com.example.clase7

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.ui.res.stringResource

@Composable
fun FormScreen(
    navController: NavController,
    viewModel: UsersViewModel = viewModel()
) {
    var name by remember { mutableStateOf("") }
    var career by remember { mutableStateOf("") }
    var studentId by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = stringResource(R.string.form_title),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(stringResource(R.string.name)) },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = career,
            onValueChange = { career = it },
            label = { Text(stringResource(R.string.career)) },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = studentId,
            onValueChange = { studentId = it },
            label = { Text(stringResource(R.string.student_id)) },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                if (name.isNotBlank() && career.isNotBlank() && studentId.isNotBlank()) {
                    val newUser = User(name, career, studentId)
                    viewModel.addUser(newUser)
                    navController.navigate(NavRoutes.USERS)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.new_user))
        }
    }
}