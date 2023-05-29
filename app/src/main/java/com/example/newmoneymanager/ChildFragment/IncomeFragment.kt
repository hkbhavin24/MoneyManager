package com.example.newmoneymanager.ChildFragment

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newmoneymanager.ParentFragment.HomeFragment
import com.example.newmoneymanager.ParentFragment.TransactionFragment
import com.example.newmoneymanager.R
import com.example.newmoneymanager.databinding.FragmentIncomeBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF

class IncomeFragment : Fragment() {
    lateinit var binding: FragmentIncomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIncomeBinding.inflate(layoutInflater)

        var total = 0;
        var income_total = 0;
        var expence_total = 0;
        for (l in TransactionFragment.list) {
            total += l.amount
            if (l.type == 1) {
                income_total += l.amount
            } else if (l.type == 0) {
                expence_total += l.amount
            }
            HomeFragment.binding.balance.text = "${income_total - expence_total}"

        }


        binding.pieChart.apply {
            setUsePercentValues(true)
            getDescription().setEnabled(false)
            setExtraOffsets(5f, 10f, 5f, 5f)
            setDragDecelerationFrictionCoef(0.95f)
            setDrawHoleEnabled(true)
            setHoleColor(Color.WHITE)
            setTransparentCircleColor(Color.WHITE)
            setTransparentCircleAlpha(110)
            setHoleRadius(58f)
            setTransparentCircleRadius(61f)
            setDrawCenterText(true)
            setRotationAngle(0f)
            setRotationEnabled(true)
            setHighlightPerTapEnabled(true)
            animateY(1400)
//            animateY(1400, Easing.EaseInOutQuad)
            legend.isEnabled = false
            setEntryLabelColor(Color.WHITE)
            setEntryLabelTextSize(12f)

            val entries: ArrayList<PieEntry> = ArrayList()
            entries.add(PieEntry(income_total.toFloat(), "Income"))
            entries.add(PieEntry(expence_total.toFloat(), "Expences"))

            val dataSet = PieDataSet(entries, "Android")
            dataSet.setDrawIcons(false)
            dataSet.sliceSpace = 3f
            dataSet.iconsOffset = MPPointF(0f, 40f)
            dataSet.selectionShift = 5f

            val colors: ArrayList<Int> = ArrayList()
            colors.add(resources.getColor(R.color.purple_200))
            colors.add(resources.getColor(R.color.red))

            val data = PieData(dataSet)
            data.setValueFormatter(PercentFormatter())
            data.setValueTextSize(15f)
            data.setValueTypeface(Typeface.DEFAULT_BOLD)
            data.setValueTextColor(Color.WHITE)
            setData(data)

            highlightValues(null)
            invalidate()

        }


        return binding.root
    }
    companion object{
        fun updated() {
            HomeFragment.list.clear()
            HomeFragment.list = HomeFragment.database.showData()
            HomeFragment.adapter.update(HomeFragment.list)
            UpdateTotal()
        }

        fun UpdateTotal() {
            var total = 0;
            var income_total = 0;
            var expence_total = 0;

            for (l in HomeFragment.list) {
                total += l.amount
                if (l.type == 1) {
                    income_total += l.amount
                } else if (l.type == 0) {
                    expence_total += l.amount
                }
            }
        }
    }
}


