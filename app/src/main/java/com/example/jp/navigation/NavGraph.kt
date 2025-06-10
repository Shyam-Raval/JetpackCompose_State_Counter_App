package com.example.jp.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.jp.FirstScreen
import com.example.jp.SecondScreen


@Composable
// we place all our screen
fun SetUpNavGraph(modifier: Modifier = Modifier) {

    val navController = rememberNavController() // ye instance tab  hee milta hain jb navigation ka depedency lgake rkhte hain
    NavHost(// nav controller and first screen kaunsa hain
        navController = navController , startDestination = FirstScreen){
        // we get a composable here that we will use
        composable<FirstScreen>{
            //first screen ka composable fn call hoga
           FirstScreen(

           ){
               navController.navigate(SecondScreen("Shyam",20))

           }

        }
        composable<SecondScreen>{
            val args = it.toRoute<SecondScreen>()
            LaunchedEffect(Unit) {
                Log.d("TAG", "${args.name} ${args.age}")
            }
            SecondScreen(){


                navController.navigate(FirstScreen){
                    popUpTo<FirstScreen>{
                        inclusive = true
                        //s1 s2
                        //if i press button again s2 will pop till we reach FirstScreen
                        //inclusive  means second screen also included

                    }
                }
            }
        }
    } // this is a composable fn


}