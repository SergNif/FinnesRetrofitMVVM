package com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.bignerdranch.android.finnesretrofitmvvm.R
import com.bignerdranch.android.finnesretrofitmvvm.data.api.MainApi
import com.bignerdranch.android.finnesretrofitmvvm.data.api.RetroService
import com.bignerdranch.android.finnesretrofitmvvm.data.api.RetrofitInstance
import com.bignerdranch.android.finnesretrofitmvvm.data.repository.MainRepository

import com.bignerdranch.android.finnesretrofitmvvm.data.util.Resource
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.data.*
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.*
import com.bignerdranch.android.finnesretrofitmvvm.domain.usecase.GetUserNameUseCase
import com.bignerdranch.android.finnesretrofitmvvm.domain.usecase.SaveUserNameUseCase
import com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.adapter.ObservableBackground
import com.bignerdranch.android.finnesretrofitmvvm.presentation.util.Constant.Companion.IC_DRAW_1
import com.bignerdranch.android.finnesretrofitmvvm.presentation.util.Constant.Companion.IC_DRAW_1_NO_PRESS
import com.bignerdranch.android.finnesretrofitmvvm.presentation.util.Constant.Companion.IC_DRAW_4
import com.bignerdranch.android.finnesretrofitmvvm.presentation.util.Constant.Companion.IC_DRAW_4_NO_PRESS
import com.bignerdranch.android.finnesretrofitmvvm.presentation.util.Constant.Companion.IC_DRAW_5
import com.bignerdranch.android.finnesretrofitmvvm.presentation.util.Constant.Companion.IC_DRAW_5_NO_PRESS
import com.google.gson.Gson

import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.*

