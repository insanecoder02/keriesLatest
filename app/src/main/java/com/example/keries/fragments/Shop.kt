package com.example.keries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.keries.R
import com.example.keries.adapter.productAdapter
import com.example.keries.dataClass.productDataClass
import com.google.firebase.firestore.FirebaseFirestore

class Shop : Fragment() {

    private lateinit var productRecyclerView: RecyclerView
    private lateinit var productadapter: productAdapter
    private val db = FirebaseFirestore.getInstance()
    private var productList : MutableList<productDataClass> = mutableListOf()
    private lateinit var toolText : TextView
    private lateinit var logoTool : ImageView
    private lateinit var notifyTool : ImageView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_shop, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView and adapter
        productRecyclerView = view.findViewById(R.id.productreyclerview)
        productadapter = productAdapter(productList, this)

        // Set the layout manager and adapter for the RecyclerView
        productRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        productRecyclerView.adapter = productadapter

        // Initialize SwipeRefreshLayout
        swipeRefreshLayout = view.findViewById(R.id.swiperefreshshop)
        swipeRefreshLayout.setOnRefreshListener {
            fetchFirestoreData()
        }

        // Initial fetch of data
        if (savedInstanceState == null) {
            fetchFirestoreData()
        }
    }

    fun onItemClick(item: productDataClass) {
        // Your item click logic here
    }

    private fun fetchFirestoreData() {
        productList.clear()

        val db = FirebaseFirestore.getInstance()
        db.collection("Merch")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val name = document.getString("name") ?: ""
                    val type = document.getString("type") ?: ""
                    val description = document.getString("desc") ?: ""
                    val prize = document.getString("cost") ?: ""
                    val url = document.getString("url") ?: ""
                    val form = document.getString("form") ?: ""
                    val item = productDataClass(name, type, description, prize, url, form)
                    productList.add(item)
                }

                // Notify the adapter that the data set has changed
                productadapter.notifyDataSetChanged()

                // Stop the refresh animation
                swipeRefreshLayout.isRefreshing = false
            }
            .addOnFailureListener { exception ->
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()

                // Stop the refresh animation in case of failure
                swipeRefreshLayout.isRefreshing = false
            }
    }
}
