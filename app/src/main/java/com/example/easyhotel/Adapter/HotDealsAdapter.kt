package com.example.easyhotel.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.easyhotel.R
import java.util.*

class HotDealsAdapter(private val hotDeals: List<HotDeal>) :
    RecyclerView.Adapter<HotDealsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var mListener: OnItemClickListener

        interface OnItemClickListener {
            fun onItemClick(position: Int)
        }

        fun setOnItemClickListener(clickListener: OnItemClickListener) {
            mListener = clickListener
        }

        private val hotDealTitle: TextView = itemView.findViewById(R.id.hot_deal_title)
        private val hotDealDescription: TextView = itemView.findViewById(R.id.hot_deal_description)
        private val hotDealExpiryDate: TextView = itemView.findViewById(R.id.hot_deal_expiry_date)

        fun bind(hotDeal: HotDeal) {
            hotDealTitle.text = hotDeal.title
            hotDealDescription.text = hotDeal.description
            hotDealExpiryDate.text = formatDate(hotDeal.expiryDate)
        }

        private fun formatDate(date: Date): String {
            // TODO: Implement date formatting
            return date.toString()
        }

        init {
            itemView.setOnClickListener {
                if (::mListener.isInitialized) {
                    mListener.onItemClick(adapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.hot_deal_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(hotDeals[position])
    }

    override fun getItemCount(): Int {
        return hotDeals.size
    }
}