class MainViewModel(
    app: Application,
    val mainRepository: MainRepository,
    private val getUserNameUseCase: GetUserNameUseCase,
    private val saveUserNameUseCase: SaveUserNameUseCase,

//    private val sharedPreferences: SharedPrefUserStorage
) : AndroidViewModel(app) {
    lateinit var usersData: UsersData
    lateinit var dataUser: User// = User()
    fun isDataUserInitialized() = ::dataUser.isInitialized
    var ic_fon_1: Int = IC_DRAW_1_NO_PRESS//  R.drawable.ic_male_female_button_no_press
    var ic_fon_1_no_press: Int = IC_DRAW_1 // R.drawable.ic_male_female_button
    var ic_fon_4: Int = IC_DRAW_4_NO_PRESS  // R.drawable.ic_fon_text_page_4_no_press
    var ic_fon_4_no_press: Int = IC_DRAW_4 // R.drawable.ic_fon_text_page_4
    var ic_fon_5: Int = IC_DRAW_5  // R.drawable.ic_button_text_fon_page5
    var ic_fon_5_no_press: Int = IC_DRAW_5_NO_PRESS  // R.drawable.ic_button_text_fon_page5_no_press

    //    @Bindable
//    lateinit var dataPage1: DataPage1
    var dataPage1 = DataPage1()//(woman = false, man = false)

    //    lateinit var datap: DataPage1
    lateinit var mail: String

    //    lateinit var dataPage3: DataPage3
    var dataPage3: DataPage3 = DataPage3()

    var dataPage4 = DataPage4()//  (minimalPhysicalActive = false,
//        fastWalkOnFoot = false,
//    examine1_2TimesWeek = false,
//    examine3_5TimesWeek = false,
//    everyDayFitness = false)//false,false,false,false,Date(),0)
//    lateinit var dataPage5: DataPage5

    var dataPage5: DataPage5 = DataPage5()

    //    lateinit var dataPage6: DataPage6
    var dataPage6: DataPage6 = DataPage6()

    //    lateinit var dataPage7: DataPage7
    var dataPage7: DataPage7 = DataPage7()

    //    lateinit var dataPage8: DataPage8
    var dataPage8: DataPage8 = DataPage8()

    //    lateinit var dataPage9: DataPage9
    var dataPage9: DataPage9 = DataPage9()

    //    lateinit var dataPage10: DataPage10
    var dataPage10: DataPage10 = DataPage10()

    var createNewUserLiveData: MutableLiveData<UsersData?> = MutableLiveData()
//    val liveCreateNewUserLiveData: LiveData<UsersData>
//        get() = createNewUserLiveData

    var createPostQueryNewUserLiveData: MutableLiveData<Resource<UsersData>> = MutableLiveData()
    val searchDataUse: MutableLiveData<Resource<UsersData>> = MutableLiveData()
    var clock = Clock.systemUTC()
    val TAG = "ViewModel"

    var backgr = ObservableBackground()
    var backgro = ObservableBackground()
    fun setDrawableResource() {
        backgr.setDrawableResource(R.drawable.ic_button_text_fon_page5)
        backgro.setDrawableResource(R.drawable.ic_button_text_fon_page5_no_press)
    }


//    @BindingAdapter("android:background")
//    fun setBackground(view: View, drawable: Drawable?) {
//        if (dataPage1.woman){view.background = IC_DRAW_1}
//    }

    //UsersData
    private var mutableUsersData = MutableLiveData<UsersData>()
    val liveUsersData: LiveData<UsersData>
        get() = mutableUsersData

    //RegisterPage
    private val fullNameEmailRegister = MutableLiveData<String>()
    val livefullNameEmailRegister: LiveData<String>
        get() = fullNameEmailRegister

    var registrOrLogin = MutableLiveData<String>()

    val btnPage4 = ic_fon_4
    val btnPage4NoPress = ic_fon_4_no_press
    val btnPage5 = ic_fon_5
    val btnPage5NoPress = ic_fon_5_no_press

    private val emailRegister = MutableLiveData<String>()
    val liveemailRegister: LiveData<String>
        get() = emailRegister

    private val passwordRegister = MutableLiveData<String>()
    val livepasswordRegister: LiveData<String>
        get() = passwordRegister

    //LoginPage1

    private var idUsersData = MutableLiveData<Int>()
    val liveIdUsersData: MutableLiveData<Int>
        get() = idUsersData

    private val emailLogin = MutableLiveData<String>()
    val liveEmailLogin: LiveData<String>
        get() = emailLogin

    private val passwordLogin = MutableLiveData<String>()
    val livePasswordLogin: LiveData<String>
        get() = passwordLogin

    //page1
    private val colorPressed = MutableLiveData<Int>()
    val liveColorPressed: LiveData<Int>
        get() = colorPressed

    private var colorNoPressed = MutableLiveData<Int>()
    val liveColorNoPressed: LiveData<Int>
        get() = colorNoPressed

    //page3
    private var ageDataPage3 = MutableLiveData<String>()
    val liveAgeDataPage3: LiveData<String>
        get() = ageDataPage3

    private var heightDataPage3 = MutableLiveData<String>()
    val liveHeightDataPage3: LiveData<String>
        get() = heightDataPage3

    private var weightDataPage3 = MutableLiveData<String>()
    val liveWeighDataPage3: LiveData<String>
        get() = weightDataPage3

    private var diceWeightDataPage3 = MutableLiveData<String>()
    val liveDiceWeightDataPage3: LiveData<String>
        get() = diceWeightDataPage3

    //page4
    private var Button1 = MutableLiveData<Int>()
    val liveButton1: LiveData<Int>
        get() = Button1
    private var Button2 = MutableLiveData<Int>()
    val liveButton2: LiveData<Int>
        get() = Button2
    private var Button3 = MutableLiveData<Int>()
    val liveButton3: LiveData<Int>
        get() = Button3
    private var Button4 = MutableLiveData<Int>()
    val liveButton4: LiveData<Int>
        get() = Button4
    private var Button5 = MutableLiveData<Int>()
    val liveButton5: LiveData<Int>
        get() = Button5

    //page5
    private var page5Button1 = MutableLiveData<Int>()
    val livepage5Button1: LiveData<Int>
        get() = page5Button1
    private var page5Button2 = MutableLiveData<Int>()
    val livepage5Button2: LiveData<Int>
        get() = page5Button2
    private var page5Button3 = MutableLiveData<Int>()
    val livepage5Button3: LiveData<Int>
        get() = page5Button3
    private var page5Button4 = MutableLiveData<Int>()
    val livepage5Button4: LiveData<Int>
        get() = page5Button4
    private var page5Button5 = MutableLiveData<Int>()
    val livepage5Button5: LiveData<Int>
        get() = page5Button5
    private var page5Button6 = MutableLiveData<Int>()
    val livepage5Button6: LiveData<Int>
        get() = page5Button6
    private var page5Button7 = MutableLiveData<Int>()
    val livepage5Button7: LiveData<Int>
        get() = page5Button7
    private var page5Button8 = MutableLiveData<Int>()
    val livepage5Button8: LiveData<Int>
        get() = page5Button8

    //page6
    private var page6Button1 = MutableLiveData<Int>()
    val livepage6Button1: LiveData<Int>
        get() = page6Button1
    private var page6Button2 = MutableLiveData<Int>()
    val livepage6Button2: LiveData<Int>
        get() = page6Button2
    private var page6Button3 = MutableLiveData<Int>()
    val livepage6Button3: LiveData<Int>
        get() = page6Button3
    private var page6Button4 = MutableLiveData<Int>()
    val livepage6Button4: LiveData<Int>
        get() = page6Button4
    private var page6Button5 = MutableLiveData<Int>()
    val livepage6Button5: LiveData<Int>
        get() = page6Button5
    private var page6Button6 = MutableLiveData<Int>()
    val livepage6Button6: LiveData<Int>
        get() = page6Button6
    private var page6Button7 = MutableLiveData<Int>()
    val livepage6Button7: LiveData<Int>
        get() = page6Button7
    private var page6Button8 = MutableLiveData<Int>()
    val livepage6Button8: LiveData<Int>
        get() = page6Button8

    //page7
    private var page7Button1 = MutableLiveData<Int>()
    val livepage7Button1: LiveData<Int>
        get() = page7Button1
    private var page7Button2 = MutableLiveData<Int>()
    val livepage7Button2: LiveData<Int>
        get() = page7Button2
    private var page7Button3 = MutableLiveData<Int>()
    val livepage7Button3: LiveData<Int>
        get() = page7Button3
    private var page7Button4 = MutableLiveData<Int>()
    val livepage7Button4: LiveData<Int>
        get() = page7Button4
    private var page7Button5 = MutableLiveData<Int>()
    val livepage7Button5: LiveData<Int>
        get() = page7Button5
    private var page7Button6 = MutableLiveData<Int>()
    val livepage7Button6: LiveData<Int>
        get() = page7Button6

    //page8
    private var page8Button1 = MutableLiveData<Int>()
    val livepage8Button1: LiveData<Int>
        get() = page8Button1
    private var page8Button2 = MutableLiveData<Int>()
    val livepage8Button2: LiveData<Int>
        get() = page8Button2
    private var page8Button3 = MutableLiveData<Int>()
    val livepage8Button3: LiveData<Int>
        get() = page8Button3
    private var page8Button4 = MutableLiveData<Int>()
    val livepage8Button4: LiveData<Int>
        get() = page8Button4

    //page9
    private var page9Button1 = MutableLiveData<Int>()
    val livepage9Button1: LiveData<Int>
        get() = page9Button1
    private var page9Button2 = MutableLiveData<Int>()
    val livepage9Button2: LiveData<Int>
        get() = page9Button2
    private var page9Button3 = MutableLiveData<Int>()
    val livepage9Button3: LiveData<Int>
        get() = page9Button3
    private var page9Button4 = MutableLiveData<Int>()
    val livepage9Button4: LiveData<Int>
        get() = page9Button4

    //page10
    private var page10Button1 = MutableLiveData<Int>()
    val livepage10Button1: LiveData<Int>
        get() = page10Button1
    private var page10Button2 = MutableLiveData<Int>()
    val livepage10Button2: LiveData<Int>
        get() = page10Button2
    private var page10Button3 = MutableLiveData<Int>()
    val livepage10Button3: LiveData<Int>
        get() = page10Button3
    private var page10Button4 = MutableLiveData<Int>()
    val livepage10Button4: LiveData<Int>
        get() = page10Button4


    //page1
    fun clickBoy() {
        dataPage1 = DataPage1(woman = false, man = true)

        dataPage1.fitness_id = dataPage1.id
//        colorNoPressed.value = ic_fon_1
//        colorPressed.value = ic_fon_1_no_press
    }

    fun clickGirl() {
        dataPage1 = DataPage1(woman = true, man = false)
        dataPage1.fitness_id = dataPage1.id
//        colorPressed.value = ic_fon_1
//        colorNoPressed.value = ic_fon_1_no_press
    }

    //page3
    fun changeHight(text: String) {
//        dataPage3 = DataPage3(height = text.toString().toInt())
        dataPage3.height = text.toInt()
        dataPage3.date = LocalDateTime.now().toString().split("T")[0]
        dataPage3.fitness_id = dataPage3.id
        heightDataPage3.value = text
    }

    fun changeAge(text: String) {
//        dataPage3 = DataPage3(age = text.toString().toInt())
        dataPage3.age = text.toInt()
        dataPage3.date = LocalDateTime.now().toString().split("T")[0]
        dataPage3.fitness_id = dataPage3.id
        ageDataPage3.value = text
    }

    fun changeWeght(text: String, date: String = LocalDateTime.now().toString().split("T")[0]) {
//        dataPage3 = DataPage3(weight = text.toString().toDouble())
        dataPage3.weight = text//.toDouble()
        dataPage3.date = date//LocalDateTime.now().toString().split("T")[0]
        dataPage3.fitness_id = dataPage3.id
        weightDataPage3.value = text
    }

    fun changeDiceWeght(text: String) {
//        dataPage3 = DataPage3(desired_weight = text.toString().toDouble())
        dataPage3.desired_weight = text//.toDouble()
        dataPage3.date = LocalDateTime.now().toString().split("T")[0]
        dataPage3.fitness_id = dataPage3.id
        diceWeightDataPage3.value = text//.toDouble()
    }

    //page4
    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    fun changeColorButton(numberButton: String) {
        val number: Int = numberButton.toInt()
        when (number) {
            1 -> {
                dataPage4 = DataPage4(id = 0,
                    minimalPhysicalActive = true,
                    fastWalkOnFoot = false,
                    examine1_2TimesWeek = false,
                    examine3_5TimesWeek = false,
                    everyDayFitness = false,
                    date = LocalDateTime.now().toString().split("T")[0]
                )
//                dataPage4.minimalPhysicalActive = true
//                dataPage4.fastWalkOnFoot = false
//                dataPage4.examine1_2TimesWeek = false
//                dataPage4.examine3_5TimesWeek = false
//                dataPage4.everyDayFitness = false
//                dataPage4.date = LocalDateTime.now().toString().split("T")[0]
//                Button1.value = "#FF3695F4"
//                Button2.value = "#FFFFFF"
//                Button3.value = "#FFFFFF"
//                Button4.value = "#FFFFFF"
//                Button5.value = "#FFFFFF"
//
                Button1.value = btnPage4  //  R.drawable.ic_fon_text_page_4   // "#FF3695F4"
                Button2.value = btnPage4NoPress  // "#FFFFFF"
                Button3.value = btnPage4NoPress  // "#FFFFFF"
                Button4.value = btnPage4NoPress  // "#FFFFFF"
                Button5.value = btnPage4NoPress  // "#FFFFFF"

            }
            2 -> {
                dataPage4 = DataPage4(id = 0,
                    minimalPhysicalActive = false,
                    fastWalkOnFoot = true,
                    examine1_2TimesWeek = false,
                    examine3_5TimesWeek = false,
                    everyDayFitness = false,
                    date = LocalDateTime.now().toString().split("T")[0])
//                dataPage4.minimalPhysicalActive = false
//                dataPage4.fastWalkOnFoot = true
//                dataPage4.examine1_2TimesWeek = false
//                dataPage4.examine3_5TimesWeek = false
//                dataPage4.everyDayFitness = false
//                dataPage4.date = LocalDateTime.now().toString().split("T")[0]
//                Button1.value = "#FFFFFF"
//                Button2.value = "#FF3695F4"
//                Button3.value = "#FFFFFF"
//                Button4.value = "#FFFFFF"
//                Button5.value = "#FFFFFF"
                Button1.value = btnPage4NoPress   // "#FF3695F4"
                Button2.value = btnPage4  //  R.drawable.ic_fon_text_page_4  // "#FFFFFF"
                Button3.value = btnPage4NoPress  // "#FFFFFF"
                Button4.value = btnPage4NoPress  // "#FFFFFF"
                Button5.value = btnPage4NoPress
            }
            3 -> {
                dataPage4 = DataPage4(id = 0,
                    minimalPhysicalActive = false,
                    fastWalkOnFoot = false,
                    examine1_2TimesWeek = true,
                    examine3_5TimesWeek = false,
                    everyDayFitness = false,
                    date = LocalDateTime.now().toString().split("T")[0])
//                dataPage4.minimalPhysicalActive = false
//                dataPage4.fastWalkOnFoot = false
//                dataPage4.examine1_2TimesWeek = true
//                dataPage4.examine3_5TimesWeek = false
//                dataPage4.everyDayFitness = false
//                dataPage4.date = LocalDateTime.now().toString().split("T")[0]
//                Button1.value = "#FFFFFF"
//                Button2.value = "#FFFFFF"
//                Button3.value = "#FF3695F4"
//                Button4.value = "#FFFFFF"
//                Button5.value = "#FFFFFF"
                Button1.value = btnPage4NoPress   // "#FF3695F4"
                Button2.value = btnPage4NoPress  // "#FFFFFF"
                Button3.value = btnPage4  //  R.drawable.ic_fon_text_page_4  // "#FFFFFF"
                Button4.value = btnPage4NoPress  // "#FFFFFF"
                Button5.value = btnPage4NoPress
            }
            4 -> {
                dataPage4 = DataPage4(id = 0,
                    minimalPhysicalActive = false,
                    fastWalkOnFoot = false,
                    examine1_2TimesWeek = false,
                    examine3_5TimesWeek = true,
                    everyDayFitness = false,
                    date = LocalDateTime.now().toString().split("T")[0])
//                dataPage4.minimalPhysicalActive = false
//                dataPage4.fastWalkOnFoot = false
//                dataPage4.examine1_2TimesWeek = false
//                dataPage4.examine3_5TimesWeek = true
//                dataPage4.everyDayFitness = false
//                dataPage4.date = LocalDateTime.now().toString().split("T")[0]
//                Button1.value = "#FFFFFF"
//                Button2.value = "#FFFFFF"
//                Button3.value = "#FFFFFF"
//                Button4.value = "#FF3695F4"
//                Button5.value = "#FFFFFF"
                Button1.value = btnPage4NoPress   // "#FF3695F4"
                Button2.value = btnPage4NoPress  // "#FFFFFF"
                Button3.value = btnPage4NoPress  // "#FFFFFF"
                Button4.value = btnPage4  //  R.drawable.ic_fon_text_page_4  // "#FFFFFF"
                Button5.value = btnPage4NoPress
            }
            5 -> {
                dataPage4 = DataPage4(id = 0,
                    minimalPhysicalActive = false,
                    fastWalkOnFoot = false,
                    examine1_2TimesWeek = false,
                    examine3_5TimesWeek = false,
                    everyDayFitness = true,
                    date = LocalDateTime.now().toString().split("T")[0])
//                dataPage4.minimalPhysicalActive = false
//                dataPage4.fastWalkOnFoot = false
//                dataPage4.examine1_2TimesWeek = false
//                dataPage4.examine3_5TimesWeek = false
//                dataPage4.everyDayFitness = true
//                dataPage4.date = LocalDateTime.now().toString().split("T")[0]
//                Button1.value = "#FFFFFF"
//                Button2.value = "#FFFFFF"
//                Button3.value = "#FFFFFF"
//                Button4.value = "#FFFFFF"
//                Button5.value = "#FF3695F4"
                Button1.value = btnPage4NoPress   // "#FF3695F4"
                Button2.value = btnPage4NoPress  // "#FFFFFF"
                Button3.value = btnPage4NoPress  // "#FFFFFF"
                Button4.value = btnPage4NoPress  // "#FFFFFF"
                Button5.value = btnPage4  //  R.drawable.ic_fon_text_page_4
            }
        }

        dataPage4.id = getIdFromSharedPreferenses()
        dataPage4.fitness_id = dataPage4.id
    }

    fun createLocalDataPage4() {
        dataPage4.date = LocalDateTime.now().toString().split("T")[0]
    }

    //page5
    fun changeColorButtonPage51() {
        if (dataPage5.chicken) {
            dataPage5.chicken = false// = DataPage5(chicken = false)
//            page5Button1.value = "#FFFFFF"
//            page5Button1.value = ic_fon_5
        } else {
            if (!dataPage5.withoutMeat) {
                dataPage5.chicken = true// = DataPage5(chicken = true)
//                page5Button1.value = "#FF3695F4"
//                page5Button1.value = ic_fon_5_no_press
            }
        }
        dataPage5.date = LocalDateTime.now().toString().split("T")[0]
        dataPage5.fitness_id = dataPage5.id
    }

    fun changeColorButtonPage52() {
        if (dataPage5.tyrkey) {
            dataPage5.tyrkey = false// = DataPage5(tyrkey = false)
//            page5Button2.value = "#FFFFFF"
            page5Button2.value = ic_fon_5
        } else {
            if (!dataPage5.withoutMeat) {
                dataPage5.tyrkey = true// = DataPage5(tyrkey = true)
//                page5Button2.value = "#FF3695F4"
                page5Button2.value = ic_fon_5_no_press
            }
        }
        dataPage5.date = LocalDateTime.now().toString().split("T")[0]
        dataPage5.fitness_id = dataPage5.id
    }

    fun changeColorButtonPage53() {
        if (dataPage5.pork) {
            dataPage5.pork = false// = DataPage5(pork = false)
//            page5Button3.value = "#FFFFFF"
            page5Button3.value = ic_fon_5
        } else {
            if (!dataPage5.withoutMeat) {
                dataPage5.pork = true // = DataPage5(pork = true)
//                page5Button3.value = "#FF3695F4"
                page5Button3.value = ic_fon_5_no_press
            }
        }
        dataPage5.date = LocalDateTime.now().toString().split("T")[0]
        dataPage5.fitness_id = dataPage5.id
    }

    fun changeColorButtonPage54() {
        if (dataPage5.meat) {
            dataPage5.meat = false// = DataPage5(meat = false)
//            page5Button4.value = "#FFFFFF"
            page5Button4.value = ic_fon_5
        } else {
            if (!dataPage5.withoutMeat) {
                dataPage5.meat = true// = DataPage5(meat = true)
//                page5Button4.value = "#FF3695F4"
                page5Button4.value = ic_fon_5_no_press
            }
        }
        dataPage5.date = LocalDateTime.now().toString().split("T")[0]
        dataPage5.fitness_id = dataPage5.id
    }

    fun changeColorButtonPage55() {
        if (dataPage5.fish) {
            dataPage5.fish = false// = DataPage5(fish = false)
//            page5Button5.value = "#FFFFFF"
            page5Button5.value = ic_fon_5
        } else {
            if (!dataPage5.withoutFish) {
                dataPage5.fish = true// = DataPage5(fish = true)
//                page5Button5.value = "#FF3695F4"
                page5Button5.value = ic_fon_5_no_press
            }
        }
        dataPage5.date = LocalDateTime.now().toString().split("T")[0]
        dataPage5.fitness_id = dataPage5.id
    }

    fun changeColorButtonPage56() {
        if (dataPage5.seaFood) {
            dataPage5.seaFood = false // = DataPage5(seaFood = false)
//            page5Button6.value = "#FFFFFF"
            page5Button6.value = ic_fon_5
        } else {
            if (!dataPage5.withoutFish) {
                dataPage5.seaFood = true// = DataPage5(seaFood = true)
//                page5Button6.value = "#FF3695F4"
                page5Button6.value = ic_fon_5_no_press
            }
        }
        dataPage5.date = LocalDateTime.now().toString().split("T")[0]
        dataPage5.fitness_id = dataPage5.id
    }

    fun changeColorButtonPage57() {
        if (dataPage5.withoutMeat) {
            dataPage5.withoutMeat = false// = DataPage5(withoutMeat = false)
            page5Button7.value = ic_fon_5
        } else {
            dataPage5.withoutMeat = true
            dataPage5.chicken = false
            dataPage5.tyrkey = false
            dataPage5.pork = false
            dataPage5.meat = false

            page5Button7.value = ic_fon_5_no_press
            page5Button1.value = ic_fon_5
            page5Button2.value = ic_fon_5
            page5Button3.value = ic_fon_5
            page5Button4.value = ic_fon_5
        }
        dataPage5.date = LocalDateTime.now().toString().split("T")[0]
        dataPage5.fitness_id = dataPage5.id
    }

    fun changeColorButtonPage58() {
        if (dataPage5.withoutFish) {
            dataPage5.withoutFish = false// = DataPage5(withoutFish = false)
            page5Button8.value = ic_fon_5
        } else {
            dataPage5.withoutFish = true
            dataPage5.fish = false
            dataPage5.seaFood = false
            page5Button8.value = ic_fon_5_no_press
            page5Button5.value = ic_fon_5
            page5Button6.value = ic_fon_5
        }
        dataPage5.date = LocalDateTime.now().toString().split("T")[0]
        dataPage5.fitness_id = dataPage5.id
    }

    fun createLocalDataPage5() {
        dataPage5.date = LocalDateTime.now().toString().split("T")[0]
        dataPage5.fitness_id = dataPage5.id
    }

    //page6
    fun changeColorButtonPage61() {
        if (dataPage6.zucchini) {
            dataPage6.zucchini = false// = DataPage6(zucchini = false)
            page6Button1.value = ic_fon_5
        } else {
            dataPage6.zucchini = true// = DataPage6(zucchini = true)
            page6Button1.value = ic_fon_5_no_press
        }
        dataPage6.date = LocalDateTime.now().toString().split("T")[0]
        dataPage6.fitness_id = dataPage6.id
    }

    fun changeColorButtonPage62() {
        if (dataPage6.tomato) {
            dataPage6.tomato = false// = DataPage6(tomato = false)
            page6Button2.value = ic_fon_5
        } else {
            dataPage6.tomato = true// = DataPage6(tomato = true)
            page6Button2.value = ic_fon_5_no_press
        }
        dataPage6.date = LocalDateTime.now().toString().split("T")[0]
        dataPage6.fitness_id = dataPage6.id
    }

    fun changeColorButtonPage63() {
        if (dataPage6.eggplant) {
            dataPage6.eggplant = false// = DataPage6(eggplant = false)
            page6Button3.value = ic_fon_5
        } else {
            dataPage6.eggplant = true// = DataPage6(eggplant = true)
            page6Button3.value = ic_fon_5_no_press
        }
        dataPage6.date = LocalDateTime.now().toString().split("T")[0]
        dataPage6.fitness_id = dataPage6.id
    }

    fun changeColorButtonPage64() {
        if (dataPage6.cauliflower) {
            dataPage6.cauliflower = false// = DataPage6(cauliflower = false)
            page6Button4.value = ic_fon_5
        } else {
            dataPage6.cauliflower = true// = DataPage6(cauliflower = true)
            page6Button4.value = ic_fon_5_no_press
        }
        dataPage6.date = LocalDateTime.now().toString().split("T")[0]
        dataPage6.fitness_id = dataPage6.id
    }

    fun changeColorButtonPage65() {
        if (dataPage6.cucumbers) {
            dataPage6.cucumbers = false// = DataPage6(cucumbers = false)
            page6Button5.value = ic_fon_5
        } else {
            dataPage6.cucumbers = true// = DataPage6(cucumbers = true)
            page6Button5.value = ic_fon_5_no_press
        }
        dataPage6.date = LocalDateTime.now().toString().split("T")[0]
        dataPage6.fitness_id = dataPage6.id
    }

    fun changeColorButtonPage66() {
        if (dataPage6.broccoli) {
            dataPage6.broccoli = false// = DataPage6(broccoli = false)
            page6Button6.value = ic_fon_5
        } else {
            dataPage6.broccoli = true// = DataPage6(broccoli = true)
            page6Button6.value = ic_fon_5_no_press
        }
        dataPage6.date = LocalDateTime.now().toString().split("T")[0]
        dataPage6.fitness_id = dataPage6.id
    }

    fun changeColorButtonPage67() {
        if (dataPage6.mushrooms) {
            dataPage6.mushrooms = false// = DataPage6(mushrooms = false)
            page6Button7.value = ic_fon_5
        } else {
            dataPage6.mushrooms = true// = DataPage6(mushrooms = true)
            page6Button7.value = ic_fon_5_no_press
        }
        dataPage6.date = LocalDateTime.now().toString().split("T")[0]
        dataPage6.fitness_id = dataPage6.id
    }

    fun changeColorButtonPage68() {
        if (dataPage6.avocado) {
            dataPage6.avocado = false// = DataPage6(avocado = false)
            page6Button8.value = ic_fon_5
        } else {
            dataPage6.avocado = true// = DataPage6(avocado = true)
            page6Button8.value = ic_fon_5_no_press
        }
        dataPage6.date = LocalDateTime.now().toString().split("T")[0]
        dataPage6.fitness_id = dataPage6.id
    }

    fun createLocalDataPage6() {
        dataPage6.date = LocalDateTime.now().toString().split("T")[0]
        dataPage6.fitness_id = dataPage6.id
    }

    //page7
    fun changeColorButtonPage71() {
        if (dataPage7.egg) {
            dataPage7.egg = false// = DataPage7(egg = false)
            page7Button1.value = ic_fon_5
        } else {
            dataPage7.egg = true// = DataPage7(egg = true)
            page7Button1.value = ic_fon_5_no_press
        }
        dataPage7.date = LocalDateTime.now().toString().split("T")[0]
        dataPage7.fitness_id = dataPage7.id
    }

    fun changeColorButtonPage72() {
        if (dataPage7.cheese) {
            dataPage7.cheese = false// = DataPage7(cheese = false)
            page7Button2.value = ic_fon_5
        } else {
            dataPage7.cheese = true// = DataPage7(cheese = true)
            page7Button2.value = ic_fon_5_no_press
        }
        dataPage7.date = LocalDateTime.now().toString().split("T")[0]
        dataPage7.fitness_id = dataPage7.id
    }

    fun changeColorButtonPage73() {
        if (dataPage7.nuts) {
            dataPage7.nuts = false// = DataPage7(nuts = false)
            page7Button3.value = ic_fon_5
        } else {
            dataPage7.nuts = true// = DataPage7(nuts = true)
            page7Button3.value = ic_fon_5_no_press
        }
        dataPage7.date = LocalDateTime.now().toString().split("T")[0]
        dataPage7.fitness_id = dataPage7.id
    }

    fun changeColorButtonPage74() {
        if (dataPage7.cottage) {
            dataPage7.cottage = false// = DataPage7(cottage = false)
            page7Button4.value = ic_fon_5
        } else {
            dataPage7.cottage = true// = DataPage7(cottage = true)
            page7Button4.value = ic_fon_5_no_press
        }
        dataPage7.date = LocalDateTime.now().toString().split("T")[0]
        dataPage7.fitness_id = dataPage7.id
    }

    fun changeColorButtonPage75() {
        if (dataPage7.kefir) {
            dataPage7.kefir = false
            page7Button5.value = ic_fon_5
        } else {
            dataPage7.kefir = true// = DataPage7(kefir = true)
            page7Button5.value = ic_fon_5_no_press
        }
        dataPage7.date = LocalDateTime.now().toString().split("T")[0]
        dataPage7.fitness_id = dataPage7.id
    }

    fun changeColorButtonPage76() {
        if (dataPage7.yogurt) {
            dataPage7.yogurt = false// = DataPage7(yogurt = false)
            page7Button6.value = ic_fon_5
        } else {
            dataPage7.yogurt = true// = DataPage7(yogurt = true)
            page7Button6.value = ic_fon_5_no_press
        }
        dataPage7.date = LocalDateTime.now().toString().split("T")[0]
        dataPage7.fitness_id = dataPage7.id
    }

    fun createLocalDataPage7() {
        dataPage7.date = LocalDateTime.now().toString().split("T")[0]
        dataPage7.fitness_id =
            dataPage7.id// = DataPage7(date = LocalDateTime.now().toString().split("T")[0])
    }

    //page8
    fun changeColorButtonPage8(numberButton: String) {
        val number: Int = numberButton.toInt()
        when (number) {
            1 -> {
                dataPage8.waterWithoutGas = true
                dataPage8.waterSugarGas = false
                dataPage8.coffee = false
                dataPage8.tea = false
                dataPage8.date = LocalDateTime.now().toString().split("T")[0]
                page8Button1.value = ic_fon_4
                page8Button2.value = ic_fon_4_no_press
                page8Button3.value = ic_fon_4_no_press
                page8Button4.value = ic_fon_4_no_press
            }
            2 -> {
                dataPage8.waterWithoutGas = false
                dataPage8.waterSugarGas = true
                dataPage8.coffee = false
                dataPage8.tea = false
                dataPage8.date = LocalDateTime.now().toString().split("T")[0]
                page8Button1.value = ic_fon_4_no_press
                page8Button2.value = ic_fon_4
                page8Button3.value = ic_fon_4_no_press
                page8Button4.value = ic_fon_4_no_press
            }
            3 -> {
                dataPage8.waterWithoutGas = false
                dataPage8.waterSugarGas = false
                dataPage8.coffee = true
                dataPage8.tea = false
                dataPage8.date = LocalDateTime.now().toString().split("T")[0]
                page8Button1.value = ic_fon_4_no_press
                page8Button2.value = ic_fon_4_no_press
                page8Button3.value = ic_fon_4
                page8Button4.value = ic_fon_4_no_press
            }
            4 -> {
                dataPage8.waterWithoutGas = false
                dataPage8.waterSugarGas = false
                dataPage8.coffee = false
                dataPage8.tea = true
                dataPage8.date = LocalDateTime.now().toString().split("T")[0]
                page8Button1.value = ic_fon_4_no_press
                page8Button2.value = ic_fon_4_no_press
                page8Button3.value = ic_fon_4_no_press
                page8Button4.value = ic_fon_4
            }
        }
        dataPage8.fitness_id = dataPage8.id
        Log.e(TAG, "data8page ${dataPage8.id} ${dataPage8.fitness_id}")
    }

    fun createLocalDataPage8() {
        dataPage8.date = LocalDateTime.now().toString().split("T")[0]
    }

    //page9
    fun changeColorButtonPage9(numberButton: String) {
        val number: Int = numberButton.toInt()
        when (number) {
            1 -> {
                dataPage9.workOffice = true
                dataPage9.regularTraffic = false
                dataPage9.OnFoot = false
                dataPage9.InHome = false
                dataPage9.date = LocalDateTime.now().toString().split("T")[0]
                page9Button1.value = ic_fon_4
                page9Button2.value = ic_fon_4_no_press
                page9Button3.value = ic_fon_4_no_press
                page9Button4.value = ic_fon_4_no_press
            }
            2 -> {
                dataPage9.workOffice = false
                dataPage9.regularTraffic = true
                dataPage9.OnFoot = false
                dataPage9.InHome = false
                dataPage9.date = LocalDateTime.now().toString().split("T")[0]
                page9Button1.value = ic_fon_4_no_press
                page9Button2.value = ic_fon_4
                page9Button3.value = ic_fon_4_no_press
                page9Button4.value = ic_fon_4_no_press
            }
            3 -> {
                dataPage9.workOffice = false
                dataPage9.regularTraffic = false
                dataPage9.OnFoot = true
                dataPage9.InHome = false
                dataPage9.date = LocalDateTime.now().toString().split("T")[0]
                page9Button1.value = ic_fon_4_no_press
                page9Button2.value = ic_fon_4_no_press
                page9Button3.value = ic_fon_4
                page9Button4.value = ic_fon_4_no_press
            }
            4 -> {
                dataPage9.workOffice = false
                dataPage9.regularTraffic = false
                dataPage9.OnFoot = false
                dataPage9.InHome = true
                dataPage9.date = LocalDateTime.now().toString().split("T")[0]
                page9Button1.value = ic_fon_4_no_press
                page9Button2.value = ic_fon_4_no_press
                page9Button3.value = ic_fon_4_no_press
                page9Button4.value = ic_fon_4
            }
        }
        dataPage9.fitness_id = dataPage9.id
        Log.e(TAG, "data9page ${dataPage9.id} ${dataPage9.fitness_id}")
    }

    //page10
    fun changeColorButtonPage10(numberButton: String) {
        val number: Int = numberButton.toInt()
        when (number) {
            1 -> {
                dataPage10.fastFood = true
                dataPage10.laterNight = false
                dataPage10.fastSugar = false
                dataPage10.Nothing = false
                dataPage10.date = LocalDateTime.now().toString().split("T")[0]

                page10Button1.value = ic_fon_4
                page10Button2.value = ic_fon_4_no_press
                page10Button3.value = ic_fon_4_no_press
                page10Button4.value = ic_fon_4_no_press
            }
            2 -> {
                dataPage10.fastFood = false
                dataPage10.laterNight = true
                dataPage10.fastSugar = false
                dataPage10.Nothing = false
                dataPage10.date = LocalDateTime.now().toString().split("T")[0]

                page10Button1.value = ic_fon_4_no_press
                page10Button2.value = ic_fon_4
                page10Button3.value = ic_fon_4_no_press
                page10Button4.value = ic_fon_4_no_press
            }
            3 -> {
                dataPage10.fastFood = false
                dataPage10.laterNight = false
                dataPage10.fastSugar = true
                dataPage10.Nothing = false
                dataPage10.date = LocalDateTime.now().toString().split("T")[0]

                page10Button1.value = ic_fon_4_no_press
                page10Button2.value = ic_fon_4_no_press
                page10Button3.value = ic_fon_4
                page10Button4.value = ic_fon_4_no_press
            }
            4 -> {
                dataPage10.fastFood = false
                dataPage10.laterNight = false
                dataPage10.fastSugar = false
                dataPage10.Nothing = true
                dataPage10.date = LocalDateTime.now().toString().split("T")[0]

                page10Button1.value = ic_fon_4_no_press
                page10Button2.value = ic_fon_4_no_press
                page10Button3.value = ic_fon_4_no_press
                page10Button4.value = ic_fon_4
            }
        }
        dataPage10.fitness_id = dataPage10.id
        Log.e(TAG, "data10page ${dataPage10.id} ${dataPage10.fitness_id}")
    }

    fun createLocalDataPage10() {
        dataPage10 = DataPage10(date = LocalDateTime.now().toString().split("T")[0])
    }

    // вызов функции получения новостей
//    fun getBreakingNews(params: UsersData) = viewModelScope.launch {
//        safeBreakingNewsCall(params)
//    }

    private suspend fun safeBreakingNewsCall(params: UsersData) {
        try {

            val response = mainRepository.getBreakingNews(params)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> Log.e(TAG, Resource.Error<String>("Network Failure").toString())
//                    breakingNews.postValue(Resource.Error("Network Failure"))
//                else ->  breakingNews.postValue(Resource.Error("Connection Error"))
            }
        }
    }

    fun initial() {
//    dataPage5 = DataPage5(true,false,false,false,false,false,false,false, Date())
//        dataPage6 =
//            DataPage6(true, false, false, false, false, false, false, true, Date())
//        dataPage7 = DataPage7(true, false, false, false, false, false, Date())
//        dataPage8 = DataPage8(true, false, false, false, Date())
//        dataPage9 = DataPage9(true, false, false, false, Date())
//        dataPage10 = DataPage10(true, false, false, false, Date())
    }

    fun getRandomString(length: Int): String {
        val allowedChars =
            ('a'..'z')  // ('A'..'Z') + ('0'..'9')
        return (1..length).map { allowedChars.random() }
            .joinToString("")
    }

    fun createDataUser(
        fullName: String = "Bob",
        email: String = "testmail@@qq.qq",  //getRandomString(5) + "@qq.qq",
        password: String = "123456",
        id: Int = 0,
    ) {
//        mail = getRandomString(5) + "@qq.qq"
        dataUser = User(id = id, fullName = fullName, email = email, password = password)
        dataUser.fitness_id =dataUser.id!!
    }

    // вызов функции отправки данных POST запросом
    fun query() = viewModelScope.launch {
        Log.e(TAG, "inside query")
        createClassUseraData()
        safeCallPostQuery(usersData)
    }

    // вызов функции отправки и обновления данных PATCH запросом
    fun updateQuery() = viewModelScope.launch {
        Log.e(TAG, "inside query")
        createClassUseraData()
        safeCallUdatePatchQuery(usersData)
    }

