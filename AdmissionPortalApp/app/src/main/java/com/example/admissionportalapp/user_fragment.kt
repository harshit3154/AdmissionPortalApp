package com.example.admissionportalapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.io.ByteArrayOutputStream

class user_fragment : Fragment() {

    private val REQUEST_CODE_CAMERA = 100
    private lateinit var imageView: ImageView
    private var capturedBitmap: Bitmap? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_fragment, container, false)
        val name: EditText = view.findViewById(R.id.name)
        val fname: EditText = view.findViewById(R.id.fname)
        val mname: EditText = view.findViewById(R.id.mname)
        val address: EditText = view.findViewById(R.id.address)
        val course: EditText = view.findViewById(R.id.course)
        val email:EditText=view.findViewById(R.id.email)
        val password:EditText=view.findViewById(R.id.password)
        imageView = view.findViewById(R.id.image)

        imageView.setOnClickListener {
            var b=AlertDialog.Builder(requireContext())
            b.setIcon(R.drawable.circle_border)
            b.setTitle("Remember to take picture in landscape mode?")
            b.setCancelable(true)
            b.setPositiveButton("Yes"){
                a,b->
                run {
                    openCamera()
                }
            }
            b.setNegativeButton("NO"){
                a,b->{
            }
            }
            b.show()

        }

        val button: Button = view.findViewById(R.id.submit)
        button.setOnClickListener {
            val db = DBHelper(requireContext(), null)

            // Save the bitmap only if it is not null
            capturedBitmap?.let {
                db.addDetails(
                    name.text.toString(),
                    fname.text.toString(),
                    mname.text.toString(),
                    course.text.toString(),
                    address.text.toString(),
                    it,
                    email.text.toString(),
                    password.text.toString()
                )
                Toast.makeText(requireContext(), "Data saved", Toast.LENGTH_SHORT).show()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, AdminPageFragment())
                    .addToBackStack(null)
                    .commit()
            } ?: Toast.makeText(requireContext(), "Please capture an image", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CODE_CAMERA)
    }

    // Handle the result from the camera intent
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            // Get the photo from the camera intent
            val photo: Bitmap? = data?.extras?.get("data") as? Bitmap

            // Check if the photo is not null before setting it
            if (photo != null) {
                // Set the image in the ImageView
                imageView.setImageBitmap(photo)
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP

                // Store the bitmap for later use
                capturedBitmap = photo
            } else {
                Toast.makeText(requireContext(), "Failed to capture image", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
