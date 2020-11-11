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
@Table(name = "TESTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tests.findAll", query = "SELECT t FROM Tests t"),
    @NamedQuery(name = "Tests.findById", query = "SELECT t FROM Tests t WHERE t.id = :id"),
    @NamedQuery(name = "Tests.findByUsername", query = "SELECT t FROM Tests t WHERE t.username = :username"),
    @NamedQuery(name = "Tests.findByStatus", query = "SELECT t FROM Tests t WHERE t.status = :status"),
    @NamedQuery(name = "Tests.findByNotified", query = "SELECT t FROM Tests t WHERE t.notified = :notified"),
    @NamedQuery(name = "Tests.findNauw", query = "SELECT t.username from History fromm, History too, Contacts c, Tests t where fromm.idcontact = too.idcontact and fromm.username != too.username and c.id = fromm.idcontact and (fromm.username = :currentUsername or too.username = :currentUsername ) and too.username = t.username and t.status ='positief' and c.sort ='nauw'"),
    @NamedQuery(name = "Tests.findMiddelmatig", query = "SELECT t.username from History fromm, History too, Contacts c, Tests t where fromm.idcontact = too.idcontact and fromm.username != too.username and c.id = fromm.idcontact and (fromm.username = :currentUsername or too.username = :currentUsername ) and too.username = t.username and t.status ='positief' and c.sort ='middelmatig'")})
public class Tests implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 20)
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 20)
    @Column(name = "STATUS")
    private String status;
    @Size(max = 20)
    @Column(name = "NOTIFIED")
    private String notified;

    public Tests() {
    }

    public Tests(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotified() {
        return notified;
    }

    public void setNotified(String notified) {
        this.notified = notified;
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
        if (!(object instanceof Tests)) {
            return false;
        }
        Tests other = (Tests) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sessionbeans.Tests[ id=" + id + " ]";
    }
    
}
