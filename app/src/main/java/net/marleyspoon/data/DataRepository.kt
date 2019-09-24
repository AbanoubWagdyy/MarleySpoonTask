package net.marleyspoon.data

class DataRepository private constructor() : RepositorySource {

    companion object {
        private var INSTANCE: DataRepository? = null

        fun getInstance(): DataRepository {
            if (INSTANCE == null) {
                INSTANCE = DataRepository()
            }
            return INSTANCE as DataRepository
        }
    }
}