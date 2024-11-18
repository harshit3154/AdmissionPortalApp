package com.example.admissionportalapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView

class AdminPageFragment : Fragment() {
    lateinit var gridView:GridView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View?=layoutInflater.inflate(R.layout.fragment_admin_page,container,false)
        gridView=view!!.findViewById(R.id.gridView)
        val list = mutableListOf<CustomAdminData>()
        list.add(CustomAdminData("View Application"))
        list.add(CustomAdminData("Add Application"))
        list.add(CustomAdminData("Edit Admission"))
        list.add(CustomAdminData("Generate Report"))
        list.add(CustomAdminData("delete the recordg"))
        gridView.adapter=GridViewAdapter(requireContext(),list,this)
        return view
    }
}