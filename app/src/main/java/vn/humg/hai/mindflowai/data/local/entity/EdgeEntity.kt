package vn.humg.hai.mindflowai.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "edges",
    foreignKeys = [
        ForeignKey(
            entity = NodeEntity::class,
            parentColumns = ["id"],
            childColumns = ["sourceNodeId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = NodeEntity::class,
            parentColumns = ["id"],
            childColumns = ["targetNodeId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["sourceNodeId"]),
        Index(value = ["targetNodeId"])
    ]
)
data class EdgeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val sourceNodeId: Long,
    val targetNodeId: Long
)
