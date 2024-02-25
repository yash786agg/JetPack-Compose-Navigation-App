package com.android.onboarding.compose.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.onboarding.R

@Composable
fun OutlinedTextButton(buttonText: String, onBtnClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        OutlinedButton(
            onClick = {
                onBtnClick()
            }, modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter), colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )
        ) {
            Text(
                text = buttonText,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun OnBoardingBottomTextButton(onBackBtnClick: () -> Unit, onNextBtnClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = stringResource(id = R.string.text_back),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .clickable {
                    onBackBtnClick()
                },
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black
        )

        Text(
            text = stringResource(id = R.string.text_next),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .clickable {
                    onNextBtnClick()
                },
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black
        )
    }
}