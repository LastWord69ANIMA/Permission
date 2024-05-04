package com.example.permission

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.permission.ui.theme.PermissionTheme
import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 許可ダイアログを表示
        launcher.launch(Manifest.permission.CAMERA)
        setContent {
            PermissionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "カメラ権限が許可されてないよ", Toast.LENGTH_SHORT).show()
            // TODO: - リクエストを送信する処理
            val permissions = arrayOf(Manifest.permission.CAMERA)
            val REQUEST_CODE = 100
            requestPermissions(permissions,REQUEST_CODE)
        } else {
            Toast.makeText(this, "カメラ権限の許可OK", Toast.LENGTH_SHORT).show()
        }
    }
    //パーミッション単体の許可申請ダイヤログを表示
    private val launcher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            // ダイアログの結果で処理を分岐
            if (result) {
                Toast.makeText(this, "許可されました", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this, "否認されました", Toast.LENGTH_SHORT)
                    .show()
            }
        }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PermissionTheme {
        Greeting("Android")
    }
}