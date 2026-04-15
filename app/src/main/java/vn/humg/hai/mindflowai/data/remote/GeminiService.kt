package vn.humg.hai.mindflowai.data.remote

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import vn.humg.hai.mindflowai.data.remote.model.MindMapResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeminiService @Inject constructor(
    private val generativeModel: GenerativeModel,
    private val gson: Gson
) {
    suspend fun generateMindMap(topic: String): MindMapResponse? = withContext(Dispatchers.IO) {
        val prompt = """
            Bạn là một chuyên gia giáo dục và xây dựng bản đồ tri thức. 
            Hãy phân tích chủ đề: "$topic" thành một bản đồ tri thức theo phong cách Micro-learning.
            
            Yêu cầu quan trọng:
            1. Trả về kết quả DUY NHẤT dưới dạng JSON.
            2. JSON phải có cấu trúc:
            {
              "topic": "Tên chủ đề",
              "description": "Mô tả ngắn gọn về lộ trình học này",
              "nodes": [
                {"id": 1, "title": "Tiêu đề node", "content": "Nội dung bài học cực kỳ ngắn gọn (micro-learning) dưới 300 chữ"}
              ],
              "edges": [
                {"from": 1, "to": 2}
              ]
            }
            3. Nội dung bài học (content) phải chất lượng, chia nhỏ kiến thức để người dùng dễ tiếp thu ngay.
            4. Không bao gồm bất kỳ văn bản nào khác ngoài JSON.
        """.trimIndent()

        try {
            val response = generativeModel.generateContent(prompt)
            val jsonText = response.text?.trim()?.let {
                // Loại bỏ markdown code blocks nếu AI trả về (vd: ```json ... ```)
                it.removeSurrounding("```json", "```").removeSurrounding("```")
            }
            
            return@withContext gson.fromJson(jsonText, MindMapResponse::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
