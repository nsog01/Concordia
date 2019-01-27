package presentation.controllers.files;

import presentation.views.files.HouseholdInfoPanel;

public class HouseholdInfoPanelController {
	HouseholdInfoPanel householdInfoPanel;

	public HouseholdInfoPanelController(HouseholdInfoPanel householdInfoPanel){
		this.householdInfoPanel = householdInfoPanel;
	}
	public HouseholdInfoPanel getHouseholdInfoPanel()
	{
		return this.householdInfoPanel;
	}

}
