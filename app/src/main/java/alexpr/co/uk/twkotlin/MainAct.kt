package alexpr.co.uk.twkotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainAct : AppCompatActivity() {

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.main_act)
        setClickListeners()
    }

    private fun printSomething(message: String) {
        Log.e("alexp", "writing to console... $message")
    }

    private var clickCount: Int = 0

    private fun setClickListeners() {
        findViewById<View>(R.id.main_layout).setOnClickListener {
            Log.e("alexp", "item clicked")
            clickCount++
            findViewById<TextView>(R.id.text_view).setText("Text Clicked $clickCount times")
        }
    }


}
