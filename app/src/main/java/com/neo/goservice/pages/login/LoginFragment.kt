package com.neo.goservice.pages.login

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.res.XmlResourceParser
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.*
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.TextView
import com.neo.goservice.AppInjector
import com.neo.goservice.R
import com.neo.goservice.pages.base.InteractionView
import com.neo.goservice.pages.base.OnPageInteractionListener
import com.neo.goservice.utils.MiscUtils
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : InteractionView<OnPageInteractionListener.Primary>(), View.OnFocusChangeListener,
        View.OnClickListener {
    private lateinit var mViewModel: LoginViewModel
    private var mPasswordIsShow: Boolean = false

    companion object {
        fun newInstance(): LoginFragment = LoginFragment()
        private val TAG = LoginFragment::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = AppInjector.obtainViewModel(this)
        mViewModel.initLoginError
        mViewModel.initLoginSuccess
        mViewModel.initLoginProgress
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView_forget_password.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        editText_account.onFocusChangeListener = this
        editText_password.onFocusChangeListener = this
        editText_account.requestFocus()

        if (!MiscUtils.checkContextIsNull(context)) {
            MiscUtils.showSoftInput(context, editText_account)
            val spinnerAdapter: ArrayAdapter<String> = ArrayAdapter(context!!, R.layout.view_lang_dorpdown, R.id.text, LanguageEnum.getTitle(context!!))
            spinner_lang.adapter = spinnerAdapter as SpinnerAdapter?
        }
        imageView_switch_password.setOnClickListener(this)
        button_login.setOnClickListener(this)
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        when (v?.id) {
            R.id.editText_account -> {
                if (!MiscUtils.checkContextIsNull(context)) {
                    text_account.setTextColor(ContextCompat.getColor(context!!, android.R.color.white))
                    text_password.setTextColor(ContextCompat.getColor(context!!, R.color.black30))
                    editText_account.setTextColor(ContextCompat.getColor(context!!, android.R.color.white))
                    editText_password.setTextColor(ContextCompat.getColor(context!!, R.color.black30))
                }
            }
            R.id.editText_password -> {
                if (!MiscUtils.checkContextIsNull(context)) {
                    text_account.setTextColor(ContextCompat.getColor(context!!, R.color.black30))
                    text_password.setTextColor(ContextCompat.getColor(context!!, android.R.color.white))
                    editText_account.setTextColor(ContextCompat.getColor(context!!, R.color.black30))
                    editText_password.setTextColor(ContextCompat.getColor(context!!, android.R.color.white))
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.textView_forget_password -> {
                AlertDialog.Builder(v.context)
                        .setView(R.layout.view_forget_password_dialog)
                        .setPositiveButton(R.string.common_ok, object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                //TODO call forget password API
                            }
                        })
                        .setNegativeButton(R.string.common_cancel, null)
                        .show()
            }
            R.id.imageView_switch_password -> {
                if (mPasswordIsShow) {
                    editText_password.transformationMethod = PasswordTransformationMethod.getInstance()
                    mPasswordIsShow = false
                } else {
                    editText_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    mPasswordIsShow = true
                }
                editText_password.setSelection(editText_password.text.length)
            }
            R.id.button_login -> {
                mViewModel.login(editText_account.text.toString(), editText_password.text.toString())
            }
        }
    }
}