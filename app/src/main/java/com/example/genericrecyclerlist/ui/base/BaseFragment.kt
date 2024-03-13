package com.example.genericrecyclerlist.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.IllegalStateException
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<VM: BaseViewModel, T: ViewDataBinding>: BaseVmFragment<VM>() {
    private var _binding: T? = null
    val mBinding: T get() = _binding ?: throw IllegalStateException("View Data Binding haven't been provided yet")
    private val dialog by lazy { AlertDialog.Builder(requireContext()) }
    abstract fun onBindingCreated(binding: T)
    abstract fun setClickListener()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding  = inflateBindingWithGeneric(inflater, container, false)
        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showLoading(msg: String) {
        dialog
            .setTitle(msg)
            .setMessage(msg)
            .setPositiveButton("OK") { dialog, _ ->
                // Perform action when OK button is clicked
                dialog.dismiss() // Dismiss the dialog
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                // Perform action when Cancel button is clicked
                dialog.dismiss() // Dismiss the dialog
            }
            .show()
            .setOnDismissListener { mViewModel._showLoading.value = "" }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBindingCreated(mBinding)
        mViewModel._showLoading.observe(viewLifecycleOwner) {
            if(it.isNotEmpty()) {
                showLoading(it)
            }
        }
        setClickListener()
    }

    @JvmName("inflateWithGeneric")
    fun <VB : ViewBinding> AppCompatActivity.inflateBindingWithGeneric(layoutInflater: LayoutInflater): VB =
        withGenericBindingClass<VB>(this) { clazz ->
            clazz.getMethod("inflate", LayoutInflater::class.java).invoke(null, layoutInflater) as VB
        }.also { binding ->
            if (binding is ViewDataBinding) {
                binding.lifecycleOwner = this
            }
        }

    @JvmName("inflateWithGeneric")
    fun <VB : ViewBinding> Fragment.inflateBindingWithGeneric(layoutInflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean): VB =
        withGenericBindingClass<VB>(this) { clazz ->
            clazz.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
                .invoke(null, layoutInflater, parent, attachToParent) as VB
        }.also { binding ->
            if (binding is ViewDataBinding) {
                binding.lifecycleOwner = viewLifecycleOwner
            }
        }

    fun < VB : ViewBinding> withGenericBindingClass(any: Any, block: (Class<VB>) -> VB): VB {
        var genericSuperclass = any.javaClass.genericSuperclass
        var superclass = any.javaClass.superclass
        while (superclass != null) {
            if (genericSuperclass is ParameterizedType) {
                try {
                    return block.invoke(genericSuperclass.actualTypeArguments[1] as Class<VB>)
                } catch (e: NoSuchMethodException) {
                    throw NoSuchMethodException()
                } catch (e: ClassCastException) {
                    throw ClassCastException()
                } catch (e: InvocationTargetException) {
                    throw e.targetException
                }
            }
            genericSuperclass = superclass.genericSuperclass
            superclass = superclass.superclass
        }
        throw IllegalArgumentException("There is no generic of ViewBinding.")
    }
}