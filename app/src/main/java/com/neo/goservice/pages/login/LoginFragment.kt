package com.neo.goservice.pages.login

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import com.neo.goservice.AppInjector
import com.neo.goservice.R
import com.neo.goservice.constants.Page
import com.neo.goservice.helps.SnackbarHelper
import com.neo.goservice.interfaces.OnViewModelCallbackListener
import com.neo.goservice.pages.base.InteractionView
import com.neo.goservice.pages.base.OnPageInteractionListener
import com.neo.goservice.repository.data.Login
import com.neo.goservice.repository.data.LoginVerify
import com.neo.goservice.repository.data.ReLogin
import com.neo.goservice.repository.remote.StatusCode
import com.neo.goservice.utils.MiscUtils
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : InteractionView<OnPageInteractionListener.Primary>(), View.OnFocusChangeListener,
        View.OnClickListener, OnViewModelCallbackListener {
    private lateinit var mViewModel: LoginViewModel
    private var mPasswordIsShow: Boolean = false

    companion object {
        fun newInstance(): LoginFragment = LoginFragment()
        private val TAG = LoginFragment::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = AppInjector.obtainViewModel(this)
        mViewModel.loginError.observe(this, Observer { onError(it) })
        mViewModel.loginSuccess.observe(this, Observer { onSuccess(it) })
        mViewModel.loginProgress.observe(this, Observer { onProgress(it) })

        mViewModel.tokenVerifyError.observe(this, Observer { onError(it) })
        mViewModel.tokenVerifySuccess.observe(this, Observer { onSuccess(it) })
        mViewModel.tokenVerifyProgress.observe(this, Observer { onProgress(it) })

        mViewModel.reLoginError.observe(this, Observer { onError(it) })
        mViewModel.reLoginSuccess.observe(this, Observer { onSuccess(it) })
        mViewModel.reLoginProgress.observe(this, Observer { onProgress(it) })
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
        //先驗證看token是否還生效
        mViewModel.checkToken()

        editText_account.onFocusChangeListener = this
        editText_password.onFocusChangeListener = this
        editText_account.requestFocus()

        if (!MiscUtils.checkContextIsNull(context)) {
            MiscUtils.showSoftInput(context, editText_account)
            val spinnerAdapter: ArrayAdapter<String> =
                    ArrayAdapter(context!!, R.layout.view_lang_dorpdown, R.id.text, LanguageEnum.getTitle(context!!))
            spinner_lang.adapter = spinnerAdapter
        }
        imageView_switch_password.setOnClickListener(this)
        button_login.setOnClickListener(this)

        //Test account
        editText_account.setText("app01@app.test")
        editText_password.setText("1")
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

    override fun onSuccess(it: Pair<Int, Any?>?) {
        it?.let {
            when (it.first) {
                mViewModel.TAG_LOGIN -> {
                    it.second?.let { it1 ->
                        it1 as Login
                        if (it1.CompanyList != null) {
                            if (it1.CompanyList?.size!! > 1) {//多間公司
                                val items: ArrayList<String> = ArrayList()
                                for (item in it1.CompanyList!!) {
                                    item.company_name?.let { it2 -> items.add(it2) }
                                }

                                var companyId: String = it1.CompanyList!![0].company_id ?: ""
                                AlertDialog.Builder(context)
                                        .setTitle(R.string.login_choose_company_title)
                                        .setSingleChoiceItems(items.toArray(arrayOfNulls(items.size)), 0, object : DialogInterface.OnClickListener {
                                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                                companyId = it1.CompanyList!![which].company_id
                                                        ?: ""
                                            }
                                        })
                                        .setPositiveButton(R.string.common_ok, object : DialogInterface.OnClickListener {
                                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                                mViewModel.reLogin(editText_account.text.toString(), editText_password.text.toString(), companyId)
                                            }
                                        })
                                        .setNegativeButton(R.string.common_cancel, null)
                                        .setCancelable(false)
                                        .show()
                            } else {//單間公司
                                if (!TextUtils.isEmpty(it1.Token)) {
                                    mViewModel.saveToken(it1.Token)
                                    goToHome()
                                } else {
                                    getInteractionListener().showNoticeDialog(getString(R.string.error_undefine), type = SnackbarHelper.TYPE_ERROR)
                                }
                            }
                        } else {
                            getInteractionListener().showNoticeDialog(getString(R.string.error_company_list_null), type = SnackbarHelper.TYPE_ERROR)
                        }
                    }
                }
                mViewModel.TAG_RELOGIN -> {
                    it.second?.let { it1 ->
                        it1 as ReLogin
                        if (!TextUtils.isEmpty(it1.Token)) {
                            mViewModel.saveToken(it1.Token)
                            goToHome()
                        } else {
                            getInteractionListener().showNoticeDialog(getString(R.string.error_undefine), type = SnackbarHelper.TYPE_ERROR)
                        }
                    }
                }
                mViewModel.TAG_TOKEN_VERIFY -> {
                    it.second?.let { it1 ->
                        it1 as LoginVerify
                        if (!TextUtils.isEmpty(it1.StatusCode)) {
                            if (it1.StatusCode.hashCode() == StatusCode.TAG_SUCCESS) {
                                goToHome()
                            } else {
                                Log.d(TAG, "[TAG_TOKEN_VERIFY] it1.StatusCode = ${it1.StatusCode}")
                            }
                        } else {
                            Log.e(TAG, "[TAG_TOKEN_VERIFY] Status code is null")
                        }
                    }
                }
                else -> {

                }
            }

        }
    }

    override fun onError(t: Pair<Int, Throwable?>?) {
    }

    override fun onProgress(b: Pair<Int, Boolean?>?) {
    }

    private fun goToHome() {
        getInteractionListener().switchPage(R.id.fragment_container, Page.HOME, Bundle(), false, true)
    }
}