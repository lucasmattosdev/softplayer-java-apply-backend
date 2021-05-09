package br.com.lucasmattos.softplayer.java.apply.backend.core;

import br.com.lucasmattos.softplayer.java.apply.backend.entity.UsuarioEntity;

public class SessionApplication {
    private static final ThreadLocal userThreadLocal = new ThreadLocal();

    public static void setUsuario(UsuarioEntity usuario) {
        userThreadLocal.set(usuario);
    }
    
    public static UsuarioEntity getUsuario() {
        return (UsuarioEntity) userThreadLocal.get();
    }

    public static void remove() {
        userThreadLocal.remove();
    }
}
