package com.example.dialogfragment.view

import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dialogfragment.R
import java.util.stream.IntStream.range


class GestureSelectComponent: DialogFragment() {

    companion object {
        const val TAG = "MobileLiveFilterSelectComponent"
        fun newInstance(): GestureSelectComponent {
            return GestureSelectComponent()
        }
    }

    private var mRootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        //设置无边框
        setStyle(STYLE_NORMAL, R.style.dialogFullScreen)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(R.layout.gesture_select_layout, container, false)
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //此方法在视图已经创建后返回的，这个view还没有添加到父级中，在此方法进行适配
        initView(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        val params = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;//设置宽度为铺满
        params.gravity = Gravity.BOTTOM //将对话框放到布局下面，也就是屏幕下方
        params.dimAmount = 0.0F//对话框外部的背景设为透明
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
        super.onStart()
    }

    private fun initView(view: View){
        //配置单手比心
        setRecyclerView(view.findViewById<RecyclerView>(R.id.gs_single_hand_gesture),view)
        //配置双手比心
        setRecyclerView(view.findViewById<RecyclerView>(R.id.gs_double_hand_gesture),view)
    }

    private fun setRecyclerView(targetRecyclerView:RecyclerView, view: View){
        //配置单手比心
        val targetLayoutManager = LinearLayoutManager(context)
        targetLayoutManager.orientation = LinearLayoutManager.HORIZONTAL // 设置为水平
        //点击事件
        val targetAdapter = GsPhotoAdapter()
        targetRecyclerView.adapter = targetAdapter
        targetAdapter.setOnItemClickListener(object :OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                //清除其他item的线框
                val itemCount = targetAdapter.itemCount
                for (i in 0 until itemCount){
                    val otherHolder = targetRecyclerView.findViewHolderForAdapterPosition(i)
                    //清除线框
                    otherHolder?.itemView?.findViewById<FrameLayout>(R.id.photo_out_border)?.setBackgroundResource(0)
                }
                val outBorder = view.findViewById<FrameLayout>(R.id.photo_out_border)
                outBorder.setBackgroundResource(R.drawable.gs_choose_bg)
            }
        })
        targetRecyclerView.layoutManager = targetLayoutManager
    }

}