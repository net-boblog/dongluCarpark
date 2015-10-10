package com.dongluhitec.card.domain.db.singlecarpark;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.dongluhitec.card.domain.db.DomainObject;

@Entity
public class SingleCarparkSystemSetting extends DomainObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2546271381567702939L;
	@Column(unique=true)
	private String settingKey;
	private String settingValue;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSettingKey() {
		return settingKey;
	}

	public void setSettingKey(String settingKey) {
		this.settingKey = settingKey;
		if (pcs != null)
			pcs.firePropertyChange("settingKey", null, null);
	}

	public String getSettingValue() {
		return settingValue;
	}

	public void setSettingValue(String settingValue) {
		this.settingValue = settingValue;
		if (pcs != null)
			pcs.firePropertyChange("settingValue", null, null);
	}
	
}
