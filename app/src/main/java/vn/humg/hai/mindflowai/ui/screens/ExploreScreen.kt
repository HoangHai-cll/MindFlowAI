package vn.humg.hai.mindflowai.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import vn.humg.hai.mindflowai.ui.theme.*
import vn.humg.hai.mindflowai.ui.viewmodel.HomeViewModel
import vn.humg.hai.mindflowai.ui.viewmodel.HomeUiState

data class TopicItem(val title: String, val color: Color, val icon: ImageVector)

@Composable
fun ExploreScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToMap: (Long) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        if (uiState is HomeUiState.Success) {
            onNavigateToMap((uiState as HomeUiState.Success).topicId)
            viewModel.resetState()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBg)
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(text = "Khám phá", style = MaterialTheme.typography.displayLarge, color = White, fontSize = 32.sp)
            Text(text = "Dòng chảy Tri thức", style = MaterialTheme.typography.displayLarge, color = AccentCyan, fontSize = 32.sp)
            Text(
                text = "Dựa trên sở thích của bạn",
                style = MaterialTheme.typography.bodyMedium,
                color = SecondaryText,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            val topics = listOf(
                TopicItem("AI & Machine Learning", TopicBlue, Icons.Default.SmartToy),
                TopicItem("UI/UX Design", TopicPurple, Icons.Default.Palette),
                TopicItem("Data Science", TopicOrange, Icons.Default.BarChart),
                TopicItem("Web Development", TopicGreen, Icons.Default.Language)
            )

            Column {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    TopicCard(topics[0], modifier = Modifier.weight(1f)) { viewModel.generateMindMap(topics[0].title) }
                    TopicCard(topics[1], modifier = Modifier.weight(1f)) { viewModel.generateMindMap(topics[1].title) }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    TopicCard(topics[2], modifier = Modifier.weight(1f)) { viewModel.generateMindMap(topics[2].title) }
                    TopicCard(topics[3], modifier = Modifier.weight(1f)) { viewModel.generateMindMap(topics[3].title) }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
            DailyChallengeCard()
        }

        if (uiState is HomeUiState.Loading) {
            Box(
                modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.7f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = AccentCyan)
            }
        }
        
        if (uiState is HomeUiState.Error) {
            Snackbar(
                modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp),
                action = {
                    TextButton(onClick = { viewModel.resetState() }) { Text("OK", color = AccentCyan) }
                }
            ) {
                Text((uiState as HomeUiState.Error).message)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicCard(topic: TopicItem, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = modifier.height(180.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = topic.color)
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Icon(imageVector = topic.icon, contentDescription = null, tint = White, modifier = Modifier.size(32.dp).align(Alignment.TopStart))
            Text(text = topic.title, color = White, style = MaterialTheme.typography.titleLarge, fontSize = 18.sp, modifier = Modifier.align(Alignment.BottomStart))
        }
    }
}

@Composable
fun DailyChallengeCard() {
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(24.dp), colors = CardDefaults.cardColors(containerColor = CardBg)) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(text = "Thử thách hàng ngày", color = White, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(text = "Hoàn thành 3 bài học để nhận 500 XP", color = SecondaryText, modifier = Modifier.padding(top = 8.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues()
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth().background(Brush.horizontalGradient(listOf(AccentCyan, Color(0xFF34D399))), shape = RoundedCornerShape(12.dp)).padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "THAM GIA NGAY", color = DarkBg, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
