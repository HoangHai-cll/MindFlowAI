package vn.humg.hai.mindflowai.data.remote.model

import com.google.gson.annotations.SerializedName

data class MindMapResponse(
    @SerializedName("topic") val topic: String,
    @SerializedName("description") val description: String,
    @SerializedName("nodes") val nodes: List<NodeResponse>,
    @SerializedName("edges") val edges: List<EdgeResponse>
)

data class NodeResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String
)

data class EdgeResponse(
    @SerializedName("from") val fromId: Long,
    @SerializedName("to") val toId: Long
)
