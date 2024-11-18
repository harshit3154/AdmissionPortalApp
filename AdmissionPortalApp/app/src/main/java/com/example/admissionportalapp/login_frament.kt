package com.example.admissionportalapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.replace


class login_frament : Fragment() {
    lateinit var userList:MutableList<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View?=inflater.inflate(R.layout.fragment_login_frament, container, false)
        val user_name=view?.findViewById<EditText>(R.id.etUsername)
        val password=view?.findViewById<EditText>(R.id.etPassword)
        val button=view?.findViewById<Button>(R.id.btnLogin)
        val textView=view?.findViewById<TextView>(R.id.tvSignUp)
        button?.setOnClickListener(){
            if(user_name!!.text.toString().equals("root") && password!!.text.toString().equals("root")){
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container,AdminPageFragment())
                    .addToBackStack(null)
                    .commit()
            }
            else if(DBHelper(requireContext(),null).isUserValid(user_name?.text.toString(),password?.text.toString())){
                if (password != null) {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,UserDetail(user_name.text.toString(),password?.text.toString()))
                        .addToBackStack(null)
                        .commit()
                }
            }else if(user_name?.text.toString().isNotEmpty() && password?.text.toString().isNotEmpty()){
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container,user_fragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
        textView?.setOnClickListener(){
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,user_fragment())
                .addToBackStack(null)
                .commit()
        }
        return view
    }
}