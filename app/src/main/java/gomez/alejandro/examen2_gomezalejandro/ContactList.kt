package gomez.alejandro.examen2_gomezalejandro

object ContactList {
    private val contacts = ArrayList(
        listOf(
            Contact("Alejandro", "Gómez", "Microsoft", "alejandro@gmail.com", "1234567890", "green_main"),
            Contact("Natasha", "Ruiz", "Labs Nath", "nath@gmail.com", "0987654321", "green_harder"),
            Contact("Kat", "Cordova", "Oxxo", "joker@gmail.com", "111111111", "red")
        )
    )

    fun addContact(contact: Contact) {
        contacts.add(contact)
    }

    fun removeContact(conPhone: String) {
        contacts.remove(contacts.find { it.conPhone == conPhone })
    }

    fun getContacts(): ArrayList<Contact> {
        return contacts
    }

    fun getContactByPhone(conPhone: String): Contact? {
        return contacts.find { it.conPhone == conPhone }
    }
}
