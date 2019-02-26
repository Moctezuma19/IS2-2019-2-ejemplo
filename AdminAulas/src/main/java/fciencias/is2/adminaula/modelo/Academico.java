/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fciencias.is2.adminaula.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jrivera
 */
@Entity
@Table(name = "academico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Academico.findAll", query = "SELECT a FROM Academico a")
    , @NamedQuery(name = "Academico.findByNombre", query = "SELECT a FROM Academico a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Academico.findByApellido1", query = "SELECT a FROM Academico a WHERE a.apellido1 = :apellido1")
    , @NamedQuery(name = "Academico.findByApellido2", query = "SELECT a FROM Academico a WHERE a.apellido2 = :apellido2")
    , @NamedQuery(name = "Academico.findByRfc", query = "SELECT a FROM Academico a WHERE a.rfc = :rfc")
    , @NamedQuery(name = "Academico.findByCorreo", query = "SELECT a FROM Academico a WHERE a.correo = :correo")
    , @NamedQuery(name = "Academico.findByDepto", query = "SELECT a FROM Academico a WHERE a.depto = :depto")})
public class Academico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 200)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 200)
    @Column(name = "apellido1")
    private String apellido1;
    @Size(max = 200)
    @Column(name = "apellido2")
    private String apellido2;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "rfc")
    private String rfc;
    @Size(max = 200)
    @Column(name = "correo")
    private String correo;
    @Size(max = 40)
    @Column(name = "depto")
    private String depto;

    public Academico() {
    }

    public Academico(String rfc) {
        this.rfc = rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDepto() {
        return depto;
    }

    public void setDepto(String depto) {
        this.depto = depto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rfc != null ? rfc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Academico)) {
            return false;
        }
        Academico other = (Academico) object;
        if ((this.rfc == null && other.rfc != null) || (this.rfc != null && !this.rfc.equals(other.rfc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fciencias.is2.adminaula.modelo.Academico[ rfc=" + rfc + " ]";
    }
    
}
