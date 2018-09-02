package com.twigs.admin.user;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/*select tu.username, tp.name from taurus_user_permission tup
        left join taurus_user tu on tu.taurus_id = tup.taurus_user_id
        join taurus_permission tp on tp.taurus_id = tup.taurus_permission_id and tup.authorised = true

<p:ajax event="tabChange" listener="#{taurusUserListController.onTabChange}" update=":userListForm:userList"/>*/

@Scope(value = "session")
@Component(value = "taurusUserListController")
@ELBeanName(value = "taurusUserListController")
@Join(path = "/", to = "/user/taurus-user-list.jsf")
public class TaurusUserListController {

    @Autowired
    private TaurusUserService userService;

    private List<TaurusUser> taurusUsers;
    private DualListModel<TaurusPermission> selectedUserPermissions;
    private AccordionPanel activeAccordionPanel;

    @PostConstruct
    public void init() {
        taurusUsers = userService.findAllUsers();
        selectedUserPermissions = new DualListModel<>();
    }

    public List<TaurusUser> getTaurusUsers() {
        return taurusUsers;
    }

    public void onTabChange(TabChangeEvent event) {
        activeAccordionPanel = (AccordionPanel)event.getTab().getParent();
        selectedUserPermissions = userService.createPickListOfPermissionsForUser(taurusUsers.get(getActiveTabIndex()).getTaurusId());
    }

    public DualListModel<TaurusPermission> getSelectedUserPermissions() {
        return selectedUserPermissions;// = userService.createPickListOfPermissionsForUser(taurusUsers.get(getActiveTabIndex()).getTaurusId());
    }

    public void setSelectedUserPermissions(DualListModel<TaurusPermission> selectedUserPermissions) {
        this.selectedUserPermissions = selectedUserPermissions;
    }

    public void onPickListTransfer(TransferEvent event) {
        userService.updateAndSaveUserPermissionList((List<TaurusPermission>)event.getItems(), taurusUsers.get(getActiveTabIndex()), event.isAdd());
    }

    public void markUserAuditComplete(boolean auditComplete) {
        taurusUsers.set(getActiveTabIndex(), userService.markUserAuditComplete(taurusUsers.get(getActiveTabIndex()), auditComplete));
    }

    public int getActiveTabIndex() {
        return null != activeAccordionPanel ? Integer.valueOf(activeAccordionPanel.getActiveIndex()) : 0;
    }

    public String getActiveTabIndexString() {
        return null != activeAccordionPanel ? activeAccordionPanel.getActiveIndex() : "0";
    }
}
