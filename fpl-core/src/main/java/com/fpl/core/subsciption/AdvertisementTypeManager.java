package com.fpl.core.subsciption;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fpl.common.AbstractTransformer;
import com.fpl.core.subsciption.advertisement.AdTypeListPV;
import com.fpl.core.subsciption.advertisement.AdTypePersistPV;
import com.fpl.core.subsciption.advertisement.IAdvertisementTypeManager;
import com.fpl.country.Country;
import com.fpl.persistence.country.ICountryDAO;
import com.fpl.persistence.subscription.IAdvertisementTypeDAO;
import com.fpl.subscription.AdvertisementType;
import com.fpl.util.DateUtil;
import com.fpl.util.StringUtil;

@Component
public class AdvertisementTypeManager implements IAdvertisementTypeManager {
	
	@Autowired
	@Qualifier("fpl.subscrption.AdvertisementTypeDAO")
	private IAdvertisementTypeDAO advertisementTypeDAO;
	@Autowired
	@Qualifier("fpl.subscrption.CountryDAO")
	private ICountryDAO countryDAO;
	
	@Override
	public Collection<AdTypeListPV> getAdvertisementType() {
		final Collection<AdvertisementType> advertisementTypes = advertisementTypeDAO.getAllEntities();
		return new AbstractTransformer<AdvertisementType, AdTypeListPV>() {
			@Override
			public AdTypeListPV transform(final AdvertisementType advertisementType) {
				final AdTypeListPV param = new AdTypeListPV();
				param.setTypeName(advertisementType.getType());
				param.setId(""+advertisementType.getId());
				param.setStartDate(DateUtil.getFormattedDate(advertisementType.getStartDate()));
				param.setSubRate(advertisementType.getSubscriptionRate());
				return param;
			}
		}.transform(advertisementTypes);
	}

	@Override
	public Date persist(final AdTypePersistPV persistParam) {
		final AdvertisementType advertisementType = new AdvertisementType();
		advertisementType.setActicityFlag(persistParam.getActiveFlag());
		final Country country = countryDAO.getCountryByName(persistParam.getCountry());
		advertisementType.setCountryid(country.getId());
		advertisementType.setDimension(persistParam.getDimension());
		advertisementType.setDiscountPolicy(persistParam.getDiscountPolicy());
		advertisementType.setEndDate(DateUtil.getFormattedDate(persistParam.getpEndDate(), "yyyy-MM-dd"));
		advertisementType.setFileSize(persistParam.getFileSize());
		advertisementType.setFileType(persistParam.getFileTypes());
		advertisementType.setInsertedDate(new Date());
		advertisementType.setItemSubmit(persistParam.getItemSubmit());
		String media = "";
		if(StringUtil.isNotEmpty(persistParam.getMediaMobile()) && "true".equalsIgnoreCase(persistParam.getMediaMobile())) {
			media = "mobile";
		}
		if(StringUtil.isNotEmpty(persistParam.getMediaWeb()) && "true".equalsIgnoreCase(persistParam.getMediaWeb())) {
			media = StringUtil.isNotEmpty(media) ? media+"^web" : "web";
		}
		advertisementType.setMedia(media);
		advertisementType.setNote(persistParam.getComments());
		advertisementType.setRegistrationDate(new Date());
		advertisementType.setStartDate(DateUtil.getFormattedDate(persistParam.getpStartDate(), "yyyy-MM-dd"));
		advertisementType.setSubscriptionRate(persistParam.getSubRate());
		advertisementType.setSubscrptionDeadLine(persistParam.getSubDeadline());
		advertisementType.setType(persistParam.getAdvType());
		advertisementType.setUpdateDate(new Date());
		advertisementTypeDAO.save(advertisementType, true);
		return advertisementType.getUpdateDate();
	}
}
