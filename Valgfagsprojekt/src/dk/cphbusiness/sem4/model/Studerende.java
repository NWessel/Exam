package dk.cphbusiness.sem4.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "STUDERENDE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Studerende.findAll", query = "SELECT s FROM Studerende s"),
    @NamedQuery(name = "Studerende.findByEmail", query = "SELECT s FROM Studerende s WHERE s.email = :email"),
    @NamedQuery(name = "Studerende.findByNavn", query = "SELECT s FROM Studerende s WHERE s.navn = :navn"),
    @NamedQuery(name = "Studerende.findByHarstemt1", query = "SELECT s FROM Studerende s WHERE s.harstemt1 = :harstemt1"),
    @NamedQuery(name = "Studerende.findByHarstemt2", query = "SELECT s FROM Studerende s WHERE s.harstemt2 = :harstemt2")})
public class Studerende implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "NAVN")
    private String navn;
    @Column(name = "HARSTEMT1")
    private int harstemt1;
    @Column(name = "HARSTEMT2")
    private int harstemt2;
    @JoinTable(name = "FINALVALGFAG", joinColumns = {
        @JoinColumn(name = "EMAIL", referencedColumnName = "EMAIL")}, inverseJoinColumns = {
        @JoinColumn(name = "VALGFAG_ID", referencedColumnName = "VALGFAG_ID")})
    @ManyToMany
    private Collection<Valgfag> valgfagCollection;

    public Studerende() {
    }

    public Studerende(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getHarstemt1() {
        return harstemt1;
    }

    public void setHarstemt1(int harstemt1) {
        this.harstemt1 = harstemt1;
    }

    public int getHarstemt2() {
        return harstemt2;
    }

    public void setHarstemt2(int harstemt2) {
        this.harstemt2 = harstemt2;
    }

    @XmlTransient
    public Collection<Valgfag> getValgfagCollection() {
        return valgfagCollection;
    }

    public void setValgfagCollection(Collection<Valgfag> valgfagCollection) {
        this.valgfagCollection = valgfagCollection;
    }

    @Override
    public String toString() {
        return "dk.cphbusiness.sem4.model.Studerende[ email=" + email + " ]";
    }
    
}
