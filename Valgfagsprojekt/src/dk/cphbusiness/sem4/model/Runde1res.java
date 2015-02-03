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
@Table(name = "RUNDE1RES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Runde1res.findAllValgfag", query = "SELECT r FROM Valgfag r"),
    @NamedQuery(name = "Runde1res.findAll", query = "SELECT r FROM Runde1res r"),
    @NamedQuery(name = "Runde1res.findByEmail", query = "SELECT r FROM Runde1res r WHERE r.email = :email")})
public class Runde1res implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    @JoinColumn(name = "P1B", referencedColumnName = "VALGFAG_ID")
    @ManyToOne
    private Valgfag p1b;
    @JoinColumn(name = "P2A", referencedColumnName = "VALGFAG_ID")
    @ManyToOne
    private Valgfag p2a;
    @JoinColumn(name = "P2B", referencedColumnName = "VALGFAG_ID")
    @ManyToOne
    private Valgfag p2b;
    @JoinColumn(name = "P1A", referencedColumnName = "VALGFAG_ID")
    @ManyToOne
    private Valgfag p1a;

    public Runde1res() {
    }

    public Runde1res(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Valgfag getP1b() {
        return p1b;
    }

    public void setP1b(Valgfag p1b) {
        this.p1b = p1b;
    }

    public Valgfag getP2a() {
        return p2a;
    }

    public void setP2a(Valgfag p2a) {
        this.p2a = p2a;
    }

    public Valgfag getP2b() {
        return p2b;
    }

    public void setP2b(Valgfag p2b) {
        this.p2b = p2b;
    }

    public Valgfag getP1a() {
        return p1a;
    }

    public void setP1a(Valgfag p1a) {
        this.p1a = p1a;
    }

    @Override
    public String toString() {
        return "dk.cphbusiness.sem4.model.Runde1res[ email=" + email + " ]";
    }
    
}
