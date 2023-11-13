package br.edu.ifpb.gerasenha

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class PasswordAdapter(var context: Context, var passwdList: ArrayList<Password>) : BaseAdapter(){
    override fun getCount(): Int {
        return this.passwdList.size
    }

    override fun getItem(position: Int): Any {
        return this.passwdList[position]
    }

    override fun getItemId(position: Int): Long {
        return this.passwdList[position].getId().toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = if(convertView == null) {
            //LayoutInflater.from(context).inflate(R.layout., parent, false)
        } else {
            convertView
        }
    }
}
