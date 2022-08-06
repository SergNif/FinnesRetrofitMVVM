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
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentPart2Page7Binding

class Part2Page7Fragment : Fragment() {
    val TAG = "Fragment Part2 Page7"
    private val sharedViewModels: SharedViewModels by activityViewModels()
    lateinit var binding: FragmentPart2Page7Binding

    companion object {
        fun newInstance() = Part2Page7Fragment()
    }

    private lateinit var viewModel: Part2Page7ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_part2_page7, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(Part2Page7ViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPart2Page7Binding.bind(view)

        binding.frag = this@Part2Page7Fragment
        binding.vmod = sharedViewModels

        binding.lifecycleOwner = this

        val txtHtml = "<ul><li>Срывы на сладкое и мучное (их не стоит исключать совсем, но дозируйте приемы, чтобы не нарушить норму дневного потребления калорий).</li>" +
                "<li>Жареное и копченое. Такая термическая обработка пищи возможна, если жарить без масла, на открытом огне, а коптить не более 20 минут натуральным способом (не искусственным дымом).</li>" +
                "<il>Вареной и печеной еде предпочтите сырые овощи и фрукты, потребляйте максимум зелени всех разновидностей.</li>" +
                "<li>Тяжелый ужин с большими порциями. Мясо или рыбу отварите или потушите, обязательно добавьте свежий овощ (например, 200 г припущенной говядины с одним свежим огурцом).</li>" +
                "<li>Частое употребление алкоголя. Следует избегать, так как он довольно калориен и может спровоцировать сильное чувство голода.</li>" +
                "<li>Во время еды воду пить нельзя. Равно как и чай или сок. Заварите стаканчик чая лишь за час до еды и спустя полчаса после.</li>" +
                "<li>Аккуратнее с солью, приправами и соусами. Все это сильно стимулирует аппетит и может привести к нарушению режима и перееданию.</li>" +
                "<li>Приемы пищи не стоит пропускать. Пусть у вас с собой всегда будет упаковка орешков, вода с лимоном или горсть изюма. Так вы усмирите аппетит и избежите переедания во время отложенного приема пищи.</li></ul>"

        val spanned: Spanned = HtmlCompat
        .fromHtml(txtHtml,
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
    binding.someId.text = spanned

}

fun onClickHouse(view: View) {
    findNavController().navigate(R.id.action_part2Page7Fragment_to_part2Page1Fragment)
}

}