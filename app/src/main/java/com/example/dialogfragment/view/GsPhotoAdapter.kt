package com.example.dialogfragment.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.dialogfragment.R


class GsPhotoAdapter:
    RecyclerView.Adapter<ViewHolder>() {

    //展示数量
    private val limitPhotoCount = 3
    private var mOnItemClickListener:OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.gs_photo_item, parent, false)

        if(itemCount > limitPhotoCount){
            view.layoutParams.width = ( parent.width/(limitPhotoCount+0.5) ).toInt()
        }else{
            view.layoutParams.width = (parent.width/limitPhotoCount)
        }


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                mOnItemClickListener?.onItemClick(it,position)
            }
    }

    override fun getItemCount(): Int {
        return 10
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.mOnItemClickListener = onItemClickListener
    }


}