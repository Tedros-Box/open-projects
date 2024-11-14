/**
 * 
 */
package uk.riosdevida.domain;

/**
 * @author Davis Gordon
 *
 */
public interface DomainApp {

	static final String SEPARATOR = "_";
	static final String VIEW = "VIEW";
	static final String MODULE = "MODULE";
	static final String FORM = "FORM";
	
	static final String R_VIDA_ = "R_VIDA";
	static final String WEB_PAGE = "WEB_PAGE";
	static final String WEB_LANGUAGE = "WEB_LANGUAGE";
	static final String WEB_ELEMENT = "WEB_ELEMENT";
	
	static final String SEP = SEPARATOR;
	
	static final String MNEMONIC = "RDV";

	static final String R_VIDA_MODULE_ID = MNEMONIC + SEP + R_VIDA_ + SEP + MODULE;
	static final String R_VIDA_FORM_ID = MNEMONIC + SEP + R_VIDA_ + SEP + FORM;
	static final String R_VIDA_VIEW_ID = MNEMONIC + SEP + R_VIDA_ + SEP + VIEW;
	
	static final String WEB_PAGE_MODULE_ID = MNEMONIC + SEP + WEB_PAGE + SEP + MODULE;
	static final String WEB_PAGE_FORM_ID = MNEMONIC + SEP + WEB_PAGE + SEP + FORM;
	static final String WEB_PAGE_VIEW_ID = MNEMONIC + SEP + WEB_PAGE + SEP + VIEW;
	
	static final String WEB_LANGUAGE_MODULE_ID = MNEMONIC + SEP + WEB_LANGUAGE + SEP + MODULE;
	static final String WEB_LANGUAGE_FORM_ID = MNEMONIC + SEP + WEB_LANGUAGE + SEP + FORM;
	static final String WEB_LANGUAGE_VIEW_ID = MNEMONIC + SEP + WEB_LANGUAGE + SEP + VIEW;
	
	static final String WEB_ELEMENT_MODULE_ID = MNEMONIC + SEP + WEB_ELEMENT + SEP + MODULE;
	static final String WEB_ELEMENT_FORM_ID = MNEMONIC + SEP + WEB_ELEMENT + SEP + FORM;
	static final String WEB_ELEMENT_VIEW_ID = MNEMONIC + SEP + WEB_ELEMENT + SEP + VIEW;
	

}
