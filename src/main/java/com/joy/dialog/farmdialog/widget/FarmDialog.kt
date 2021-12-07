package com.joy.dialog.farmdialog.widget

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.joy.dialog.farmdialog.R

class FarmDialog {
    var mTitleView: TextView? = null//标题
    var mContentView: TextView? = null//内容
    var mBtnCancel: TextView? = null//取消按钮
    var mBtnConfirm: TextView? = null //确定按钮
    var mDividerView: View? = null//取消和确定的分割线
    private lateinit var mContext: Context
    private var mDialog: Dialog? = null
    private val width = WindowManager.LayoutParams.MATCH_PARENT
    private val height = WindowManager.LayoutParams.WRAP_CONTENT

    constructor(context: Context) {
        mContext = context
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.dialog_farm_layout, null)
        //获取view
        mTitleView = view.findViewById(R.id.title_view)
        mContentView = view.findViewById(R.id.content_view)
        mBtnCancel = view.findViewById(R.id.btn_cancel)
        mBtnConfirm = view.findViewById(R.id.btn_confirm)
        mDividerView = view.findViewById(R.id.divider_view)

        mDialog = Dialog(mContext, R.style.SimpleDialog)
        val win = mDialog?.getWindow()
        win!!.setGravity(Gravity.CENTER)
        mDialog?.setCancelable(true)
        mDialog?.setCanceledOnTouchOutside(true)
        mDialog?.setContentView(view)
        mBtnCancel?.setOnClickListener { dismiss() }
        mDialog?.setOnCancelListener {
            if (null != cancleOnTouchOutSideListenter) {
                cancleOnTouchOutSideListenter?.setCancle()
            }
        }
    }

    //设置标题
    fun setTitle(titleStr: String): FarmDialog {
        mTitleView!!.text = titleStr
        return this
    }

    fun setTitle(titleResId: Int): FarmDialog {
        mTitleView!!.setText(titleResId)
        return this
    }

    fun setTitleColor(colorRes: Int): FarmDialog {
        mTitleView!!.setTextColor(mContext.resources.getColor(colorRes))
        return this
    }

    //设置内容
    fun setContent(contentStr: String): FarmDialog {
        mContentView!!.text = contentStr
        return this
    }

    fun setContent(contentResId: Int): FarmDialog {
        mContentView!!.setText(contentResId)
        return this
    }

    fun setContentColor(colorRes: Int): FarmDialog {
        mContentView!!.setTextColor(mContext.resources.getColor(colorRes))
        return this
    }

    fun setContentGravity(gravity: Int): FarmDialog {
        mContentView!!.gravity = gravity
        return this
    }

    //设置取消的文字
    fun setCancelText(cancelStr: String): FarmDialog {
        mBtnCancel!!.text = cancelStr
        return this
    }

    fun setCancelText(cancelRes: Int): FarmDialog {
        mBtnCancel!!.setText(cancelRes)
        return this
    }

    fun setCancelColor(colorRes: Int): FarmDialog {
        mBtnCancel!!.setTextColor(mContext.resources.getColor(colorRes))
        return this
    }

    //设置确定
    fun setConfirmText(sureStr: String): FarmDialog {
        mBtnConfirm!!.text = sureStr
        return this
    }

    fun setConfirmText(sureRes: Int): FarmDialog {
        mBtnConfirm!!.setText(sureRes)
        return this
    }

    fun setConfirmClickAble(clickAble: Boolean): FarmDialog{
        mBtnConfirm!!.isClickable = clickAble
        return this
    }

    fun setConfirmColor(colorRes: Int): FarmDialog {
        mBtnConfirm!!.setTextColor(mContext.resources.getColor(colorRes))
        return this
    }

    fun show() {
        mDialog!!.show()
        val win = mDialog!!.window
        val params = win!!.attributes
        params.width = width
        params.height = height
        win.attributes = params
    }

    fun setCancelListener(listener: View.OnClickListener): FarmDialog {
        if (listener != null) {
            mBtnCancel!!.setOnClickListener(listener)
        }
        return this
    }

    fun setConfirmListener(listener: View.OnClickListener): FarmDialog {
        if (listener != null) {
            mBtnConfirm!!.setOnClickListener(listener)
        }
        return this
    }

    fun isShow(): Boolean {
        return mDialog!!.isShowing
    }

    fun dismiss() {
        mDialog!!.dismiss()
    }

    fun setCanceledOnTouchOutside(CanceledOnTouchOutside: Boolean): FarmDialog {
        mDialog!!.setCanceledOnTouchOutside(CanceledOnTouchOutside)
        return this
    }

    fun setCanceledAble(cancelAble: Boolean): FarmDialog{
        mDialog!!.setCancelable(cancelAble)
        return this
    }

    fun setCancleOnTouchOutSideListenter(cancleOnTouchOutSideListenter: CancleOnTouchOutSideListenter) {
        this.cancleOnTouchOutSideListenter = cancleOnTouchOutSideListenter
    }

    private var cancleOnTouchOutSideListenter: CancleOnTouchOutSideListenter? = null

    interface CancleOnTouchOutSideListenter {
        fun setCancle()
    }

    fun setCancleVisible(isVisible: Boolean) {
        if (isVisible) {
            mBtnCancel!!.visibility = View.VISIBLE
            mDividerView!!.visibility = View.VISIBLE
        } else {
            mBtnCancel!!.visibility = View.GONE
            mDividerView!!.visibility = View.GONE
        }
    }

    fun setTitleVisible(visible: Boolean) {
        if (visible) {
            mTitleView!!.visibility = View.VISIBLE
        } else {
            mTitleView!!.visibility = View.GONE
        }
    }
}