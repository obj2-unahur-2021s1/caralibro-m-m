package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class PublicacionTest : DescribeSpec ({

    describe("publicaciones"){
        val calidadSd = Sd()

        val fotoEnCuzco = Foto(768, 1024)
        val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
        val videoEnAruba = Video(30,calidadSd)
        describe("de tipo foto"){
            it("se crea correctamente una publicacion del tipo foto"){
                fotoEnCuzco.shouldNotBeNull()
            }
            it("el tamanio que ocupa deberia ser 550548"){

            }
        }
        describe("de tipo texto"){
            it("se crea correctamente una publicacion del tipo texto"){
                saludoCumpleanios.shouldNotBeNull()
            }

        }
        describe("de tipo video"){
            it("se crea correctamente un video"){
                videoEnAruba.shouldNotBeNull()
            }
        }






    }



})