package dk.cphbusiness.sem4.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "RUNDE1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Runde1.findAll", query = "SELECT r FROM Valgfag r"),
    @NamedQuery(name = "Runde1.findByValgfagId", query = "SELECT r FROM Runde1 r WHERE r.valgfagId = :valgfagId")})
public class Runde1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "VALGFAG_ID")
    private int valgfagId;
    @JoinColumn(name = "VALGFAG_ID", referencedColumnName = "VALGFAG_ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Valgfag valgfag;

    public Runde1() {
    }

    public Runde1(int valgfagId) {
        this.valgfagId = valgfagId;
    }

    public int getValgfagId() {
        return valgfagId;
    }

    public void setValgfagId(int valgfagId) {
        this.valgfagId = valgfagId;
    }

    public Valgfag getValgfag() {
        return valgfag;
    }

    public void setValgfag(Valgfag valgfag) {
        this.valgfag = valgfag;
    }

    @Override
    public String toString() {
        return "dk.cphbusiness.sem4.model.Runde1[ valgfagId=" + valgfagId + " ]";
    }  
}
