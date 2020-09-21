package com.wpfl5.navigationpractice.graph1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.wpfl5.navigationpractice.R


class MainHomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_nav1).setOnClickListener {
            findNavController()
                .navigate(
                    MainHomeFragmentDirections
                        .actionMainHomeFragmentToMainOneFragment2()
                )
        }

        view.findViewById<Button>(R.id.btn_nav2).setOnClickListener {
            findNavController()
                .navigate(
                    MainHomeFragmentDirections
                        .actionMainHomeFragmentToNavGraph2()
                )
        }

        view.findViewById<Button>(R.id.btn_global).setOnClickListener {
            findNavController()
                .navigate(
                    MainHomeFragmentDirections
                        .actionGlobalMainFragment()
                )
        }

    }

}