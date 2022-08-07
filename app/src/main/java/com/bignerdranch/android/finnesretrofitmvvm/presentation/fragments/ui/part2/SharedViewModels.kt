package com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.part2

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.core.util.Pair
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.finnesretrofitmvvm.data.api.RetroService
import com.bignerdranch.android.finnesretrofitmvvm.data.api.RetrofitInstance
import com.bignerdranch.android.finnesretrofitmvvm.data.repository.UserRepositoryImpl
import com.bignerdranch.android.finnesretrofitmvvm.data.storage.SharedPrefUserStorage
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.data.DataPage3
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.menu.MenuDay
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.SaveUserNameParam
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.user.User
import com.bignerdranch.android.finnesretrofitmvvm.domain.usecase.GetUserNameUseCase
import com.bignerdranch.android.finnesretrofitmvvm.domain.usecase.SaveUserNameUseCase
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*

class SharedViewModels(application: Application) : AndroidViewModel(application) {

    val TAG = "SharedViewModels"

    var positionRecycklerViewEdit: Int? = null
    lateinit var listMenuDay: List<MenuDay>

    var createNewUserLiveData: MutableLiveData<User?> = MutableLiveData()
    val formatterYYYY_MM_DD = SimpleDateFormat("yyyy-MM-dd")
    val formatterDD_MMM_YYYY = SimpleDateFormat("dd MMM yyyy")

    //    val isThingInitialized: Boolean by lazy { ::_user.isInitialized }
    fun isThingInitialized() = ::_user.isInitialized

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext
    private val userRepository by lazy(LazyThreadSafetyMode.NONE) {
        UserRepositoryImpl(userStorage = SharedPrefUserStorage(context = context))
    }
    private val getUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetUserNameUseCase(userRepository = userRepository)
    }
    private val saveUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) {
        SaveUserNameUseCase(userRepository = userRepository)
    }


    private var _id: MutableLiveData<Int> = MutableLiveData(0)
    val id: LiveData<Int> = _id
    private var _fullName: MutableLiveData<String> = MutableLiveData("fulName")
    val fullName: LiveData<String> = _fullName
    private var _email: MutableLiveData<String> = MutableLiveData("f@f.rt")
    val email: LiveData<String> = _email
    private var _passwordFB: MutableLiveData<String> = MutableLiveData("pasw")
    val passwordFB: LiveData<String> = _passwordFB

    var newPasswordFB: String = ""

    private var _age: MutableLiveData<Int> = MutableLiveData(18)
    var age: LiveData<Int> = _age
    private var _height: MutableLiveData<Int> = MutableLiveData(140)
    val height: LiveData<Int> = _height
    private var _weight: MutableLiveData<String> = MutableLiveData("40.0")
    val weight: LiveData<String> = _weight
    private var _desired_weight: MutableLiveData<String> = MutableLiveData("40.0")
    val desired_weight: LiveData<String> = _desired_weight
    private var _date: MutableLiveData<String> = MutableLiveData("")
    val date: LiveData<String> = _date
    private var _fitness_id: MutableLiveData<Int> = MutableLiveData(0)
    val fitness_id: LiveData<Int> = _fitness_id


    private var _startData: MutableLiveData<String> =
        MutableLiveData(LocalDateTime.now().plusDays(0).toString().split("T")[0])
    val startData: LiveData<String>
        get() = _startData

    private var _endData: MutableLiveData<String> =
        MutableLiveData(LocalDateTime.now().plusDays(1).toString().split("T")[0])
    val endData: LiveData<String>
        get() = _endData

    private var _startDataAPI: MutableLiveData<String> =
        MutableLiveData(LocalDateTime.now().plusDays(0).toString().split("T")[0])
    val startDataAPI: LiveData<String>
        //    formatterDD_MMM_YYYY.format(formatterYYYY_MM_DD.parse(_startDataAPI.value.toString()))
        get() = _startDataAPI

    private var _endDataAPI: MutableLiveData<String> =
        MutableLiveData(LocalDateTime.now().plusDays(1).toString().split("T")[0])
    val endDataAPI: LiveData<String>
        get() = _endDataAPI

    private var _pickerCalendarData = MutableLiveData<Pair<Long, Long>>()
    val pickerCalendarData: MutableLiveData<Pair<Long, Long>>
        get() = _pickerCalendarData

    private var _listWeightForChart = mutableListOf<Entry>()
    val listWeightForChart: MutableList<Entry>
        get() = _listWeightForChart

    lateinit var _data3: DataPage3
    lateinit var _user: User

    private val CHART_LABEL = "DAY_CHART"
    private val dayData = mutableListOf<Entry>()
    private val _lineDataSet = MutableLiveData(LineDataSet(listWeightForChart, CHART_LABEL))
    val lineDataSet: LiveData<LineDataSet> = _lineDataSet


    fun funListWeightForChart(listMenuDay: List<MenuDay>): MutableList<Entry> {
        this.listMenuDay = listMenuDay
        Log.e(TAG, "Wiegth ${listMenuDay}")
        listMenuDay.forEachIndexed { index, e ->
            _listWeightForChart.add(Entry(index.toFloat(), e.weight.toFloat()))
            Log.e(TAG, "Wiegth ${e.weight.toFloat()}")
        }
        _lineDataSet.value = LineDataSet(listWeightForChart, CHART_LABEL)
        return _listWeightForChart
    }

    fun sharedData3(
        id: Int = 13,
        age: Int = 18,
        height: Int = 140,
        weight: String = "40.0",
        desired_weight: String = "42.0",
        date: Long = 25412,
        fitness_id: Int = 13,
    ): DataPage3 {
        _data3 = DataPage3(id = getIdFromSharedPreferenses(),
            age = age,
            height,
            weight,
            desired_weight,
            LocalDateTime.now().toString().split("T")[0],
            fitness_id)
        return _data3
    }

    val outputDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    val outputDateFormatAPI = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    fun formatDataPicker(picker: Pair<Long, Long>) {
        _startData.value = outputDateFormat.format(picker.first).toString()
        _endData.value = outputDateFormat.format(picker.second).toString()
        _pickerCalendarData.value = picker
    }

    fun formatDataPickerAPI(picker: Pair<Long, Long>? = pickerCalendarData.value) {
        if (picker != null) {
            _startDataAPI.value = outputDateFormatAPI.format(picker.first)
            Log.e(TAG, " PICKER  ${_startDataAPI.value.toString()}")
        }
        if (picker != null) {
            _endDataAPI.value = outputDateFormatAPI.format(picker.second)
            Log.e(TAG, " PICKER  ${_endDataAPI.value.toString()}")
        }
    }

    fun getIdFromSharedPreferenses(): Int {
        val resultGetIdData: Int = saveUserNameUseCase.executeGet()
//        resultLiveMutable.value = "Get id = resultGetIdData"
        Log.e(TAG, "getIdFromApi viewModel $resultGetIdData")
        _id.value = resultGetIdData
        return resultGetIdData
    }
