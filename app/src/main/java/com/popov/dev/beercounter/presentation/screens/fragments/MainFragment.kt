package com.popov.dev.beercounter.presentation.screens.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.popov.dev.beercounter.R
import com.popov.dev.beercounter.databinding.FragmentMainBinding
import com.popov.dev.beercounter.presentation.adapters.CountAdapter
import com.popov.dev.beercounter.presentation.viewmodels.MainViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding ?: throw java.lang.RuntimeException("FragmentChooseLevelBinding == null")

    private val countAdapter by lazy {
        CountAdapter()
    }

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.countListLiveData.observe(requireActivity()) {
            countAdapter.submitList(it)
        }

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }
    private fun setupRecyclerVew() {
        val rvCount = binding.rvMain
        rvCount.adapter = countAdapter
        rvCount.recycledViewPool.setMaxRecycledViews(
            CountAdapter.VIEW_TYPE_ENABLED, CountAdapter.MAX_POOL_SIZE
        )
        rvCount.recycledViewPool.setMaxRecycledViews(
            CountAdapter.VIEW_TYPE_DISABLED, CountAdapter.MAX_POOL_SIZE
        )
//        setupLongClickListener()
//        setupClickListener()
        setupSwipeListener(rvCount)
    }
    private fun setupSwipeListener(rvCount: RecyclerView) {
        val callback = object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = countAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteCountItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvCount)
    }
    private fun setupClickListener() {
        countAdapter.onCountItemClickListener = {
//            if (isOnePAneMode()) {
//                val intent = ShopItemActivity.newIntentEditItem(this, it.id)
//                startActivity(intent)
//            } else {
//                launchFragment(ShopItemFragment.newInstanceEditItem(it.id))
//            }
            findNavController().navigate(R.id.action_MainFragment_to_EditFragment)
        }
    }
    companion object{
        private const val TYPE = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}