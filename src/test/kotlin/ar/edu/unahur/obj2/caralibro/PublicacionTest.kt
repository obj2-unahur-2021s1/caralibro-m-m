package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class PublicacionTest : DescribeSpec ({

    describe("publicaciones"){
        val fotoEnCuzco = Foto(768, 1024)
        val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")

        describe("de tipo foto"){
            it("se crea correctamente una publicacion del tipo foto"){
                (fotoEnCuzco != null).shouldBeTrue()
            }
        }
        describe("de tipo texto"){
            it("se crea correctamente una publicacion del tipo texto"){
                (saludoCumpleanios != null).shouldBeTrue()
            }

        }





    }



})