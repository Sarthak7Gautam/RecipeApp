package eu.tutorials.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import eu.tutorials.myrecipeapp.ui.theme.CategoryScreenDetail

@Composable
fun RecipeApp(navController: NavHostController){
    val RecipeviewModel:MainViewModel= viewModel()
    val viewstate by RecipeviewModel.categoriesState

    NavHost(navController = navController, startDestination =Screen.RecipeScreen.route){
        composable(Screen.RecipeScreen.route){
                RecipeScreen(viewstate=viewstate,navigatetoDetail ={
                    navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                    navController.navigate(Screen.DetailScreen.route)
                })
            }
        composable(Screen.DetailScreen.route){
            val category=navController.previousBackStackEntry?.savedStateHandle
                ?.get<Category>("cat")?: Category("","","","")
            CategoryScreenDetail(category = category)
        }
        }
    }