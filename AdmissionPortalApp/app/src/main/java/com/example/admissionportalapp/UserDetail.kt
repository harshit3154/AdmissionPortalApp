package com.example.admissionportalapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserDetail(var name:String,var password:String ): Fragment() {
    lateinit var list:ArrayList<RecycleData>

    @SuppressLint("CutPasteId", "MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v:View= inflater.inflate(R.layout.fragment_user_detail, container, false)

        var image=v.findViewById<ImageView>(R.id.photou)
        var name=v.findViewById<TextView>(R.id.nameu)
        var fname=v.findViewById<TextView>(R.id.fnameu)
        var mname=v.findViewById<TextView>(R.id.mnameu)
        var course=v.findViewById<TextView>(R.id.courseu)
        var address=v.findViewById<TextView>(R.id.courseu)
        var email=v.findViewById<TextView>(R.id.emailu)

        val dbHelper = DBHelper(requireContext(), null)
        val query = "SELECT * FROM record"

        list = dbHelper.readData(query, name.toString(),password) as ArrayList<RecycleData>

        if(list.isNotEmpty()){
            val imageBlob = list[0].image
            if (imageBlob != null) {
                image.setImageBitmap(imageBlob)
            }
            Toast.makeText(requireContext(),"list is not empyt",Toast.LENGTH_SHORT).show()
            name.text=list[0].name
            fname.text=list[0].fname
            mname.text=list[0].mname
            course.text=list[0].course
            address.text=list[0].address
            email.text=list[0].email
        }
        return v
    }
}