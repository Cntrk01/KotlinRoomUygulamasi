package com.example.kotlinroomdatabaseapp.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kotlinroomdatabaseapp.R
import com.example.kotlinroomdatabaseapp.data.User
import com.example.kotlinroomdatabaseapp.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mViewModel : UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_update, container, false)
        mViewModel=ViewModelProvider(this).get(UserViewModel::class.java)

        view.updatepersonAge.setText(args.currentUser.age.toString())
        view.updatepersonName.setText(args.currentUser.firstName)
        view.updatepersonLastName.setText(args.currentUser.lastName)


        view.updateButton.setOnClickListener {
            updatePerson()
        }

        return view
    }
    private fun updatePerson(){
        val name=updatepersonName.text.toString()
        val lastname=updatepersonLastName.text.toString()
        val age=Integer.parseInt(updatepersonAge.text.toString())


        if(inputCheck(name,lastname,updatepersonAge.text)){
            val updateUser=User(args.currentUser.id,name,lastname,age)
            mViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(),"Güncelleme Başarılı",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Alanları Boş Bırakmayın",Toast.LENGTH_SHORT).show()

        }
    }

    private fun inputCheck(name:String,last:String,age:Editable):Boolean{
        return !(TextUtils.isEmpty(name)&&TextUtils.isEmpty(last)&&age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.deleteData){
            deleteData()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteData(){
        val builder=AlertDialog.Builder(requireContext())
        builder.setTitle("Sil ${args.currentUser.firstName}?")
        builder.setMessage("Kullanıcıyı Silmek İstediğinden Emin Misiniz ?")
        builder.setPositiveButton("Evet"){_,_ ->
            mViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),"${args.currentUser.firstName} Kullanıcısı Silindi",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("Hayır"){_,_ ->

        }
        val alertDialog=builder.create()
        alertDialog.show()
    }

}