package com.example.jp

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun ComposableSideEffects(modifier: Modifier = Modifier) {
    var cnt by remember{mutableStateOf(0)}

    Box(
        contentAlignment = Alignment.Center
    ){
        Column {
            Button(
                onClick = {
                    cnt++;
                }
            ) {
                Text(text = "INC")
                Text(text = "$cnt")



            }
        }

       LaunchedEffect(
           //ye key decide krta hain ki kb run hoga
           //unit means initial recomposition main run hoga
           // in recompostion it will check ki key change hua ki nhi
           // if we want to make it run again
           //pass key1 = count
            key1 = Unit
       ) { //ye fn bhi baar baar call hoga which we dont want
           // as cnt ka state change ho rha hain
           //these are sideeffects
           //delay bhi daal skte hain

           delay(200) //exectutes in background , launch effecct coroutine ka scope use krta hain

           val data = getData()

           Log.d("papaya coders" , "$data $cnt")

       }


    }

}

@Composable
fun ComposableDisposableEffect(modifier: Modifier = Modifier) {
    var clicked by remember{ mutableStateOf(false) }
    DisposableEffect(
        key1 = clicked
    ) {
        //in launch effect we get courinte scope but isme nhi milta
        // cant use delay


        //used when hm exit kre and clean up required hain
        //koi bhi code jo sideeffct hain + clean up chhye
        //called when -> app closed and ->key changed
        Log.d("Tag" , "Resource Occupied")
        onDispose {
            Log.d("Tag" , "Resource Released")

        } //compulsory





    }
    Button(onClick = {clicked = !clicked}) {
        Text(text="click")
    }
}

@Composable
fun ComposableSideEffect(modifier: Modifier = Modifier) {
    var clicked by remember{ mutableStateOf(0) }

    SideEffect {
        // no need to provide key
        //scheduled to run
        //recompostion might never end
        //iske andar ka code tb run hoga jb successfull recomp ho rha hain
        //ye niche output nhi aara as recomposition scope main nhi hain
        //bas button ke liye recompostion ho rha ahin

        Log.d("TAG" , "COunt $clicked")

    }
    Button(onClick = {clicked++}) {
        Text(text="click $clicked")
    }
    //if i write below code then Log is visible as bas button recompose nhi ho rha hain isliye
    Text(text="ok $clicked")


}


@Composable
fun ComposableRememberScope(modifier: Modifier = Modifier) {
    //simple composable fn
    var scope = rememberCoroutineScope()
    var buttonText by remember { mutableStateOf("Hello") }

    Button(
        onClick = {
//            LaunchedEffect() { } we cant use this as button is not composable fn
            scope.launch {
                delay(2000)
                // on clicking 2 sec baad hello -> word
                // delay is suspend fn
                // no need to provide any key

                buttonText = "Word"

            }
        }
    ){
        Text(text = buttonText)

    }
}
@Preview(showBackground = true)

@Composable
fun ComposableRememberUpdatedSate(modifier: Modifier = Modifier) {
    var count by remember { mutableStateOf(0) }
    Button(
        onClick = {
            count = Random.nextInt(1,100)
            Log.d("Papaya" , "Random cnt: $count")

        }
    ){
        Text("Click")

    }
    ShowUpdatedValue(count)


}

@Composable
fun ShowUpdatedValue(count :Int) {
    // rememeber updaed state use hoga
    val updatedCount = rememberUpdatedState(count)
    LaunchedEffect(key1 = count) {
        delay(10000)
        Log.d("Papaya" , "updated cnt : ${updatedCount.value}")
    }
}


@Composable
fun ComposableProduceState () {
    var initialState by remember{
        mutableStateOf("Loading...")
    }
    var count by remember { mutableIntStateOf(0) }
   //on clicking count button this code will run again
    val dataFetched by produceState(
        "" , key1 = count
    ) {
        Log.d("Tag" , "Getting Data")
        val data = getData()
        value =  if (data.isNotEmpty()) ""
        else "Data did not fetched"
        Log.d("Tag" , "Got Data")

    }
//

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ){
        Button(onClick = {
            count++;
        }) {
            if(dataFetched == "Loading..."){
                CircularProgressIndicator()
            }
            else{
                Text(dataFetched)
            }

        }








    }



}
@Preview(showSystemUi = true , showBackground = true)
@Composable
fun ComposableDerivedStateOf(modifier: Modifier = Modifier) {
    var count by remember { mutableStateOf(0) }
    val derivedCnt by remember {
        derivedStateOf {
        count > 3
    }
    }

    Column (
    ){
        Button(onClick ={count++}) {
            Text("Button1")
        }
        // lkein isme dikkat ye hain ki ye code baar baar run ho rha if we keep count
        //recomposition badh rha hain
        // this is where we use derived state

        if(derivedCnt){
            Log.d("TAG" , "Showing button")
            Button(
                onClick = {}
            ) {
                Text("Button2")
            }

        }
        else {
            Log.d("TAG" , "Hidding button")
        }

    }

}
// lets create a fn that fetches data from db
//dummy netowrk call
suspend fun getData():List<String>{
    delay(4000)
    return listOf("papaya","coders")
}