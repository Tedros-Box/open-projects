/**
 * 
 */
package uk.riosdevida.module.webcontent.model;

import org.tedros.common.model.TFileEntity;
import org.tedros.core.model.TFormatter;
import org.tedros.fx.TUsualKey;
import org.tedros.fx.annotation.control.TCheckBoxField;
import org.tedros.fx.annotation.control.TConverter;
import org.tedros.fx.annotation.control.TDetailListField;
import org.tedros.fx.annotation.control.TFieldBox;
import org.tedros.fx.annotation.control.TGenericType;
import org.tedros.fx.annotation.control.THRadioGroup;
import org.tedros.fx.annotation.control.THTMLEditor;
import org.tedros.fx.annotation.control.TLabel;
import org.tedros.fx.annotation.control.TNumberSpinnerField;
import org.tedros.fx.annotation.control.TRadio;
import org.tedros.fx.annotation.control.TSelectImageField;
import org.tedros.fx.annotation.control.TTab;
import org.tedros.fx.annotation.control.TTabPane;
import org.tedros.fx.annotation.control.TTextField;
import org.tedros.fx.annotation.form.TSetting;
import org.tedros.fx.annotation.layout.TFlowPane;
import org.tedros.fx.annotation.layout.TPane;
import org.tedros.fx.annotation.presenter.TBehavior;
import org.tedros.fx.annotation.presenter.TDecorator;
import org.tedros.fx.annotation.presenter.TEditModalPresenter;
import org.tedros.fx.annotation.presenter.TPresenter;
import org.tedros.fx.annotation.process.TEjbService;
import org.tedros.fx.annotation.scene.TNode;
import org.tedros.fx.annotation.scene.control.TControl;
import org.tedros.fx.annotation.scene.control.TLabeled;
import org.tedros.fx.annotation.scene.layout.TRegion;
import org.tedros.fx.collections.ITObservableList;
import org.tedros.fx.domain.TEnvironment;
import org.tedros.fx.model.TEntityModelView;
import org.tedros.fx.presenter.dynamic.TDynaPresenter;
import org.tedros.fx.presenter.modal.behavior.TEditModalBehavior;
import org.tedros.fx.presenter.modal.decorator.TEditModalDecorator;
import org.tedros.server.model.ITFileBaseModel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import uk.riosdevida.RDV_Key;
import uk.riosdevida.ejb.controller.IWebContentController;
import uk.riosdevida.entity.ContentType;
import uk.riosdevida.entity.WebContent;
import uk.riosdevida.entity.WebContentItem;
import uk.riosdevida.module.webcontent.converter.ContentTypeConverter;
import uk.riosdevida.module.webcontent.setting.WebContentSetting;
import uk.riosdevida.start.TConstant;

/**
 * @author Davis
 *
 */
@TSetting(WebContentSetting.class)
@TEditModalPresenter(presenter = @TPresenter(
		behavior = @TBehavior(type = TEditModalBehavior.class), 
		decorator = @TDecorator(type = TEditModalDecorator.class, 
			buildSaveButton=false, buildDeleteButton=false, 
			region = @TRegion(prefHeight = 600, parse = true),
			viewTitle=TUsualKey.EDIT_CONTENT), 
		type = TDynaPresenter.class))
@TEjbService(model = WebContent.class, serviceName = IWebContentController.JNDI_NAME)
public class WebContentMV extends TEntityModelView<WebContent> {
		
	@TLabel(text=RDV_Key.RVIDA_ORDER_LABEL)
	@TNumberSpinnerField(maxValue = 99)
	@TFlowPane(hgap=20,
	pane=@TPane(children={"position","type", "title", "enabled"}))
	private SimpleIntegerProperty  position;
	
	@TLabel(text=TUsualKey.TYPE)
	@THRadioGroup( required = true,
			converter = @TConverter(parse = true, type = ContentTypeConverter.class),
			radio = { 
		@TRadio(text = RDV_Key.RVIDA_TEXT_LABEL, userData = "TEXT"),
		@TRadio(text = RDV_Key.RVIDA_ALBUM_LABEL, userData = "ALBUM"),
		@TRadio(text = RDV_Key.RVIDA_TABS_LABEL, userData = "TABS")
	})
	private SimpleObjectProperty<ContentType> type;

	@TLabel(text=TUsualKey.TITLE)
	@TTextField(maxLength = 500)
	private SimpleStringProperty title;

	@TCheckBoxField(labeled = @TLabeled(parse = true, text=TUsualKey.ENABLED))
	private SimpleObjectProperty<Boolean> enabled;
	
	@TTabPane(tabs = { 
			@TTab(fields = { "content"}, text = RDV_Key.RVIDA_TEXT_LABEL),
			@TTab(fields = { "images" }, text = RDV_Key.RVIDA_ALBUM_LABEL),
			@TTab(fields = { "items"}, text = RDV_Key.RVIDA_TABS_LABEL)})
	private SimpleLongProperty id;
	
	@THTMLEditor(control = @TControl(prefHeight = 300, parse = true))
	private SimpleStringProperty content;
		
	@TLabel(text=TUsualKey.IMAGE)
	@TSelectImageField(source=TEnvironment.LOCAL, 
	target=TEnvironment.REMOTE, preLoadFileBytes = true,
	remoteOwner = TConstant.UUI)
	@TGenericType(model = TFileEntity.class)
	private ITObservableList<ITFileBaseModel> images;
		
	@TFieldBox(node=@TNode(id="field", parse = true))
	@TDetailListField(region=@TRegion(maxHeight=500, parse = true),
	modelView = WebContentItemMV.class, entity = WebContentItem.class)
	@TGenericType(model = WebContentItem.class, modelView=WebContentItemMV.class)
	protected ITObservableList<WebContentItemMV> items;
	
	public WebContentMV(WebContent entity) {
		super(entity);
		super.formatToString(TFormatter.create()
				.add("[%s]", position)
				.add("[%s] ", type)
				.add("%s", title));
	}
	

}