//    private suspend fun callQuery2() {
////        initial()
//        try {
//            Log.e(TAG, "fastapi")
////        createDataUser()
//            createClassUseraData()
//            createNewUser(usersData)
////        getBreakingNews(params = usersData)
//            Log.e(TAG, "fastapi2")
//        } catch (t: Throwable) {
//            Log.e(TAG, "fastapi2 error")
//        }
//    }


    private fun handlecallPostQueryResponse(response: Response<UsersData>): Resource<UsersData>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                usersData = resultResponse

                return Resource.Success(usersData ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    fun createClassUseraData() {
        usersData = UsersData(
            5,
//            "Bob",
//            "qq@qq.qq",
//            "123456",
            this.dataUser,
            this.dataPage1,
            this.dataPage3,
            this.dataPage4,
            this.dataPage5,
            this.dataPage6,
            this.dataPage7,
            this.dataPage8,
            this.dataPage9,
            this.dataPage10,


            )

    }

    suspend fun safeCallPostQuery(user: UsersData) {
        createPostQueryNewUserLiveData.postValue(Resource.Loading())

        val retroService = RetrofitInstance.getRetroInstance().create(MainApi::class.java)
        val call = retroService.createUser(params = user)
        call.enqueue(object : Callback<UsersData> {
            override fun onFailure(call: Call<UsersData>, t: Throwable) {
                Log.e(TAG, "Retrofit 1")
                mutableUsersData.postValue(null)
            }

            override fun onResponse(call: Call<UsersData>, response: Response<UsersData>) {
                if (response.isSuccessful) {
                    // получен ответ от сервера после записи данных
                    Log.e(TAG, "Retrofit 2 ${response.body()?.id}")
                    mutableUsersData.postValue(response.body())
                    setIdUsersData(response.body()!!.id)
                    idUsersData.value = response.body()!!.id!!
                    saveIdToSharedPreferenses(idUsersData.value!!)
                    dataUser.id?.let { dataUser.fullName?.let { it1 -> dataUser.email?.let { it2 -> dataUser.password?.let { it3 -> saveUserToSharedPreferens(id = it, fullName = it1, email = it2, password = it3, fitness_id = dataUser.id!!) } } } }
                    Log.e(TAG, "Retrofit 22 ${mutableUsersData.value}")
                } else {
                    Log.e(TAG, "Retrofit 3")
                    mutableUsersData.postValue(null)
                }
            }
        })
    }

    fun safeCallUdatePatchQuery(user: UsersData) {
        createPostQueryNewUserLiveData.postValue(Resource.Loading())

        val retroService = RetrofitInstance.getRetroInstance().create(MainApi::class.java)
        val us: User = getUserFromSharedPreferenses()
        Log.e(TAG, "$us")
        val call = us.email?.let {
            us.password?.let { it1 ->
                us.fullName?.let { it2 ->
                    retroService.updateUser(
//                        email = it,
//                        password = it1,
//                        fullName = it2,
                        params = user, user_id = getIdFromSharedPreferenses())
                }
            }
        }
        call?.enqueue(object : Callback<UsersData> {
            override fun onFailure(call: Call<UsersData>, t: Throwable) {
                Log.e(TAG, "Retrofit 1")
                mutableUsersData.postValue(null)
            }

            override fun onResponse(call: Call<UsersData>, response: Response<UsersData>) {
                if (response.isSuccessful) {
                    // получен ответ от сервера после записи данных
                    Log.e(TAG, "Retrofit 2 ${response.body()?.id}")
                    mutableUsersData.postValue(response.body())
                    setIdUsersData(response.body()!!.id)
                    idUsersData.value = response.body()!!.id!!
                    saveIdToSharedPreferenses(idUsersData.value!!)
                    dataUser.id?.let { dataUser.fullName?.let { it1 -> dataUser.email?.let { it2 -> dataUser.password?.let { it3 -> saveUserToSharedPreferens(id = it, fullName = it1, email = it2, password = it3, fitness_id = dataUser.id!!) } } } }
                    Log.e(TAG, "Retrofit 22 ${mutableUsersData.value}")
                } else {
                    Log.e(TAG, "Retrofit 3")
                    mutableUsersData.postValue(null)
                }
            }
        })
    }

    private fun setIdUsersData(id: Int?) {
        dataPage1.fitness_id = id
        dataPage1.id = id

        dataPage3.fitness_id = id
        dataPage3.id = id

        dataPage4.fitness_id = id
        dataPage4.id = id

        dataPage5.fitness_id = id
        dataPage5.id = id

        dataPage6.fitness_id = id
        dataPage6.id = id

        dataPage7.fitness_id = id
        dataPage7.id = id

        dataPage8.fitness_id = id
        dataPage8.id = id

        dataPage9.fitness_id = id
        dataPage9.id = id

        dataPage10.fitness_id = id
        dataPage10.id = id

        if (id != null) {
            dataUser.fitness_id = id.toInt()
        }
        dataUser.id = id

        usersData.id = id
        Log.e(TAG, "verify ID ${usersData}")

//        id?.let { sharedPreferences.saveId(it) }
    }

    fun loginDataChanged(emailText: String, passwordText: String) {
        emailLogin.value = emailText
        passwordLogin.value = passwordText

    }

    fun loginDataChangedReg(fullName: String, email: String, password: String) {
        fullNameEmailRegister.value = fullName
        emailRegister.value = email
        passwordRegister.value = password
    }

    fun getQueryUserDataCall(id: Int, emailLogin: String, passwordLogin: String) =
        viewModelScope.launch {
            safeGetQueryUserDataCall(id, emailLogin, passwordLogin)
        }

    private suspend fun safeGetQueryUserDataCall(
        id: Int,
        emailLogin: String,
        passwordLogin: String,
    ) {
        searchDataUse.postValue(Resource.Loading())
        try {
//            if(hasInternetConnection()) {
            val response = mainRepository.getDataUse(id, emailLogin, passwordLogin)
            searchDataUse.postValue(handleGetUserDataResponse(response))
            Log.e(TAG, response.body().toString())
            Log.e(TAG, "${response.body()}")
//            val gson = Gson()
//            usersData = gson.fromJson(response.body().toString(), UsersData::class.java)
//            Log.e(TAG, " USER data ${usersData.toString()}")
//            answerApiServer = response
//            }else{
//            searchDataUse.postValue(Resource.Error("No internet connection"))
//            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> searchDataUse.postValue(Resource.Error("Network Failure"))
                else -> searchDataUse.postValue(Resource.Error("Connection Error"))
            }
        }
    }

    private fun handleGetUserDataResponse(response: Response<UsersData>): Resource<UsersData>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
