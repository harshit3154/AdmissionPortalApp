package com.example.admissionportalapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ViewApplicant : Fragment() {

    lateinit var recycleView: RecyclerView
    lateinit var rad:RecycleAdapter
    lateinit var list:ArrayList<RecycleData>
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view= inflater.inflate(R.layout.fragment_view_applicant, container, false)
        var db=DBHelper(requireContext(),null)
        recycleView=view.findViewById(R.id.recycle)
        recycleView.setHasFixedSize(true)
        val gm=GridLayoutManager(requireContext(),1)
        recycleView.layoutManager=gm
        rad=RecycleAdapter(requireContext(),R.layout.recycle_layout,db.readData("SELECT * FROM record"))
        try{
            recycleView.adapter=rad
        }catch (e:Exception){

        }
        return view
    }

}