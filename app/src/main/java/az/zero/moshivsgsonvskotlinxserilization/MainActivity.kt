package az.zero.moshivsgsonvskotlinxserilization

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val data = SampleClass("p1", "en")

        /** Gson way */
        val gson = Gson()
        val gsonIn = gson.toJson(data)
        Log.e("Gson", gsonIn)
        val gsonOut = gson.fromJson(jsonString, SampleClass::class.java)
        Log.e("Gson", "$gsonOut")

        /** Moshi way */

        val jsonAdapter = Moshi.Builder().build().adapter(SampleClass::class.java)
        val moshiIn = jsonAdapter.toJson(data)
        Log.e("moshi", moshiIn)
        val moshiOut = jsonAdapter.fromJson(jsonString)
        Log.e("moshi", moshiOut.toString())


        /** Kotlinx serialization way */
        val serIn = Json.encodeToString(SampleClass.serializer(), data)
        Log.e("kotlinx ser", serIn)
        val serOut = Json.decodeFromString<SampleClass>(jsonString)
        Log.e("kotlinx ser", serOut.toString())
    }

    // TODO: Use JsonClass annotation for Moshi and Serializable for Kotlinx serialization
    @JsonClass(generateAdapter = true)
    @Serializable
    data class SampleClass(val name: String, val language: String)

    private val jsonString = "{\"language\":\"en\",\"name\":\"project1\"}"


}

