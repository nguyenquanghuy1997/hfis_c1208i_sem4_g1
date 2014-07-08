/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author The
 */
@Entity
@Table(name = "LabSchedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LabSchedule.findAll", query = "SELECT l FROM LabSchedule l"),
    @NamedQuery(name = "LabSchedule.findById", query = "SELECT l FROM LabSchedule l WHERE l.id = :id"),
    @NamedQuery(name = "LabSchedule.findBySlot", query = "SELECT l FROM LabSchedule l WHERE l.slot = :slot"),
    @NamedQuery(name = "LabSchedule.findByDate", query = "SELECT l FROM LabSchedule l WHERE l.date = :date"),
    @NamedQuery(name = "LabSchedule.findByDetail", query = "SELECT l FROM LabSchedule l WHERE l.detail = :detail")})
public class LabSchedule implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Slot")
    private Integer slot;
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Size(max = 50)
    @Column(name = "Detail")
    private String detail;
    @JoinColumn(name = "RequestAccount", referencedColumnName = "Username")
    @ManyToOne
    private Account requestAccount;

    public LabSchedule() {
    }

    public LabSchedule(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Account getRequestAccount() {
        return requestAccount;
    }

    public void setRequestAccount(Account requestAccount) {
        this.requestAccount = requestAccount;
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
        if (!(object instanceof LabSchedule)) {
            return false;
        }
        LabSchedule other = (LabSchedule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LabSchedule[ id=" + id + " ]";
    }
    
}