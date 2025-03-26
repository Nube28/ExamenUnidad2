package gomez.alejandro.examen2_gomezalejandro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contact_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val contactPhone = intent.getStringExtra("contactPhone")

        val contact = contactPhone?.let { ContactList.getContactByPhone(it) }

        if (contact != null) {
            var ivProfile: ImageView = findViewById(R.id.iv_profile)
            val resourceId =
                resources.getIdentifier("ic_${contact.conColor}", "drawable", packageName)
            ivProfile?.setImageResource(resourceId)

            var tvName: TextView = findViewById(R.id.tv_name)
            tvName.text = "${contact.conName} ${contact.conLastName}"

            var tvCompany: TextView = findViewById(R.id.tv_company)
            tvCompany.text = contact.conCompany

            var tvEmail: TextView = findViewById(R.id.tv_email)
            tvEmail.text = contact.conEmail

            var tvPhone: TextView = findViewById(R.id.tv_phone)
            tvPhone.text = contact.conPhone

            var btnCall: Button = findViewById(R.id.btn_call)
            btnCall.text = "${btnCall.text} ${contact.conName}"
            btnCall.setOnClickListener {
                val intent: Intent = Intent(this, LlamadaActivity::class.java)
                intent.putExtra("contactPhone", contact.conPhone)
                startActivity(intent)
            }

        } else {
            Toast.makeText(this, "Contacto no encontrado", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}