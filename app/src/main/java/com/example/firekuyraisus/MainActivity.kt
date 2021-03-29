package com.example.firekuyraisus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bclear.setOnClickListener {
            st.setText("")
            sur.setText("")
        }

        bsend.setOnClickListener {
            val Name = st.text.toString()
            val Surname = sur.text.toString()

            val fire = FirebaseDatabase.getInstance()
            val ref = fire.getReference("Employee") //Employee in Line 24 and 27 should be same name
            val id:String? = ref.push().key // id to string

            val Employee = Employee(id.toString(),Name,Surname)
            ref.child(id.toString()).setValue(Employee).addOnCompleteListener{
                Toast.makeText(applicationContext,"Complete", Toast.LENGTH_LONG).show()
                st.setText("")
                sur.setText("")
            }
        }
    }
}