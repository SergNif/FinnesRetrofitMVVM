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
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentMenuDayPart2Binding
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentPart2Page1Binding
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentPart2Page2Binding

class Part2Page2Fragment : Fragment() {
    val TAG = "Fragment Part2 Page2"
    private val sharedViewModels: SharedViewModels by activityViewModels()
    lateinit var binding: FragmentPart2Page2Binding

    companion object {
        fun newInstance() = Part2Page2Fragment()
    }

    private lateinit var viewModel: Part2Page2ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_part2_page2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(Part2Page2ViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPart2Page2Binding.bind(view)

binding.houseButton.setOnClickListener{
    onClickHouse(view = view)
}

        val htmlString : String  = "TextView first line... <br />" +
                "<b>Bold Text</b> | <i>Italic Text</i> and <br/>" +
                "<u>Underlined text</u>"
val htmlStr:String = "<ul><li>\tСочетайте мясо с овощами и фруктами.</li>" +

        "<li>\t  Если сильно хочется, то немного можно сладкого. Но не превышать допустимую норму сахоросодержащих продуктов в сутки — 5 чайных ложек. А лучше вовсе замените сахар на мед. Все десерты можно употреблять только в первой половине дня, чтобы успеть до вечера сжечь полученные калории.</li>" +
        "\n" +
        "<li>\t  Следите за тем, чтобы в ваш организм поступало достаточное количество белка (человеку его требуется не менее 100−150 г в сутки). Белок является строительным материалом, обновляющим клетки и поддерживающим работоспособность мышц. Если вы отказались от мяса и птицы, следует употреблять растительные белки, которые в большом количестве присутствуют в бобовых, орехах и сое.</li>" +
        "\n" +
        "<li>\t  Обходите стороной полуфабрикаты, фастфуд и соусы, а также консервы. Сахар и соль в большом количестве добавляется даже в кетчуп.<li>"
        // spanned is the interface for text that has
        // markup objects attached to ranges of it
        val spanned : Spanned = HtmlCompat
            // HtmlCompat is the backwards compatible version of Html  калории.
            .fromHtml(
                htmlStr, // source
                // flag that separate block-level elements with blank lines
                // (two newline characters) in between
                HtmlCompat.FROM_HTML_MODE_LEGACY // flags
            )

        // finally, show html formatted text in text view  action_part2Page2Fragment_to_part2Page1Fragment
        binding.textDown.text = spanned
    }

    private fun onClickHouse(view: View) {
        findNavController().navigate(R.id.action_part2Page2Fragment_to_part2Page1Fragment)
    }

}