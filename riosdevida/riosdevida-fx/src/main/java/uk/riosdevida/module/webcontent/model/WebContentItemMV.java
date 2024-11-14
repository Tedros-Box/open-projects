/**
 * 
 */
package uk.riosdevida.module.webcontent.model;

import org.tedros.common.model.TByteEntity;
import org.tedros.common.model.TFileEntity;
import org.tedros.core.model.TFormatter;
import org.tedros.fx.TUsualKey;
import org.tedros.fx.annotation.control.TCheckBoxField;
import org.tedros.fx.annotation.control.TFileField;
import org.tedros.fx.annotation.control.THTMLEditor;
import org.tedros.fx.annotation.control.TLabel;
import org.tedros.fx.annotation.control.TNumberSpinnerField;
import org.tedros.fx.annotation.control.TTab;
import org.tedros.fx.annotation.control.TTabPane;
import org.tedros.fx.annotation.control.TTextField;
import org.tedros.fx.annotation.layout.TFlowPane;
import org.tedros.fx.annotation.layout.TPane;
import org.tedros.fx.annotation.presenter.TBehavior;
import org.tedros.fx.annotation.presenter.TDecorator;
import org.tedros.fx.annotation.presenter.TDetailListViewPresenter;
import org.tedros.fx.annotation.presenter.TPresenter;
import org.tedros.fx.annotation.scene.control.TControl;
import org.tedros.fx.annotation.scene.control.TLabeled;
import org.tedros.fx.domain.TFileExtension;
import org.tedros.fx.domain.TFileModelType;
import org.tedros.fx.model.TEntityModelView;
import org.tedros.fx.presenter.dynamic.TDynaPresenter;
import org.tedros.fx.presenter.entity.behavior.TDetailCrudViewBehavior;
import org.tedros.fx.presenter.entity.decorator.TDetailCrudViewDecorator;
import org.tedros.fx.property.TSimpleFileProperty;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import uk.riosdevida.RDV_Key;
import uk.riosdevida.entity.WebContentItem;

/**
 * @author Davis
 *
 */

@TDetailListViewPresenter(
	presenter = @TPresenter(behavior = @TBehavior(type = TDetailCrudViewBehavior.class), 
							decorator = @TDecorator(type = TDetailCrudViewDecorator.class, 
							viewTitle = "Items"), 
							type = TDynaPresenter.class))
public class WebContentItemMV extends TEntityModelView<WebContentItem> {
	
	@TTabPane(tabs = { 
			@TTab(fields = { "position", "content"}, text = TUsualKey.MAIN_DATA),
			@TTab(fields = {"image"}, text = TUsualKey.IMAGE)})
	private SimpleLongProperty id;
	
	@TLabel(text=RDV_Key.RVIDA_ORDER_LABEL)
	@TNumberSpinnerField(maxValue = 99)
	@TFlowPane(hgap=20, 
	pane=@TPane(children={"position", "name", "enabled"}))
	private SimpleIntegerProperty  position;
	
	@TLabel(text=TUsualKey.NAME)
	@TTextField(maxLength = 250, required = true)
	private SimpleStringProperty name;

	@TCheckBoxField(labeled = @TLabeled(parse = true, text=TUsualKey.ENABLED))
	private SimpleObjectProperty<Boolean> enabled;
	
	@THTMLEditor(required = true, 
	control = @TControl(prefHeight = 300, parse = true))
	private SimpleStringProperty content;
		
	@TFileField(propertyValueType=TFileModelType.ENTITY, preLoadFileBytes = true,
		extensions = TFileExtension.ALL_IMAGES, showImage = true)
	private TSimpleFileProperty<TFileEntity> image;
	
	public WebContentItemMV(WebContentItem entity) {
		super(entity);
		super.formatToString(TFormatter.create()
				.add("[%s]", position)
				.add("%s", name));
		if(entity.getImage()==null) {
			var e = new TFileEntity();
			e.setByteEntity(new TByteEntity());
			image.setValue(e);
		}
	}
	

}