//                searchNewsPage++
//                usersData = resultResponse
                val gson = Gson()
                usersData =
                    resultResponse//gson.fromJson(resultResponse.toString(), UsersData::class.java)
                Log.e(TAG, " 55 mUSER data ${usersData.toString()}")
                Log.e(TAG, " 66 mUSER data ${resultResponse.id}")
                responseToClass(resultResponse)
                return Resource.Success(usersData ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun responseToClass(resultResponse: UsersData) {
        usersData.id = resultResponse.id
        dataUser = resultResponse.userpage!!
        Log.e(TAG, " 66 mUSER data ${dataUser}")
        dataPage1 = resultResponse.datapage1!!
        Log.e(TAG, " 66 mUSER data ${dataPage1}")
        dataPage3 = resultResponse.datapage3!!

        ageDataPage3.value = dataPage3.age.toString()
        heightDataPage3.value = dataPage3.height.toString()
        weightDataPage3.value = dataPage3.weight
        diceWeightDataPage3.value = dataPage3.desired_weight

        Log.e(TAG, " 66 mUSER data ${dataPage3}")
        dataPage4 = resultResponse.datapage4!!
        Log.e(TAG, " 66 mUSER data ${dataPage4}")
        dataPage5 = resultResponse.datapage5!!
        Log.e(TAG, " 66 mUSER data ${dataPage5}")
        dataPage6 = resultResponse.datapage6!!
        Log.e(TAG, " 66 mUSER data ${dataPage6}")
        dataPage7 = resultResponse.datapage7!!
        Log.e(TAG, " 66 mUSER data ${dataPage7}")
        dataPage8 = resultResponse.datapage8!!
        Log.e(TAG, " 66 mUSER data ${dataPage8}")
        dataPage9 = resultResponse.datapage9!!
        Log.e(TAG, " 66 mUSER data ${dataPage9}")
        dataPage10 = resultResponse.datapage10!!
        Log.e(TAG, " 66 mUSER data ${dataPage10}")
    }

    private var resultLiveMutable = MutableLiveData<String>()
    val resultLive: LiveData<String> = resultLiveMutable

    fun saveIdToSharedPreferenses(id: Int) {
        val params = SaveUserNameParamId(idFromApi = id)
//        val params = idUsersData.value?.let { SaveUserNameParam(idFromApi = it.toInt()) }
        val resultData: Boolean = saveUserNameUseCase.executeSave(param = id)
        resultLiveMutable.value = "Save result = $resultData"
        Log.e(TAG, "saveIdFromApi viewModel $id")
    }

    fun getIdFromSharedPreferenses(): Int {
        val resultGetIdData: Int = saveUserNameUseCase.executeGet()
        resultLiveMutable.value = "Get id = resultGetIdData"
        Log.e(TAG, "getIdFromApi viewModel $resultGetIdData")
        return resultGetIdData
    }

    fun getUserFromSharedPreferenses(): User {
        val userName: User = getUserNameUseCase.execute()
        resultLiveMutable.value = "${userName.fullName} ${userName.email}"
        return userName
    }

    fun saveUserToSharedPreferens(
        id: Int = 0,
        fullName: String,
        email: String,
        password: String,
        fitness_id: Int = 0,
    ) {
        val params = SaveUserNameParam(
            id = id,
            fullName = fullName,
            email = email,
            passwordFB = password,
            fitness_id = fitness_id
        )
        val resultData: Boolean = saveUserNameUseCase.executeSave(param = params)
        resultLiveMutable.value = "Save result = $resultData"
    }

    fun launchUpdateDataPage3() = viewModelScope.launch {
        if (getIdFromSharedPreferenses() != 0) {
            updateDataPage3()
        }
    }

    private suspend fun updateDataPage3() {
        val retrofitInstance = RetrofitInstance.getRetroInstance().create(RetroService::class.java)
        val call = retrofitInstance.updateHigthWeigthAge(user_id = getIdFromSharedPreferenses(),
            userData3 = this.dataPage3)//, date = java.sql.Date(2022872))
        call.enqueue(object : Callback<UsersData> {
            override fun onFailure(call: Call<UsersData>, t: Throwable) {
                Log.e(TAG, "Data Page3 NULL")
                mutableUsersData.postValue(null)
            }

            override fun onResponse(call: Call<UsersData>, response: Response<UsersData>) {
                if (response.isSuccessful) {
                    Log.e(TAG, "Data response Body IS ${response.body()}")
                    Log.e(TAG, "Data User IS ${dataUser}")
                    Log.e(TAG, "Data Page3 IS ${dataPage3}")
                    mutableUsersData.postValue(response.body())
                    dataUser.id?.let { dataUser.fullName?.let { it1 -> dataUser.email?.let { it2 -> dataUser.password?.let { it3 -> saveUserToSharedPreferens(id = it, fullName = it1, email = it2, password = it3, fitness_id = dataUser.id!!) } } } }
                    Log.e(TAG, "Retrofit 22 ${mutableUsersData.value}")
                } else {
                    Log.e(TAG, "Data Page3 ELSE ${response.body()}")
                    mutableUsersData.postValue(null)
                }
            }
        }
        )
    }


//    fun load() {
//        val userName: UserName = getUserNameUseCase.execute()
//        resultLiveMutable.value = "${userName.firstName} ${userName.lastName}"
//    }


}



