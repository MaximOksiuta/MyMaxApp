package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import com.example.myapplication.databinding.ActivitySignUpBinding
import com.example.myapplication.utils.CredentialsCheck

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tiEdEmail.setText(intent.getStringExtra("email"))
        binding.tiEtPassword.setText(intent.getStringExtra("password"))
        binding.tiEdName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT){
                return@setOnEditorActionListener !CredentialsCheck.checkPersonalName(binding.tiEdName.text.toString(), binding.tilName)
            }
            return@setOnEditorActionListener false
        }
        binding.tiEdEmail.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT){
                return@setOnEditorActionListener !CredentialsCheck.checkEmail(binding.tiEdEmail.text.toString(), binding.tilEmail)
            }
            return@setOnEditorActionListener false
        }
        binding.tiEtPassword.setOnEditorActionListener{ _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT){
                return@setOnEditorActionListener !CredentialsCheck.checkPassword(binding.tiEtPassword.text.toString(), binding.tilPassword)
            }
            return@setOnEditorActionListener false
        }
        binding.tiEdRepeatPassword.setOnEditorActionListener{ _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE){
                return@setOnEditorActionListener !CredentialsCheck.overlappingPasswordsCheck(binding.tiEtPassword.text.toString(), binding.tiEdRepeatPassword.text.toString(), binding.tilRepeatPassword)
            }
            return@setOnEditorActionListener false
        }
        binding.btnSignIn.setOnClickListener {
            if (CredentialsCheck.checkPersonalName(binding.tiEdName.text.toString(), binding.tilName)
                && CredentialsCheck.checkEmail(binding.tiEdEmail.text.toString(), binding.tilEmail) &&
                CredentialsCheck.checkPassword(binding.tiEtPassword.text.toString(), binding.tilPassword) &&
                CredentialsCheck.overlappingPasswordsCheck(binding.tiEtPassword.text.toString(), binding.tiEdRepeatPassword.text.toString(), binding.tilRepeatPassword)){
                val intent = Intent(this, FilmsActivity::class.java)
                startActivity(intent)
            }
        }
        binding.btnGoToSignIn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", binding.tiEdEmail.text.toString())
            intent.putExtra("password", binding.tiEtPassword.text.toString())
            startActivity(intent)
        }
        binding.tiEdEmail.addTextChangedListener {
            binding.tilEmail.error = ""
        }
        binding.tiEtPassword.addTextChangedListener{
            binding.tilPassword.error = ""
        }
        binding.tiEdName.addTextChangedListener {
            binding.tilName.error = ""
        }
        binding.tiEdRepeatPassword.addTextChangedListener {
            binding.tilRepeatPassword.error = ""
        }
    }
}