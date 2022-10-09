package tsukasa.finatic.quotesapp

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets.UTF_8
import kotlin.text.Charsets.UTF_8

class MainViewModel(val context:Context):ViewModel() {
    private var quoteList:Array<Quotes> = emptyArray()
    private var index = 0
    
    init {
        quoteList = loadQuoteFromAssets()
    }

    private fun loadQuoteFromAssets(): Array<Quotes> {
        //here we have to pass context from mainActivity
        //view can't be passed as this is viewmodel
        //now mainviewmodel me context pass karna hai so we will use factory


        //HOW TO READ A FILE
        //pehle file open then uska size then bytearray of that size named as buffer then read the buffer with inputStream and then close now convert it into string
        val inputStream = context.assets.open("quotesEliteClassroom.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        //now this string will be a json data
        //now converting byte array in string
        val json = String(buffer, Charset.defaultCharset())
        val gson = Gson()
        //now parsing this json string and it's type
        //that's how we typecast it
        return gson.fromJson(json,Array<Quotes>::class.java)
    }


    fun getQuote()=quoteList[index]
    fun nextQuote()=quoteList[++index%12]
    fun previousQuote()=quoteList[--index%12]
}