package ar.edu.unahur.obj2.caralibro

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

class UsuarioTest : DescribeSpec({
  describe("Caralibro") {

    // Publicaciones
    val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
    val fotoEnCuzco = Foto(768, 1024)
    val fotoEnUNAHUR = Foto(720, 1080)

    // Usuarios
    val juana = Usuario()
    val zuckerberg = Usuario()
    val saverin = Usuario()
    val parker = Usuario()

    factorDeCompresion.factor = 0.7

    describe("Un usuario") {
      it("puede calcular el espacio que ocupan sus publicaciones") {
        juana.agregarPublicacion(fotoEnCuzco)
        juana.agregarPublicacion(saludoCumpleanios)
        juana.espacioDePublicaciones().shouldBe(550548)
      }
      it("el usuario le da me gusta a una publicacion"){
        zuckerberg.agregarPublicacion(fotoEnCuzco)
        zuckerberg.darleMeGustaAUnaPublicacion(fotoEnCuzco)
        fotoEnCuzco.cantidadDeMeGustasQueTieneLaPublicacion().shouldBe(1)
      }
      it("el usuario le da me gusta a una publicación repetida") {
        zuckerberg.agregarPublicacion(fotoEnCuzco)
        zuckerberg.darleMeGustaAUnaPublicacion(fotoEnCuzco)
        shouldThrowAny { zuckerberg.darleMeGustaAUnaPublicacion(fotoEnCuzco) }
        fotoEnCuzco.cantidadDeMeGustasQueTieneLaPublicacion().shouldBe(1)
      }
      it("el usuario zuckerberg tiene mas amigos que el saverin"){
        zuckerberg.agregarUnAmigoNuevo(parker)
        zuckerberg.esMasAmistosoQue(saverin).shouldBeTrue() // agregué la condición
      }
      it("el usuario zuckerberg no puede ver la fotoEnUNAHUR") {
        // FALTA DESARROLLAR fun puedeVer(Publicacion)
      }
      it("el usuario Zukerberg le da mg y se agrega a los que le dieron mg a la publicacion"){
        zuckerberg.darleMeGustaAUnaPublicacion(fotoEnCuzco)
        fotoEnCuzco.usuariosQueLeGusta.size.shouldBe(1)
      }
    }
  }
})
