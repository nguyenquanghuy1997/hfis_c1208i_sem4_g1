/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import biz.RequestManager;
import biz.ScheduleManager;
import entity.Request;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author The
 */
@ManagedBean
@RequestScoped
public class NotificationUI {

    @EJB
    private ScheduleManager scheduleManager;
    @EJB
    private RequestManager requestManager;
    @ManagedProperty(value = "#{login}")
    private Login loginBean;

    /**
     * Creates a new instance of NotificationUI
     */
    public NotificationUI() {
    }
    public void clearMessage(){
        requestManager.clearMessages(loginBean.getAccount().getUsername());
    }
    public int getTotalNoti() {
        if (loginBean.getRoleAdmin()) {
            return getUnassignComplaintsCount() + getUnresolvedLabRequestsCount() + getMessages().size();
        } else if (loginBean.getRoleTechnical()) {
            return getUnresolvedComplaintsCount() + getMessages().size();
        } else {
            return getMessages().size();
        }
    }

    //Technical Staff
    public int getUnresolvedComplaintsCount() {
        return requestManager.getUnresolvedComplaints(
                loginBean.getAccount().getUsername()).size();
    }

    //Admin
    public int getUnresolvedLabRequestsCount() {
        return scheduleManager.getUnacceptedSchedule().size();
    }

    public int getUnassignComplaintsCount() {
        return requestManager.getUnassignedComplaints().size();
    }

    public List<Request> getMessages() {
        if (loginBean.getAccount() != null) {
            return requestManager.getMessages(loginBean.getAccount().getUsername());
        }
        return new LinkedList<>();
    }

    public Login getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(Login loginBean) {
        this.loginBean = loginBean;
    }

}
