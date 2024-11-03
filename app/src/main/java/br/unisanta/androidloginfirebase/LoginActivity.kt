package br.unisanta.androidloginfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.unisanta.androidloginfirebase.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        auth = Firebase.auth
        setContentView(binding.root)



        binding.loginBtn.setOnClickListener {

            val email = binding.emailLogin.text.toString()
            val password = binding.passwordLogin.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful)
                    {
                        val user = auth.currentUser
                        Toast.makeText(baseContext, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(baseContext, "Erro ao tentar logar.", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        binding.registerTV.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.forgotPassword.setOnClickListener{

            val email = binding.emailLogin.text.toString()

            Firebase.auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(baseContext,"Email de redefinição de senha enviado!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(baseContext, "Erro ao enviar email.", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}