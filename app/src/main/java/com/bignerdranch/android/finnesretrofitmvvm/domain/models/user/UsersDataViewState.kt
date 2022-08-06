package com.bignerdranch.android.finnesretrofitmvvm.domain.models.user

data class UsersDataViewState (

// userpage
val email: String,
val password: String,
val fullName: String,

// datapage9
val InHome: String,
val OnFoot: String,
val date: String,
val regularTraffic: String,
val workOffice: String,

// datapage8
val coffee: String,
val tea: String,
val waterSugarGas: String,
val waterWithoutGas: String,

// datapage7
val cheese: String,
val cottage: String,
val egg: String,
val kefir: String,
val nuts: String,
val yogurt: String,

// datapage6
val avocado: String,
val broccoli: String,
val cauliflower: String,
val cucumbers: String,
val eggplant: String,
val mushrooms: String,
val tomato: String,
val zucchini: String,

// datapage5
val chicken: String,
val fish: String,
val meat: String,
val pork: String,
val seaFood: String,
val tyrkey: String,
val withoutFish: String,
val withoutMeat: String,

// datapage4
val everyDayFitness: String,
val examine1_2TimesWeek: String,
val examine3_5TimesWeek: String,
val fastWalkOnFoot: String,
val minimalPhysicalActive: String,

// datapage3
val age: Int,
val desired_weight: Double,
val height: Int,
val weight: Double,

// datapage10
val Nothing: String,
val fastFood: String,
val fastSugar: String,
val laterNight: String,

// datapage1
val man: String,
val woman: String,

)