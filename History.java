/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author basti
 */
@Entity
@Table(name = "HISTORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "History.findAll", query = "SELECT h FROM History h"),
    @NamedQuery(name = "History.findById", query = "SELECT h FROM History h WHERE h.id = :id"),
    @NamedQuery(name = "History.findByIdcontact", query = "SELECT h FROM History h WHERE h.idcontact = :idcontact"),
    @NamedQuery(name = "History.findByIduser", query = "SELECT h FROM History h WHERE h.iduser = :iduser"),
    @NamedQuery(name = "History.findHistory", query = "SELECT h FROM History h WHERE h.iduser = :iduser"),
    @NamedQuery(name = "History.findEntity", query = "SELECT c FROM History fromm, History too, Contacts c WHERE fromm.idcontact = too.idcontact AND fromm.iduser != too.iduser AND c.id = too.idcontact AND fromm.iduser = :currentUserId AND too.iduser = :selectedUserId"),
    @NamedQuery(name = "History.updatedContact", query = "UPDATE Contacts c SET c.sort = :selectedSort, c.addedby = :selectedUser WHERE c.id = :contactID"),
    })
public class History implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCONTACT")
    private int idcontact;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDUSER")
    private int iduser;

    public History() {
    }

    public History(Integer id) {
        this.id = id;
    }

    public History(Integer id, int idcontact, int iduser) {
        this.id = id;
        this.idcontact = idcontact;
        this.iduser = iduser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdcontact() {
        return idcontact;
    }

    public void setIdcontact(int idcontact) {
        this.idcontact = idcontact;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
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
        if (!(object instanceof History)) {
            return false;
        }
        History other = (History) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sessionbeans.History[ id=" + id + " ]";
    }
    
}
