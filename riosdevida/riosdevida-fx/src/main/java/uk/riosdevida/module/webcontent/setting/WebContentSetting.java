/**
 * 
 */
package uk.riosdevida.module.webcontent.setting;

import java.util.function.Consumer;

import org.apache.commons.lang3.StringUtils;
import org.tedros.api.descriptor.ITComponentDescriptor;
import org.tedros.core.TLanguage;
import org.tedros.core.context.TedrosContext;
import org.tedros.fx.TUsualKey;
import org.tedros.fx.collections.ITObservableList;
import org.tedros.fx.control.THRadioGroup;
import org.tedros.fx.control.THTMLEditor;
import org.tedros.fx.form.TSetting;
import org.tedros.fx.modal.TConfirmMessageBox;
import org.tedros.server.model.ITFileBaseModel;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.WeakChangeListener;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import uk.riosdevida.RDV_Key;
import uk.riosdevida.entity.ContentType;
import uk.riosdevida.module.webcontent.model.WebContentItemMV;

/**
 * 
 */
public class WebContentSetting extends TSetting {
	
	private ChangeListener<Tab> chl0;
	private ChangeListener<ContentType> chl1;

	public WebContentSetting(ITComponentDescriptor descriptor) {
		super(descriptor);
	}


	@Override
	public void dispose() {
		chl0 = null;
		chl1 = null;
	}
	@Override
	@SuppressWarnings("unchecked")
	public void run() {
		
		chl0 = (a,o,n) -> {
			if(n.getText().equals(TLanguage.getInstance().getString(RDV_Key.RVIDA_TEXT_LABEL))) {
				selectToggle(ContentType.TEXT);
			}else if(n.getText().equals(TLanguage.getInstance().getString(RDV_Key.RVIDA_ALBUM_LABEL))) {
				selectToggle(ContentType.ALBUM);
			}else {
				selectToggle(ContentType.TABS);
			}
		};
		
		TabPane tabPane = super.getLayout("id");
		tabPane.getSelectionModel().selectedItemProperty().addListener(new WeakChangeListener<>(chl0));
		
		chl1 = (a, o, n)->{
			if(n==null)
				return;
			processChange(o, n);
		};
		
		SimpleObjectProperty<ContentType> prop = (SimpleObjectProperty<ContentType>) super.getProperty("type");
		prop.addListener(new WeakChangeListener<>(chl1));
	}

	@SuppressWarnings("unchecked")
	private void processChange(ContentType old_, ContentType new_) {
		if(new_==null)
			return;
		
		TabPane tabPane = super.getLayout("id");
		
		THTMLEditor content = (THTMLEditor) super.getControl("content");
		ITObservableList<WebContentItemMV> items = (ITObservableList<WebContentItemMV>) super.getProperty("items");
		ITObservableList<ITFileBaseModel> images = (ITObservableList<ITFileBaseModel>) super.getProperty("images");
		
		switch (new_) {
			case TEXT: {
				StringBuilder b = new StringBuilder("");
				
				if(!images.isEmpty()) {
					b.append(TLanguage.getInstance().getString(RDV_Key.RVIDA_ALBUM_LABEL));
				}
				if(!items.isEmpty()) {
					if(!b.toString().equals(""))
						b.append(TLanguage.getInstance().getString(" "+TUsualKey.AND+" "));
					b.append(RDV_Key.RVIDA_TABS_LABEL);
				}
				
				if(!b.toString().equals("")) {
					exec(TLanguage.getInstance().getFormatedString(RDV_Key.RVIDA_CLEAN_FIELDS_MESSAGE, b.toString()), c->{
						if(c) {
							images.clear();
							items.clear();
							tabPane.getSelectionModel().select(0);
						}else
							selectToggle(old_);
					});
				}else
					tabPane.getSelectionModel().select(0);
				return;
			}
			case ALBUM: {
				StringBuilder b = new StringBuilder("");
				if(StringUtils.isNotBlank(content.tHTMLProperty().getValue()))
					b.append(TLanguage.getInstance().getString(RDV_Key.RVIDA_TEXT_LABEL));
				if(!items.isEmpty()) {
					if(!b.toString().equals(""))
						b.append(TLanguage.getInstance().getString(" "+TUsualKey.AND+" "));
					b.append(RDV_Key.RVIDA_TABS_LABEL);
				}
				
				if(!b.toString().equals("")) {
					exec(TLanguage.getInstance().getFormatedString(RDV_Key.RVIDA_CLEAN_FIELDS_MESSAGE, b.toString()), c->{
						if(c) {
							content.tHTMLProperty().setValue("");
							items.clear();
							tabPane.getSelectionModel().select(1);
						} else
							selectToggle(old_);
					});
				}else
					tabPane.getSelectionModel().select(1);
				
				return;
			}
			case TABS: {
				
				StringBuilder b = new StringBuilder("");
				if(StringUtils.isNotBlank(content.tHTMLProperty().getValue()))
					b.append(TLanguage.getInstance().getString(RDV_Key.RVIDA_TEXT_LABEL));
				if(!images.isEmpty()) {
					if(!b.toString().equals(""))
						b.append(TLanguage.getInstance().getString(" "+TUsualKey.AND+" "));
					b.append(TLanguage.getInstance().getString(RDV_Key.RVIDA_ALBUM_LABEL));
				}
				
				if(!b.toString().equals("")) {
					exec(TLanguage.getInstance().getFormatedString(RDV_Key.RVIDA_CLEAN_FIELDS_MESSAGE, b.toString()), c->{
						if(c) {
							content.tHTMLProperty().setValue("");
							images.clear();
							tabPane.getSelectionModel().select(2);
						}else
							selectToggle(old_);
					});
				}else
					tabPane.getSelectionModel().select(2);
				return;
			}						
		}
	}


	private void selectToggle(ContentType old_) {
		THRadioGroup radio = super.getControl("type");
		radio.getTogleeGroup().selectToggle(radio.getTogleeGroup()
		.getToggles().stream()
		.filter(p->p.getUserData().equals(old_.name()))
		.findFirst().get());
	}
	
	private void exec(String msg, Consumer<Boolean> consumer) {
		ChangeListener<Number> chl = (a0, a1, a2) -> {
			TedrosContext.hideModal();
			consumer.accept(a2.equals(1));	
		};
		
		TConfirmMessageBox confirm = new TConfirmMessageBox(msg);
		confirm.tConfirmProperty().addListener(chl);
		TedrosContext.showModal(confirm);
	}

}
