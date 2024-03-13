//package cn.com.vpu.common.view
//
//import android.content.Context
//import android.graphics.Color
//import android.text.Spannable
//import android.text.SpannableString
//import android.text.TextPaint
//import android.text.method.LinkMovementMethod
//import android.text.style.ClickableSpan
//import android.util.AttributeSet
//import android.view.View
//import androidx.annotation.Nullable
//import androidx.appcompat.widget.AppCompatTextView
//import androidx.core.content.ContextCompat
//import com.example.genericrecyclerlist.R
//
//class CustomLinkSpanTextView @JvmOverloads constructor(
//    context: Context,
//    attrs: AttributeSet? = null,
//    defStyleAttr: Int
//) : AppCompatTextView(context, attrs, defStyleAttr) {
//
//    constructor(context: Context) : this(context, null) {}
//    constructor(context: Context, @Nullable attrs: AttributeSet?) : this(context, attrs, 0) {}
//
//    var spannable: CustomClickListener? = null
//    fun setOnClickListener(customClickListener: OnClickListener) {
//        rootView.setOnClickListener(customClickListener)
//    }
//
//    interface CustomClickListener {
//        fun onSpannableTextClick(view: View?)
//        fun onNormalTextClick(view: View?)
//    }
//
//    init {
//        attrs?.let { attrStyle ->
//            val typedArray =
//                context.obtainStyledAttributes(attrStyle, R.styleable.CustomTextViewStyle)
//
//            val highlightedColor =
//                typedArray.getColor(R.styleable.CustomTextViewStyle_highlightedColor, Color.BLACK)
//            val isUnderLined =
//                typedArray.getBoolean(R.styleable.CustomTextViewStyle_isUnderlined, false)
//            val selectedTextRes =
//                typedArray.getString(R.styleable.CustomTextViewStyle_selectedTextRes)
//            val spannableString = SpannableString(selectedTextRes)
//            val indexOfSpan = selectedTextRes?.let { text.indexOf(it) } ?: 0
//            typedArray.recycle()
//
//            if (text.isNotEmpty() && indexOfSpan != -1) {
//                spannableString.setSpan(object : ClickableSpan() {
//                    override fun onClick(view: View) {
//                        return
//                    }
//
//                    override fun updateDrawState(ds: TextPaint) {
//                        ds.isUnderlineText = isUnderLined
//                        if (highlightedColor != -1) {
//                            ds.color = highlightedColor
//                        }
//                    }
//                }, indexOfSpan, indexOfSpan + spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//
//                this.text = spannableString
//                this.movementMethod = LinkMovementMethod.getInstance()
//                this.highlightColor = ContextCompat.getColor(context, R.color.black)
//            }
//        }
//    }
//}