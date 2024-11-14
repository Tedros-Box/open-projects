/**
 * 
 */
package uk.riosdevida.module.webcontent.model;

import org.tedros.common.model.TByteEntity;
import org.tedros.common.model.TFileEntity;
import org.tedros.core.model.TFormatter;
import org.tedros.fx.TUsualKey;
import org.tedros.fx.annotation.control.TCheckBoxField;
import org.tedros.fx.annotation.control.TComboBoxField;
import org.tedros.fx.annotation.control.TEditEntityModal;
import org.tedros.fx.annotation.control.TFieldBox;
import org.tedros.fx.annotation.control.TFileField;
import org.tedros.fx.annotation.control.TGenericType;
import org.tedros.fx.annotation.control.TLabel;
import org.tedros.fx.annotation.control.TProcess;
import org.tedros.fx.annotation.control.TTab;
import org.tedros.fx.annotation.control.TTabPane;
import org.tedros.fx.annotation.control.TTextField;
import org.tedros.fx.annotation.effect.TDropShadow;
import org.tedros.fx.annotation.effect.TEffect;
import org.tedros.fx.annotation.layout.THBox;
import org.tedros.fx.annotation.layout.THGrow;
import org.tedros.fx.annotation.layout.TPane;
import org.tedros.fx.annotation.layout.TPriority;
import org.tedros.fx.annotation.presenter.TBehavior;
import org.tedros.fx.annotation.presenter.TDecorator;
import org.tedros.fx.annotation.presenter.TDetailListViewPresenter;
import org.tedros.fx.annotation.presenter.TPresenter;
import org.tedros.fx.annotation.query.TOrder;
import org.tedros.fx.annotation.query.TQuery;
import org.tedros.fx.annotation.scene.TNode;
import org.tedros.fx.annotation.scene.control.TLabeled;
import org.tedros.fx.annotation.text.TText;
import org.tedros.fx.collections.ITObservableList;
import org.tedros.fx.control.TText.TTextStyle;
import org.tedros.fx.domain.TFileExtension;
import org.tedros.fx.domain.TFileModelType;
import org.tedros.fx.model.TEntityModelView;
import org.tedros.fx.presenter.dynamic.TDynaPresenter;
import org.tedros.fx.presenter.entity.behavior.TDetailCrudViewBehavior;
import org.tedros.fx.presenter.entity.decorator.TDetailCrudViewDecorator;
import org.tedros.fx.property.TSimpleFileProperty;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;
import uk.riosdevida.RDV_Key;
import uk.riosdevida.ejb.controller.IWebLanguageController;
import uk.riosdevida.entity.WebContent;
import uk.riosdevida.entity.WebLanguage;
import uk.riosdevida.entity.WebRegion;

/**
 * @author Davis
 *
 */
@TDetailListViewPresenter(presenter = @TPresenter(
		behavior = @TBehavior(type = TDetailCrudViewBehavior.class), 
		decorator = @TDecorator(type = TDetailCrudViewDecorator.class, 
			viewTitle = RDV_Key.RVIDA_REGION_VIEW), 
		type = TDynaPresenter.class))
public class WebRegionMV extends TEntityModelView<WebRegion> {
	
	@TFieldBox(alignment=Pos.CENTER_LEFT, node=@TNode(id="t-fieldbox-info", 
			effect=@TEffect(dropShadow=@TDropShadow, parse = false), parse = true))
	@TText(text=RDV_Key.RVIDA_REGION_DESC, textAlignment=TextAlignment.LEFT, textStyle=TTextStyle.LARGE)
	private SimpleStringProperty header;
	
	@TTabPane(tabs = { 
		@TTab(fields = { "name", "twitter", "defaultOnLoad", "logo"}, text = TUsualKey.MAIN_DATA),
		@TTab(fields = { "contents" }, text = TUsualKey.CONTENT),
		@TTab(fields = { "image" }, text = TUsualKey.BACKGROUND_IMAGE)})
	private SimpleLongProperty id;
		
	@TLabel(text=TUsualKey.NAME)
	@TTextField(maxLength = 500, required = true)
	@THBox(pane=@TPane(children={
			"name","language"}),
	hgrow = @THGrow(priority = {
			@TPriority(field = "name", priority = Priority.ALWAYS),
			@TPriority(field = "language", priority = Priority.ALWAYS)
	}))
	private SimpleStringProperty name;
	
	@TLabel(text=RDV_Key.RVIDA_LANGUAGE_LABEL)
	@TComboBoxField(
		process=@TProcess(service = IWebLanguageController.JNDI_NAME, 
		query = @TQuery(entity = WebLanguage.class, orderBy=@TOrder(field="name"))))
	private SimpleObjectProperty<WebLanguage> language;
	
	@TLabel(text="X (Twitter)")
	@TTextField
	@THBox(pane=@TPane(children={
			"twitter", "facebook", "instagram", "youtube"}),
	hgrow = @THGrow(priority = {
			@TPriority(field = "twitter", priority = Priority.ALWAYS),
			@TPriority(field = "facebook", priority = Priority.ALWAYS),
			@TPriority(field = "instagram", priority = Priority.ALWAYS),
			@TPriority(field = "youtube", priority = Priority.ALWAYS)
	}))
	private SimpleStringProperty twitter;
	
	@TLabel(text="Facebook")
	@TTextField
	private SimpleStringProperty facebook;
	
	@TLabel(text="Instagram")
	@TTextField
	private SimpleStringProperty instagram;
	
	@TLabel(text="Youtube")
	@TTextField
	private SimpleStringProperty youtube;
	
	@TCheckBoxField(labeled = @TLabeled(parse = true, text=RDV_Key.RVIDA_DEFAULT_LABEL))
	private SimpleObjectProperty<Boolean> defaultOnLoad;
	
	@TLabel(text=TUsualKey.LOGO)
	@TFileField(propertyValueType=TFileModelType.ENTITY, preLoadFileBytes = true, maxImageWidth = 305,
			extensions = TFileExtension.ALL_IMAGES, showImage = true)
	private TSimpleFileProperty<TFileEntity> logo;
	
	@TEditEntityModal(model = WebContent.class, modelView=WebContentMV.class, modalHeight = 700)
	@TGenericType(model = WebContent.class, modelView=WebContentMV.class)
	protected ITObservableList<WebContentMV> contents;
	
	@TFileField(propertyValueType=TFileModelType.ENTITY, preLoadFileBytes = true,
			extensions = TFileExtension.ALL_IMAGES, showImage = true)
	private TSimpleFileProperty<TFileEntity> image;
	
	public WebRegionMV(WebRegion entity) {
		super(entity);		
		super.formatToString(TFormatter.create()
				.add("[%s] ", language)
				.add("%s", name));
		if(entity.getImage()==null) {
			var e0 = new TFileEntity();
			e0.setByteEntity(new TByteEntity());
			image.setValue(e0);
		}
		if(entity.getLogo()==null) {
			var e1 = new TFileEntity();
			e1.setByteEntity(new TByteEntity());
			logo.setValue(e1);
		}
	}
}
