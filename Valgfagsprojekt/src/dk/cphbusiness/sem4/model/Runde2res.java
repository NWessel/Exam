package dk.cphbusiness.sem4.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "RUNDE2RES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Runde2res.findAll", query = "SELECT r FROM Runde2res r"),
    @NamedQuery(name = "Runde2res.findByEmail", query = "SELECT r FROM Runde2res r WHERE r.email = :email")})
public class Runde2res implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    @JoinColumn(name = "AP2", referencedColumnName = "VALGFAG_ID")
    @ManyToOne
    private Valgfag ap2;
    @JoinColumn(name = "BP1", referencedColumnName = "VALGFAG_ID")
    @ManyToOne
    private Valgfag bp1;
    @JoinColumn(name = "BP2", referencedColumnName = "VALGFAG_ID")
    @ManyToOne
    private Valgfag bp2;
    @JoinColumn(name = "AP1", referencedColumnName = "VALGFAG_ID")
    @ManyToOne
    private Valgfag ap1;

    public Runde2res() {
    }

    public Runde2res(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Valgfag getAp2() {
        return ap2;
    }

    public void setAp2(Valgfag ap2) {
        this.ap2 = ap2;
    }

    public Valgfag getBp1() {
        return bp1;
    }

    public void setBp1(Valgfag bp1) {
        this.bp1 = bp1;
    }

    public Valgfag getBp2() {
        return bp2;
    }

    public void setBp2(Valgfag bp2) {
        this.bp2 = bp2;
    }

    public Valgfag getAp1() {
        return ap1;
    }

    public void setAp1(Valgfag ap1) {
        this.ap1 = ap1;
    }

    @Override
    public String toString() {
        return "dk.cphbusiness.sem4.model.Runde2res[ email=" + email + " ]";
    }
    
}
