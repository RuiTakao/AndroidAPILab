package com.takaobrog.androidapilab

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val clickListener = View.OnClickListener {
            onItemClick(it)
        }

        view.findViewById<View>(R.id.btnCamera).setOnClickListener(clickListener)
    }

    private fun onItemClick(view: View) {
        when (view.id) {
            R.id.btnCamera -> {
                val intent = Intent().apply {
                    component = ComponentName(
                        "com.takaobrog.camera",
                        "com.takaobrog.camera.CameraActivity"
                    )
                }
                startActivity(intent)
            }
        }
    }
}