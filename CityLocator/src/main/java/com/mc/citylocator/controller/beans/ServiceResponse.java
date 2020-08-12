package com.mc.citylocator.controller.beans;

public class ServiceResponse {
	private String connected;

	public String getConnected() {
		return connected;
	}

	public void setConnected(String connected) {
		this.connected = connected;
	}

	@Override
	public String toString() {
		return "ServiceResponse [connected=" + connected + "]";
	}

}
