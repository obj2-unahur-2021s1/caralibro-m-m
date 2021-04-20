package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class PublicacionTest : DescribeSpec ({

    describe("publicaciones"){

        val fotoEnCuzco = Foto(768, 1024)
        val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
        val videoEnAruba = Video(30,CalidadSd)
        val videoEnMiami = Video(50,Calidad720)

        describe("de tipo foto"){
            it("se crea correctamente una publicacion del tipo foto"){
                fotoEnCuzco.shouldNotBeNull()
            }
            it("el tamanio que ocupa deberia ser 550548"){
                fotoEnCuzco.espacioQueOcupa().shouldBe(550503)
            }
            it("se crea una nueva foto, se modifica el factor de compresion y se les aplica a las 2"){
                val fotoEnElObelisco = Foto(768,1020)
                fotoEnElObelisco.cambiarElValorDeCompresion(0.5)
                fotoEnCuzco.espacioQueOcupa().shouldBe(393216)
                fotoEnElObelisco.espacioQueOcupa().shouldBe(391680)
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