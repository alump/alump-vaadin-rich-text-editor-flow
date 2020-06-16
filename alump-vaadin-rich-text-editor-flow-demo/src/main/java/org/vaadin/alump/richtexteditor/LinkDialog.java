package org.vaadin.alump.richtexteditor;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

/**
 * Simple dummy server side link editor dialog
 */
public class LinkDialog extends Dialog {

    private final RichTextEditorLinkEvent event;
    private TextField linkField;

    public LinkDialog(RichTextEditorLinkEvent event) {
        this.event = event;
        setWidth("400px");

        VerticalLayout layout = new VerticalLayout();
        layout.setWidth("100%");

        linkField = new TextField();
        linkField.setWidth("100%");
        linkField.setPlaceholder("Write URL here...");
        linkField.setLabel("Link address:");
        event.getUrl().ifPresent(linkField::setValue);

        HorizontalLayout buttons = new HorizontalLayout();

        Button cancel = new Button("Cancel", e -> cancel());
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttons.add(cancel);

        Span placeHolder = new Span("");
        buttons.addAndExpand(placeHolder);

        if (event.isExisting()) {
            Button remove = new Button("Remove", e -> remove());
            remove.addThemeVariants(ButtonVariant.LUMO_ERROR);
            buttons.add(remove);
        }

        Button apply = new Button("Apply", e -> apply(linkField.getValue()));
        apply.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttons.add(apply);

        layout.add(linkField, buttons);
        add(layout);
    }

    private void apply(String url) {
        event.getResponseChannel().apply(url);
        close();
    }

    private void cancel() {
        event.getResponseChannel().cancel();
        close();
    }

    private void remove() {
        event.getResponseChannel().remove();
        close();
    }
}