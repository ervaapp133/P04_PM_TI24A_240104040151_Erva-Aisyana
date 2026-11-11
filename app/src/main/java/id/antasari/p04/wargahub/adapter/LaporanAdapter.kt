package id.antasari.p04.wargahub.adapter

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.antasari.p04.wargahub.R
import id.antasari.p04.wargahub.data.Laporan
import id.antasari.p04.wargahub.databinding.ItemLaporanBinding

class LaporanAdapter(
    private val onItemClick: (Laporan) -> Unit
) : ListAdapter<Laporan, LaporanAdapter.LaporanHolder>(Pembanding) {

    private var selectedId: Long = -1L

    // Pengayaan: Menandai ID yang dipilih
    fun setSelected(id: Long) {
        selectedId = id
        notifyDataSetChanged()
    }

    inner class LaporanHolder(
        private val binding: ItemLaporanBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Laporan, onClick: (Laporan) -> Unit) {
            val context = binding.root.context
            binding.tvJudul.text = item.judul
            binding.tvKategori.text = item.kategori
            binding.tvStatus.text = context.getString(
                R.string.row_status, item.status
            )
            binding.root.contentDescription = context.getString(
                R.string.row_description,
                item.judul, item.kategori, item.status
            )
            
            // Pengayaan: Merubah background untuk item yang terpilih
            if (item.id == selectedId) {
                binding.root.setBackgroundColor(ContextCompat.getColor(context, R.color.p04_selected_bg))
            } else {
                val outValue = TypedValue()
                context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
                binding.root.setBackgroundResource(outValue.resourceId)
            }
            
            binding.root.setOnClickListener { onClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaporanHolder {
        val binding = ItemLaporanBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return LaporanHolder(binding)
    }

    override fun onBindViewHolder(holder: LaporanHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }

    companion object {
        private val Pembanding = object : DiffUtil.ItemCallback<Laporan>() {
            override fun areItemsTheSame(a: Laporan, b: Laporan) = a.id == b.id
            override fun areContentsTheSame(a: Laporan, b: Laporan) = a == b
        }
    }
}
