package com.example.admissionportalapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.ByteArrayInputStream
import java.sql.Blob

class RecycleAdapter(var context: Context,var layout1:Int,var l:MutableList<RecycleData>)
    :RecyclerView.Adapter<RecycleAdapter.MyHolder>() {
    class MyHolder(var v: View)
        :RecyclerView.ViewHolder(v){
        var image=v.findViewById<ImageView>(R.id.photo)
        var name=v.findViewById<TextView>(R.id.name)
        var fname=v.findViewById<TextView>(R.id.fname)
        var mname=v.findViewById<TextView>(R.id.mname)
        var course=v.findViewById<TextView>(R.id.course)
        var address=v.findViewById<TextView>(R.id.course)
        var email=v.findViewById<TextView>(R.id.email)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var v:View=LayoutInflater.from(context).inflate(layout1,parent,false)
        return MyHolder(v)
    }

    override fun getItemCount(): Int {
        return l.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.name.text=l[position].name
        holder.fname.text=l[position].fname
        holder.mname.text=l[position].mname
        holder.course.text=l[position].course
        holder.address.text=l[position].address
        holder.email.text=l[position].email
        val imageBlob = l[position].image
        if (imageBlob != null) {
            holder.image.setImageBitmap(imageBlob)
        }

    }
    private fun convertBlobToBitmap(blob: ByteArray): Bitmap {
        val inputStream = ByteArrayInputStream(blob)
        return BitmapFactory.decodeStream(inputStream)
    }
}

class RecycleData(var name:String,var fname:String,var mname:String,var image:Bitmap,var course:String,var address:String,var email:String){
}