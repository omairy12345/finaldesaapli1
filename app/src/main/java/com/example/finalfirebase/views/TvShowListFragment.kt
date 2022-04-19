package com.example.finalfirebase.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalfirebase.R
import com.example.finalfirebase.databinding.FragmentTvShowListBinding
import com.example.finalfirebase.viewmodel.EpisoDateViewModel
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TvShowListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TvShowListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val binding=FragmentTvShowListBinding.inflate(inflater,container,false)

        binding.tvShowsList.LayoutManager = LinearLayoutManager (requireContext())



        val ViewModel=ViewModelProvider(requireActivity()).get(EpisoDateViewModel::class.java)
        ViewModel.tvShowList.observe(viewLifecycleOwner,{
            binding

            val data=it;

        })
        ViewModel._error.observe(viewLifecycleOwner,{
            Snackbar.make(binding.root,it,Snackbar.LENGTH_SHORT).show()
        })

        ViewModel.loadTvShows()

        return  binding.root

        return inflater.inflate(R.layout.fragment_tv_show_list, container, false)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TvShowListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TvShowListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}