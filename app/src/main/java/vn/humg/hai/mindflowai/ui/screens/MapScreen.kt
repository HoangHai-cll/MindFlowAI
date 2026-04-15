package vn.humg.hai.mindflowai.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vn.humg.hai.mindflowai.ui.theme.*

@Composable
fun MapScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        // --- 1. Top Search Bar ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, start = 24.dp, end = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                placeholder = { Text("Tìm kiếm kiến thức...", color = SecondaryText) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = SecondaryText) },
                shape = RoundedCornerShape(28.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = CardBg,
                    focusedContainerColor = CardBg,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = AccentCyan
                )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Surface(
                modifier = Modifier.size(56.dp),
                shape = CircleShape,
                color = CardBg
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.EmojiEvents, contentDescription = null, tint = AccentCyan)
                }
            }
        }

        // --- 2. Mindmap Mockup ---
        Box(modifier = Modifier.fillMaxSize()) {
            // Node: MD3 (Center Start)
            MapNode(
                title = "Phát triển Android",
                label = "MD3",
                status = "active",
                modifier = Modifier.align(Alignment.CenterStart).padding(start = 40.dp)
            )

            // Node: Cơ bản về Kotlin (Top End)
            MapNode(
                title = "Cơ bản về Kotlin",
                status = "completed",
                modifier = Modifier.align(Alignment.TopEnd).padding(top = 180.dp, end = 40.dp)
            )

            // Node: Jetpack Compose (Bottom End)
            MapNode(
                title = "Jetpack Compose",
                status = "pending",
                modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = 180.dp, end = 40.dp)
            )
        }
    }
}

@Composable
fun MapNode(
    title: String,
    status: String, // completed, active, pending
    modifier: Modifier = Modifier,
    label: String? = null
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(if (label != null) 100.dp else 80.dp)
                .background(
                    color = when (status) {
                        "active" -> Color.Transparent
                        "completed" -> AccentCyan.copy(alpha = 0.2f)
                        else -> CardBg
                    },
                    shape = CircleShape
                )
                .border(
                    width = if (status == "active") 4.dp else 2.dp,
                    color = if (status == "pending") Color.Gray else AccentCyan,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (label != null) {
                Text(label, color = AccentCyan, fontWeight = FontWeight.Bold, fontSize = 24.sp)
            } else {
                Icon(
                    imageVector = if (status == "completed") Icons.Default.Check else Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = if (status == "pending") Color.Gray else AccentCyan,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = title,
            color = White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
