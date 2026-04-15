package vn.humg.hai.mindflowai.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import vn.humg.hai.mindflowai.data.local.dao.MindMapDao
import vn.humg.hai.mindflowai.data.local.entity.EdgeEntity
import vn.humg.hai.mindflowai.data.local.entity.NodeEntity
import vn.humg.hai.mindflowai.data.local.entity.TopicEntity

@Database(
    entities = [TopicEntity::class, NodeEntity::class, EdgeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mindMapDao(): MindMapDao
}
