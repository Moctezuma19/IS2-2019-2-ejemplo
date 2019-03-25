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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jrivera
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id")
    , @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo")
    , @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password")
    , @NamedQuery(name = "Usuario.findByIsacademico", query = "SELECT u FROM Usuario u WHERE u.isacademico = :isacademico")
    , @NamedQuery(name = "Usuario.findByIsadministrador", query = "SELECT u FROM Usuario u WHERE u.isadministrador = :isadministrador")
    , @NamedQuery(name = "Usuario.findByRfc", query = "SELECT u FROM Usuario u WHERE u.rfc = :rfc")
    , @NamedQuery(name = "Usuario.findByDepto", query = "SELECT u FROM Usuario u WHERE u.depto = :depto")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByApellido1", query = "SELECT u FROM Usuario u WHERE u.apellido1 = :apellido1")
    , @NamedQuery(name = "Usuario.findByApellido2", query = "SELECT u FROM Usuario u WHERE u.apellido2 = :apellido2")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "correo")
    private String correo;
    @Size(max = 20)
    @Column(name = "password")
    private String password;
    @Column(name = "isacademico")
    private Boolean isacademico;
    @Column(name = "isadministrador")
    private Boolean isadministrador;
    @Size(max = 18)
    @Column(name = "rfc")
    private String rfc;
    @Size(max = 40)
    @Column(name = "depto")
    private String depto;
    @Size(max = 200)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 200)
    @Column(name = "apellido1")
    private String apellido1;
    @Size(max = 200)
    @Column(name = "apellido2")
    private String apellido2;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsacademico() {
        return isacademico;
    }

    public void setIsacademico(Boolean isacademico) {
        this.isacademico = isacademico;
    }

    public Boolean getIsadministrador() {
        return isadministrador;
    }

    public void setIsadministrador(Boolean isadministrador) {
        this.isadministrador = isadministrador;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getDepto() {
        return depto;
    }

    public void setDepto(String depto) {
        this.depto = depto;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fciencias.is2.adminaula.modelo.Usuario[ id=" + id + " ]";
    }
    
}
