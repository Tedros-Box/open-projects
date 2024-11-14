/**
 * 
 */
package uk.riosdevida.module.webcontent.model;

import org.tedros.core.annotation.security.TAuthorizationType;
import org.tedros.core.annotation.security.TSecurity;
import org.tedros.fx.TUsualKey;
import org.tedros.fx.annotation.control.TCheckBoxField;
import org.tedros.fx.annotation.control.TLabel;
import org.tedros.fx.annotation.control.TTextField;
import org.tedros.fx.annotation.form.TForm;
import org.tedros.fx.annotation.layout.TFlowPane;
import org.tedros.fx.annotation.layout.TPane;
import org.tedros.fx.annotation.presenter.TBehavior;
import org.tedros.fx.annotation.presenter.TDecorator;
import org.tedros.fx.annotation.presenter.TListViewPresenter;
import org.tedros.fx.annotation.presenter.TPresenter;
import org.tedros.fx.annotation.process.TEjbService;
import org.tedros.fx.annotation.scene.TNode;
import org.tedros.fx.annotation.scene.control.TLabeled;
import org.tedros.fx.model.TEntityModelView;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import uk.riosdevida.RDV_Key;
import uk.riosdevida.domain.DomainApp;
import uk.riosdevida.ejb.controller.IWebLanguageController;
import uk.riosdevida.entity.WebLanguage;

/**
 * @author Davis
 *
 */
@TForm(header = "", showBreadcrumBar=false, scroll=true)
@TEjbService(serviceName = IWebLanguageController.JNDI_NAME, model=WebLanguage.class)
@TListViewPresenter(
	presenter=@TPresenter(decorator = @TDecorator(viewTitle=RDV_Key.RVIDA_LANGUAGES_VIEW,
		buildModesRadioButton=false),
		behavior=@TBehavior(runNewActionAfterSave=false)))
@TSecurity(id=DomainApp.WEB_LANGUAGE_FORM_ID, appName = RDV_Key.APP_RVIDA,
	moduleName = RDV_Key.MODULE_WEB_SITE, viewName = RDV_Key.RVIDA_LANGUAGES_VIEW,
	allowedAccesses={TAuthorizationType.VIEW_ACCESS, TAuthorizationType.EDIT, 
					TAuthorizationType.SAVE, TAuthorizationType.DELETE, TAuthorizationType.NEW})
public class WebLanguageMV extends TEntityModelView<WebLanguage> {

	@TLabel(text=TUsualKey.NAME)
	@TTextField(maxLength=120, required = true, 
	node=@TNode(requestFocus=true, parse = true) )
	@TFlowPane(hgap=20,
	pane=@TPane(children={"name","defaultOnLoad"}))
	private SimpleStringProperty name;
	
	@TCheckBoxField(labeled = @TLabeled(parse = true, text=RDV_Key.RVIDA_DEFAULT_LABEL))
	private SimpleObjectProperty<Boolean> defaultOnLoad;
	
	@TLabel(text=RDV_Key.RVIDA_LOCALE_LABEL)
	@TTextField(maxLength=3)
	private SimpleStringProperty locale;
	
	public WebLanguageMV(WebLanguage entity) {
		super(entity);
	}

	@Override
	public SimpleStringProperty toStringProperty() {
		return name;
	}


}
