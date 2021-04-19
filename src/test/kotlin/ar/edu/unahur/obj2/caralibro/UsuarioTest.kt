package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class UsuarioTest : DescribeSpec({
  describe("Caralibro") {
    val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
    val fotoEnCuzco = Foto(768, 1024)

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
      
    }
  }
})
