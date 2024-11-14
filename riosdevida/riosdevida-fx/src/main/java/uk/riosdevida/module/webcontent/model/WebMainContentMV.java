/**
 * 
 */
package uk.riosdevida.module.webcontent.model;

import org.tedros.core.annotation.security.TAuthorizationType;
import org.tedros.core.annotation.security.TSecurity;
import org.tedros.fx.TUsualKey;
import org.tedros.fx.annotation.control.TAutoCompleteEntity;
import org.tedros.fx.annotation.control.TDetailListField;
import org.tedros.fx.annotation.control.TGenericType;
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
import org.tedros.fx.annotation.query.TCondition;
import org.tedros.fx.annotation.query.TQuery;
import org.tedros.fx.annotation.scene.TNode;
import org.tedros.fx.collections.ITObservableList;
import org.tedros.fx.model.TEntityModelView;
import org.tedros.person.ejb.controller.IPersonController;
import org.tedros.person.model.LegalPerson;
import org.tedros.server.query.TCompareOp;
import org.tedros.server.query.TLogicOp;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import uk.riosdevida.RDV_Key;
import uk.riosdevida.domain.DomainApp;
import uk.riosdevida.ejb.controller.IWebMainPageController;
import uk.riosdevida.entity.WebMainPage;
import uk.riosdevida.entity.WebRegion;

/**
 * @author Davis
 *
 */
@TForm(header = "", showBreadcrumBar=false, scroll=true)
@TEjbService(serviceName = IWebMainPageController.JNDI_NAME, model=WebMainPage.class)
@TListViewPresenter(
	presenter=@TPresenter(decorator = @TDecorator(viewTitle=RDV_Key.RVIDA_CONTENT_VIEW,
		buildModesRadioButton=false),
		behavior=@TBehavior(runNewActionAfterSave=false)))
@TSecurity(id=DomainApp.WEB_PAGE_FORM_ID, appName = RDV_Key.APP_RVIDA,
	moduleName = RDV_Key.MODULE_WEB_SITE, viewName = RDV_Key.RVIDA_CONTENT_VIEW,
	allowedAccesses={TAuthorizationType.VIEW_ACCESS, TAuthorizationType.EDIT, 
					TAuthorizationType.SAVE, TAuthorizationType.DELETE, TAuthorizationType.NEW})
public class WebMainContentMV extends TEntityModelView<WebMainPage> {
		
	@TLabel(text=RDV_Key.RVIDA_DOMAIN_LABEL)
	@TTextField(required = true, 
	node=@TNode(requestFocus=true, parse = true))
	@TFlowPane(hgap=20,
	pane=@TPane(children={"domain", "name", "person"}))
	private SimpleStringProperty domain;
	
	@TLabel(text=TUsualKey.NAME)
	@TTextField(required = true)	
	private SimpleStringProperty name;
	
	@TLabel(text=TUsualKey.LEGAL_PERSON)
	@TAutoCompleteEntity(required=true, 
	startSearchAt=3, showMaxItems=30,
	service = IPersonController.JNDI_NAME,
	query = @TQuery(entity = LegalPerson.class, 
		condition = {
			@TCondition(field = "name", operator=TCompareOp.LIKE),
			@TCondition(logicOp=TLogicOp.OR, field = "otherName", 
			operator=TCompareOp.LIKE)}))
	private SimpleObjectProperty<LegalPerson> person;
	
	@TDetailListField(modelView = WebRegionMV.class, entity = WebRegion.class)
	@TGenericType(model=WebRegion.class, modelView=WebRegionMV.class)
	private ITObservableList<WebRegionMV> region;
	
	public WebMainContentMV(WebMainPage entity) {
		super(entity);
	}

	@Override
	public SimpleStringProperty toStringProperty() {
		return domain;
	}


}
