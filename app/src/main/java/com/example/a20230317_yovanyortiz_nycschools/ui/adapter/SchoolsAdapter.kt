package com.example.a20230317_yovanyortiz_nycschools.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a20230317_yovanyortiz_nycschools.data.model.domain.SchoolInfoDomain
import com.example.a20230317_yovanyortiz_nycschools.databinding.ItemSchoolsBinding

class SchoolsAdapter(
    private val schoolsList: MutableList<SchoolInfoDomain> = mutableListOf(),
    private val clickListener: (SchoolInfoDomain) -> Unit
) : RecyclerView.Adapter<SchoolsAdapter.ViewHolder>() {

    fun updateSchools(newSchool: List<SchoolInfoDomain>){
        schoolsList.clear()
        schoolsList.addAll(newSchool)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val items: ItemSchoolsBinding) :
        RecyclerView.ViewHolder(items.root) {
        fun fillInfo(schoolResponse: SchoolInfoDomain, clickListener: (SchoolInfoDomain) -> Unit) {
            items.tvSchoolName.text = schoolResponse.schoolName
            items.tvLatitude.text = "Latitude: ${schoolResponse.latitude} Longitud: ${schoolResponse.longitude}"
            items.tvLocation.text = "Address: ${schoolResponse.location}"
            items.tvPhone.text = "Phone number: ${schoolResponse.phoneNumber}"
            items.tvEmail.text = "Email: ${schoolResponse.schoolEmail}"
            items.tvWebsite.text = "Website: ${schoolResponse.website}"
//            items.tvOverview.text = schoolResponse.overView
            itemView.setOnClickListener {clickListener(schoolResponse)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemSchoolsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int = schoolsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fillInfo(schoolsList[position],clickListener)
    }

}
