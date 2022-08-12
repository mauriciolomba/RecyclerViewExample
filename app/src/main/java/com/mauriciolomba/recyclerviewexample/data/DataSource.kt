package com.mauriciolomba.recyclerviewexample.data

import android.content.Context
import android.database.Cursor
import android.provider.CallLog
import android.util.Log
import com.mauriciolomba.recyclerviewexample.R
import com.mauriciolomba.recyclerviewexample.model.Affirmation
import com.mauriciolomba.recyclerviewexample.model.Call

class DataSource {

    fun loadAffirmations() : List<Affirmation>{
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation1),
            Affirmation(R.string.affirmation2),
            Affirmation(R.string.affirmation3),
            Affirmation(R.string.affirmation4),
            Affirmation(R.string.affirmation5),
            Affirmation(R.string.affirmation6),
            Affirmation(R.string.affirmation7),
            Affirmation(R.string.affirmation8),
            Affirmation(R.string.affirmation9),
            Affirmation(R.string.affirmation10)
        )
    }

//    fun loadCalls(): List<Call>{
//        return listOf<Call>(
//            Call("1111-1111", "01:20", "incoming"),
//            Call("2222-2222", "02:00", "incoming"),
//            Call("3333-3333", "00:30", "missed"),
//            Call("4444-4444", "01:35", "outgoing"),
//            Call("5555-5555", "00:47", "incoming"),
//            Call("6666-6666", "03:10", "outgoing"),
//            Call("1111-1111", "01:20", "incoming"),
//            Call("2222-2222", "02:00", "missed"),
//            Call("3333-3333", "00:30", "outgoing"),
//            Call("4444-4444", "01:35", "outgoing"),
//            Call("5555-5555", "00:47", "incoming"),
//            Call("6666-6666", "03:10", "outgoing"),
//            Call("1111-1111", "01:20", "incoming"),
//            Call("2222-2222", "02:00", "incoming"),
//            Call("3333-3333", "00:30", "outgoing"),
//            Call("4444-4444", "01:35", "missed"),
//            Call("5555-5555", "00:47", "incoming"),
//            Call("6666-6666", "03:10", "missed")
//        )
//    }

    fun loadCallsFromCallLog(context : Context) : List<Call>{
        val c : Cursor? = context.contentResolver.query(
            CallLog.Calls.CONTENT_URI,
            arrayOf(
                CallLog.Calls._ID,
                CallLog.Calls.NUMBER,
                CallLog.Calls.DURATION,
                CallLog.Calls.TYPE,
                CallLog.Calls.CACHED_NAME
            ),
            null,
            null,
            null
        )

        val counter : Int = c ?. count ?: 0
        Log.i("DATASOURCE", "counter = $counter")

        val calls: MutableList<Call> = mutableListOf()

        if(c?.moveToFirst() == true){
            do {
                val number = c.getString(c.getColumnIndexOrThrow(CallLog.Calls.NUMBER))
                val duration = c.getInt(c.getColumnIndexOrThrow(CallLog.Calls.DURATION))
                val type = c.getInt(c.getColumnIndexOrThrow(CallLog.Calls.TYPE))
                val name = c.getString(c.getColumnIndexOrThrow(CallLog.Calls.CACHED_NAME))


                val call = Call(number,duration,type,name)
                calls.add(call)

                Log.i("DATASOURCE", "Number: $number - Duration: $duration - Type: $type - Name: $name")
            } while(c.moveToNext())
        }
        c?.close()

        Log.i("DATASOURCE", "Calls: $calls")

        return calls
    }
}