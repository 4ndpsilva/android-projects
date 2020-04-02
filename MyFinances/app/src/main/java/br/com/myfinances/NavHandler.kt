package br.com.myfinances

import androidx.navigation.NavController

class NavHandler(var navController: NavController) {
    fun navigate(from: Int, to: Int){
        if(from == navController.currentDestination?.id){
            navController.navigate(to)
        }
    }
}