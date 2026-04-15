package vn.humg.hai.mindflowai.data.repository

import kotlinx.coroutines.flow.Flow
import vn.humg.hai.mindflowai.data.local.dao.MindMapDao
import vn.humg.hai.mindflowai.data.local.entity.EdgeEntity
import vn.humg.hai.mindflowai.data.local.entity.NodeEntity
import vn.humg.hai.mindflowai.data.local.entity.TopicEntity
import vn.humg.hai.mindflowai.data.remote.GeminiService
import vn.humg.hai.mindflowai.data.remote.model.MindMapResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MindMapRepository @Inject constructor(
    private val mindMapDao: MindMapDao,
    private val geminiService: GeminiService
) {
    fun getAllTopics(): Flow<List<TopicEntity>> = mindMapDao.getAllTopics()

    fun getNodesByTopic(topicId: Long): Flow<List<NodeEntity>> = mindMapDao.getNodesByTopic(topicId)

    fun getEdgesByTopic(topicId: Long): Flow<List<EdgeEntity>> = mindMapDao.getEdgesByTopic(topicId)

    suspend fun createNewMindMap(topicName: String): Result<Long> {
        return try {
            val response = geminiService.generateMindMap(topicName)
                ?: return Result.failure(Exception("AI không phản hồi hoặc dữ liệu sai định dạng"))

            val topicId = saveToDatabase(response)
            Result.success(topicId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun saveToDatabase(response: MindMapResponse): Long {
        val topicId = mindMapDao.insertTopic(
            TopicEntity(
                title = response.topic,
                description = response.description
            )
        )

        val idMapping = mutableMapOf<Long, Long>()

        response.nodes.forEach { nodeResp ->
            val nodeId = mindMapDao.insertNode(
                NodeEntity(
                    topicId = topicId,
                    title = nodeResp.title,
                    content = nodeResp.content
                )
            )
            idMapping[nodeResp.id] = nodeId
        }

        val edges = response.edges.mapNotNull { edgeResp ->
            val sourceId = idMapping[edgeResp.fromId]
            val targetId = idMapping[edgeResp.toId]
            
            if (sourceId != null && targetId != null) {
                EdgeEntity(sourceNodeId = sourceId, targetNodeId = targetId)
            } else null
        }
        
        if (edges.isNotEmpty()) {
            mindMapDao.insertEdges(edges)
        }

        return topicId
    }
}
