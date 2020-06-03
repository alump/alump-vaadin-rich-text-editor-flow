package org.vaadin.alump.richtexteditor;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.function.Consumer;

public class ValueDialog extends Dialog {

    private TextField valueField;

    public ValueDialog(Consumer<String> valueConsumer, String initialValue) {
        this(valueConsumer);
        valueField.setValue(initialValue);
    }

    public ValueDialog(Consumer<String> valueConsumer) {
        valueField = new TextField();
        valueField.setLabel("Value");

        Button applyButton = new Button("Add", e -> {
            valueConsumer.accept(valueField.getValue());
            ValueDialog.this.close();
        });
        applyButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button cancelButton = new Button("Cancel", e -> ValueDialog.this.close());
        cancelButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        HorizontalLayout buttonRow = new HorizontalLayout(applyButton, cancelButton);

        VerticalLayout dialogLayout = new VerticalLayout(valueField, buttonRow);
        add(dialogLayout);
    }
}
