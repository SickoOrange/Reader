package org.swdc.reader.ui.views;

import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLView;
import javafx.scene.control.Accordion;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.BeanPropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.swdc.reader.core.configs.PDFConfig;
import org.swdc.reader.core.configs.TextConfig;
import org.swdc.reader.ui.ApplicationConfig;
import org.swdc.reader.utils.UIUtils;

import javax.annotation.PostConstruct;

/**
 * Created by lenovo on 2019/6/8.
 */
@FXMLView("/views/ConfigView.fxml")
public class ConfigView extends AbstractFxmlView {

    @Autowired
    private ApplicationConfig config;

    @Autowired
    private TextConfig textConfig;

    @Autowired
    private PDFConfig pdfConfig;

    @PostConstruct
    protected void initUI() throws Exception {
        UIUtils.configUI((BorderPane)getView(), config);
        BorderPane pane = (BorderPane)getView();
        TabPane configTabs = (TabPane) pane.getCenter();

        PropertySheet configSheet = new PropertySheet(UIUtils.getProperties(config));
        configSheet.getStyleClass().add("config-sheet");
        configSheet.setPropertyEditorFactory(item -> UIUtils.getEditor(item, config));
        configSheet.setModeSwitcherVisible(false);
        configTabs.getTabs().add(new Tab("通用配置", configSheet));

        PropertySheet textReaderSheet = new PropertySheet(UIUtils.getProperties(textConfig));
        textReaderSheet.setPropertyEditorFactory(item -> UIUtils.getEditor(item, config));
        textReaderSheet.setModeSwitcherVisible(false);
        textReaderSheet.getStyleClass().add("config-sheet");
        configTabs.getTabs().add(new Tab("文本渲染", textReaderSheet));

        PropertySheet pdfReaderSheet = new PropertySheet(UIUtils.getProperties(pdfConfig));
        pdfReaderSheet.setPropertyEditorFactory(item -> UIUtils.getEditor(item, config));
        pdfReaderSheet.setModeSwitcherVisible(false);
        pdfReaderSheet.getStyleClass().add("config-sheet");
        configTabs.getTabs().add(new Tab("Adobe PDF渲染", pdfReaderSheet));
    }

}
