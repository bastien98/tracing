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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author basti
 */
@Entity
@Table(name = "CONTACTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contacts.findAll", query = "SELECT c FROM Contacts c"),
    @NamedQuery(name = "Contacts.findById", query = "SELECT c FROM Contacts c WHERE c.id = :id"),
    @NamedQuery(name = "Contacts.findBySort", query = "SELECT c FROM Contacts c WHERE c.sort = :sort"),
    @NamedQuery(name = "Contacts.findByAddedby", query = "SELECT c FROM Contacts c WHERE c.addedby = :addedby"),
    @NamedQuery(name = "Contacts.findByAddedby", query = "SELECT c FROM Contacts c WHERE c.addedby = :addedby"),
    @NamedQuery (name = "Contacts.hvlNauw", query = "SELECT count(c.addedby) FROM Contacts c where c.addedby = :currentUsername and c.sort = 'nauw'"),
    @NamedQuery (name = "Contacts.hvlMiddelmatig", query = "SELECT count(c.addedby) FROM Contacts c where c.addedby = :currentUsername and c.sort = 'middelmatig'"),
    @NamedQuery (name = "Contacts.hvlVeilig", query = "SELECT count(c.addedby) FROM Contacts c where c.addedby = :currentUsername and c.sort = 'veilig'"),
    @NamedQuery (name = "Contacts.countNauwRows", query = "SELECT count(c.id) FROM Contacts c where c.sort = 'nauw'"),
    @NamedQuery (name = "Contacts.countGewoonRows", query = "SELECT count(c.id) FROM Contacts c where c.sort = 'middelmatig'"),
    @NamedQuery (name = "Contacts.countVeiligRows", query = "SELECT count(c.id) FROM Contacts c where c.sort = 'veilig'"),
    @NamedQuery(name = "Contacts.updateCseenToYes" , query = "UPDATE Contacts SET cseen='yes' WHERE id= :id"),
    @NamedQuery(name = "Contacts.findByAddedbyWithSeenNo", query = "SELECT c FROM Contacts c WHERE c.addedby = :addedby and c.cseen='no'")})
public class Contacts implements Serializable {

    @Size(max = 20)
    @Column(name = "CSEEN")
    private String cseen;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 20)
    @Column(name = "SORT")
    private String sort;
    @Size(max = 20)
    @Column(name = "ADDEDBY")
    private String addedby;

    public Contacts() {
    }

    public Contacts(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getAddedby() {
        return addedby;
    }

    public void setAddedby(String addedby) {
        this.addedby = addedby;
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
        if (!(object instanceof Contacts)) {
            return false;
        }
        Contacts other = (Contacts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sessionbeans.Contacts[ id=" + id + " ]";
    }

    public String getCseen() {
        return cseen;
    }

    public void setCseen(String cseen) {
        this.cseen = cseen;
    }
    
}
