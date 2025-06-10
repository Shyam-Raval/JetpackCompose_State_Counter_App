package com.example.jp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jp.ui.theme.JPTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.jp.navigation.SetUpNavGraph
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                Color.Black.toArgb()   //icon will turn white
            )
//            statusBarStyle = SystemBarStyle.light(
//                Color.Gray.toArgb(),//if koi device jisme always light sys icon hain
//                Color.Blue.toArgb()
//
//            ) //icon will turn black/white
        ) //jo bhi screen hoti hain usko spread out krta hain

        setContent {
            JPTheme {  //composable fn
              Box(
                  modifier = Modifier.fillMaxSize(),
                 // contentAlignment = Alignment.Center //button in center
              ){
                  SetUpNavGraph() //root fn


              }//we dont want ki har screen ka khud se likhe to sbko isme krlenge change


            }
        }
    }

}


@Composable
fun ComposableTextField() {
    var text by remember { mutableStateOf("") }
    var passwordVisibile by remember { mutableStateOf(false) }
    TextField(
        value = text,
        onValueChange = { tftext ->
            text = tftext

        },
        enabled = true,
        readOnly = false,
        label = {
            Text(text = "email")
        },
//        placeholder = {
//            Text(text = "enter your mail")
//        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = ""
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                passwordVisibile = !passwordVisibile // t-> f and f-> t
            }) {
                Icon(
                    imageVector =
                    if (passwordVisibile == false) {
                        ImageVector.vectorResource(R.drawable.visibility_24dp_e3e3e3_fill0_wght400_grad0_opsz24)
                    } else {
                        ImageVector.vectorResource(R.drawable.visibility_off_24dp_e3e3e3_fill0_wght400_grad0_opsz24)

                    },

                    contentDescription = ""
                )
            }

        },
//        prefix = {
//            Text(text = "Prefix text")
//        },
//        suffix = {
//            Text(text = "@gmail.com")
//        },
//        supportingText = {
//            Text(text = "this is support text")
//        },
        isError = true,
        //visualTransformation = if(passwordVisibile == false)PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
//        keyboardActions = KeyboardActions(
//            onSearch = {
//             //   Log.d("onSearch","onSearch")
//            }
//        ),
        shape = RoundedCornerShape(20.dp),


        )

}


@Composable
fun ComposableChips(modifier: Modifier = Modifier) {
    var selectedChip by remember { mutableStateOf("All") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .background(Color.Black)
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        FilterChip(


            onClick = {
                selectedChip = "All"
            },
            selected = selectedChip == "All",
            label = { Text(text = "All", fontSize = 12.sp, maxLines = 1) },
            leadingIcon = {
                if (selectedChip == "All") {
                    Icon(imageVector = Icons.Default.Done, contentDescription = "")

                }
            },
            colors = FilterChipDefaults.filterChipColors().copy(
                containerColor = Color.Blue.copy(0.6f),
                labelColor = Color.White,
                leadingIconColor = Color.White,
                selectedLabelColor = Color.White,
                selectedContainerColor = Color.Blue


            )

        )
        FilterChip(

            onClick = {
                selectedChip = "High"
            },
            selected = selectedChip == "High",
            label = { Text(text = "High", fontSize = 12.sp, maxLines = 1) },
            leadingIcon = {
                if (selectedChip == "High") {
                    Icon(imageVector = Icons.Default.Done, contentDescription = "")

                }
            },
            colors = FilterChipDefaults.filterChipColors().copy(
                containerColor = Color.Green.copy(0.6f),
                labelColor = Color.White,
                leadingIconColor = Color.White,
                selectedContainerColor = Color.Green


            )

        )
        FilterChip(

            onClick = {
                selectedChip = "Medium"
            },
            selected = selectedChip == "Medium",
            label = { Text(text = "Medium", fontSize = 12.sp, maxLines = 1) },
            leadingIcon = {
                if (selectedChip == "Medium") {
                    Icon(imageVector = Icons.Default.Done, contentDescription = "")

                }
            },
            colors = FilterChipDefaults.filterChipColors().copy(
                containerColor = Color.Yellow.copy(0.6f),
                labelColor = Color.White,
                leadingIconColor = Color.White,
                selectedContainerColor = Color.Yellow


            )

        )
        FilterChip(

            onClick = {
                selectedChip = "Low"
            },
            selected = (selectedChip == "Low"),
            label = { Text(text = "Low", fontSize = 12.sp, maxLines = 1) },
            leadingIcon = {
                if (selectedChip == "Low") {
                    Icon(imageVector = Icons.Default.Done, contentDescription = "")

                }
            },
            colors = FilterChipDefaults.filterChipColors().copy(
                containerColor = Color.Red.copy(0.6f),
                labelColor = Color.White,
                leadingIconColor = Color.White,
                selectedContainerColor = Color.Red


            )

        )
    }

}

