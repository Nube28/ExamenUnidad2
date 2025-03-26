package gomez.alejandro.examen2_gomezalejandro

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LlamadaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_llamada)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val contactPhone = intent.getStringExtra("contactPhone")

        val contact = contactPhone?.let { ContactList.getContactByPhone(it) }

        if (contact != null) {
            val ivProfile: ImageView = findViewById(R.id.iv_profile)
            val tvName: TextView = findViewById(R.id.tv_name)
            val tvPhone: TextView = findViewById(R.id.tv_phone)
            val btnCall: ImageView = findViewById(R.id.btn_call)

            tvName.text = "${contact.conName} ${contact.conLastName}"
            tvPhone.text = contact.conPhone

            val resourceId = resources.getIdentifier("ic_${contact.conColor}", "drawable", packageName)
            ivProfile.setImageResource(resourceId)

            btnCall.setOnClickListener {
                finish()
            }
        }
    }
}