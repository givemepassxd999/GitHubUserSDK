package github.user.aardemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import github.user.sdk.ui.MainActivity


class FirstActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
    }
}