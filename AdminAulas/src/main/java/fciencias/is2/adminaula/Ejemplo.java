/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fciencias.is2.adminaula;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import fciencias.is2.adminaula.modelo.Usuario;
import fciencias.is2.adminaula.modelo.manejador.UsuarioJpaController;
import fciencias.is2.adminaula.modelo.manejador.exceptions.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jrivera
 */
@ManagedBean(name = "ejemplo")
@RequestScoped
public class Ejemplo implements java.io.Serializable {

    private final UsuarioJpaController bd_usuario;
    private final EntityManagerFactory emf;
    private Usuario academico;

    public Ejemplo() {
        emf = Persistence.createEntityManagerFactory("AdminAula-PU");
        bd_usuario = new UsuarioJpaController(emf);
        academico = new Usuario();
        academico.setIsacademico(true);
    }

    public Usuario getAcademico() {
        return academico;
    }

    public void setAcademico(Usuario nuevo) {
        this.academico = nuevo;
    }

    public void guarda() {
        try {
            bd_usuario.create(academico);
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("./restriccion.xhtml");
            System.out.println("pase");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    /*
    public boolean validaRFC(FacesContext context,
            UIComponent component,
            Object value) throws ValidatorException {
        String patron = "([A-Z]^{3})([0-9]^{6})[A-Z][A-Z]";
        Pattern patr = Pattern.compile(patron);
        Matcher match = patr.matcher((String) value);
        if (!match.matches()) {
            FacesMessage mensajeError = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No es un RFC", null);
            throw new ValidatorException(mensajeError);
        }
        return false;
    }*/

    public void imprime() {
        //System.out.println(example);
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("./ejemplo.xhtml");
        } catch (Exception e) {
            FacesMessage facesMessage
                    = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "ERROR 01" + e.toString(), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

}
