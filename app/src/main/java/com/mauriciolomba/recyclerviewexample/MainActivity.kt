package com.mauriciolomba.recyclerviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.mauriciolomba.recyclerviewexample.adapter.CallAdapter
import com.mauriciolomba.recyclerviewexample.data.DataSource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val myDataSet = DataSource().loadAffirmations()
        //val callDataSet = DataSource().loadCalls()
        val callDataSet = DataSource().loadCallsFromCallLog(this)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = CallAdapter(this, callDataSet)

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //recyclerView.setHasFixedSize(true)
    }
}