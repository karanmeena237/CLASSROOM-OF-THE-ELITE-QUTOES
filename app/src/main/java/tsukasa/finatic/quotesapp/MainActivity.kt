package tsukasa.finatic.quotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    private val Episode:TextView
    get()=findViewById(R.id.Episode)

    private val quoteText: TextView
    get()=findViewById(R.id.quoteText)

    private val quoteAuthor:TextView
    get()=findViewById(R.id.quoteAuthor)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel=ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)
        setQuote(mainViewModel.getQuote())
    }

    fun setQuote(quotes: Quotes){
        Episode.text=quotes.episode
        quoteText.text=quotes.text
        quoteAuthor.text = quotes.author
    }

    fun onNext(view: View) {
       setQuote(mainViewModel.nextQuote())
    }
    fun onPrevious(view: View) {
        setQuote((mainViewModel.previousQuote()))
    }
    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,"Author:- ${mainViewModel.getQuote().author}\n"+mainViewModel.getQuote().text+"\n\nApp By Karan")
        startActivity(intent)
    }
}