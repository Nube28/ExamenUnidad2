package gomez.alejandro.examen2_gomezalejandro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var adapter: ContactOverviewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var btn_add_contact: Button = findViewById(R.id.btn_add_contact)
        btn_add_contact.setOnClickListener{
            val intent: Intent = Intent(this, AddContactActivity::class.java)
            startActivity(intent)
        }

        var lv_contacts: ListView = findViewById(R.id.lv_contacts)
        adapter = ContactOverviewAdapter(this)
        lv_contacts.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter?.notifyDataSetChanged()
    }
}

class ContactOverviewAdapter: BaseAdapter {
    var context: Context? = null

    constructor(context: Context, ){
        this.context = context
    }

    override fun getCount(): Int {
        return ContactList.getContacts().size
    }

    override fun getItem(position: Int): Any {
        return ContactList.getContacts()[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val contact = ContactList.getContacts()[position]
        val inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflator.inflate(R.layout.contact_overview, null)

        val contactImage = view.findViewById<ImageView>(R.id.contact_iv_overview)
        val contactName = view.findViewById<TextView>(R.id.contact_tv_name_overview)
        val contactCompany = view.findViewById<TextView>(R.id.contact_tv_company_overview)
        val deleteButton = view.findViewById<ImageView>(R.id.btn_delete_contact)

        contactName.text = "${contact.conName} ${contact.conLastName}"
        contactCompany.text = contact.conCompany

        val resourceId = context!!.resources.getIdentifier("ic_${contact.conColor}", "drawable", context!!.packageName)
        contactImage.setImageResource(resourceId)

        deleteButton.setOnClickListener {
            ContactList.removeContact(contact.conPhone)
            notifyDataSetChanged()
            Toast.makeText(context, "Contacto eliminado", Toast.LENGTH_SHORT).show()
        }

        view.setOnClickListener {
            val intent = Intent(context, ContactDetailActivity::class.java)
            intent.putExtra("contactPhone", contact.conPhone)
            context!!.startActivity(intent)
        }

        return view
    }
}