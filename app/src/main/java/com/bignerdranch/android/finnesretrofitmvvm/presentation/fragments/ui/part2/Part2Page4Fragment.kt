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
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentPart2Page4Binding

class Part2Page4Fragment : Fragment() {
    val TAG = "Fragment Part2 Page4"
    private val sharedViewModels: SharedViewModels by activityViewModels()
    lateinit var binding: FragmentPart2Page4Binding

    companion object {
        fun newInstance() = Part2Page4Fragment()
    }

    private lateinit var viewModel: Part2Page4ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_part2_page4, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(Part2Page4ViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPart2Page4Binding.bind(view)

        binding.frag = this@Part2Page4Fragment
        binding.vmod = sharedViewModels

        binding.lifecycleOwner = this

val txtHtml = "<h3>Режим приемов пищи для «жаворонков»<h3><h6>(людей, которые просыпаются, к примеру, в 6.00 часов, а ложатся в 22.00)</h6>" +
        "<lu><li>В 7.00 утра завтракайте</li><li>В 10.00 устройте себе второй легкий завтрак</li><li>В 13.00 отправляйтесь на обед</li>" +
        "<li>В 16.00 время для полдника</li><li>В 19.00 ужинайте</li></lu><br/>" +
        "<h3>Пищевой режим для «сов»</h3>" +
        "<h6>(людей, встающих после 9.00 часов и ложащихся спать около 00.00 ночи)</h6>" +
        "<lu><li>В 10.00 утра завтракайте</li><li>В 13.00 время для ланча</li><li>В 15.00 пора обедать</li>В 17.00 отправляйтесь на полдник</li><li>В 20.00 пора ужинать</li></lu><br/>Таким образом, подстраивайте режим приема пищи под свой распорядок дня.<h3>Главные рекомендации</h3><lu><li>Завтракать следует через один час после подъема</li><li>С утра натощак пейте 250 мл теплой простой воды</li><li>Между любыми приемами пищи выдерживайте время в 2-3 часа</li><li>Садитесь ужинать раньше или не позднее двух часов до сна</li><li>Для правильного похудения необходимо вести учет калорий всех съеденных продуктов. Для этого в этом приложение в блокноте ставьте пометки даже о выпитом объеме воды или сока."

        val spanned: Spanned = HtmlCompat
            .fromHtml(txtHtml,
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        binding.someId.text = spanned

    }

    fun onClickHouse(view: View) {
        findNavController().navigate(R.id.action_part2Page4Fragment_to_part2Page1Fragment)
    }

}