package ar.edu.unahur.obj2.caralibro

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

class UsuarioTest : DescribeSpec({
  describe("Caralibro") {

    // Publicaciones
    val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz",publicoConListaDeExcluidos)
    val fotoEnCuzco = Foto(768, 1024,publico)
    val fotoEnUNAHUR = Foto(720, 1080,soloAmigos)
    // agregué dos publicaciones del otro test || pero parece que esto no funciona
    val videoEnAruba = Video(30,CalidadSd,privadoConListaDePermitidos)
    val videoEnMiami = Video(50,Calidad720,publicoConListaDeExcluidos)

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
        saverin.agregarPublicacion(fotoEnUNAHUR)
        saverin.permiteVerLaPublicacion(zuckerberg,fotoEnUNAHUR).shouldBe(false)
      }
      it("el usuario Zukerberg le da mg y se agrega a los que le dieron mg a la publicacion"){
        zuckerberg.darleMeGustaAUnaPublicacion(fotoEnCuzco)
        fotoEnCuzco.usuariosQueLeGusta.size.shouldBe(1)
      }
      it("parker y saverin pueden ver todas las publicaciones"){
        // esto lo reformularía "mejores amigos de zuckerberg"
        zuckerberg.agregarPublicacion(fotoEnCuzco)
        zuckerberg.agregarPublicacion(fotoEnUNAHUR)

        zuckerberg.agregarUnAmigoNuevo(saverin)
        zuckerberg.agregarUnAmigoNuevo(parker)

        zuckerberg.mejoresAmigos().shouldContainInOrder(saverin,parker)
      }
      it("amigo más popular de juana") {
        // publicaciones de juana
        val fotoPerfil = Foto(768, 1024,publico)
        juana.agregarPublicacion(fotoEnCuzco)
        juana.agregarPublicacion(saludoCumpleanios)
        juana.agregarPublicacion(fotoEnUNAHUR)
        juana.agregarPublicacion(fotoPerfil)

        // amigos que dieron me gusta
        zuckerberg.darleMeGustaAUnaPublicacion(saludoCumpleanios)
        saverin.darleMeGustaAUnaPublicacion(saludoCumpleanios)
        saverin.darleMeGustaAUnaPublicacion(fotoPerfil)
        parker.darleMeGustaAUnaPublicacion(saludoCumpleanios)
        parker.darleMeGustaAUnaPublicacion(fotoEnCuzco)
        parker.darleMeGustaAUnaPublicacion(fotoEnUNAHUR)
        parker.darleMeGustaAUnaPublicacion(fotoPerfil)

        juana.cantidadPublicaciones().shouldBe(4)
        juana.cuantasPublicacionesMiasPuedeVer(saverin).shouldBe(2)
        juana.amigoMasPopular().shouldBe(parker)
      }
    }
  }
})

