package com.inferno271.leadappfin.ui.main

import com.inferno271.leadappfin.data.model.Call

interface MainContract {

    interface View {
        fun showCalls(calls: List<Call>)
        // Добавьте другие методы для отображения различных частей пользовательского интерфейса
    }

    interface Presenter {
        fun loadCalls()
        // Добавьте другие методы для обработки пользовательских действий и бизнес-логики
    }
}
