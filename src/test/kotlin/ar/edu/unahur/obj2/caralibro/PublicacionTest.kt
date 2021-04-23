package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class PublicacionTest : DescribeSpec ({

    describe("publicaciones"){

        val fotoEnCuzco = Foto(768, 1024,publico)
        val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz",soloAmigos)
        val videoEnAruba = Video(30,CalidadSd,privadoConListaDePermitidos)
        val videoEnMiami = Video(50,Calidad720,publicoConListaDeExcluidos)
        val videoEnPaloAlto = Video(60,Calidad1080,publico)

        describe("de tipo foto"){
            it("se crea correctamente una publicacion del tipo foto"){
                fotoEnCuzco.shouldNotBeNull()
            }
            it("el tamanio que ocupa deberia ser 550548"){
                fotoEnCuzco.espacioQueOcupa().shouldBe(550503)
            }
            it("se crea una nueva foto, se modifica el factor de compresion y se les aplica a las 2"){
                val fotoEnElObelisco = Foto(768,1020,soloAmigos)
                fotoEnElObelisco.cambiarElValorDeCompresion(0.5)
                fotoEnCuzco.espacioQueOcupa().shouldBe(393216)
                fotoEnElObelisco.espacioQueOcupa().shouldBe(391680)
            }
        }
        describe("de tipo texto"){
            it("se crea correctamente una publicacion del tipo texto"){
                saludoCumpleanios.shouldNotBeNull()
            }
            it("el espacio que ocupa es 45"){
                (saludoCumpleanios.espacioQueOcupa() == 45).shouldBeTrue()
            }

        }
        describe("de tipo video"){
            it("se crea correctamente un video"){
                videoEnAruba.shouldNotBeNull()
            }
            it("El espacio del video en miami deberia ser 150"){
                (videoEnMiami.espacioQueOcupa() == 150).shouldBeTrue()
            }
            it("el espacio del video en miami no es 200"){
                (videoEnMiami.espacioQueOcupa() == 200).shouldBeFalse()
            }
            it("el espacio del video en palo alto deberia ser 360 "){
                (videoEnPaloAlto.espacioQueOcupa() == 360).shouldBeTrue()
            }
            it("el espacio del video en palo alto no es 300"){
                (videoEnPaloAlto.espacioQueOcupa() == 300).shouldBeFalse()
            }
        }
    }
})