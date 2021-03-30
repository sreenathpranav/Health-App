package com.example.healthapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(),SensorEventListener{

    var running= false
    var sensorManager: SensorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager // type  casting
    }

    override fun onResume() {     //activity_lifecycle
        super.onResume()
        running= true
        var stepsSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)           //type step counter ,?- for avoiding if null

    if(stepsSensor == null)
    {
        Toast.makeText(this,"no step counter sensor",Toast.LENGTH_SHORT).show()
    }
        else{
        sensorManager?.registerListener(this,stepsSensor,SensorManager.SENSOR_DELAY_NORMAL)
           }
    }

    override fun onPause() {
        super.onPause()
        running = false
        sensorManager?.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (running) {
            //update the UI
            val steps: TextView = findViewById(R.id.stepsValue)
            if (event != null) {
                steps.text = ""+ event.values[0]
            }
        }
    }
}