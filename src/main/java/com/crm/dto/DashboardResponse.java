package com.crm.dto;

public class DashboardResponse {

    private long totalCustomers;
    
    private long totalLeads;
    
    private long totalActualCustomers;
    
    private long newLeads;
    private long qualifiedLeads;
    
    private long convertedLeads;
    
    private long lostLeads;

    private long pendingFollowUps;
    
    private long successfulFollowUps;
    
    private long failedFollowUps;

	public long getTotalCustomers() {
		return totalCustomers;
	}

	public void setTotalCustomers(long totalCustomers) {
		this.totalCustomers = totalCustomers;
	}

	public long getTotalLeads() {
		return totalLeads;
	}

	public void setTotalLeads(long totalLeads) {
		this.totalLeads = totalLeads;
	}

	public long getTotalActualCustomers() {
		return totalActualCustomers;
	}

	public void setTotalActualCustomers(long totalActualCustomers) {
		this.totalActualCustomers = totalActualCustomers;
	}

	public long getNewLeads() {
		return newLeads;
	}

	public void setNewLeads(long newLeads) {
		this.newLeads = newLeads;
	}

	public long getQualifiedLeads() {
		return qualifiedLeads;
	}

	public void setQualifiedLeads(long qualifiedLeads) {
		this.qualifiedLeads = qualifiedLeads;
	}

	public long getConvertedLeads() {
		return convertedLeads;
	}

	public void setConvertedLeads(long convertedLeads) {
		this.convertedLeads = convertedLeads;
	}

	public long getLostLeads() {
		return lostLeads;
	}

	public void setLostLeads(long lostLeads) {
		this.lostLeads = lostLeads;
	}

	public long getPendingFollowUps() {
		return pendingFollowUps;
	}

	public void setPendingFollowUps(long pendingFollowUps) {
		this.pendingFollowUps = pendingFollowUps;
	}

	public long getSuccessfulFollowUps() {
		return successfulFollowUps;
	}

	public void setSuccessfulFollowUps(long successfulFollowUps) {
		this.successfulFollowUps = successfulFollowUps;
	}

	public long getFailedFollowUps() {
		return failedFollowUps;
	}

	public void setFailedFollowUps(long failedFollowUps) {
		this.failedFollowUps = failedFollowUps;
	}
    
    
}