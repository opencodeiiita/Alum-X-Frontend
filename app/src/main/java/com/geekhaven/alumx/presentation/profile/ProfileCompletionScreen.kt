package com.geekhaven.alumx.presentation.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileCompletionScreen() {

    val bgColor = Color(0xFF0B1220)
    val blue = Color(0xFF1E6BFF)
    val cardBg = Color(0xFF141C2F)

    // Form States
    var graduationYear by remember { mutableStateOf("") }
    var branch by remember { mutableStateOf("") }
    var currentRole by remember { mutableStateOf("") }
    var shortBio by remember { mutableStateOf("") }
    var linkedinUrl by remember { mutableStateOf("") }
    var skillInput by remember { mutableStateOf("") }

    val skills = remember { mutableStateListOf("UX Design", "Strategy") }
    val interests = listOf("Technology", "Mentorship", "Startups", "Networking", "Career Growth")
    val selectedInterests = remember { mutableStateListOf("Mentorship", "Networking") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Color.White)
            Spacer(Modifier.weight(1f))
            Text("Profile Setup", color = Color.White, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.weight(1f))
            Text("Skip", color = Color.Gray)
        }

        Spacer(Modifier.height(20.dp))

      
        Row(Modifier.fillMaxWidth()) {
            Text("Profile Strength", color = Color.Gray)
            Spacer(Modifier.weight(1f))
            Text("75% Complete", color = blue)
        }

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            interests.forEach { interest ->
                SelectableChip(
                    text = interest,
                    selected = selectedInterests.contains(interest),
                    onClick = {
                        if (selectedInterests.contains(interest)) selectedInterests.remove(interest) else selectedInterests.add(interest)
                    }
                )
            }
        }

        LinearProgressIndicator(
            progress = { 0.75f },
            color = blue,
            trackColor = Color.DarkGray,
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(50))
        )

        Spacer(Modifier.height(24.dp))

        
        Text(
            "Let's finish your profile.",
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            "Complete your details to get better mentorship matches and rank higher in Smart Search.",
            color = Color.Gray,
            fontSize = 14.sp
        )

        Spacer(Modifier.height(28.dp))

      
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.BottomEnd
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE0C4A6)),
                    contentAlignment = Alignment.Center
                ) {
                   
                }

              
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(blue, CircleShape)
                        .padding(6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Edit, null, tint = Color.White)
                }
            }
            Spacer(Modifier.height(8.dp))
            Text("Change Photo", color = blue, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        }

        Spacer(Modifier.height(32.dp))

     
        ProfileTextField(graduationYear, { graduationYear = it }, "Graduation Year", "e.g. 2024")
        Spacer(Modifier.height(16.dp))

        ProfileTextField(branch, { branch = it }, "Branch / Department", "e.g. Computer Science")
        Spacer(Modifier.height(16.dp))

        ProfileTextField(currentRole, { currentRole = it }, "Current Role / Domain", "e.g. Product Designer")
        Spacer(Modifier.height(16.dp))

        ProfileTextField(shortBio, { shortBio = it }, "Short Bio (Optional)", "Tell us about yourself...", false)
        Spacer(Modifier.height(16.dp))

        ProfileTextField(linkedinUrl, { linkedinUrl = it }, "LinkedIn / Portfolio URL (Optional)", "https://linkedin.com/in/...")

        Spacer(Modifier.height(24.dp))

     
        SectionTitle("Skills", "Add at least 3")

        OutlinedTextField(
            value = skillInput,
            onValueChange = { skillInput = it },
            placeholder = { Text("e.g. Product Management", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Default.Search, null, tint = Color.Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = cardBg,
                unfocusedContainerColor = cardBg,
                focusedBorderColor = blue,
                unfocusedBorderColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = blue
            ),
            singleLine = true,
            trailingIcon = {
                if (skillInput.isNotEmpty()) {
                    IconButton(onClick = {
                        if (skillInput.isNotBlank()) {
                            skills.add(skillInput)
                            skillInput = ""
                        }
                    }) {
                        Icon(Icons.Default.Add, null, tint = blue)
                    }
                }
            }
        )

        @OptIn(ExperimentalLayoutApi::class)
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            skills.forEach { skill ->
                RemovableChip(text = skill, onRemove = { skills.remove(skill) })
            }
            // Add Skill Chip (Static)
            Box(
                modifier = Modifier
                    .background(Color(0xFF1C2333), RoundedCornerShape(20.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text("+ Figma", color = Color.Gray, fontSize = 13.sp)
            }
            Box(
                modifier = Modifier
                    .background(Color(0xFF1C2333), RoundedCornerShape(20.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text("+ Prototyping", color = Color.Gray, fontSize = 13.sp)
            }
        }

        Spacer(Modifier.height(24.dp))

      
        SectionTitle("Interests")

        @OptIn(ExperimentalLayoutApi::class)
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            interests.forEach { interest ->
                SelectableChip(
                    text = interest,
                    selected = selectedInterests.contains(interest),
                    onClick = {
                        if (selectedInterests.contains(interest)) {
                            selectedInterests.remove(interest)
                        } else {
                            selectedInterests.add(interest)
                        }
                    }
                )
            }
        }



        Spacer(Modifier.height(24.dp))

       
        Text("Experience", color = Color.White, fontWeight = FontWeight.SemiBold)

        Spacer(Modifier.height(12.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = cardBg),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
            
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color.White, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("G", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }

                Spacer(Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text("Senior Product Designer", color = Color.White, fontWeight = FontWeight.Medium, fontSize = 14.sp)
                    Text("Google • Full-time", color = Color.Gray, fontSize = 12.sp)
                    Text("Jan 2021 - Present", color = Color.Gray, fontSize = 12.sp)
                }

                Icon(Icons.Default.Edit, null, tint = Color.Gray, modifier = Modifier.size(20.dp))
            }
        }

        Spacer(Modifier.height(16.dp))

        OutlinedButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFF2B3240))
        ) {
            Icon(Icons.Default.Add, null, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text("Add Position")
        }

        Spacer(Modifier.height(32.dp))

      
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            colors = ButtonDefaults.buttonColors(containerColor = blue),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Complete Profile →", fontSize = 16.sp, color = Color.White)
        }

        Spacer(Modifier.height(20.dp))
    }
}


