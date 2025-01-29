package ru.evgenykuzakov.lab18

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvMain: RecyclerView = findViewById(R.id.rvMain)
        val pbUploadData: ProgressBar = findViewById(R.id.pbUploadData)
        val btGetData: Button = findViewById(R.id.btGetData)
        rvMain.adapter = СurrencyAdapter(mutableListOf<CurrencyToRuble>())
        rvMain.visibility = View.GONE

        btGetData.setOnClickListener{
            pbUploadData.visibility = View.VISIBLE
            val scope = CoroutineScope(Dispatchers.IO)
            scope.launch {
                Common.initData()
                withContext(Dispatchers.Main) {
                    pbUploadData.visibility = View.GONE
                    val adapter = СurrencyAdapter(Common.currencies)
                    rvMain.adapter = adapter
                    rvMain.visibility = View.VISIBLE
                }
            }

        }
    }
}