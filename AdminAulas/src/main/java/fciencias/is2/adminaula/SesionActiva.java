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
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.StreamedContent;
/**
 *
 * @author jrivera
 */


@ManagedBean
@SessionScoped
public class SesionActiva implements Serializable {

    /**
     * Usuario correspondiente a esta sesión.
     */
    private Usuario usuario;

    /**
     * La página anterior. Sólo se usa para regresar después de iniciar
     * o cerrar sesión.
     */
    private String paginaAnterior;

    /**
     * El parámetro ID de la página anterior. Sólo se usa para recordar
     * qué puesto se estaba viendo si la página anterior es
     * DetallesPuesto. Tal vez exista una solución mejor.
     */
    private String paginaAnteriorId;

    public SesionActiva() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getPaginaAnterior() {
        return paginaAnterior;
    }

    public void setPaginaAnterior(String paginaAnterior) {
        this.paginaAnterior = paginaAnterior;
    }

    public String getPaginaAnteriorId() {
        return paginaAnteriorId;
    }

    public void setPaginaAnteriorId(String paginaAnteriorId) {
        this.paginaAnteriorId = paginaAnteriorId;
    }

    /**
     * Cierra la sesión actual.
     *
     * @return la página a la que se debe regresar
     */
    public String cerrarSesion() {
        usuario = null;
        return obtenerPaginaAnterior();
    }

    /**
     * Obtiene al usuario actual.
     *
     * @return el usuario actual o null si no ha iniciado sesión
     */
    public Usuario obtenerUsuarioActual() {
        return usuario;
    }


    /**
     * Dice si se ha iniciado sesión
     *
     * @return true si hay una sesión activa, false en otro caso
     */
    public boolean getHaySesionActiva() {
        return usuario != null;
    }

    /**
     * Dice si el usuario actual es un administrador.
     *
     * @return true si hay una sesión de administrador activa, false si no hay
     * una sesión activa o el usuario actual no es administrador
     */
    public boolean getEsAdministrador() {
        return usuario != null && usuario.getIsadministrador();
    }

    /**
     * Redirige a la página principal.
     *
     * @return una cadena que redirige a la página principal de academico o administrador
     */
    public String redirigeAPaginaPrincipal() {
        if(usuario.getIsacademico()){
            return "soloadmin?faces-redirect=true";
        }
        return "registro?faces-redirect=true";
    }
    
     public String redirigeAPaginaRestriccion() {
        return "restriccion?faces-redirect=true";
    }

    /**
     * Obtiene la dirección de la página anterior y olvida cuál era
     * (establece el atributo en null). En caso que no haya ninguna
     * registrada, regresa "index".
     *
     * @return una cadena que redirige a la página anterior, o a la página
     * principal si no se registró una página anterior
     */
    public String obtenerPaginaAnterior() {
        String pagina = "index";
        if (paginaAnterior != null) {
            pagina = paginaAnterior;
            paginaAnterior = null;
        }

        pagina += "?faces-redirect=true";

        if (paginaAnteriorId != null && !paginaAnteriorId.isEmpty()) {
            pagina += "&id=" + paginaAnteriorId;
            paginaAnteriorId = null;
        }

        return pagina;
    }

    /**
     * Regresa el identificador de la vista para iniciar sesión. Sólo
     * existe para poder ajustar el valor de la página anterior antes
     * de ir a la página.
     *
     * @return una cadena que redirige a la página para iniciar sesión
     */
    public String irAIniciarSesion() {
        return "index?faces-redirect=true";
    }
    
    public boolean esAdministrador(){
        return usuario.getIsadministrador();
    }
    
    public boolean esAcademico(){
        return usuario.getIsacademico();
    }

}
