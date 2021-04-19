package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

class UsuarioTest : DescribeSpec({
  describe("Caralibro") {
    val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
    val fotoEnCuzco = Foto(768, 1024)
    val fotoEnUNAHUR = Foto(720, 1080)

    // ¿El escenario general de usuarios se definiría acá y dentro del 'describe'
    // cada situación particular...? ¿juana, suckerberg, etc. van acá y algunas
    // acciones? así también se pueden reutilizar en varios test

    factorDeCompresion.factor = 0.7
    describe("Un usuario") {
      it("puede calcular el espacio que ocupan sus publicaciones") {
        val juana = Usuario()
        juana.agregarPublicacion(fotoEnCuzco)
        juana.agregarPublicacion(saludoCumpleanios)
        juana.espacioDePublicaciones().shouldBe(550548)
      }
      it("el usuario le da me gusta a una publicacion"){
        val zuckerberg = Usuario()
        zuckerberg.agregarPublicacion(fotoEnCuzco)
        zuckerberg.darleMeGustaAUnaPublicacion(fotoEnCuzco)
        fotoEnCuzco.cantidadDeMeGustasQueTieneLaPublicacion().shouldBe(1)
      }
      it("el usuario le da me gusta a una publicación repetida") {
        val zuckerberg = Usuario()
        // debería ser Null porque se está instanciando nuevamente a zuckberg y
        // no sería el mismo objeto que en el 'it' anterior
        // además que falta desarrollar esa función (quizás no sea un Null el retorno)
        zuckerberg.darleMeGustaAUnaPublicacion((fotoEnCuzco)).shouldBeNull()
      }
      it("el usuario zuckerberg tiene mas amigos que el saverin"){
        // CONSULTA: estos usuarios son locales al 'it' y no serían los
        // mismos que antes (por ejemplo, este zuckerberg es una nueva instancia)
        val zuckerberg = Usuario()
        val saverin = Usuario()
        val parker = Usuario()

        zuckerberg.agregarUnAmigoNuevo(parker)
        zuckerberg.esMasAmistosoQue(saverin).shouldBeTrue() // agregué la condición
      }
      it("el usuario zuckerberg no puede ver la fotoEnUNAHUR") {
        // FALTA DESARROLLAR fun puedeVer(Publicacion)
      }
    }
  }
})
