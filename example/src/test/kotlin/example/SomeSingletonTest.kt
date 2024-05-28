package example

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Test

@MicronautTest
class SomeSingletonTest {

    @Inject lateinit var ss: SomeSingleton

    @Test
    fun test() {
        ss.x()
    }

}