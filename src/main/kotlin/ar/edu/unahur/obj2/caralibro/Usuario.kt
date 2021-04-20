package ar.edu.unahur.obj2.caralibro

class Usuario() {

  val publicaciones = mutableListOf<Publicacion>()

  val amigosDelUsuario = mutableListOf<Usuario>()

  fun agregarPublicacion(publicacion: Publicacion) {
    publicaciones.add(publicacion)
  }

  fun agregarUnAmigoNuevo(amigoNuevo: Usuario){
    check(!amigosDelUsuario.contains(amigoNuevo)){
      "El usuario que se quiere agregar ya es amigo"
    }
    amigosDelUsuario.add(amigoNuevo)
  }

  fun cantidadDeAmigosDelUsuario() = amigosDelUsuario.size

  fun espacioDePublicaciones() = publicaciones.sumBy { it.espacioQueOcupa() }

  fun darleMeGustaAUnaPublicacion(publicacion: Publicacion){
    publicacion.agregarUnUsuarioAUsuariosQueLeDieronMeGusta(this)
  }

  fun esMasAmistosoQue(usuarioAComparar: Usuario) = this.cantidadDeAmigosDelUsuario() > usuarioAComparar.cantidadDeAmigosDelUsuario()
}
