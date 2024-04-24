package com.example.torch

import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import com.example.torch.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    var state: Boolean = false
    var loop = 1
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.torch.setImageResource(R.drawable.off)

        binding.torch.setOnClickListener {
            if (!state) {
                binding.torch.setImageResource(R.drawable.on)
                state = true
                turnon()
            } else {
                binding.torch.setImageResource(R.drawable.off)
                state = false
                turnoff()
            }
        }
//        binding.sos.setOnClickListener {
//            if (!state) {
//                state = true
//                SOS(state)
//            } else {
//                state = false
//                SOS(state)
//            }
//        }


    }


    private fun turnon() {
        val cameraManager: CameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        var cameraid = cameraManager.cameraIdList[0]
        try {
            cameraManager.setTorchMode(cameraid.toString(), true)
        } catch (e: Exception) {
            Toast.makeText(this, "Error ${e.message.toString()}", Toast.LENGTH_SHORT).show()
        }
        return
    }

    private fun turnoff() {
        val cameraManager: CameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        var cameraid = cameraManager.cameraIdList[0]
        try {
            cameraManager.setTorchMode(cameraid.toString(), false)
        } catch (e: Exception) {
            Toast.makeText(this, "Error ${e.message.toString()}", Toast.LENGTH_SHORT).show()
        }
        return
    }

//    private fun SOS(state: Boolean) {
//        if (state) {
//            binding.sos.setImageResource(R.drawable.soson)
//                turnoff()
//                loop = 1
//                while (loop <= 100) {
//                    turnon()
//                    Thread.sleep(1000)
//                    turnoff()
//                    Thread.sleep(1000)
//                    loop++
//                }
//
//        } else {
//            binding.sos.setImageResource(R.drawable.sos)
//
//            return
//        }
//    }
}