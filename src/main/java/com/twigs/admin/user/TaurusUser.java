package com.twigs.admin.user;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TAURUS_USER")
public class TaurusUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "TAURUS_ID")
    private int taurusId;
    @Column(name = "DISPLAY_NAME")
    private String displayName;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "AUDIT_COMPLETE")
    private boolean auditComplete;

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "TAURUS_USER_PERMISSION",
//                joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
//                inverseJoinColumns = {@JoinColumn(name = "PERMISSION_ID", referencedColumnName = "ID")})
//    private List<TaurusPermission> taurusPermissions;


    public TaurusUser() {
    }

    public TaurusUser( int taurusId, String displayName, String username, boolean auditComplete ) {
        this.taurusId = taurusId;
        this.displayName = displayName;
        this.username = username;
        this.auditComplete = auditComplete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaurusId() {
        return taurusId;
    }

    public void setTaurusId(int taurusId) {
        this.taurusId = taurusId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAuditComplete() {
        return auditComplete;
    }

    public void setAuditComplete( boolean auditComplete ) {
        this.auditComplete = auditComplete;
    }
}
