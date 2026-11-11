package id.antasari.p04.wargahub.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.antasari.p04.wargahub.R
import id.antasari.p04.wargahub.adapter.LaporanAdapter
import id.antasari.p04.wargahub.data.LaporanContoh
import id.antasari.p04.wargahub.databinding.FragmentListBinding

class ListFragment : Fragment(R.layout.fragment_list) {
    private var _binding: FragmentListBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var contohKosong = false
    private var searchQuery = ""
    private var selectedId = -1L
    private var laporanAdapter: LaporanAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            contohKosong = it.getBoolean(KEY_KOSONG)
            searchQuery = it.getString(KEY_SEARCH, "")
            selectedId = it.getLong(KEY_SELECTED, -1L)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)

        val adapter = LaporanAdapter { laporan ->
            selectedId = laporan.id
            laporanAdapter?.setSelected(selectedId)
            
            val nav = findNavController()
            if (nav.currentDestination?.id == R.id.listFragment) {
                nav.navigate(
                    R.id.action_list_to_detail,
                    bundleOf(DetailFragment.ARG_ID to laporan.id)
                )
            }
        }
        
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        laporanAdapter = adapter
        laporanAdapter?.setSelected(selectedId)

        binding.rvLaporan.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLaporan.adapter = adapter

        // Listener untuk fitur pencarian (Pengayaan)
        binding.etCari.setText(searchQuery)
        binding.etCari.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                searchQuery = s.toString()
                tampilkanDaftar()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.btnModeKosong.setOnClickListener {
            contohKosong = !contohKosong
            tampilkanDaftar()
        }

        tampilkanDaftar()
    }

    private fun tampilkanDaftar() {
        val baseData = if (contohKosong) emptyList() else LaporanContoh.semua
        
        // Pengayaan: Filter data berdasarkan pencarian
        val data = baseData.filter { it.judul.contains(searchQuery, ignoreCase = true) }
        
        laporanAdapter?.submitList(data)

        binding.rvLaporan.isVisible = data.isNotEmpty()
        binding.emptyContainer.isVisible = contohKosong
        // Tampilkan pesan search empty jika data kosong tapi bukan mode "contoh kosong" dan ada text pencarian
        binding.searchEmptyContainer.isVisible = !contohKosong && data.isEmpty() && searchQuery.isNotEmpty()
        
        binding.tvJumlah.text = getString(R.string.list_count, data.size)
        binding.btnModeKosong.setText(
            if (contohKosong) R.string.show_sample else R.string.try_empty
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(KEY_KOSONG, contohKosong)
        outState.putString(KEY_SEARCH, searchQuery)
        outState.putLong(KEY_SELECTED, selectedId)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        binding.rvLaporan.adapter = null
        laporanAdapter = null
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val KEY_KOSONG = "contoh_kosong"
        private const val KEY_SEARCH = "search_query"
        private const val KEY_SELECTED = "selected_id"
    }
}
