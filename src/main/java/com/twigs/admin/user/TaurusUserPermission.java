package com.twigs.admin.user;

import javax.persistence.*;

@Entity
@Table(name = "TAURUS_USER_PERMISSION")
public class TaurusUserPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "TAURUS_USER_ID")
    private int taurusUserId;
    @Column(name = "TAURUS_PERMISSION_ID")
    private int taurusPermissionId;
    @Column(name = "AUTHORISED")
    private boolean authorised;

    public TaurusUserPermission() {
    }

    public TaurusUserPermission(int taurusUserId, int taurusPermissionId, boolean authorised) {
        this.taurusUserId = taurusUserId;
        this.taurusPermissionId = taurusPermissionId;
        this.authorised = authorised;
    }

    public TaurusUserPermission(int taurusUserId, int taurusPermissionId) {
        this.taurusUserId = taurusUserId;
        this.taurusPermissionId = taurusPermissionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaurusUserId() {
        return taurusUserId;
    }

    public void setTaurusUserId(int taurusUserId) {
        this.taurusUserId = taurusUserId;
    }

    public int getTaurusPermissionId() {
        return taurusPermissionId;
    }

    public void setTaurusPermissionId(int taurusPermissionId) {
        this.taurusPermissionId = taurusPermissionId;
    }

    public boolean isAuthorised() {
        return authorised;
    }

    public void setAuthorised(boolean authorised) {
        this.authorised = authorised;
    }
}
