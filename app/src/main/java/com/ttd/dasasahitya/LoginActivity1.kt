package com.ttd.dasasahitya

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.ConnectionResult

import com.google.android.gms.common.api.GoogleApiClient




open class LoginActivity1 : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var googleApiClient:GoogleApiClient
    var mAuthListener: FirebaseAuth.AuthStateListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail().build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
//        findViewById<Button>(R.id.button).setOnClickListener { signInGoogle() }

//        findViewById<Button>(R.id.normal_sign_in).setOnClickListener {
//            val email = findViewById<EditText>(R.id.email).text.toString()
//            val pass = findViewById<EditText>(R.id.password).text.toString()
//            auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    val user = auth.currentUser
//                    if (user != null) {
//                        val intent = Intent(this@LoginActivity1, MainActivity::class.java)
//
//                        intent.putExtra("email", user.email)
//                        intent.putExtra("name", user.displayName)
//                        startActivity(intent)
//                        finish()
//                    }
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Toast.makeText(baseContext, "Authentication failed.",
//                        Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//        findViewById<Button>(R.id.sign_up).setOnClickListener {
//            val intent = Intent(this@LoginActivity1, RegisterActivity::class.java)
//            startActivity(intent)
//        }
        googleApiClient = GoogleApiClient.Builder(this)
            .enableAutoManage(this, this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()

        mAuthListener = FirebaseAuth.AuthStateListener {
            val user = it.currentUser
            if (user != null) {
                val intent = Intent(this@LoginActivity1, MainActivity::class.java)

                intent.putExtra("email", user.email)
                intent.putExtra("name", user.displayName)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleResults(task)
            }
        }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account: GoogleSignInAccount? = task.result
            if (account != null) {
                updateUI(account)
            }
        } else {
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("email", account.email)
                intent.putExtra("name", account.displayName)
                startActivity(intent)
            } else {
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

            }
        }
    }



    override fun onStart() {
        super.onStart()
        mAuthListener?.let { auth.addAuthStateListener(it) }
    }

    override fun onResume() {
        super.onResume()
        mAuthListener?.let { auth.addAuthStateListener(it) }
    }

    override fun onStop() {
        super.onStop()
        mAuthListener?.let { auth.removeAuthStateListener(it) }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("Not yet implemented")
    }
}