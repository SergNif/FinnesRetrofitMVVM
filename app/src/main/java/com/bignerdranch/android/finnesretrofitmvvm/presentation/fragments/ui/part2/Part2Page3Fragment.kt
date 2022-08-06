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
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentPart2Page2Binding
import com.bignerdranch.android.finnesretrofitmvvm.databinding.FragmentPart2Page3Binding

class Part2Page3Fragment : Fragment() {
    val TAG = "Fragment Part2 Page3"
    private val sharedViewModels: SharedViewModels by activityViewModels()
    lateinit var binding: FragmentPart2Page3Binding

    companion object {
        fun newInstance() = Part2Page3Fragment()
    }

    private lateinit var viewModel: Part2Page3ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_part2_page3, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(Part2Page3ViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPart2Page3Binding.bind(view)

        binding.frag = this@Part2Page3Fragment
        binding.vmod = sharedViewModels

        binding.lifecycleOwner = this

        val txtHtml = "<h3>Первый день</h3>" +
                "<b>Утренний прием пищи:</b> рис 200 г, сливочное масло 10 г, один банан или одно яблоко, черный кофе.<br/>" +
                "\n<b>Перекус:</b> подсушенный серый хлеб, вареное яйцо, томат.<br/>" +
         "\n<b>Дневной прием пищи:</b> скумбрия на пару 200 г, салат из пекинской капусты с горошком и подсолнечным маслом 180 грамм.<br/>"+
                "\n<b>Второй перекус:</b> нежирный творожок 120 г с ложкой 10% сметаны, зеленое яблоко, 200 мл чая.<br/>" +
                "\n<b>Вечерний прием пищи:</b> отварные овощи 220 г, запеченный кусок говядины 140 г.<br/>" +
                "<h3>Второй день</h3>" +
                "<b>Утренний прием пищи:</b> бутерброд из кусочка цельнозернового хлеба, сливочный творог и пластик огурца, виноград 100 г, чай или кофе с медом.<br/>" +
                "<b>Перекус:</b> творог 50 г с чайной ложкой меда. Дневной прием пищи: бульон мясной 200 г, свежий салат из пекинской капусты с огурцом и томатом, заправленным соком лимонным.<br/>" +
                "<b>Второй перекус:</b> красное яблоко и одно киви, чай зеленый или травяной.<br/>" +
                "<b>Вечерний прием пищи:</b> постная говядина 200 г, два свежих огурца.<br/>" +
                "<h3>Третий день</h3>" +
                "<b>Утренний прием пищи:</b> вареная овсянка без молока — 210 г, ложка меда, авокадо и несладкий кофе.<br/>" +
                "<b>Перекус:</b> орешки кедровые или грецкие 60 г, зеленое яблоко, чай, кружок лимона.<br/>" +
                "<b>Дневной прием пищи:</b> бурый рис 150 г, столько же припущенных овощей.<br/>" +
                "<b>Второй перекус:</b> запеканка из творога, манки, банана 150 г, чай травяной.<br/>" +
                "<b>Вечерний прием пищи:</b> морепродукты очищенные 200 г, два огурца и один томат.<br/>" +
                "<h3>Четвертый день</h3>" +
                "<b>Утренний прием пищи:</b> овсянка на молоке 200 г, свежая малина, ежевика, голубика или клубника — 100 г.<br/>" +
                "<b>Перекус:</b> нежирный несладкий йогурт 100 г, чайная ложка меда и свежесваренный черный кофе.<be />" +
                "<b>Дневной прием пищи:</b> запеченная нежирная рыба 250 г, квашеная капуста 130 г.<br/>" +
                "<b>Второй перекус:</b> салат из помидоров, огурцов, приправленный нежирной сметаной 200 г.<br/>" +
                "<b>Вечерний прием пищи:</b> запеченная курица 200 г без кожуры, посыпанная 30 г пармезана, плюс два огурца.<br/>" +
                "<h3>Пятый день</h3>" +
                "<b>Утренний прием пищи:</b> пюре картофельное на воде 200 г с добавлением 30 г масла сливочного, одно яйцо вареное, один огурец.<br/>" +
                "<b>Перекус:</b> зеленый чай и два киви.<br/>" +
                "<b>Дневной прием пищи:</b> грибной суп с перловкой 260 г, подсушенный ломтик хлеба или сухарики и 10 г сыра.<br/>" +
                "<b>Второй перекус:</b> домашняя запеканка из творога, изюма и йогурта 150 г.<br/>" +
                "<b>Вечерний прием пищи:</b> запеченный хек 200 г и морская капуста 100 г.<br/>" +
                "<h3>Шестой день</h3>" +
                "<b>Утренний прием пищи:</b> взбитый омлет из двух яиц и 150 мл молока, свежесваренный черный кофе.<br/>" +
                "<b>Перекус:</b> грейпфрут или помело.<br/>" +
                "<b>Дневной прием пищи:</b> печеный картофель 150 г с шампиньонами 100 г, куриное мясо запеченное 70 г.<br/>" +
                "<b>Второй перекус:</b> кефир или нежирный питьевой йогурт 200 мл, одно зеленой яблоко.<br/>" +
                "<b>Вечерний прием пищи:</b> нежирный творог 150 г без добавления сахара, два запеченных в духовке яблока.<br/>" +
                "<h3>Седьмой день</h3>" +
                "<b>Утренний прием пищи:</b> пшенная каша на воде 200 г со сливочным маслом 30 г, стакан черного чая без сахара.<br/>" +
                "<b>Второй утренний прием пищи:</b> киви и банан. Дневной прием пищи: овощная припущенная запеканка + 20 г сыра — 250 г, отварное куриное филе — 100 г.<br/>" +
                "<b>Второй перекус:</b> отварные креветки 200 г, морковный или томатный сок 200 мл.<br/>" +
                "<b>Вечерний прием пищи:</b> рыбная котлета на пару 150 г, белый рис отварной 100 г, один томат.<br /?"
        val spanned: Spanned = HtmlCompat
            .fromHtml(txtHtml,
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        binding.someId.text = spanned

    }

    fun onClickHouse(view: View) {
        findNavController().navigate(R.id.action_part2Page3Fragment_to_part2Page1Fragment)
    }

}