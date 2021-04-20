package ar.edu.unahur.obj2.caralibro

import kotlin.math.ceil

abstract class Publicacion {
  var usuariosQueLeGusta = mutableListOf<Usuario>()
  abstract fun espacioQueOcupa(): Int
  //cambie a la forma que dijiste vos
  fun cantidadDeMeGustasQueTieneLaPublicacion() = usuariosQueLeGusta.size

  fun agregarUnUsuarioAUsuariosQueLeDieronMeGusta(usuarioQueLeDioMg: Usuario){
    check(!usuariosQueLeGusta.contains(usuarioQueLeDioMg)){
      "El usuario ya le dio me gusta a la publicacion"
    }
    usuariosQueLeGusta.add(usuarioQueLeDioMg)
  }
}



class Foto(val alto: Int, val ancho: Int) : Publicacion() {
  val factorDeCompresionDeFoto = factorDeCompresion
  override fun espacioQueOcupa() = ceil(alto * ancho * factorDeCompresionDeFoto.factor).toInt()

  fun cambiarElValorDeCompresion(nuevoValor : Double){
    factorDeCompresion.cambiarFactorDeCompresion(nuevoValor)
  }
}

class Texto(val contenido: String) : Publicacion() {
  override fun espacioQueOcupa() = contenido.length
}
class Video(val duracion: Int, val calidad: Calidad) : Publicacion(){
  var calidadDeVideo = calidad
  override fun espacioQueOcupa() = duracion * calidadDeVideo.valorPorCalidad()

  fun cambiarCalidad( nuevaCalidad: Calidad){
      calidadDeVideo = nuevaCalidad
  }
}

abstract class Calidad() {
 open fun valorPorCalidad() = 1
}
object CalidadSd : Calidad(){

}

object Calidad720 : Calidad() {
  override fun valorPorCalidad() = 3
}

object Calidad1080: Calidad() {
  override fun valorPorCalidad() = Calidad720.valorPorCalidad() * 2
}
object factorDeCompresion{
  var factor = 0.7
  fun cambiarFactorDeCompresion(nuevoFactor: Double){
    factor = nuevoFactor
  }
}
