package com.fpl.core.subsciption.advertisement;

import java.util.Collection;
import java.util.Date;

public interface IAdvertisementTypeManager {

	Collection<AdTypeListPV> getAdvertisementType();
	
	Date persist(AdTypePersistPV persistParam);
}
