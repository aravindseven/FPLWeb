package com.fpl.controller.support;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;

/**
 * @author Yaazhsoft
 *
 */
public class AjaxViewResolver extends AbstractCachingViewResolver {

	private String ajaxPrefix;
	private View ajaxView;

	@Override
	protected View loadView(final String viewName, final Locale locale) throws Exception {
		View view = null;
		if (viewName.startsWith(this.ajaxPrefix)) {
			view = ajaxView;
		}
		return view;
	}

	public String getAjaxPrefix() {
		return ajaxPrefix;
	}

	public void setAjaxPrefix(final String ajaxPrefix) {
		this.ajaxPrefix = ajaxPrefix;
	}

	public View getAjaxView() {
		return ajaxView;
	}

	public void setAjaxView(final View ajaxView) {
		this.ajaxView = ajaxView;
	}
}
