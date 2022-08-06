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
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentPart2Page6Binding

class Part2Page6Fragment : Fragment() {
    val TAG = "Fragment Part2 Page6"
    private val sharedViewModels: SharedViewModels by activityViewModels()
    lateinit var binding: FragmentPart2Page6Binding

    companion object {
        fun newInstance() = Part2Page6Fragment()
    }

    private lateinit var viewModel: Part2Page6ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_part2_page6, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(Part2Page6ViewModel::class.java)
        // TODO: Use the ViewModel
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPart2Page6Binding.bind(view)

        binding.frag = this@Part2Page6Fragment
        binding.vmod = sharedViewModels

        binding.lifecycleOwner = this
        val txtHtml = "<h3>Нужно придерживаться, чтобы начать худеть:</h3>" +
                "<ul><il>Пьем воду. Каждое утро, сразу после пробуждения, необходимо выпивать стакан воды комнатной температуры.</li>" +
                "<li>Кушаем часто. Питаться нужно согласно принципу дробности. При этом общее количество подходов к столу должно приравниваться к пяти. Соблюдение этого принципа помогает желудку быстрее и качественнее справляться с поступающими в него продуктами.</li>" +
                "<li>Соблюдаем баланс. Нужно употреблять овощи в таком же количестве, что и продукты – источники ненасыщенных жирных кислот. Таковыми являются семечки, орехи, авокадо, растительные масла.</li>" +
                "<li>Углеводы – утром, белки – вечером. Продукты, которые богаты углеводами, нужно потреблять в первой половине дня. В вечернее время следует отдавать предпочтение белковым блюдам.</li>" +
                "<li>Щадящая термическая обработка. Продукты можно отваривать, тушить, запекать и готовить на пару. Жарка под запретом.</li>" +
                "<li>Два литра воды в день – это обязательный ее лимит.</li>" +
                "<li>Упор на медленные углеводы. Они дольше перевариваются, поэтому помогают худеть. В меню должны присутствовать крупы, овощи с низким содержанием сахара, макароны из твердых сортов пшеницы. Сочетать эти продукты с животными и растительными жирами не следует.</li></ul>"
        val spanned: Spanned = HtmlCompat
            .fromHtml(txtHtml,
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        binding.someId.text = spanned

    }

    fun onClickHouse(view: View) {
        findNavController().navigate(R.id.action_part2Page6Fragment_to_part2Page1Fragment)
    }

}