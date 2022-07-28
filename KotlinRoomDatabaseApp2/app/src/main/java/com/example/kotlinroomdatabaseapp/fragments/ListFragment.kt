package com.example.kotlinroomdatabaseapp.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinroomdatabaseapp.R
import com.example.kotlinroomdatabaseapp.adapter.ListAdapter
import com.example.kotlinroomdatabaseapp.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment(),SearchView.OnQueryTextListener {
    private lateinit var mUserViewModel:UserViewModel
    private var recyclerViewAdapter = ListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_list,container,false)

        val recyclerview=view.recyclerView
        recyclerview.adapter=recyclerViewAdapter
        recyclerview.layoutManager=LinearLayoutManager(requireContext())

        mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            recyclerViewAdapter.setData(it)
        })

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        val search=menu?.findItem(R.id.dataSearch)
        val searchView=search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled=true
        searchView?.setOnQueryTextListener(this)
    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query !=null){
            searchDataInDatabase(query)
        }
        return true
    }
    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText !=null){
            searchDataInDatabase(newText)
        }
        return true
    }
    private fun searchDataInDatabase(search:String){
        val searchQuery="$search"
        mUserViewModel.searchData(searchQuery).observe(this, Observer {
            if(it !=null){
                    recyclerViewAdapter.setData(it)
            }
        })
    }


}
