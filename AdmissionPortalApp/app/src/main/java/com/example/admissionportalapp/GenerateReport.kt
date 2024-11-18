package com.example.admissionportalapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GenerateReport : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View=inflater.inflate(R.layout.fragment_generate_report, container, false)
        var floatinActionButton:FloatingActionButton=view.findViewById(R.id.back)
        floatinActionButton.setOnClickListener(){
            parentFragmentManager.beginTransaction().replace(R.id.fragment_container,AdminPageFragment()).addToBackStack(null).commit()
        }
        var textView:TextView=view.findViewById(R.id.textView)
        var text= RecycleAdapter(requireContext(),R.layout.recycle_layout,DBHelper(requireContext(),null).readData("select * from record")).itemCount.toString()
        textView.text=" The total number of student addmited are : ${text}"
        return view
    }

}