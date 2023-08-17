package com.d103.asaf.ui.bus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.d103.asaf.R
import com.d103.asaf.common.config.ApplicationClass
import com.d103.asaf.databinding.FragmentBusBinding
import com.d103.asaf.databinding.FragmentMapBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BusFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BusFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentBusBinding

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
        binding = FragmentBusBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BusFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BusFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the FrameLayout that will hold the MapFragment
        val mapContainer = view.findViewById<FrameLayout>(R.id.map_view)
        val splashAnim: LottieAnimationView = binding.ongoing
        // Create a new instance of MapFragment
//        val mapFragment = MapFragment()
//
//        // Replace the FrameLayout with MapFragment
//        childFragmentManager.beginTransaction()
//            .replace(R.id.map_view, mapFragment)
//            .commit()

        splashAnim.setAnimation(R.raw.construction)
        splashAnim.playAnimation() // 애니메이션 재생

        binding.fragmentBusMapSeoulBtn.setOnClickListener {
            // 서울 버튼 클릭 시 서울에 마커 추가 로직 실행
            addMarkerToSeoul()
        }

        binding.fragmentBusMapGumiBtn.setOnClickListener {
            addMarkerToGumi()
        }

        // 뒤로가기 눌렸을 때 처리
        val auth = ApplicationClass.sharedPreferences.getString("authority")
        if(auth == "교육생"){
            // Override the default back button behavior
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // Navigate to StudentHomeFragment
                    findNavController().navigate(R.id.action_busFragment_to_StudentHomeFragment)
                }
            })
        }else{
            // Override the default back button behavior
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_busFragment_to_ProHomeFragment)
                }
            })
        }
    }

    // 서울에 마커 추가하는 함수
    private fun addMarkerToSeoul() {

        val mapFragment = childFragmentManager.findFragmentById(R.id.map_view) as? MapFragment
        mapFragment?.addMarkerToSeoul()
    }

    // 구미에 마커 추가하는 함수
    private fun addMarkerToGumi() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_view) as? MapFragment
        mapFragment?.addMarkerToGumi()
    }
}