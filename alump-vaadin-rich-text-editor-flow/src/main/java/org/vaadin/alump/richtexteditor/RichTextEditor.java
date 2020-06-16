package org.vaadin.alump.richtexteditor;

/*
 * #%L
 * Vaadin Rich Text Editor for Vaadin 10
 * %%
 * Copyright (C) 2017 - 2018 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file license.html distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <http://vaadin.com/license/cval-3>.
 * #L%
 */

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.page.PendingJavaScriptResult;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.HasValueChangeMode;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.SerializableConsumer;
import com.vaadin.flow.internal.JsonSerializer;
import com.vaadin.flow.shared.Registration;
import elemental.json.JsonObject;

import java.util.Objects;

/**
 * Server-side component for the {@code <vaadin-rich-text-editor>} component.
 * <p>
 * The value of the rich text editor is in
 * <a href="https://github.com/quilljs/delta">Delta</a> format. The
 * {@link #setValue(String) setValue} and {@link #getValue() getValue} methods
 * deal with the default Delta format, but it is also possible to get and set
 * the value as an HTML string using
 * <code>rte.{@link #asHtml()}.{@link AsHtml#getValue() getValue()}</code>,
 * <code>rte.{@link #asHtml()}.{@link AsHtml#setValue(String) setValue()}</code>
 * and {@link #getHtmlValue()}.
 *
 * @author Vaadin Ltd
 *
 */