data class cricketor(
    val name: String,
    val color: Color
)

val cricketPlayers = listOf(
    cricketor("virat kohli", Color.Red),
    cricketor("mahendra dhone", Color.Blue),
    cricketor("rinku singh", Color.Magenta),
    cricketor("sunil narine", Color.Cyan),
    cricketor("virat kohli", Color.Red),
    cricketor("mahendra dhone", Color.Blue),
    cricketor("rinku singh", Color.Magenta),
    cricketor("sunil narine", Color.Cyan),
    cricketor("virat kohli", Color.Red),
    cricketor("mahendra dhone", Color.Blue),
    cricketor("rinku singh", Color.Magenta),
    cricketor("sunil narine", Color.Cyan),
    cricketor("virat kohli", Color.Red),
    cricketor("mahendra dhone", Color.Blue),
    cricketor("rinku singh", Color.Magenta),
    cricketor("sunil narine", Color.Cyan),


    )


@Composable
fun CricketerItem(
    //initilization
    modifier: Modifier = Modifier,
    name: String = "Virat kohli",
    color: Color = Color.Blue
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()


            .shadow(
                elevation = 10.dp,
                spotColor = color,
                shape = RoundedCornerShape(15.dp)
            ),
        color = Color.White,
        shadowElevation = 10.dp,
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(1.dp, color)
    ) {
        Row(

        ) {
            Spacer(modifier = Modifier.width(15.dp))
            Icon(
                modifier = Modifier
                    .padding(4.dp, 16.dp)
                    .size(45.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(color)
                    .padding(2.dp),
                imageVector = Icons.Default.Person,
                contentDescription = "",
                tint = Color.White

            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                modifier = Modifier.padding(10.dp),
                text = name,
                maxLines = 1,
                style = TextStyle(
                    fontSize = 40.sp,

                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    shadow = Shadow(
                        color = color,
                        blurRadius = 20f
                    )
                )
            )
        }


    }

}

@Composable
fun ComposableLazyColumn(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        reverseLayout = true

    ) {
        //prove list here
        items(cricketPlayers) { it ->
            val name = it.name
            val color = it.color
            CricketerItem(name = name, color = color)


        }
    }
}


@Composable
fun ComposableCheckBox(
    modifier: Modifier = Modifier
) {
    //Initialize state for every child
    val childCheckBoxes = remember {
        mutableStateListOf(false, false, false)
    }

    // now we create a parent state:
    val parentState = when {
        //.all -> return true if all are true or empty , if any one i false it returns false
        //.none -> if any one true it return false else return false
        childCheckBoxes.all { it } -> ToggleableState.On
        childCheckBoxes.none { it } -> ToggleableState.Off

        else -> ToggleableState.Indeterminate

    }
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Select All")
            TriStateCheckbox(
                state = parentState,
                onClick = {
                    //initially parent state == off , so newState becomes true
                    val newState = (parentState != ToggleableState.On)
                    //saare child true ho gye
                    childCheckBoxes.forEachIndexed { index, _ ->
                        childCheckBoxes[index] = newState
                    }

                }
            )

        }
        //child checkBox:

        childCheckBoxes.forEachIndexed { index, checked ->  //we access index as well as what ever value will be in the index
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Option ${index + 1}")
                Checkbox(
                    checked = checked,
                    onCheckedChange = { isChecked ->
                        childCheckBoxes[index] = isChecked
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposableTopAppBar(drawerState: DrawerState) {
    val scope = rememberCoroutineScope()
    TopAppBar(
        title = { Text(text = "home") },
        navigationIcon = {
            IconButton(
                onClick = {
                    drawerState.apply {
                        //can use suspend fn close and open in suspending fn or courinte scope
                        scope.launch {
                            if (isOpen) close()
                            else open()
                        }

                    }
                },
            ) { Icon(imageVector = Icons.Default.Home, contentDescription = null) }

        },
        //multiple icon provided , by defailt in row
        actions = {


            Icon(imageVector = Icons.Default.Settings, contentDescription = null)
            //we will provide width between both icon usinf spacer
            Spacer(modifier = Modifier.width(3.dp))
            Icon(imageVector = Icons.Default.Info, contentDescription = null)

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),


        )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ComposableBottomAppBar(modifier: Modifier = Modifier) {

    BottomAppBar(
        contentPadding = PaddingValues(start = 20.dp)
    ) {
        Icon(imageVector = Icons.Default.Build, contentDescription = "")
        Spacer(modifier = Modifier.width(20.dp))
        Icon(imageVector = Icons.Default.Done, contentDescription = "")
        Spacer(modifier = Modifier.width(20.dp))
        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposableScaffold(drawerState: DrawerState) {
    //ye appropitae jaga pe rakh deta hain composable ko , jese topappbar top pe hoga and bottom ko bottomappbar
    //val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier,
        topBar = {

            ComposableTopAppBar(drawerState)
        },
        bottomBar = {
            ComposableBottomAppBar()
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Add, contentDescription = ""
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Start
    ) { innerPadding ->
        ComposableLazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        );


    }

}


@Composable
fun ComposableFAB(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        FloatingActionButton(
            onClick = {}, modifier = Modifier.align(
                Alignment.Center
            )
        ) {
            Icon(
                imageVector = Icons.Default.Add, contentDescription = ""
            )
        }

    }


}


@Composable
fun ComposableNavigationDrawer() {
    var ib by remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(
        if (ib) DrawerValue.Open
        else DrawerValue.Closed
    )
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(202.dp)
            ) {
                Text(text = "Compose Playlist")
                HorizontalDivider()
                NavigationDrawerItem(
                    label = {
                        Text(text = "Inbox")
                    },
                    selected = true,
                    onClick = {},
                    icon = {
                        Icon(imageVector = Icons.Default.Email, contentDescription = "")

                    },
                    badge = {

                        Icon(imageVector = Icons.Default.Check, contentDescription = "")
                    }

                )
                var ok1 by remember { mutableStateOf(false) }

                var ok by remember { mutableStateOf(false) }
                NavigationDrawerItem(
                    label = {
                        Text(text = "Inbox")
                    },
                    selected = false,
                    onClick = { ok = !ok },
                    icon = {
                        Icon(imageVector = Icons.Default.Email, contentDescription = "")

                    },
                    badge = {

                        if (ok == true) Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = ""
                        )
                    }

                )

                NavigationDrawerItem(

                    label = {
                        Text(text = "Inbox")
                    },
                    selected = ok1,
                    onClick = { ok1 = !ok1 },
                    icon = {
                        Icon(imageVector = Icons.Default.Email, contentDescription = "")

                    },
                    badge = {

                        if (ok1 == true) Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = ""
                        )
                    },


                    )

            }


        },
        drawerState = drawerState

    ) {

        ComposableScaffold(drawerState)

    }
}

