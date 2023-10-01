package com.nxtgenhub.mynda.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nxtgenhub.mynda.databinding.ActivityCreateAccountBinding

class CreateAccountActivity : AppCompatActivity() {

    private var binding: ActivityCreateAccountBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }
}