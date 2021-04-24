package ar.edu.unahur.obj2.caralibro

class Usuario() {

  val publicaciones = mutableListOf<Publicacion>()

  val amigosDelUsuario = mutableListOf<Usuario>()

  val listaDeExclusion = mutableListOf<Usuario>()

  fun agregarPublicacion(publicacion: Publicacion) {
    publicaciones.add(publicacion)
  }

  // esta función es temporal para saber la cantidad de publicaciones de alguien
  fun cantidadPublicaciones() = publicaciones.size

  fun agregarUnAmigoNuevo(amigoNuevo: Usuario){
    check(!amigosDelUsuario.contains(amigoNuevo)){
      "El usuario que se quiere agregar ya es amigo"
    }
    amigosDelUsuario.add(amigoNuevo)
  }

  fun agregarUnUsuarioALaListaDeExcluidos(usuarioAAgregar: Usuario){
    check(!listaDeExclusion.contains(usuarioAAgregar)){
      "El usuario que se quiere agregar ya forma parte de la lista de usuarios de exclusion"
    }
    listaDeExclusion.add(usuarioAAgregar)
  }

  fun cantidadDeAmigosDelUsuario() = amigosDelUsuario.size

  fun espacioDePublicaciones() = publicaciones.sumBy { it.espacioQueOcupa() }

  fun darleMeGustaAUnaPublicacion(publicacion: Publicacion){
    publicacion.agregarUnUsuarioAUsuariosQueLeDieronMeGusta(this)
  }

  fun esMasAmistosoQue(usuarioAComparar: Usuario) = this.cantidadDeAmigosDelUsuario() > usuarioAComparar.cantidadDeAmigosDelUsuario()

  fun permiteVerLaPublicacion(usuarioQueDeseaVerLaPublicacion: Usuario,publicacion: Publicacion) = publicacion.puedeSerVistaPorUnUsuario(usuarioQueDeseaVerLaPublicacion,this)

  fun puedeVerTodasLasPublicaciones(usuarioQuePodria: Usuario) = publicaciones.all {it.puedeSerVistaPorUnUsuario(usuarioQuePodria,this)}

  // revisar
  fun mejoresAmigos() = amigosDelUsuario.filter { it.puedeVerTodasLasPublicaciones(it) }
  // cambiaría 'it' por 'this', ya que cada uno de la lista puede ver las publicaciones de la instancia
  //fun mejoresAmigos() = amigosDelUsuario.filter { it.puedeVerTodasLasPublicaciones(this) }


  fun cuantasPublicacionesMiasPuedeVer(otroAmigo: Usuario) = publicaciones.count { it.puedeSerVistaPorUnUsuario(otroAmigo, this) }
  // fun amigoMasPopular() = amigosDelUsuario.maxOf { it.cuantasPublicacionesMiasPuedeVer(it) }

  fun amigoMasPopular() = amigosDelUsuario.maxByOrNull { this.cuantasPublicacionesMiasPuedeVer(it) }

  //etapa 7
  fun meStalkea(algunUsuario: Usuario) = this.cantidadDeMgQueLeDioUnUsuarioAMisPublicaciones(algunUsuario) >= (this.cantidadPublicaciones()*0.9)

  fun cantidadDeMgQueLeDioUnUsuarioAMisPublicaciones(unUsuario:Usuario) = publicaciones.count { it.elUsuarioLeDioMgALaPublicacion(unUsuario)}
}
