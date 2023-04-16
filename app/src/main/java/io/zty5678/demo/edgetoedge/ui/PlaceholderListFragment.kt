package io.zty5678.demo.edgetoedge.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.grzegorzojdana.spacingitemdecoration.Spacing
import com.grzegorzojdana.spacingitemdecoration.SpacingItemDecoration
import io.zty5678.demo.edgetoedge.MyViewModelFactory
import io.zty5678.demo.edgetoedge.PageViewModel
import io.zty5678.demo.edgetoedge.data.OfflineItemsRepository
import io.zty5678.demo.edgetoedge.data.SentenceCategory
import io.zty5678.demo.edgetoedge.databinding.FragmentPlaceholderListBinding
import io.zty5678.demo.edgetoedge.utils.LogUtils


class PlaceholderListFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentPlaceholderListBinding? = null

    val adapter = HomeListAdapter()

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        pageViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(OfflineItemsRepository(requireContext()))
        ).get(PageViewModel::class.java)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPlaceholderListBinding.inflate(inflater, container, false)
        val root = binding.root

        ViewCompat.setOnApplyWindowInsetsListener(
            binding.root

        ) { v: View?, insetsCompat: WindowInsetsCompat ->

            val insets: Insets = insetsCompat.getInsets(WindowInsetsCompat.Type.systemBars())

            binding.recyclerview.setPadding(0, 0, 0, insets.bottom)
            binding.recyclerview.clipToPadding = false


            insetsCompat
        }


        binding.recyclerview.adapter = adapter

        var categoryStr: String? = arguments?.getString(ARG_CATEGORY);
        val category: SentenceCategory = if (categoryStr != null) {
            SentenceCategory.valueOf(categoryStr)
        } else {
            SentenceCategory.SENTENCE;
        }
        LogUtils.d("categoryStr=$categoryStr, category=$category")


        pageViewModel.loadData(category);


        val verticalSpacing = 30
        val spacing = Spacing(
            0, verticalSpacing,
            Rect(0, verticalSpacing, 0, verticalSpacing),
            Rect(verticalSpacing, 0, verticalSpacing, 0)
        )

        binding.recyclerview.addItemDecoration(SpacingItemDecoration(spacing))

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pageViewModel.chatSentenceList.observe(viewLifecycleOwner, Observer {
            LogUtils.d("observe  it=$it")
            adapter.submitList(it)
        })
    }

    companion object {

        private const val ARG_CATEGORY = "category"

        @JvmStatic
        fun newInstance(category: SentenceCategory): PlaceholderListFragment {
            return PlaceholderListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CATEGORY, category.name)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}