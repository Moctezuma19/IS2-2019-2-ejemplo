/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fciencias.is2.adminaula;

import fciencias.is2.adminaula.modelo.Usuario;
import fciencias.is2.adminaula.modelo.manejador.UsuarioJpaController;
import fciencias.is2.adminaula.modelo.manejador.exceptions.*;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jrivera
 */
@ManagedBean
@ViewScoped
public class InicioSesion implements Serializable {

    private static final String MENSAJE_CUENTA_NO_EXISTE
            = "El usuario no existe";
    private static final String MENSAJE_CUENTA_NO_CONFIRMADA
            = "Esta cuenta no ha sido confirmada";
    private static final String MENSAJE_CUENTA_ELIMINADA
            = "Esta cuenta desactivada por el administrador";
    private static final String MENSAJE_CONTRASENA_INCORRECTA
            = "Contraseña incorrecta";

    private static final String MENSAJE_CORREO_ENVIADO
            = "Se ha reenviado el correo de confirmación a tu dirección de correo";
    private static final String MENSAJE_CORREO_NO_ENVIADO
            = "No se ha podido enviar el correo de confirmación."
            + " Vuelve a intentarlo más tarde.";

    /**
     * Controlador de JPA para buscar al usuario en la BD.
     */
    private final UsuarioJpaController bd_usuario;
    private final EntityManagerFactory emf;
    /**
     * La cuenta con la que se quiere iniciar sesión. Puede ser un nombre de
     * usuario o una dirección de correo.
     */
    private String cuenta;

    /**
     * Contraseña usada para iniciar sesión.
     */
    private String contrasena;

    /**
     * Bean de sesión para recordar el usuario que inicia de sesión.
     */
    @ManagedProperty("#{sesionActiva}")
    private SesionActiva sesionActiva;

    /**
     * La página a la que hay que ir luego de iniciar sesión.
     */
    private String paginaAnterior;

    /**
     * Bandera que indica si el usuario no ha sido confirmado.
     */
    private boolean sinConfirmar;

    public InicioSesion() {
        emf = Persistence.createEntityManagerFactory("AdminAula-PU");
        bd_usuario = new UsuarioJpaController(emf);
        //jpaUsuario = new FabricaControladorJpa().obtenerControladorJpaUsuario();
    }

    /**
     * Obtiene la página anterior desde sesionActiva. Obtenerlo aquí hace que
     * sesionActiva lo olvide en cuanto se entra a la página de iniciar sesión.
     */
    @PostConstruct
    private void init() {
        paginaAnterior = sesionActiva.obtenerPaginaAnterior();
    }

    /**
     * Inicia sesión con los datos actuales.
     *
     * @return La página que debe mostrar. null si hay errores o index si pudo
     * iniciar sesión.
     */
    public String iniciarSesion() {
        Usuario usuario = bd_usuario.buscarUsuario(cuenta);
        /* No hace nada si no está confirmada. */
        if (usuario == null) {
            return null;
        }
        sesionActiva.setUsuario(usuario);
        return paginaAnterior;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public SesionActiva getSesionActiva() {
        return sesionActiva;
    }

    public void setSesionActiva(SesionActiva sesionActiva) {
        this.sesionActiva = sesionActiva;
    }

}
