package com.fpl.controller.profile.admin;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fpl.controller.support.FPLAbstractController;
import com.fpl.controller.support.FPLPageName;
import com.fpl.controller.support.RequestToPageViewTransfer;
import com.fpl.profile.admin.AdminProfilePV;
import com.fpl.profile.admin.ProfileSearchResultPV;
import com.fpl.profile.admin.SearchProfilePV;
import com.fpl.services.profile.IProfileSearcherService;

public class SearchProfileController extends FPLAbstractController {
	
	private IProfileSearcherService profileSearcherService;
	
	@Override
	protected ModelAndView executeRequest(final HttpServletRequest request, final HttpServletResponse response) {
		final ModelAndView model = new ModelAndView(FPLPageName.ADMIN_MANAGE_PROFILE.getPageName());
		final SearchProfilePV searchProfile = RequestToPageViewTransfer.getPageView(SearchProfilePV.class, request);
		final Collection<ProfileSearchResultPV> searchResultList = profileSearcherService.getProfileList(searchProfile);
		final AdminProfilePV adminProfilePV = new AdminProfilePV();
		model.addObject("adminParam", adminProfilePV);
		model.addObject("searchProfile", searchProfile);
		model.addObject("profileSearchResult", searchResultList);
		return model;
	}

	/**
	 * @param profileSearcherService the profileSearcherService to set
	 */
	public void setProfileSearcherService(final IProfileSearcherService profileSearcherService) {
		this.profileSearcherService = profileSearcherService;
	}
}
