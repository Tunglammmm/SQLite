package com.example.sqlite

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sqlite.ui.theme.SQLiteTheme

class MainActivity : ComponentActivity() {
    private lateinit var databaseHelper: SQLdatabaseHelper
    private lateinit var etName: EditText
    private lateinit var etPhone: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var btnView: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = SQLdatabaseHelper(this)
        etName = findViewById(R.id.etName)
        etPhone = findViewById(R.id.etPhone)
        btnAdd = findViewById(R.id.btnAdd)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
        btnView = findViewById(R.id.btnView)
        tvResult = findViewById(R.id.tvResult)

        btnAdd.setOnClickListener {
            val name = etName.text.toString()
            val phone = etPhone.text.toString()
            if (databaseHelper.addContact(name, phone)) {
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show()
            }
        }

        btnUpdate.setOnClickListener {
            val name = etName.text.toString()
            val phone = etPhone.text.toString()
            if (databaseHelper.updateContact(name, phone)) {
                Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show()
            }
        }

        btnDelete.setOnClickListener {
            val name = etName.text.toString()
            if (databaseHelper.deleteContact(name)) {
                Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show()
            }
        }

        btnView.setOnClickListener {
            tvResult.text = databaseHelper.getAllContacts()
        }
    }
}