@Composable
fun ProfileTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String = "",
    singleLine: Boolean = true
) {
    Column {
        Text(
            text = label,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color.Gray) },
            singleLine = singleLine,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF141C2F),
                unfocusedContainerColor = Color(0xFF141C2F),
                focusedBorderColor = Color(0xFF1E6BFF),
                unfocusedBorderColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color(0xFF1E6BFF)
            )
        )
    }
}

@Composable
fun SectionTitle(title: String, action: String? = null) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
        Text(title, color = Color.White, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.weight(1f))
        action?.let { Text(it, color = Color.Gray, fontSize = 12.sp) }
    }
    Spacer(Modifier.height(12.dp))
}

@Composable
fun RemovableChip(text: String, onRemove: () -> Unit) {
    Surface(
        color = Color(0xFF141C2F), // Darker blue/bg
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, Color(0xFF1E6BFF)),
        modifier = Modifier.height(32.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            Text(text, color = Color(0xFF1E6BFF), fontSize = 13.sp)
            Spacer(Modifier.width(6.dp))
            IconButton(
                onClick = onRemove,
                modifier = Modifier.size(16.dp)
            ) {
                Icon(Icons.Default.Close, null, tint = Color(0xFF1E6BFF))
            }
        }
    }
}

@Composable
fun SelectableChip(text: String, selected: Boolean, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        color = if (selected) Color(0xFF1E6BFF) else Color(0xFF141C2F),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.height(32.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(text, color = Color.White, fontSize = 13.sp)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Phone View")
@Composable
fun PreviewProfile() {
    ProfileCompletionScreen()
}

@Preview(showBackground = true, heightDp = 1500, name = "Full Scroll View")
@Composable
fun PreviewProfileFull() {
    ProfileCompletionScreen()
}
