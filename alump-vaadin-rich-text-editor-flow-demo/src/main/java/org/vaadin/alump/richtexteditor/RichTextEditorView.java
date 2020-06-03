package org.vaadin.alump.richtexteditor;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.demo.DemoView;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.textfield.TextField;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * View for {@link RichTextEditor} demo.
 */
@Uses(ConfirmDialog.class)
@Uses(TextField.class)
@Uses(Dialog.class)
@Route("vaadin-rich-text-editor")
public class RichTextEditorView extends DemoView {

    private Map<String, String> valueMap = new HashMap<>();

    @Override
    protected void initView() {
        createDefaultEditor();
        createGetValue();
        createGetHtmlValue();
    }

    private void createDefaultEditor() {

        TextField textField = new TextField();
        add(textField);
        textField.setVisible(false);
        ConfirmDialog dialog = new ConfirmDialog();

        // begin-source-example
        // source-example-heading: Basic Rich Text Editor
        RichTextEditor rte =
                new RichTextEditor();

        rte.addServerButtonClickedListener(this::handleServerButtonClicked);
        rte.addDataEntryClickedListener(this::handleDataEntryClicked);

        add(rte);
        // end-source-example

        addCard("Basic Rich Text Editor", rte);
    }

    private void handleServerButtonClicked(RichTextEditor.ServerButtonClickedEvent event) {
        new ValueDialog(value -> addValue(event, value)).open();
    }

    private void addValue(RichTextEditor.ServerButtonClickedEvent event, String value) {
        String id = UUID.randomUUID().toString();
        valueMap.put(id, value);
        RichTextEditor.ServerButtonClickedEvent castedEvent = (RichTextEditor.ServerButtonClickedEvent)event;
        Integer index = castedEvent.getIndex();
        RichTextEditor editor = event.getSource();
        event.getSource().getElement().callJsFunction("insertDataEntry", id, value, index);
    }

    private void handleDataEntryClicked(RichTextEditor.DataEntryClickedEvent event) {
        String oldValue = valueMap.getOrDefault(event.getDataId(), null);
        if (oldValue == null) {
            Notification.show("Failed to resolve value").addThemeVariants(NotificationVariant.LUMO_ERROR);
        } else {
            new ValueDialog(value -> valueMap.put(event.getDataId(), value), oldValue).open();
        }
    }

    private void createGetValue() {
        // begin-source-example
        // source-example-heading: Save Rich Text Editor value
        Div valueBlock = new Div();
        RichTextEditor rte = new RichTextEditor();
        Button saveBtn = new Button("Save value", e -> valueBlock.setText(rte.getValue()));
        Button setBtn = new Button("Set value", e ->  rte.setValue(valueBlock.getText()));

        rte.addServerButtonClickedListener(this::handleServerButtonClicked);
        rte.addDataEntryClickedListener(this::handleDataEntryClicked);

        add(rte, saveBtn, setBtn, valueBlock);
        // end-source-example

        addCard("Save Rich Text Editor value", rte, saveBtn, setBtn, valueBlock);
    }

    private void createGetHtmlValue() {
        // begin-source-example
        // source-example-heading: Save Rich Text Editor htmlValue
        Div htmlBlock = new Div();
        RichTextEditor rte = new RichTextEditor();
        Button showHtmlValue = new Button("Show html value", e -> {
            String exsValue = htmlBlock.getElement().getProperty("innerHTML");
            if (exsValue == null || !exsValue.equals(rte.getHtmlValue())) {
                htmlBlock.getElement().setProperty("innerHTML", rte.getHtmlValue());
            }
        });

        add(rte, showHtmlValue, htmlBlock);
        // end-source-example

        addCard("Save Rich Text Editor htmlValue", rte, showHtmlValue, htmlBlock);
    }

}
