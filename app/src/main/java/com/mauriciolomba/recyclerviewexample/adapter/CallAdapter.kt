package com.mauriciolomba.recyclerviewexample.adapter

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mauriciolomba.recyclerviewexample.R
import com.mauriciolomba.recyclerviewexample.model.Call
import java.text.SimpleDateFormat
import java.util.*

class CallAdapter(
    private val context: Context,
    private val dataset: List<Call>
) : RecyclerView.Adapter<CallAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val numberTextView: TextView = view.findViewById(R.id.call_number)
        val durationTextView: TextView = view.findViewById(R.id.call_duration)
        val callIcon: ImageView = view.findViewById(R.id.call_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_call, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        var timeString: String? = convertSecondsToMinutes(item.duration)

        holder.numberTextView.text = if (TextUtils.isEmpty(item.name)) item.number else item.name

        holder.durationTextView.text = timeString

        holder.callIcon.setImageDrawable(
            when (item.type) {
                1 -> holder.callIcon.resources.getDrawable(R.drawable.ic_incoming_call)
                2 -> holder.callIcon.resources.getDrawable(R.drawable.ic_outgoing_call)
                else -> holder.callIcon.resources.getDrawable(R.drawable.ic_missed_call)
            }
        )
    }

    private fun convertSecondsToMinutes(duration: Int): String? {
        val durationToHours = duration / 3600
        val pattern = if(durationToHours >= 1) "HH:mm:ss" else "mm:ss"

        val date = SimpleDateFormat(pattern, Locale.US).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }

        val dateString = date.format(Date(duration.toLong()*1000))
        Log.i("DATASOURCE", "dateString = $dateString")

        return dateString
    }

    override fun getItemCount(): Int = dataset.size

}