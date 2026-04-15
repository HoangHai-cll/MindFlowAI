package vn.humg.hai.mindflowai.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import vn.humg.hai.mindflowai.data.local.entity.EdgeEntity
import vn.humg.hai.mindflowai.data.local.entity.NodeEntity
import vn.humg.hai.mindflowai.data.local.entity.TopicEntity

@Dao
interface MindMapDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopic(topic: TopicEntity): Long

    @Query("SELECT * FROM topics ORDER BY createdAt DESC")
    fun getAllTopics(): Flow<List<TopicEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNode(node: NodeEntity): Long // Thêm hàm này để lấy ID

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNodes(nodes: List<NodeEntity>)

    @Query("SELECT * FROM nodes WHERE topicId = :topicId")
    fun getNodesByTopic(topicId: Long): Flow<List<NodeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEdges(edges: List<EdgeEntity>)

    @Query("SELECT * FROM edges WHERE sourceNodeId IN (SELECT id FROM nodes WHERE topicId = :topicId)")
    fun getEdgesByTopic(topicId: Long): Flow<List<EdgeEntity>>

    @Transaction
    @Query("DELETE FROM topics WHERE id = :topicId")
    suspend fun deleteTopicById(topicId: Long)
}
