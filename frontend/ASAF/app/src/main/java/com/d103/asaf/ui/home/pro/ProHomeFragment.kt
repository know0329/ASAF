package com.d103.asaf.ui.home.pro

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.d103.asaf.R
import com.d103.asaf.common.component.CustomToggleButton
import com.d103.asaf.common.config.BaseFragment
import com.d103.asaf.common.model.dto.Accounts_user
import com.d103.asaf.common.model.dto.Attendance
import com.d103.asaf.common.model.dto.Classinfo
import com.d103.asaf.databinding.FragmentProHomeBinding
import java.sql.Date
import java.sql.Time
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProHomeFragment : BaseFragment<FragmentProHomeBinding>(FragmentProHomeBinding::bind, R.layout.fragment_pro_home) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var classInfoList : MutableList<Classinfo> = mutableListOf()
    private var studentInfoList : MutableList<Attendance> = mutableListOf()
    private val testUrl = "data:image/jpeg;base64,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"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToggleButton()
    }


    // 나중에 통신하면 viewModel로 빼야함
    fun initToggleButton(){

        // 반 정보 받아 오기
        classInfoList.add(Classinfo(1, 1,1,1,1))
        classInfoList.add(Classinfo(1, 2,2,3,1))

        // 반 갯수 별로 toggleButton 변경
        when(classInfoList.size){
            1 -> {
                binding.fragmentProHomeCustoomtoggleButton.moneyText.visibility = View.GONE
                binding.fragmentProHomeCustoomtoggleButton.lockerText.visibility = View.GONE
                binding.fragmentProHomeCustoomtoggleButton.seatText.setText("${classInfoList.get(0).classCode} 반")
            }
            2 -> {
                binding.fragmentProHomeCustoomtoggleButton.moneyText.visibility = View.GONE
                binding.fragmentProHomeCustoomtoggleButton.seatText.setText("${classInfoList.get(0).classCode} 반")
                binding.fragmentProHomeCustoomtoggleButton.lockerText.setText("${classInfoList.get(1).classCode} 반")

            }
            3 -> {
                binding.fragmentProHomeCustoomtoggleButton.seatText.setText("${classInfoList.get(0).classCode} 반")
                binding.fragmentProHomeCustoomtoggleButton.lockerText.setText("${classInfoList.get(1).classCode} 반")
                binding.fragmentProHomeCustoomtoggleButton.moneyText.setText("${classInfoList.get(2).classCode} 반")

            }

            0 -> {
                Toast.makeText(requireContext(), "반 정보를 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show()

            }
        }
        binding.fragmentProHomeCustoomtoggleButton.setFirstButtonClickListener {
            studentInfoList.clear()
            //해당 반 학생 출석 불러오기
            var user1 = Accounts_user(2, "Test", "Test@test.com", "test",
                Date(System.currentTimeMillis()) ,
                "010-1234-5678", testUrl, "123", "1","교육생")
            var user2 = Accounts_user(3, "Test", "Test@test.com", "test",
                Date(System.currentTimeMillis()) ,
                "010-1234-5678", "https://i.ytimg.com/vi/5z_Qw5IsJok/maxresdefault.jpg", "123", "1","교육생")

            var user3 = Accounts_user(4, "Test", "Test@test.com", "test",
                Date(System.currentTimeMillis()) ,
                "010-1234-5678", "https://i.namu.wiki/i/gWhK4SHoCDMtxOXunUt29hdDrfTjQU7rfPkgO15xg2WxS75qOQj1oXbk4WZ22O84tLrSA6IrzEIssv_mEIKwuw.webp", "123", "1","교육생")
            var user4 = Accounts_user(5, "Test", "Test@test.com", "test",
                Date(System.currentTimeMillis()) ,
                "010-1234-5678", "https://mblogthumb-phinf.pstatic.net/MjAxODA1MDNfMjQ0/MDAxNTI1MzA5NTYwMDc1.zjtNIKShHMyj7pSbrl9iiGCCVNKpkX3sdmXz0hAEVlIg.ygl01UhsaCHIm3az4GsW7eomkFV2vWqlUl9vlAFz52Qg.JPEG.cookierun_35/%EC%B9%B4%EC%B9%B4%EC%98%A4%ED%94%84%EB%A0%8C%EC%A6%88%EC%BA%90%EB%A6%AD%ED%84%B0.jpg?type=w800", "123", "1","교육생")
            var user5 = Accounts_user(6, "Test", "Test@test.com", "test",
                Date(System.currentTimeMillis()) ,
                "010-1234-5678", "https://photo.coolenjoy.co.kr/data/editor/2203/thumb-b6d6ac28e1a05fa6c43fadae6d3bff3ba5c8e1d4.jpg", "123", "1","교육생")

            studentInfoList.add(Attendance(user1, 1, 2, convertLongToSqlTime(System.currentTimeMillis()), convertLongToSqlTime(System.currentTimeMillis()+1), "출석" ))
            studentInfoList.add(Attendance(user2, 1, 2, convertLongToSqlTime(System.currentTimeMillis()), convertLongToSqlTime(System.currentTimeMillis()+1), "출석" ))
            studentInfoList.add(Attendance(user3, 1, 2, convertLongToSqlTime(System.currentTimeMillis()), convertLongToSqlTime(System.currentTimeMillis()+1), "출석" ))
            studentInfoList.add(Attendance(user4, 1, 2, convertLongToSqlTime(System.currentTimeMillis()), convertLongToSqlTime(System.currentTimeMillis()+1), "출석" ))
            studentInfoList.add(Attendance(user5, 1, 2, convertLongToSqlTime(System.currentTimeMillis()), convertLongToSqlTime(System.currentTimeMillis()+1), "출석" ))

            val adapter = UserInfoAdapter(requireContext())

            binding.fragmentProHomeRecyclerView.adapter = adapter

            adapter.submitList(studentInfoList)

        }

    }

    fun convertLongToSqlTime(time: Long): Time {
        return Time(time)
    }

    override fun onResume() {
        super.onResume()
        initToggleButton()
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProHomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProHomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}