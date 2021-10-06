package com.qoiu.holybibleapp.core

import io.realm.RealmObject

interface ToDbMapper<T: RealmObject, M : Abstract.Mapper> {

        fun mapTo(mapper: M, db: DbWrapper<T>): T

}