package ar.edu.unahur.obj2.caralibro

import kotlin.math.ceil

abstract class Publicacion {
  var usuariosQueLeGusta = mutableListOf<Usuario>()
  abstract fun espacioQueOcupa(): Int

  abstract fun puedeSerVistaPorUnUsuario(usuarioQueQuiereVerla: Usuario, usuarioQueLaTiene: Usuario)
  //cambie a la forma que dijiste vos
  fun cantidadDeMeGustasQueTieneLaPublicacion() = usuariosQueLeGusta.size

  fun agregarUnUsuarioAUsuariosQueLeDieronMeGusta(usuarioQueLeDioMg: Usuario){
    check(!usuariosQueLeGusta.contains(usuarioQueLeDioMg)){
      "El usuario ya le dio me gusta a la publicacion"
    }
    usuariosQueLeGusta.add(usuarioQueLeDioMg)
  }


}

class Foto(val alto: Int, val ancho: Int, val permiso: Permiso) : Publicacion() {
  val factorDeCompresionDeFoto = factorDeCompresion
  override fun espacioQueOcupa() = ceil(alto * ancho * factorDeCompresionDeFoto.factor).toInt()
  override fun puedeSerVistaPorUnUsuario(usuarioQueQuiereVerla: Usuario, usuarioQueLaTiene: Usuario) {
    permiso.puedeVerLaPublicacion(usuarioQueQuiereVerla, usuarioQueLaTiene)
  }

  fun cambiarElValorDeCompresion(nuevoValor : Double){
    factorDeCompresion.cambiarFactorDeCompresion(nuevoValor)
  }

}

class Texto(val contenido: String,val permiso: Permiso) : Publicacion() {
  override fun espacioQueOcupa() = contenido.length
  override fun puedeSerVistaPorUnUsuario(usuarioQueQuiereVerla: Usuario, usuarioQueLaTiene: Usuario) {
    permiso.puedeVerLaPublicacion(usuarioQueQuiereVerla, usuarioQueLaTiene)
  }
}

class Video(val duracion: Int, val calidad: Calidad,val permiso: Permiso) : Publicacion(){
  var calidadDeVideo = calidad
  override fun espacioQueOcupa() = duracion * calidadDeVideo.valorPorCalidad()

  override fun puedeSerVistaPorUnUsuario(usuarioQueQuiereVerla: Usuario, usuarioQueLaTiene: Usuario) {
    permiso.puedeVerLaPublicacion(usuarioQueQuiereVerla, usuarioQueLaTiene)
  }

  fun cambiarCalidadA720(){
    calidadDeVideo = Calidad720
  }
  fun cambiarCalidadA1080(){
    calidadDeVideo = Calidad1080
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

abstract class Permiso(){
  abstract fun puedeVerLaPublicacion(usuarioQueQuiereVerLaPublicacion :Usuario, usuarioQueTieneLaPublicacion :Usuario) : Boolean
}
object publico: Permiso() {
  override fun puedeVerLaPublicacion(usuarioQueQuiereVerLaPublicacion: Usuario, usuarioQueTieneLaPublicacion :Usuario) = true
}
object soloAmigos: Permiso() {
  override fun puedeVerLaPublicacion(usuarioQueQuiereVerLaPublicacion: Usuario, usuarioQueTieneLaPublicacion :Usuario) =  usuarioQueTieneLaPublicacion.amigosDelUsuario.contains(usuarioQueQuiereVerLaPublicacion)
}
object privadoConListaDePermitidos: Permiso(){
  override fun puedeVerLaPublicacion(usuarioQueQuiereVerLaPublicacion: Usuario, usuarioQueTieneLaPublicacion :Usuario) =  usuarioQueTieneLaPublicacion.listaDeExclusion.contains(usuarioQueQuiereVerLaPublicacion)
}

object publicoConListaDeExcluidos: Permiso(){
  override fun puedeVerLaPublicacion(usuarioQueQuiereVerLaPublicacion: Usuario, usuarioQueTieneLaPublicacion :Usuario) =  !usuarioQueTieneLaPublicacion.listaDeExclusion.contains(usuarioQueQuiereVerLaPublicacion)
}