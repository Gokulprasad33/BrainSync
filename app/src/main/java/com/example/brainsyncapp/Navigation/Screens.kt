package com.example.brainsyncapp.Navigation

sealed class Screens(val route:String){
    object Homescreen :Screens("homescreen")
    object Settingscreen :Screens("settingscreen")
    object ThemeScreen :Screens("themescreen")
}