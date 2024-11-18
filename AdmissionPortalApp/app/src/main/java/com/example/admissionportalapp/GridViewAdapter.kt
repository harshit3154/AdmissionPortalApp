package com.example.admissionportalapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment

class GridViewAdapter(context: Context,
                      var list:MutableList<CustomAdminData>,
    private val parentFragement:Fragment)

    :ArrayAdapter<CustomAdminData>(context,0,list) {
        lateinit var textView: TextView
    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view=LayoutInflater
            .from(context)
            .inflate(R.layout.grid_layout_design,parent,false)
        var mItem=list[position]
        textView=view.findViewById(R.id.content)
        textView.text=mItem.text
        view.setOnClickListener(){
            when(position){
                0->{
                    parentFragement.parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container1,ViewApplicant())
                        .addToBackStack(null)
                        .commit()
                }
                1->{
                    parentFragement.parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container1,user_fragment())
                        .addToBackStack(null)
                        .commit()
                }
                2->{

                }3-> {
                parentFragement.parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container1,GenerateReport())
                    .addToBackStack(null)
                    .commit()

                }4->{
                     DBHelper(context,null).deleteTable(DBHelper(context,null).writableDatabase)
                }
            }
        }
        return view
    }
}