@Tag("alump-vaadin-rich-text-editor")
public class RichTextEditor extends GeneratedVaadinRichTextEditor<RichTextEditor, String>
        implements HasSize, HasValueChangeMode, InputNotifier, KeyNotifier, CompositionNotifier,
        Focusable<RichTextEditor> {

    private ValueChangeMode currentMode;
    private RichTextEditorI18n i18n;
    private AsHtml asHtml;
    protected Registration linkHandlerRegistration = null;

    @DomEvent("server-button-clicked")
    public static class ServerButtonClickedEvent extends ComponentEvent<RichTextEditor> {

        private final Integer button;
        private final Integer index;

        public ServerButtonClickedEvent(RichTextEditor source, boolean fromClient,
                @EventData("event.detail.button") Integer eventButton,
                @EventData("event.detail.index") Integer index) {
            super(source, fromClient);
            this.button = eventButton;
            this.index = index;
        }

        public Integer getButton() {
            return button;
        }

        public Integer getIndex() {
            return index;
        }
    }

    @DomEvent("data-entry-clicked")
    public static class DataEntryClickedEvent extends ComponentEvent<RichTextEditor> {

        private final String dataId;

        public DataEntryClickedEvent(RichTextEditor source, boolean fromClient,
                @EventData("event.detail.id") String eventId) {

            super(source, fromClient);
            this.dataId = eventId;
        }

        public String getDataId() {
            return dataId;
        }
    }

    /**
     * Gets the internationalization object previously set for this component.
     * <p>
     * Note: updating the object content that is gotten from this method will
     * not update the lang on the component if not set back using
     * {@link RichTextEditor#setI18n(RichTextEditorI18n)}
     *
     * @return the i18n object. It will be <code>null</code>, If the i18n
     *         properties weren't set.
     */
    public RichTextEditorI18n getI18n() {
        return i18n;
    }

    /**
     * Sets the internationalization properties for this component.
     *
     * @param i18n
     *            the internationalized properties, not <code>null</code>
     */
    public void setI18n(RichTextEditorI18n i18n) {
        Objects.requireNonNull(i18n,
                "The I18N properties object should not be null");
        this.i18n = i18n;
        runBeforeClientResponse(ui -> {
            if (i18n == this.i18n) {
                JsonObject i18nObject = (JsonObject) JsonSerializer
                        .toJson(this.i18n);
                for (String key : i18nObject.keys()) {
                    ui.getPage().executeJavaScript(
                            "$0.set('i18n." + key + "', $1)", getElement(),
                            i18nObject.get(key));
                }
            }
        });
    }

    void runBeforeClientResponse(SerializableConsumer<UI> command) {
        getElement().getNode().runWhenAttached(ui -> ui
                .beforeClientResponse(this, context -> command.accept(ui)));
    }

    /**
     * Constructs an empty {@code RichTextEditor}.
     */
    public RichTextEditor() {
        super("", "", false);
        setValueChangeMode(ValueChangeMode.ON_CHANGE);
    }

    /**
     * Constructs a {@code RichTextEditor} with the initial value
     *
     * @param initialValue
     *            the initial value in Delta format, not {@code null}
     *
     * @see #setValue(Object)
     */
    public RichTextEditor(String initialValue) {
        this();
        setValue(initialValue);
    }

    /**
     * Constructs an empty {@code RichTextEditor} with a value change listener.
     *
     * @param listener
     *            the value change listener
     *
     * @see #addValueChangeListener(com.vaadin.flow.component.HasValue.ValueChangeListener)
     */
    public RichTextEditor(
            ValueChangeListener<? super ComponentValueChangeEvent<RichTextEditor, String>> listener) {
        this();
        addValueChangeListener(listener);
    }

    /**
     * Constructs an empty {@code RichTextEditor} with a value change
     * listener and an initial value.
     *
     * @param initialValue
     *            the initial value
     * @param listener
     *            the value change listener
     *
     * @see #setValue(Object)
     * @see #addValueChangeListener(com.vaadin.flow.component.HasValue.ValueChangeListener)
     */
    public RichTextEditor(String initialValue,
                          ValueChangeListener<? super ComponentValueChangeEvent<RichTextEditor, String>> listener) {
        this();
        setValue(initialValue);
        addValueChangeListener(listener);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default value is {@link ValueChangeMode#ON_CHANGE}.
     */
    @Override
    public ValueChangeMode getValueChangeMode() {
        return currentMode;
    }

    @Override
    public void setValueChangeMode(ValueChangeMode valueChangeMode) {
        currentMode = valueChangeMode;
        setSynchronizedEvent(
                ValueChangeMode.eventForMode(valueChangeMode, "value-changed"));
    }

    /**
     * Sets the value of this editor. Should be in <a href="https://github.com/quilljs/delta">Delta</a> format.
     * If the new value is not equal to
     * {@code getValue()}, fires a value change event. Throws
     * {@code NullPointerException}, if the value is null.
     * <p>
     * Note: {@link Binder} will take care of the {@code null} conversion when
     * integrates with the editor, as long as no new converter is defined.
     *
     * @see #asHtml()
     * @see AsHtml#setValue(String)
     * @param value
     *            the new value in Delta format, not {@code null}
     */
    @Override
    public void setValue(String value) {
        super.setValue(value);
    }

    /**
     * Sets content represented by sanitized HTML string into the editor.
     * The HTML string is interpreted by
     * <a href="http://quilljs.com/docs/modules/clipboard/#matchers">Quill's Clipboard matchers</a>
     * on the client side, which may not produce the exactly input HTML.
     * <p>
     * Note: The value will be set asynchronously with client-server roundtrip.
     *
     * @param htmlValueString
     *            the HTML string
     */
    private PendingJavaScriptResult setHtmlValueAsynchronously(String htmlValueString) {
        if (htmlValueString != null) {
            htmlValueString = sanitize(htmlValueString);
        }
        return getElement().callJsFunction("dangerouslySetHtmlValue", htmlValueString);
    }

    @ClientCallable
    private void updateValue(String value) {
        setValue(value);
    }

    /**
     * Returns the current value of the text editor in <a href="https://github.com/quilljs/delta">Delta</a> format. By default, the empty
     * editor will return an empty string.
     *
     * @see #getHtmlValue()
     * @see #asHtml()
     * @see AsHtml#getValue()
     * @return the current value.
     */
    @Override
    public String getValue() {
        return super.getValue();
    }


    /**
     * The value of the editor presented as an HTML string.
     * <p>
     * This represents the value currently set on the client side. If you have
     * just set the value on the server side using {@link #setValue(String)} or
     * {@link AsHtml#setValue(String)} then the value returned from this method
     * will not yet correspond to the newly set value until the next server
     * round trip.
     *
     * @see #getValue()
     * @see #asHtml()
     * @see AsHtml#getValue()
     * @return the sanitized {@code htmlValue} property from the web component
     * or {@code null} if it is not available.
     */
    public String getHtmlValue() {
        String htmlValueString = getHtmlValueString();
        if (htmlValueString == null) {
            return null;
        }

        // Using basic whitelist and adding img tag with data protocol enabled.
        return sanitize(htmlValueString);
    }

    String sanitize(String html) {
        return org.jsoup.Jsoup.clean(html,
                org.jsoup.safety.Whitelist.basic()
                        .addTags("img", "h1", "h2", "h3", "s")
                        .addAttributes("img", "align", "alt", "height", "src", "title", "width")
                        .addAttributes(":all", "style")
                        .addProtocols("img", "src", "data"));
    }

    /**
     * Add listener for server button(s) clicks
     * @param listener
     * @return
     */
    public Registration addServerButtonClickedListener(ComponentEventListener<ServerButtonClickedEvent> listener) {
        return ComponentUtil.addListener(this, ServerButtonClickedEvent.class, listener);
    }

    /**
     * Add listener when user clicks the dynamic data entries in text
     * @param listener
     * @return
     */
    public Registration addDataEntryClickedListener(ComponentEventListener<DataEntryClickedEvent> listener) {
        return ComponentUtil.addListener(this, DataEntryClickedEvent.class, listener);
    }

    public void setLinkEventListener(ComponentEventListener<RichTextEditorLinkEvent> listener) {
        if(linkHandlerRegistration != null) {
            linkHandlerRegistration.remove();
        }
        this.linkHandlerRegistration = ComponentUtil.addListener(this, RichTextEditorLinkEvent.class, listener);
        getElement().setProperty("emitlinkevents", true);
    }

    public void unsetLinkEventListener() {
        if(linkHandlerRegistration != null) {
            linkHandlerRegistration.remove();
            linkHandlerRegistration = null;
        }
        getElement().removeProperty("emitlinkevents");
    }

    /**
     * Insert dynamic data value element into text
     * @param id ID of dynamic data element
     * @param description Description shown as tooltip. Notice that to modify this after it's added you need to do it
     *                    via value JSON. No easy API to change tooltip on existing entry yet.
     * @param index If not null the location where to add. If null will try to add to current focus (notice focus
     *              might change when server round trips are done, so usually you want to provide this value).
     */
    public void insertDataValue(String id, String description, Integer index) {
        if (index != null) {
            getElement().callJsFunction("insertDataEntry", id, description, index);
        } else {
            getElement().callJsFunction("insertDataEntry", id, description);
        }
    }

    /**
     * Gets an instance of {@code HasValue} for binding the
     * html value of the editor with {@code Binder}.
     *
     * @return an instance of {@code HasValue}
     */
    public HasValue<ValueChangeEvent<String>, String> asHtml() {
        if (asHtml == null) {
            asHtml = new AsHtml(this);
        }
        return asHtml;
    }

    /**
     * Focuses editor, and moves cursor to given index. Notice that normal focus will try to recover the previous
     * selection/focus positions.
     * @param index Index to focus
     */
    public void focusToIndex(int index) {
        getElement().callJsFunction("focus", index);
    }

    /**
     * Select length of content from index
     * @param index Index to start selection
     * @param length Length of selection
     */
    public void select(int index, int length) {
        getElement().callJsFunction("select", index, length);
    }

    RichTextEditorLinkEvent.ResponseChannel createLinkResponseChannel(final String origUrl) {
        return new RichTextEditorLinkEvent.ResponseChannel() {

            private boolean responseCalled = false;

            @Override
            public void apply(String url) {
                if(responseCalled) {
                    throw new IllegalStateException("Response already sent");
                } else {
                    getElement().callJsFunction("editLinkConfirm", Objects.requireNonNull(url));
                    responseCalled = true;
                }
            }

            @Override
            public void remove() throws IllegalStateException {
                if(responseCalled) {
                    throw new IllegalStateException("Response already sent");
                } else if(origUrl == null) {
                    throw new IllegalStateException("Remove only allowed on existing links");
                } else {
                    getElement().callJsFunction("editLinkRemove");
                    responseCalled = true;
                }
            }

            @Override
            public void cancel() {
                if(responseCalled) {
                    throw new IllegalStateException("Response already sent");
                } else {
                    getElement().callJsFunction("editLinkCancel");
                    responseCalled = true;
                }
            }
        };
    }

    /**
     * Use this rich text editor as an editor with html value in {@link Binder}.
     */
    private class AsHtml implements HasValue<ValueChangeEvent<String>, String> {

        private String oldValue;
        private String value;
        private RichTextEditor rte;

        AsHtml(RichTextEditor rte) {
            this.rte = rte;
            this.value = getHtmlValue();
            rte.addValueChangeListener(event -> {
                if (event.isFromClient()) {
                   setValue(getHtmlValue(), false);
                }
            });
        }

        /**
         * Sets the value of the editor presented as an HTML string. Also
         * updates the old value which is provided in {@code ValueChangeEvent}.
         * <p>
         * On the client side the newly set HTML snippet is interpreted by
         * <a href=
         * "https://quilljs.com/docs/modules/clipboard/#matchers">Quill's
         * Clipboard matchers</a>, which may not produce the exactly same HTML
         * that was set. The server side value will be updated to reflect the
         * new state after the round trip.
         *
         * @see RichTextEditor#setValue(String)
         * @param value
         *            the HTML string
         */
        @Override
        public void setValue(String value) {
            this.oldValue = getValue();
            this.value = value;
            setHtmlValueAsynchronously(value).then(result -> {
                if (oldValue != null && !oldValue.equals(value)) {
                    fireEvent(createValueChange(oldValue, false));
                }
            });
        }

        private void setValue(String value, boolean fireEvent) {
            if (fireEvent) {
                setValue(value);
            } else {
                this.oldValue = getValue();
                this.value = value;
            }
        }

        private ComponentValueChangeEvent<RichTextEditor, String> createValueChange(String oldValue,
                                                                  boolean fromClient) {
            return new ComponentValueChangeEvent<>(rte, this, oldValue,
                    fromClient);
        }

        /**
         * Gets the value of the editor presented as an HTML string.
         * <p>
         * If you have just set the value on the server side using the
         * {@link #setValue(String) AsHtml.setValue()} method then his method
         * will give you back the exact same value until the next server round
         * trip. On the client side the newly set HTML snippet is interpreted by
         * <a href=
         * "https://quilljs.com/docs/modules/clipboard/#matchers">Quill's
         * Clipboard matchers</a>, which may not produce the exactly same HTML
         * that was set. The server side value will be updated to reflect the
         * new state after the round trip.
         *
         * @see RichTextEditor#getValue()
         * @see RichTextEditor#getHtmlValue()
         * @return the sanitized HTML string
         */
        @Override
        public String getValue() {
            return value;
        }

        /**
         * Adds a value change listener. The listener is called when the value of
         * this {@code HasValue} is changed either by the user or programmatically.
         *
         * @param listener
         *            the value change listener, not null
         * @return a registration for the listener
         */
        @Override
        public Registration addValueChangeListener(ValueChangeListener listener) {
            return
                    rte.addValueChangeListener(originalEvent -> {
                        ValueChangeEvent event = new ValueChangeEvent<String>() {
                            @Override
                            public HasValue<ValueChangeEvent<String>, String> getHasValue() {
                                return AsHtml.this;
                            }

                            @Override
                            public boolean isFromClient() {
                                return originalEvent.isFromClient();
                            }

                            @Override
                            public String getOldValue() {
                                return oldValue;
                            }

                            @Override
                            public String getValue() {
                                return AsHtml.this.getValue();
                            }
                        };
                        listener.valueChanged(event);
                    });
        }

        /**
         * Sets the editor to be read only.
         *
         * @param readOnly
         *            {@code true} to make the editor read only,
         *            {@code false} to make the editor not read only
         */
        @Override
        public void setReadOnly(boolean readOnly) {
            rte.setReadOnly(readOnly);
        }

        /**
         * Gets whether the editor is read only.
         *
         * @return {@code true} if the editor is read only,
         *         {@code false} if it is not read only
         */
        @Override
        public boolean isReadOnly() {
            return rte.isReadOnly();
        }

        /**
         * Sets the editor's required indicator visibility.
         *
         * @param requiredIndicatorVisible
         *            {@code true} to make the indicator visible,
         *            {@code false} to hide the indicator
         */
        @Override
        public void setRequiredIndicatorVisible(boolean requiredIndicatorVisible) {
            rte.setRequiredIndicatorVisible(requiredIndicatorVisible);
        }

        /**
         * Gets whether editor's required indicator is visible.
         *
         * @return {@code true} if the required indicator is visible,
         *         {@code false} if it is hidden.
         */
        @Override
        public boolean isRequiredIndicatorVisible() {
            return rte.isRequiredIndicatorVisible();
        }
    }
}
