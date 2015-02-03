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
@Table(name = "RUNDE2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Runde2.findAll", query = "SELECT r FROM Runde2 r"),
    @NamedQuery(name = "Runde2.findByValgfagId", query = "SELECT r FROM Runde2 r WHERE r.valgfagId = :valgfagId"),
    @NamedQuery(name = "Runde2.deleteAll", query = "DELETE FROM Runde2 r WHERE r.valgfagId >:tal"),
    @NamedQuery(name = "Runde2.findByPulje", query = "SELECT r FROM Runde2 r WHERE r.pulje = :pulje")})
public class Runde2 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "VALGFAG_ID")
    private int valgfagId;
    @Column(name = "PULJE")
    private String pulje;
    @JoinColumn(name = "VALGFAG_ID", referencedColumnName = "VALGFAG_ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Valgfag valgfag;

    public Runde2() {
    }

    public Runde2(int valgfagId) {
        this.valgfagId = valgfagId;
    }

    public int getValgfagId() {
        return valgfagId;
    }

    public void setValgfagId(int valgfagId) {
        this.valgfagId = valgfagId;
    }

    public String getPulje() {
        return pulje;
    }

    public void setPulje(String pulje) {
        this.pulje = pulje;
    }

    public Valgfag getValgfag() {
        return valgfag;
    }

    public void setValgfag(Valgfag valgfag) {
        this.valgfag = valgfag;
    }

    @Override
    public String toString() {
        return "dk.cphbusiness.sem4.model.Runde2[ valgfagId=" + valgfagId + " ]";
    }
    
}
