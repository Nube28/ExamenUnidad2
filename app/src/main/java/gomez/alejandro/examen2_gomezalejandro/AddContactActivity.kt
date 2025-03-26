package gomez.alejandro.examen2_gomezalejandro

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_contact)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etName: EditText = findViewById(R.id.et_name)
        val etLastName: EditText = findViewById(R.id.et_last_name)
        val etPhone: EditText = findViewById(R.id.et_phone)
        val etEmail: EditText = findViewById(R.id.et_email)
        val etCompany: EditText = findViewById(R.id.et_company)
        val spinnerColor: Spinner = findViewById(R.id.spinner_color)

        val colorOptions = arrayOf("green_main", "green_harder", "black", "red")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, colorOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerColor.adapter = adapter

        val btn_save: Button = findViewById(R.id.btn_save)
        btn_save.setOnClickListener{
            val contact = Contact(etName.text.toString(),
                etLastName.text.toString(),
                etCompany.text.toString(),
                etEmail.text.toString(),
                etPhone.text.toString(),
                spinnerColor.selectedItem.toString())

            ContactList.addContact(contact)

            Toast.makeText(this, "Contacto agregado correctamente", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}