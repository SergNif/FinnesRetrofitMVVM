package com.bignerdranch.android.finnesretrofitmvvm.domain.models.user

import androidx.room.PrimaryKey
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

data class User (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var fullName: String? = null,
    var email: String? = null,
    var password: String? = null,
)
{
    var fitness_id: Int by DelegateClass()

    class DelegateClass : ReadWriteProperty<User, Int> {
        override fun getValue(thisRef: User, property: KProperty<*>): Int {
            var df = "iin"
            return 20
        }

        override fun setValue(thisRef: User, property: KProperty<*>, value: Int) {
            var df = "iin"

        }

    }
}

