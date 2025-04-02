package com.example.BrainSync.screens

import android.app.Application
import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.brainsyncapp.database.NoteEntity
import com.example.brainsyncapp.R
import com.example.brainsyncapp.viewmodel.NoteViewModel
import com.example.brainsyncapp.viewmodel.ViewModelFactory
import java.util.Calendar
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

data class Note(
    var title: String = "",
    var content: String = "",
    var dueDate: String? = null,
    var priority: String? = "Normal",
    var label: String? = "None"
)

@Composable
fun HomeScreen(navController: NavHostController) {
    val context = LocalContext.current
    val noteViewModel: NoteViewModel = viewModel(
        factory = ViewModelFactory(context.applicationContext as Application)
    )

    val notes by noteViewModel.allNotes.observeAsState(emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("addnote") }, containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .offset(x = -16.dp, y = -30.dp)
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Floating Button")
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondary)
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                state = rememberLazyListState()
            ) {
                items(notes) { note ->
                    NoteItem(note)
                }
            }
        }
    }
}

@Composable
fun NoteItem(note: NoteEntity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(16.dp)
    ) {
        Text(
            text = note.noteTitle,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = note.noteContent,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        if (note.noteDueDate?.isNotEmpty() == true) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Due: ${note.noteDueDate}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
        if (note.noteLabel?.isNotEmpty() == true) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Label: ${note.noteLabel}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
        if (note.notePriority?.isNotEmpty() == true) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Priority: ${note.notePriority}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
fun AddNote(navController: NavHostController) {
    val context = LocalContext.current
    val noteViewModel: NoteViewModel = viewModel(
        factory = ViewModelFactory(context.applicationContext as Application)
    )

    var note by remember { mutableStateOf(Note()) }

    Scaffold(
        bottomBar = {
            FloatingToolBar(
                noteViewModel = noteViewModel,
                note = note,
                onNoteChange = { note = it },
                modifier = Modifier
                    .imePadding()
                    .fillMaxWidth()
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondary)
                .padding(innerPadding)
                .navigationBarsPadding()
        ) {
            Column(
                modifier = Modifier
                    .padding(0.dp)
                    .verticalScroll(rememberScrollState())
                    .imePadding()
                    .fillMaxSize()
            ) {
                Row { //Title
                    TextField(
                        value = note.title,
                        onValueChange = { note = note.copy(title = it) },
                        placeholder = {
                            Text(
                                "Title",
                                fontSize = 40.sp,
                                color = Color.Gray
                            )
                        },
                        textStyle = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 40.sp,
                            color = Color.Gray
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                    )
                }
                HorizontalDivider(
                    thickness = 1.2.dp,
                    color = MaterialTheme.colorScheme.primary
                )
                Row { //Notes
                    TextField(
                        value = note.content,
                        onValueChange = { note = note.copy(content = it) },
                        label = { Text(text = "Write Notes", color = Color.Black) },
                        textStyle = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 26.sp,
                            color = Color.Black
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .fillMaxSize()
                            .fillMaxHeight(0.8f)
                    )
                }
            }
        }
    }
}

//Floating toolbar
@Composable
fun FloatingToolBar(
    noteViewModel: NoteViewModel,
    note: Note,
    onNoteChange: (Note) -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(CircleShape)
            .background(color = MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.BottomCenter // Keeps it at the bottom
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            val showDatePicker = remember { mutableStateOf(false) }
            val showPriorityPicker = remember { mutableStateOf(false) }
            val showLabelPicker = remember { mutableStateOf(false) }
            val showBottomSheet = remember { mutableStateOf(false) }
            FilledIconButton(
                onClick = { showDatePicker.value = true },
                modifier = Modifier.padding(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Due Date",
                    tint = Color.Black
                )
                DueDate(showDatePicker, note, onNoteChange)
            }
            FilledIconButton(
                onClick = { showPriorityPicker.value = true },
                modifier = Modifier.padding(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Priority",
                    tint = Color.Black
                )
                PriorityDialog(showPriorityPicker, note, onNoteChange)
            }
            FilledIconButton(onClick = { showBottomSheet.value = true }, modifier = Modifier.padding(4.dp)) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add files",
                    tint = Color.Black
                )
                ShowFiles(showBottomSheet)
            }
            FilledIconButton(onClick = { showLabelPicker.value = true }, modifier = Modifier.padding(4.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_label_24),
                    contentDescription = "Label",
                    tint = Color.Black
                )
                SelectLabel(showLabelPicker, note, onNoteChange)
            }
            Button(
                onClick = {
                    val noteToSave = NoteEntity(
                        noteTitle = note.title,
                        noteContent = note.content,
                        noteLabel = note.label ?: "None",
                        notePriority = note.priority ?: "Normal",
                        noteDueDate = note.dueDate ?: ""
                    )
                    noteViewModel.insertNote(noteToSave)
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                modifier = Modifier.padding(4.dp)
            ) {
                Text(
                    text = "Save",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun DueDate(showDatePicker: MutableState<Boolean>, note: Note, onNoteChange: (Note) -> Unit) {
    val currContext = LocalContext.current
    val mCalendar = Calendar.getInstance()
    val currYear = mCalendar.get(Calendar.YEAR)
    val currMonth = mCalendar.get(Calendar.MONTH)
    val currDate = mCalendar.get(Calendar.DAY_OF_MONTH)

    val cDatePickerDialog = DatePickerDialog(
        currContext,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
            onNoteChange(note.copy(dueDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"))
            Toast.makeText(currContext, "Duedate:${note.dueDate}", Toast.LENGTH_SHORT).show()
        },
        currYear, currMonth, currDate
    )

    if (showDatePicker.value) {
        cDatePickerDialog.show()
        showDatePicker.value = false // Reset after opening
    }
}

@Composable
fun PriorityDialog(showPriorityPicker: MutableState<Boolean>, note: Note, onNoteChange: (Note) -> Unit) {
    if (showPriorityPicker.value) {
        AlertDialog(
            onDismissRequest = { showPriorityPicker.value = false },
            text = {
                Column {
                    Text("Select priority level", fontWeight = FontWeight.Bold, fontSize = 22.sp, color = MaterialTheme.colorScheme.secondary)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        Button(onClick = { onNoteChange(note.copy(priority = "Low")) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary,
                                contentColor = MaterialTheme.colorScheme.primary
                            )) {
                            Text("Low", color = Color.Black)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = { onNoteChange(note.copy(priority = "Normal")) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary,
                                contentColor = MaterialTheme.colorScheme.primary
                            )) {
                            Text("Normal", color = Color.Black)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = { onNoteChange(note.copy(priority = "High")) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary,
                                contentColor = MaterialTheme.colorScheme.primary
                            )) {
                            Text("High", color = Color.Black)
                        }
                    }
                }
            },
            confirmButton = {
                Button(onClick = { showPriorityPicker.value = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = Color.Black
                    )
                ) {
                    Text("Choose (${note.priority})")
                }
            },
            containerColor = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun SelectLabel(showLabelPicker: MutableState<Boolean>, note: Note, onNoteChange: (Note) -> Unit) {
    if (showLabelPicker.value) {
        AlertDialog(
            onDismissRequest = { showLabelPicker.value = false },
            text = {
                Column {
                    Text("Select Label", fontWeight = FontWeight.Bold, fontSize = 22.sp, color = MaterialTheme.colorScheme.secondary)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        Button(onClick = { onNoteChange(note.copy(label = "Work")) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary,
                                contentColor = MaterialTheme.colorScheme.primary
                            )) {
                            Text("Work", color = Color.Black)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = { onNoteChange(note.copy(label = "Task")) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary,
                                contentColor = MaterialTheme.colorScheme.primary
                            )) {
                            Text("Tasks", color = Color.Black)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = { onNoteChange(note.copy(label = "Ideas")) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary,
                                contentColor = MaterialTheme.colorScheme.primary
                            )) {
                            Text("Ideas", color = Color.Black)
                        }
                    }
                }
            },
            confirmButton = {
                Button(onClick = { showLabelPicker.value = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = Color.Black
                    )
                ) {
                    Text("Choose (${note.label})")
                }
            },
            containerColor = MaterialTheme.colorScheme.primary
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowFiles(showBottomSheet: MutableState<Boolean>) {
    if (showBottomSheet.value) {
        ModalBottomSheet(onDismissRequest = { showBottomSheet.value = false }, containerColor = Color.White) {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Add Items", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
            Row(modifier = Modifier.padding(20.dp)) {
                Column(
                    modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        shape = CircleShape,
                        elevation = ButtonDefaults.elevatedButtonElevation(
                            focusedElevation = 20.dp
                        ),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_add_to_photos_24),
                            contentDescription = "ImageIcon",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                    Text(text = "Image", fontWeight = FontWeight.Bold)
                }
                Column(
                    modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        shape = CircleShape,
                        elevation = ButtonDefaults.elevatedButtonElevation(
                            focusedElevation = 20.dp
                        ),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_attach_file_24),
                            contentDescription = "ImageIcon",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                    Text(text = "Documents", fontWeight = FontWeight.Bold)
                }
                Column(
                    modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        shape = CircleShape,
                        elevation = ButtonDefaults.elevatedButtonElevation(
                            focusedElevation = 20.dp
                        ),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_check_box_24),
                            contentDescription = "ImageIcon",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                    Text(text = "CheckBox", fontWeight = FontWeight.Bold)
                }
            }
            Row {
                Column(
                    modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        shape = CircleShape,
                        elevation = ButtonDefaults.elevatedButtonElevation(
                            focusedElevation = 20.dp
                        ),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_mic_24),
                            contentDescription = "ImageIcon",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                    Text(text = "Recording", fontWeight = FontWeight.Bold)
                }
                Column(
                    modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        shape = CircleShape,
                        elevation = ButtonDefaults.elevatedButtonElevation(
                            focusedElevation = 20.dp
                        ),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_gif_box_24),
                            contentDescription = "ImageIcon",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                    Text(text = "Gif", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}