package com.example.kotlinroomdatabaseapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kotlinroomdatabaseapp.R
import com.example.kotlinroomdatabaseapp.data.User
import com.example.kotlinroomdatabaseapp.data.UserViewModel
import kotlinx.android.synthetic.main.custom_row.*
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {
    private lateinit var mUserViewModel:UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_add,container,false)
        mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        view.addButton.setOnClickListener {
            insertToDatabase()
        }
        return view
    }

    private fun insertToDatabase(){
        val firstName=personName.text.toString()
        val lastName=personLastName.text.toString()
        val age=personAge.text

        if(inputCheck(firstName,lastName,age)){
            val user= User(0,firstName,lastName,Integer.parseInt(age.toString()))
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"Kullanıcı Eklendi",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Alanları Boş Bırakmayınız...",Toast.LENGTH_SHORT).show()
        }
    }
    private fun inputCheck(firstName:String,lastName:String,age:Editable):Boolean{
            return !(TextUtils.isEmpty(firstName)&&TextUtils.isEmpty(lastName)&&age.isEmpty())
    }

}