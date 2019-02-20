/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fciencias.is2.adminaula;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

/**
 *
 * @author jrivera
 */
@ManagedBean(name = "ejemplo")
@ViewScoped
public class Ejemplo implements java.io.Serializable {

    private String example;

    /**
     * Creates a new instance of Ejemplo
     */
    public Ejemplo() {
    }

    public String getExample() {
        return example;
    }

    public void setExample(String nuevo) {
        this.example = nuevo;
    }

    public void imprime() {
        System.out.println(example);
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
