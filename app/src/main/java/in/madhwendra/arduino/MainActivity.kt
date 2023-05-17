package `in`.madhwendra.arduino

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import `in`.madhwendra.arduino.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()

        binding.gpio26.setOnClickListener {
            if (binding.gpio26.text.toString().equals("ON")) {
                val database = FirebaseDatabase.getInstance()
                val reference = database.getReference("test")
                val newInt1 = 0
                reference.child("int").setValue(newInt1)
                binding.gpio26.text = "OFF"
            } else if (binding.gpio26.text.toString().equals("OFF")) {
                val database = FirebaseDatabase.getInstance()
                val reference = database.getReference("test")
                val newInt1 = 1
                reference.child("int").setValue(newInt1)
                binding.gpio26.text = "ON"
            }

        }
        binding.gpio27.setOnClickListener {
            if (binding.gpio27.text.toString().equals("ON")) {
                val database = FirebaseDatabase.getInstance()
                val reference = database.getReference("test")
                val newInt1 = 0
                reference.child("int2").setValue(newInt1)
                binding.gpio27.text = "OFF"
            } else if (binding.gpio27.text.toString().equals("OFF")) {
                val database = FirebaseDatabase.getInstance()
                val reference = database.getReference("test")
                val newInt1 = 1
                reference.child("int2").setValue(newInt1)
                binding.gpio27.text = "ON"
            }


        }

    }

    private fun getData() {
        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference("test")

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val int1 = dataSnapshot.child("int").getValue(Int::class.java)
                    val int2 = dataSnapshot.child("int2").getValue(Int::class.java)

                    if (int1 != null && int2 != null) {
                        if (int1 == 0) {
                            binding.gpio26.text = "OFF"
                        } else {
                            binding.gpio26.text = "ON"
                        }

                        if (int2 == 0) {
                            binding.gpio27.text = "OFF"
                        } else {
                            binding.gpio27.text = "ON"
                        }


                    }
                } else {

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("Firebase", "Error fetching values: ${databaseError.message}")
            }
        })


    }
}