//    private var resultLiveMutable = MutableLiveData<String>()
//    val resultLive: LiveData<String> = resultLiveMutable

    fun getUserFromSharedPreferenses(): User {
        val userName: User = getUserNameUseCase.execute()
        Log.e(TAG, "${userName.id} ${userName.fullName} ${userName.email} ${userName.password}")
        val result = User(id = getIdFromSharedPreferenses(),
            fullName = userName.fullName,
            email = userName.email,
            password = userName.password)
        changeLiveParametrs(result)
//        resultLiveMutable.value = "${userName.fullName} ${userName.email}"
        return result
    }

    fun saveChangeNamePassword(newPassword: String) {
        val userName: User = getUserNameUseCase.execute()

        userName.fullName = _user.fullName
        if (newPassword == "") {
            userName.password = _user.password
        } else {
            userName.password = newPassword
        }
//        userName.fitness_id = getIdFromSharedPreferenses()
        launchUpdateNamePassword(userName)

    }

    fun launchUpdateNamePassword(userName: User) = viewModelScope.launch {
        if (getIdFromSharedPreferenses() != 0) {
            updateNamePassword(userName)
        }
    }

    private fun updateNamePassword(userName: User) {
        val retrofitInstance = RetrofitInstance.getRetroInstance().create(RetroService::class.java)
        val call = retrofitInstance.updateNamePassword(user_id = getIdFromSharedPreferenses(),
            userName = userName)//, date = java.sql.Date(2022872))
        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e(TAG, "Data Page3 NULL")
                createNewUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    _user = response.body()!!
                    Log.e(TAG, "Data Page3 IS ${response.body()} ${_user}")
                    createNewUserLiveData.postValue(response.body())
                    _user.id?.let {
                        _user.fullName?.let { it1 ->
                            _user.email?.let { it2 ->
                                _user.password?.let { it3 ->
                                    saveUserToSharedPreferens()
                                }
                            }
                        }
                    }
                    changeLiveParametrs(_user)
//                    saveUserToSharedPreferens()

                } else {
                    Log.e(TAG, "Data Page3 ELSE ${response.body()}")
                    createNewUserLiveData.postValue(null)
                }
            }
        }
        )
    }

    private fun changeLiveParametrs(_user: User) {
        _id.value = _user.id
        _fullName.value = _user.fullName
        _email.value = _user.email
        _passwordFB.value = _user.password
        _fitness_id.value = _user.fitness_id
    }

    fun saveUserToSharedPreferens(
//        id: Int = 0,
//        fullName: String,
//        email: String,
//        passwordFB: String,
//        fitness_id: Int = 0,
    ) {
        val params = SaveUserNameParam(
            id = _user.id!!,
            fullName = _user.fullName!!,
            email = _user.email!!,
            passwordFB = _user.password!!,
            fitness_id = _user.id!!,

            )

        val resultData: Boolean = saveUserNameUseCase.executeSave(param = params)
    }

    fun getStartEndData() {
        _startData.value = getUserNameUseCase.executeGetData()[0]
        _endData.value = getUserNameUseCase.executeGetData()[1]
        changeStartEndDataAPI()
    }

    fun launchGetUserOfEmail(emailUser: String, passwUser: String) = viewModelScope.launch {
        delay(500)
        getUserOfEmail(emailUser, passwUser)
    }


    private fun getUserOfEmail(emailUser: String, passwUser: String) {
        val retrofitInstance = RetrofitInstance.getRetroInstance().create(RetroService::class.java)
//        val call = retrofitInstance.getMenuStrings(id = getIdFromSharedPreferenses(), date = LocalDateTime.now().plusDays(dateMenuRecyle).toString().split("T")[0])
        val call = retrofitInstance.getUserOnEmail(emailQuery = emailUser, passwQuery = passwUser)
        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e(TAG, "Get Menu List NULL")
                createNewUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    Log.e(TAG, "Get Menu List IS ${response.body()}")
                    _user = response.body()!!
                    createNewUserLiveData.postValue(response.body())
                    _user.id?.let {
                        _user.fullName?.let { it1 ->
                            _user.email?.let { it2 ->
                                _user.password?.let { it3 ->
                                    saveUserToSharedPreferens()
                                }
                            }
                        }
                    }
                    changeLiveParametrs(_user)
//                    Log.e(TAG, " -user ${_user.toString()}")
                    createNewUserLiveData.postValue(response.body())
//                    Log.e(TAG, " create ${createNewUserLiveData.value}")
//                    saveUserToSharedPreferens()


                } else {
                    Log.e(TAG, "Get Menu List ELSE ${response.body()}")
                    createNewUserLiveData.postValue(null)
                }
            }
        }
        )
    }

    fun saveDataStartDataCalendar(startData: String, endData: String) {
        val saveDataShared: Boolean =
            saveUserNameUseCase.executeSaveData(startData = startData, endData = endData)
    }

    fun converStringToData(dt: String, i: Long): String? {

        val formatter2 = DateTimeFormatter.ofPattern("d MMM yyyy")
        Log.e(TAG, "conv 1 ${dt}")
        val date2 = LocalDate.parse(dt, formatter2).plusDays(i)
        Log.e(TAG, "conv 2 ${date2}")

        val formatter15 = SimpleDateFormat("yyyy-MM-dd")
        //  "EEE, MMM d, ''yy"
        val date85 = formatter15.parse(date2.toString())
        val formatter25 = SimpleDateFormat("dd MMM yyyy")

//    println(formatter25.format(date85))

//    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH  )
//
//    val d = LocalDate.parse(dt, formatter).plusDays(i).toString().split("-")
//    val dtt = d[2]+"-"+d[1]+"-"+d[0]
        return date85?.let { formatter25.format(it) }
    }

    fun oneChangeStartEndData(i: Int) {

//        Log.e(TAG, "3 ${_startData.value}")
        _startData.value = converStringToData(_startData.value.toString(), i.toLong()).toString()
//        Log.e(TAG, "33 ${_startData.value}")
//        Log.e(TAG, "1 ${_endData.value}")
        _endData.value = converStringToData(_endData.value.toString(), i.toLong()).toString()
//        Log.e(TAG, "11 ${_endData.value}")
//
//        Log.e(TAG, "2 ${_endDataAPI.value}")
//        _endDataAPI.value = LocalDate.parse(_endDataAPI.value).plusDays(i.toLong()).toString()
////        _endDataAPI.value = converStringToData(_endDataAPI.value.toString(), i.toLong()).toString()
//        Log.e(TAG, "4 ${_startDataAPI.value}")
//        _startDataAPI.value = LocalDate.parse(_startDataAPI.value).plusDays(i.toLong()).toString()
//        val d = LocalDate.parse(_endDataAPI.value).plusDays(i.toLong()).toString()
//        _startDataAPI.value = converStringToData(_startDataAPI.value.toString(), i.toLong()).toString()
        changeStartEndDataAPI()
        saveDataStartDataCalendar(startData.value.toString(), endData.value.toString())
    }

    fun changeStartEndDataAPI() {
        Log.e(TAG, "startData ${startData.value} endData ${endData.value}")
        val formatter2 = DateTimeFormatter.ofPattern("d MMM yyyy")
        try {
            _startDataAPI.value = LocalDate.parse(startData.value, formatter2).toString()
            _endDataAPI.value = LocalDate.parse(endData.value, formatter2).toString()
        }catch(e: DateTimeParseException) {
            _startDataAPI.value = LocalDate.parse(startData.value.toString()).toString()//, DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString()
            _endDataAPI.value = LocalDate.parse(endData.value.toString()).toString()//, DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString()
        }

    }


}