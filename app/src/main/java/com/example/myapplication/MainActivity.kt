package com.example.myapplication

import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

private const val TAG = "MainActivity_싸피"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.text.setTextColor(getColor(R.color.startColor))
        val parentpaint = binding.text.paint
        val childpaint = binding.childtext.paint

        val parentwidth = parentpaint.measureText(binding.text.text.toString())
        val childwidth = childpaint.measureText(binding.childtext.text.toString())
        Log.d(TAG, "onCreate: $parentwidth ${binding.text.text.toString()}")

        val textShader = LinearGradient(
            0f, 0f, parentwidth, binding.text.textSize,
            intArrayOf(
                getColor(R.color.startColor),
                getColor(R.color.endColor)
            ), arrayOf(0f, 1f).toFloatArray(), Shader.TileMode.CLAMP
        )

        val childtextShader = LinearGradient(
            0f, 0f, parentwidth, binding.text.textSize,
            intArrayOf(
                getColor(R.color.startColor),
                getColor(R.color.endColor)
            ), arrayOf(0f, 1f).toFloatArray(), Shader.TileMode.CLAMP
        )

        binding.text.paint.shader = textShader
        binding.childtext.paint.shader = childtextShader



        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}