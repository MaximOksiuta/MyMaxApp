package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.utils.CredentialsCheck

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tiEdEmail.setText(intent.getStringExtra("email"))
        binding.tiEtPassword.setText(intent.getStringExtra("password"))
        binding.btnSignIn.setOnClickListener {
            if (CredentialsCheck.checkEmail(binding.tiEdEmail.text.toString(), binding.tilEmail) &&
                CredentialsCheck.checkPassword(
                    binding.tiEtPassword.text.toString(),
                    binding.tilPassword
                )
            ) {

                var intent = Intent(this, FilmsActivity::class.java)
                startActivity(intent)
            }
        }
        binding.tiEdEmail.addTextChangedListener {
            binding.tilEmail.error = ""
        }
        binding.tiEtPassword.addTextChangedListener{
            binding.tilPassword.error = ""
        }
        binding.tiEdEmail.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                return@setOnEditorActionListener !CredentialsCheck.checkEmail(
                    binding.tiEdEmail.text.toString(),
                    binding.tilEmail
                )
            }
            return@setOnEditorActionListener false
        }
        binding.tiEtPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                return@setOnEditorActionListener !CredentialsCheck.checkPassword(
                    binding.tiEtPassword.text.toString(),
                    binding.tilPassword
                )
            }
            return@setOnEditorActionListener false
        }
        binding.btnGoToSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            intent.putExtra("email", binding.tiEdEmail.text.toString())
            intent.putExtra("password", binding.tiEtPassword.text.toString())
            startActivity(intent)
        }
    }
}