package com.geekhaven.alumx.components.auth

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geekhaven.alumx.R
import com.geekhaven.alumx.ui.theme.AlumXTheme


@Composable
fun HeaderComponent(modifier: Modifier= Modifier,headerText: String,headerSubText: String){
    Column(modifier=modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo Image of AlumX",
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Spacer(modifier= Modifier.height(10.dp))
        Text(
            text = headerText,
            fontSize = 31.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Text(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            modifier = Modifier.padding(start = 32.dp, end = 32.dp),
            textAlign = TextAlign.Center,
            color = Color.White.copy(alpha = 0.7f),
            text = headerSubText
        )    }
}

@Preview(showBackground = false )
@Composable
fun HeaderComponentPreview(){
    AlumXTheme() {
    HeaderComponent(Modifier.fillMaxSize(),"AlumX","Connect. Mentor. Grow")
}}