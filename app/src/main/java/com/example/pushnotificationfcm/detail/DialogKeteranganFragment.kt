package com.example.pushnotificationfcm.detail

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.viewModels
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pushnotificationfcm.databinding.FragmentDialogKegiatanBinding

class DialogKeteranganFragment : DialogFragment() {

    private var _binding : FragmentDialogKegiatanBinding? = null
    private val binding  get() = _binding!!

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogKegiatanBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            dialog?.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            initUi()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initUi() {
        binding.btnSaveKeterangan.setOnClickListener {
            Toast.makeText(requireContext(),"Terimakasih Planning kegiatan disimpan",Toast.LENGTH_LONG).show()
            dialog?.dismiss()
        }
    }








}