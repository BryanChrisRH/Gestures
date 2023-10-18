package com.example.gestures

import android.gesture.Gesture
import android.gesture.GestureLibraries
import android.gesture.GestureLibrary
import android.gesture.GestureOverlayView
import android.gesture.Prediction
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), GestureOverlayView.OnGesturePerformedListener{
    lateinit var gestureLibrary: GestureLibrary
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniciar()
    }

    fun iniciar(){
        var overlayView=findViewById<GestureOverlayView>(R.id.Gesture)
        gestureLibrary=GestureLibraries.fromRawResource(this,R.raw.gesture)
        if(!gestureLibrary.load()){
            finish()
        }
        overlayView.addOnGesturePerformedListener(this)

    }

    override fun onGesturePerformed(p0: GestureOverlayView?, gesture: Gesture?) {
        val predictions : List<Prediction> = gestureLibrary.recognize(gesture)
        var TvPrediciones= findViewById<TextView>(R.id.textView2)
        TvPrediciones.text=""
        predictions.forEach{
            TvPrediciones.append("${it.name},${it.score}\n")
        }
    }
}