@Composable
fun ComposableAlertDialog(modifier: Modifier = Modifier) {
    var openDialog by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ) {
        Button(
            onClick = { openDialog = true },
        ) {
            Text(text = "Show Dialog")
        }
    }
    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                openDialog = false

            },
            confirmButton = {
                Button(onClick = {
                    openDialog = false
                }) {
                    Text(text = "Confirm")
                }

            },
            icon = {
                Icon(imageVector = Icons.Default.Build, contentDescription = "")

            },
            title = {
                Text(text = "Alert Dialog")
            },
            text = {
                Text(text = "This is an alert dialog")
            },
            dismissButton = {
                Button(onClick = { openDialog = false }) {
                    Text(text = "Cancel")
                }
            },
            properties = DialogProperties(false)


        )
    }
    //alert dialog has too much restriction only follow initial proerties
    //dialog gives more freedon
    if (openDialog) {
        Dialog(onDismissRequest = { openDialog = false }) {
            ComposableTextField()
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)


@Composable
fun ComposableBottomSheet(modifier: Modifier = Modifier) {

    var showBottomSheetDialog by remember { mutableStateOf(false) }
    var sheetState = rememberModalBottomSheetState()
    var scope = rememberCoroutineScope()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { showBottomSheetDialog = !showBottomSheetDialog }) {
            Text(text = "Bottom Sheet")
        }
    }

    if (showBottomSheetDialog) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxHeight(), // ye use krne se poora screen cover krega onscrolling
            onDismissRequest = { showBottomSheetDialog = false }
        ) {
            //all composable a by default in column fn

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Select",
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    textAlign = TextAlign.Center,

                    )
                Row() {
                    Spacer(modifier = Modifier.width(15.dp))
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "")
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "Edit")
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row() {
                    Spacer(modifier = Modifier.width(15.dp))
                    Icon(imageVector = Icons.Default.Share, contentDescription = "")
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "Share")
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row() {
                    Spacer(modifier = Modifier.width(15.dp))
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "")
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "Add to Cart")
                }
                Spacer(modifier = Modifier.height(20.dp))
                Spacer(modifier = Modifier.width(15.dp))
                Button(onClick = {
                    scope.launch {
                        sheetState.hide()
                        showBottomSheetDialog = false
                    }
                }) {
                    Text(text = "Hide Bottom Sheet")
                }
            Spacer(modifier = Modifier.height(20.dp))
            }





    }
}

//agr recomposition ke time koi esa code recompose ho rha hain jo nhi hona chhye , usko hm bolte hain sideeffects

