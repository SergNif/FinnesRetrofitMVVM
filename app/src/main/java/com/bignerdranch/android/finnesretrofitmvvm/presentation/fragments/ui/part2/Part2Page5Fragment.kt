package com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.part2

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Spanned
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.finnesretrofitmvvm.R
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentPart2Page3Binding
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentPart2Page5Binding

class Part2Page5Fragment : Fragment() {
    val TAG = "Fragment Part2 Page5"
    private val sharedViewModels: SharedViewModels by activityViewModels()
    lateinit var binding: FragmentPart2Page5Binding


    companion object {
        fun newInstance() = Part2Page5Fragment()
    }

    private lateinit var viewModel: Part2Page5ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_part2_page5, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(Part2Page5ViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPart2Page5Binding.bind(view)

        binding.frag = this@Part2Page5Fragment
        binding.vmod = sharedViewModels

        binding.lifecycleOwner = this

        val txtHtml = "<h4>Физические упражнения помогают сбросить вес без вреда для здоровья.</h4>" +
                "Быстрый способ похудеть – одновременно заниматься спортом и придерживаться п/п. Физическая нагрузка не только помогает нарастить мышцы и убрать лишний жир, но также улучшить работу сердечно-сосудистой системы:" +
                "<ul><il>Выпады с гантелями – эффективный способ проработать область таза и бедер.</il><il>Приседания. Это отличные упражнения для похудения в борьбе с лишними сантиметрами не только на бедрах, но и талии, т.к. во время движения будут задействованы все группы мышц.</li><li>Укрепление мышц спины. Мало кто знает, что с помощью специальных упражнений для спины можно похудеть. Они способствуют не только сжиганию жира, но и построению крепкого мышечного каркаса.</li><li>Проработка пресса. Упражнения для мышц брюшного пресса помогут сбросить лишний вес и создать стройный силуэт. Получить желанный рельеф в нижней части живота можно, создав нагрузку на этот участок тела. С такой задачей великолепно справляются популярные упражнения «велосипед» и «планка».</li><li>Отжимание. Одно из базовых упражнений, которое рекомендуется включать в каждую тренировку и выполнять регулярно. Такой вид физической нагрузки полезен людям в любом возрасте. Если сочетать отжимания с бегом или интенсивной ходьбой (скандинавской), можно ускорить метаболизм. Вследствие этого будет происходить более быстрая потеря лишних килограммов.</li></ul>"

        val spanned: Spanned = HtmlCompat
            .fromHtml(txtHtml,
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        binding.someId.text = spanned

    }

    fun onClickHouse(view: View) {
        findNavController().navigate(R.id.action_part2Page5Fragment_to_part2Page1Fragment)
    }

}