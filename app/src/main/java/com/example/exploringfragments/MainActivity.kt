package com.example.exploringfragments

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.example.exploringfragments.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val TAG = MainActivity::class.java.simpleName
    private val isBackStackEnabled = AtomicBoolean(false)

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
//        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initObservers()
    }

    fun initObservers() {
        lifecycleScope.launch {
            viewModel.textData.collect {
                addText(it)
            }
        }
    }

    fun addText(text: String) {
        Log.d(TAG, "addText: $text")
        binding.textViewTaskInfo.text =
            "${binding.textViewTaskInfo.text} \n\n $text"
    }

    private fun initView() {
        addFragments()

        binding.backStackSwitch.setOnCheckedChangeListener { _, isChecked ->
            isBackStackEnabled.set(isChecked)
        }
    }

    private fun addFragments() {
        supportFragmentManager.addOnBackStackChangedListener {
            Log.d(TAG, "BackStackCount: ${supportFragmentManager.backStackEntryCount}")
            binding.textViewTaskInfo.text =
                "${binding.textViewTaskInfo.text} \n\n Fragment count in back stack: ${supportFragmentManager.backStackEntryCount}"

            var str = ""
            for (i in supportFragmentManager.backStackEntryCount - 1 downTo 0) {
                val entry = supportFragmentManager.getBackStackEntryAt(i)
                str += "${entry.name}\n"
            }
            binding.textViewTaskInfo.text =
                "${binding.textViewTaskInfo.text} \n BackStackEntry: $str"

            Log.d(TAG, "BackStackEntry: $str")
        }

        binding.btnAddA.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            binding.textViewTaskInfo.text =
                "${binding.textViewTaskInfo.text} \n Add: ${FragmentA::class.java.simpleName}"
            fragmentTransaction.add(binding.fragmentContainer.id, FragmentA())
            if (isBackStackEnabled.get())
                fragmentTransaction.addToBackStack("Fragment A") // top of activity
            fragmentTransaction.commit()
        }

        binding.btnReplA.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            binding.textViewTaskInfo.text =
                "${binding.textViewTaskInfo.text} \n Replace: ${FragmentA::class.java.simpleName}"
            fragmentTransaction.replace(binding.fragmentContainer.id, FragmentA())
            if (isBackStackEnabled.get()) {
                fragmentTransaction.addToBackStack("Fragment A") // top of activity
            }
            fragmentTransaction.commit()
        }

        binding.btnRepB.setOnClickListener {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            binding.textViewTaskInfo.text =
                "${binding.textViewTaskInfo.text} \n Replace: ${FragmentB::class.java.simpleName}"
            fragmentTransaction.replace(binding.fragmentContainer.id, FragmentB())
            if (isBackStackEnabled.get())
                fragmentTransaction.addToBackStack("Fragment B") // top of activity
            fragmentTransaction.commit()
        }

        binding.btnAddB.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            binding.textViewTaskInfo.text =
                "${binding.textViewTaskInfo.text} \n Add: ${FragmentB::class.java.simpleName}"
            fragmentTransaction.add(binding.fragmentContainer.id, FragmentB())
            if (isBackStackEnabled.get())
                fragmentTransaction.addToBackStack("Fragment B") // top of activity
            fragmentTransaction.commit()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.d(TAG, "onSaveInstanceState")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}