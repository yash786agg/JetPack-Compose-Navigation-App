package com.android.navigation_app.ui.splash

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.navigation.NavigationItem
import kotlinx.coroutines.delay
import com.android.navigation_app.R

@Composable
fun SplashScreen(navController: NavHostController)  {
    val alpha = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        alpha.animateTo(
            1f,
            animationSpec = tween(2500)
        )
        delay(3000)

        /*if (onBoardingIsFinished(context = context)) {
            navController.popBackStack()
            navController.navigate("Home")
        } else {*/
        navController.popBackStack()
        navController.navigate(NavigationItem.WELCOME.route)
        // }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Color.DarkGray else Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_android),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = stringResource(id = R.string.text_splash_screen),
            modifier = Modifier.alpha(alpha.value),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }
}