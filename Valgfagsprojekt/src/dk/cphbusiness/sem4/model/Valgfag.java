package dk.cphbusiness.sem4.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
// Sequencer
@SequenceGenerator(name="valgfagSeq", sequenceName="valgfag_id_seq", allocationSize=1)

@Table(name = "VALGFAG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Valgfag.findAll", query = "SELECT v FROM Valgfag v"),
    @NamedQuery(name = "Valgfag.findByValgfagId", query = "SELECT v FROM Valgfag v WHERE v.valgfagId = :valgfagId"),
    @NamedQuery(name = "Valgfag.findByNavn", query = "SELECT v FROM Valgfag v WHERE v.navn = :navn"),
    @NamedQuery(name = "Valgfag.findByBeskrivelse", query = "SELECT v FROM Valgfag v WHERE v.beskrivelse = :beskrivelse"),
    @NamedQuery(name = "Valgfag.findByBpu", query = "SELECT v FROM Valgfag v WHERE v.bpu = :bpu")})
public class Valgfag implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    
    // Sequencer kobles på ID'et
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="valgfagSeq")
    
    
    @Basic(optional = false)
    @Column(name = "VALGFAG_ID")
    private int valgfagId;
    @Column(name = "NAVN")
    private String navn;
    @Column(name = "BESKRIVELSE")
    private String beskrivelse;
    @Column(name = "BPU")
    private String bpu;
    @ManyToMany(mappedBy = "valgfagCollection")
    private Collection<Studerende> studerendeCollection;
    @OneToMany(mappedBy = "p1b")
    private Collection<Runde1res> runde1resCollection;
    @OneToMany(mappedBy = "p2a")
    private Collection<Runde1res> runde1resCollection1;
    @OneToMany(mappedBy = "p2b")
    private Collection<Runde1res> runde1resCollection2;
    @OneToMany(mappedBy = "p1a")
    private Collection<Runde1res> runde1resCollection3;
    @OneToMany(mappedBy = "ap2")
    private Collection<Runde2res> runde2resCollection;
    @OneToMany(mappedBy = "bp1")
    private Collection<Runde2res> runde2resCollection1;
    @OneToMany(mappedBy = "bp2")
    private Collection<Runde2res> runde2resCollection2;
    @OneToMany(mappedBy = "ap1")
    private Collection<Runde2res> runde2resCollection3;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "valgfag")
    private Runde1 runde1;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "valgfag")
    private Runde2 runde2;

    public Valgfag() {
    }

    public Valgfag(int valgfagId) {
        this.valgfagId = valgfagId;
    }

    public int getValgfagId() {
        return valgfagId;
    }

    public void setValgfagId(int valgfagId) {
        this.valgfagId = valgfagId;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public String getBpu() {
        return bpu;
    }

    public void setBpu(String bpu) {
        this.bpu = bpu;
    }

    @XmlTransient
    public Collection<Studerende> getStuderendeCollection() {
        return studerendeCollection;
    }

    public void setStuderendeCollection(Collection<Studerende> studerendeCollection) {
        this.studerendeCollection = studerendeCollection;
    }

    @XmlTransient
    public Collection<Runde1res> getRunde1resCollection() {
        return runde1resCollection;
    }

    public void setRunde1resCollection(Collection<Runde1res> runde1resCollection) {
        this.runde1resCollection = runde1resCollection;
    }

    @XmlTransient
    public Collection<Runde1res> getRunde1resCollection1() {
        return runde1resCollection1;
    }

    public void setRunde1resCollection1(Collection<Runde1res> runde1resCollection1) {
        this.runde1resCollection1 = runde1resCollection1;
    }

    @XmlTransient
    public Collection<Runde1res> getRunde1resCollection2() {
        return runde1resCollection2;
    }

    public void setRunde1resCollection2(Collection<Runde1res> runde1resCollection2) {
        this.runde1resCollection2 = runde1resCollection2;
    }

    @XmlTransient
    public Collection<Runde1res> getRunde1resCollection3() {
        return runde1resCollection3;
    }

    public void setRunde1resCollection3(Collection<Runde1res> runde1resCollection3) {
        this.runde1resCollection3 = runde1resCollection3;
    }

    @XmlTransient
    public Collection<Runde2res> getRunde2resCollection() {
        return runde2resCollection;
    }

    public void setRunde2resCollection(Collection<Runde2res> runde2resCollection) {
        this.runde2resCollection = runde2resCollection;
    }

    @XmlTransient
    public Collection<Runde2res> getRunde2resCollection1() {
        return runde2resCollection1;
    }

    public void setRunde2resCollection1(Collection<Runde2res> runde2resCollection1) {
        this.runde2resCollection1 = runde2resCollection1;
    }

    @XmlTransient
    public Collection<Runde2res> getRunde2resCollection2() {
        return runde2resCollection2;
    }

    public void setRunde2resCollection2(Collection<Runde2res> runde2resCollection2) {
        this.runde2resCollection2 = runde2resCollection2;
    }

    @XmlTransient
    public Collection<Runde2res> getRunde2resCollection3() {
        return runde2resCollection3;
    }

    public void setRunde2resCollection3(Collection<Runde2res> runde2resCollection3) {
        this.runde2resCollection3 = runde2resCollection3;
    }

    public Runde1 getRunde1() {
        return runde1;
    }

    public void setRunde1(Runde1 runde1) {
        this.runde1 = runde1;
    }

    public Runde2 getRunde2() {
        return runde2;
    }

    public void setRunde2(Runde2 runde2) {
        this.runde2 = runde2;
    }

    @Override
    public String toString() {
        return "dk.cphbusiness.sem4.model.Valgfag[ valgfagId=" + valgfagId + " ]";
    }
    
}
