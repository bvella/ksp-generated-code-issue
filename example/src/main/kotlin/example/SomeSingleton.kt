package example

import jakarta.inject.Singleton

@Singleton
class SomeSingleton(private val service: ServiceClient) {

    fun x() {
        service.something()
    }

}