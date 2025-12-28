package com.geekhaven.alumx.components.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geekhaven.alumx.ui.theme.SurfaceColor


@Composable
fun TextInputComponent(
    modifier: Modifier = Modifier,
    title: String,
    leadingIcon: ImageVector,
    input: String,
    changeFunc: (String) -> Unit,
    placeholderText: String
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(start = 5.dp),
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = Color.White,
            text = title
        )

        OutlinedTextField(
            value = input,
            onValueChange = changeFunc,
            shape = RoundedCornerShape(10.dp),
            placeholder = { Text(placeholderText) },
            modifier = Modifier.fillMaxWidth().heightIn(min = 44.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = SurfaceColor,
                unfocusedContainerColor = SurfaceColor,
                unfocusedIndicatorColor = Color.White.copy(alpha = 0.1f)
            ),
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon, contentDescription = null,
                )
            }
        )
    }
}

