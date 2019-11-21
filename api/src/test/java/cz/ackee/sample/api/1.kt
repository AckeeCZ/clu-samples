package cz.ackee.sample.api

import com.squareup.moshi.Moshi
import org.intellij.lang.annotations.Language
import org.junit.Test

class MoshiTest1 {

    data class User(val id: Int, val name: String)

    @Language("json")
    val json = """
        {
          "id": 1,
          "name" : "David"
          ]
        }
        """.trimIndent()

    @Test
    fun `should be ok`() {
        val moshi = Moshi.Builder().build()

        val user = moshi.adapter(User::class.java).fromJson(json)
        println(user)
    }
}