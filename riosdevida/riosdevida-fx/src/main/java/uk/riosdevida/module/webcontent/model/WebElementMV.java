/**
 * 
 */
package uk.riosdevida.module.webcontent.model;

import org.tedros.core.annotation.security.TAuthorizationType;
import org.tedros.core.annotation.security.TSecurity;
import org.tedros.fx.TUsualKey;
import org.tedros.fx.annotation.control.TComboBoxField;
import org.tedros.fx.annotation.control.TConverter;
import org.tedros.fx.annotation.control.THRadioGroup;
import org.tedros.fx.annotation.control.TLabel;
import org.tedros.fx.annotation.control.TProcess;
import org.tedros.fx.annotation.control.TRadio;
import org.tedros.fx.annotation.control.TTextField;
import org.tedros.fx.annotation.form.TForm;
import org.tedros.fx.annotation.layout.TFlowPane;
import org.tedros.fx.annotation.layout.TPane;
import org.tedros.fx.annotation.presenter.TBehavior;
import org.tedros.fx.annotation.presenter.TDecorator;
import org.tedros.fx.annotation.presenter.TListViewPresenter;
import org.tedros.fx.annotation.presenter.TPresenter;
import org.tedros.fx.annotation.process.TEjbService;
import org.tedros.fx.annotation.query.TOrder;
import org.tedros.fx.annotation.query.TQuery;
import org.tedros.fx.annotation.scene.TNode;
import org.tedros.fx.model.TEntityModelView;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import uk.riosdevida.RDV_Key;
import uk.riosdevida.domain.DomainApp;
import uk.riosdevida.ejb.controller.IWebElementPageController;
import uk.riosdevida.ejb.controller.IWebLanguageController;
import uk.riosdevida.entity.ElementType;
import uk.riosdevida.entity.WebElementPage;
import uk.riosdevida.entity.WebLanguage;
import uk.riosdevida.module.webcontent.converter.ElementTypeConverter;

/**
 * @author Davis
 *
 */
@TForm(header = "", showBreadcrumBar=false, scroll=true)
@TEjbService(serviceName = IWebElementPageController.JNDI_NAME, model=WebElementPage.class)
@TListViewPresenter(
	presenter=@TPresenter(decorator = @TDecorator(viewTitle=RDV_Key.RVIDA_ELEMENT_VIEW,
		buildModesRadioButton=false),
		behavior=@TBehavior(runNewActionAfterSave=false)))
@TSecurity(id=DomainApp.WEB_ELEMENT_FORM_ID, appName = RDV_Key.APP_RVIDA,
	moduleName = RDV_Key.MODULE_WEB_SITE, viewName = RDV_Key.RVIDA_ELEMENT_VIEW,
	allowedAccesses={TAuthorizationType.VIEW_ACCESS, TAuthorizationType.EDIT, 
					TAuthorizationType.SAVE, TAuthorizationType.DELETE, TAuthorizationType.NEW})
public class WebElementMV extends TEntityModelView<WebElementPage> {
	
	@TLabel(text=TUsualKey.TYPE)
	@THRadioGroup( required = true,
			converter = @TConverter(parse = true, type = ElementTypeConverter.class),
			radio = { 
		@TRadio(text = RDV_Key.RVIDA_LANGUAGE_LABEL, userData = "LANGUAGE"),
		@TRadio(text = RDV_Key.RVIDA_LOCATION_LABEL, userData = "LOCATION")
	})
	@TFlowPane(hgap=20,
	pane=@TPane(children={"type","language", "name"}))
	private SimpleObjectProperty<ElementType> type;
	
	@TLabel(text=RDV_Key.RVIDA_LANGUAGE_LABEL)
	@TComboBoxField(required = true,
		process=@TProcess(service = IWebLanguageController.JNDI_NAME, 
		query = @TQuery(entity = WebLanguage.class, orderBy=@TOrder(field="name"))))
	private SimpleObjectProperty<WebLanguage> language;
	
	@TLabel(text=TUsualKey.NAME)
	@TTextField(maxLength=250, required = true, 
	node=@TNode(requestFocus=true, parse = true) )
	private SimpleStringProperty name;
	
	
	public WebElementMV(WebElementPage entity) {
		super(entity);
		super.formatToString("%s %s %s", type, language, name);
	}

}
