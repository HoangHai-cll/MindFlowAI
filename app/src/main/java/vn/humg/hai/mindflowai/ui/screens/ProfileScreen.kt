package vn.humg.hai.mindflowai.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vn.humg.hai.mindflowai.ui.theme.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        // Avatar with Glow effect
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(AccentCyan.copy(alpha = 0.2f), CircleShape)
                .border(2.dp, AccentCyan, CircleShape)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = Modifier.size(100.dp),
                shape = CircleShape,
                color = AccentCyan.copy(alpha = 0.5f)
            ) {
                // Placeholder icon for user
                Icon(
                    imageVector = androidx.compose.material.icons.Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(60.dp),
                    tint = DarkBg
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Name & Stats
        Text(
            text = "Học viên MindFlow",
            style = MaterialTheme.typography.titleLarge,
            color = White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Cấp độ 12 • 2,450 XP",
            style = MaterialTheme.typography.bodyMedium,
            color = SecondaryText,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Stats Cards Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatCard("24", "BÀI HỌC", modifier = Modifier.weight(1f))
            StatCard("3", "BẢN ĐỒ", modifier = Modifier.weight(1f))
            StatCard("12", "HUY HIỆU", modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun StatCard(value: String, label: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                color = AccentCyan,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = label,
                color = SecondaryText,
                style = MaterialTheme.typography.labelSmall,
                fontSize = 10.sp
            )
        }
    }
}
