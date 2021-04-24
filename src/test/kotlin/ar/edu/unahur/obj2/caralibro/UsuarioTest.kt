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
    val fotoPerfil = Foto(768, 1024,publico)

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
      it("juana tiene 4 publicaciones"){
        juana.agregarPublicacion(fotoEnCuzco)//publico
        juana.agregarPublicacion(saludoCumpleanios)//publicoConListaDeExcluidos
        juana.agregarPublicacion(fotoEnUNAHUR)//soloAmigos
        juana.agregarPublicacion(fotoPerfil)//publico

        juana.cantidadPublicaciones().shouldBe(4)
      }
      it("saverin puede ver 5 publicaciones de juana"){
        juana.agregarPublicacion(fotoEnCuzco)//publico
        juana.agregarPublicacion(saludoCumpleanios)//publicoConListaDeExcluidos
        juana.agregarPublicacion(fotoEnUNAHUR)//soloAmigos
        juana.agregarPublicacion(fotoPerfil)//publico


        juana.cuantasPublicacionesMiasPuedeVer(saverin).shouldBe(3)
      }
      it("parker puede ver 4 publicaciones"){
        juana.agregarPublicacion(fotoEnCuzco)//publico
        juana.agregarPublicacion(saludoCumpleanios)//publicoConListaDeExcluidos
        juana.agregarPublicacion(fotoEnUNAHUR)//soloAmigos
        juana.agregarPublicacion(fotoPerfil)//publico

        juana.agregarUnAmigoNuevo(parker)

        juana.cuantasPublicacionesMiasPuedeVer(parker).shouldBe(4)
      }
      it("amigo más popular de juana") {
        // publicaciones de juana
        juana.agregarPublicacion(fotoEnCuzco)//publico
        juana.agregarPublicacion(saludoCumpleanios)//publicoConListaDeExcluidos
        juana.agregarPublicacion(fotoEnUNAHUR)//soloAmigos
        juana.agregarPublicacion(fotoPerfil)//publico

        // amigos de juana
        juana.agregarUnAmigoNuevo(parker)
        juana.agregarUnAmigoNuevo(zuckerberg)

        // amigos que dieron me gusta
        zuckerberg.darleMeGustaAUnaPublicacion(saludoCumpleanios)
        saverin.darleMeGustaAUnaPublicacion(saludoCumpleanios)
        saverin.darleMeGustaAUnaPublicacion(fotoPerfil)
        parker.darleMeGustaAUnaPublicacion(saludoCumpleanios)
        parker.darleMeGustaAUnaPublicacion(fotoEnCuzco)
        parker.darleMeGustaAUnaPublicacion(fotoEnUNAHUR)
        parker.darleMeGustaAUnaPublicacion(fotoPerfil)


        juana.amigoMasPopular().shouldBe(parker)
      }
      it("usuario stalkea") {
        // algunas publicaciones nuevas
        val invitacionVacaciones = Texto("Hola, Lea! Nos vamos de vacaciones a la costa?",publicoConListaDeExcluidos)
        val fotoSecundario = Foto(480,320,publico)
        val recuerdosSecundario = Texto("Eramos tan jóvenes!!!",publico)
        val busquedaLaboral = Texto("¿Alguien necesita un programador?",publico)

        // amigos de juana --> esto se repite y podríamos ponerlo fuera.
        juana.agregarUnAmigoNuevo(parker)
        juana.agregarUnAmigoNuevo(zuckerberg)

        // Juana hace sus publicaciones y son 10
        juana.agregarPublicacion(fotoEnCuzco)//publico
        juana.agregarPublicacion(saludoCumpleanios)//publicoConListaDeExcluidos
        juana.agregarPublicacion(fotoEnUNAHUR)//soloAmigos
        juana.agregarPublicacion(fotoPerfil)//publico
        juana.agregarPublicacion(invitacionVacaciones)
        juana.agregarPublicacion(fotoSecundario)
        juana.agregarPublicacion(recuerdosSecundario)
        juana.agregarPublicacion(videoEnAruba)
        juana.agregarPublicacion(videoEnMiami)
        juana.agregarPublicacion(busquedaLaboral)

        // A parker le gustan 9 publicaciones a juana
        parker.darleMeGustaAUnaPublicacion(fotoEnCuzco)
        parker.darleMeGustaAUnaPublicacion(saludoCumpleanios)
        parker.darleMeGustaAUnaPublicacion(fotoEnUNAHUR)
        parker.darleMeGustaAUnaPublicacion(invitacionVacaciones)
        parker.darleMeGustaAUnaPublicacion(fotoPerfil)
        parker.darleMeGustaAUnaPublicacion(fotoSecundario)
        parker.darleMeGustaAUnaPublicacion(recuerdosSecundario)
        parker.darleMeGustaAUnaPublicacion(videoEnAruba)
        parker.darleMeGustaAUnaPublicacion(busquedaLaboral)

        juana.meStalkea(parker).shouldBeTrue()

      }
      it("zuckerber no stalkea a juana"){
        val invitacionVacaciones = Texto("Hola, Lea! Nos vamos de vacaciones a la costa?",publicoConListaDeExcluidos)
        val fotoSecundario = Foto(480,320,publico)
        val recuerdosSecundario = Texto("Eramos tan jóvenes!!!",publico)
        val busquedaLaboral = Texto("¿Alguien necesita un programador?",publico)

        juana.agregarUnAmigoNuevo(zuckerberg)

        juana.agregarPublicacion(fotoEnCuzco)//publico
        juana.agregarPublicacion(saludoCumpleanios)//publicoConListaDeExcluidos
        juana.agregarPublicacion(fotoEnUNAHUR)//soloAmigos
        juana.agregarPublicacion(fotoPerfil)//publico
        juana.agregarPublicacion(invitacionVacaciones)
        juana.agregarPublicacion(fotoSecundario)
        juana.agregarPublicacion(recuerdosSecundario)
        juana.agregarPublicacion(videoEnAruba)
        juana.agregarPublicacion(videoEnMiami)
        juana.agregarPublicacion(busquedaLaboral)

        // A zuckerberg le gustan 8 publicaciones de juana
        zuckerberg.darleMeGustaAUnaPublicacion(fotoEnCuzco)
        zuckerberg.darleMeGustaAUnaPublicacion(saludoCumpleanios)
        zuckerberg.darleMeGustaAUnaPublicacion(fotoEnUNAHUR)
        zuckerberg.darleMeGustaAUnaPublicacion(invitacionVacaciones)
        zuckerberg.darleMeGustaAUnaPublicacion(fotoPerfil)
        zuckerberg.darleMeGustaAUnaPublicacion(fotoSecundario)
        zuckerberg.darleMeGustaAUnaPublicacion(recuerdosSecundario)
        zuckerberg.darleMeGustaAUnaPublicacion(videoEnAruba)

        juana.meStalkea(zuckerberg).shouldBeFalse()
      }
    }
  }
})

