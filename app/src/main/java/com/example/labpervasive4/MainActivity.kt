package com.example.labpervasive4

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity(),SensorEventListener {

    lateinit var sm  : SensorManager
    lateinit var imageView: ImageView
    var sensor : Sensor? = null
    lateinit var tvData: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.imageView)
        tvData = findViewById(R.id.tvSensorData)
        sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    }

    override fun onResume() {
        super.onResume()
        sensor?.let {
            sm.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
        }

    }

    override fun onPause() {
        super.onPause()
        sm.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER){
            val xyz = event.values
            val x = xyz[0]
            val y = xyz[1]
            val z = xyz[2]
            tvData.text = """
                X = $x
                Y = $y
                Z = $z
            """.trimIndent()
            imageView.x -= x*5
            imageView.y +=  y*5


        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}