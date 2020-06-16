package org.vaadin.alump.richtexteditor;

/*
 * #%L
 * alump's Vaadin Rich Text Editor
 * %%
 * Copyright (C) 2018 - 2020 Vaadin Ltd
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

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;

import java.util.Optional;

/**
 * Event emitted when link button or instance is clicked. One of the methods behind getResponseChannel() is expected to
 * be called following this event - once.
 */
@DomEvent("link-clicked")
public class RichTextEditorLinkEvent extends ComponentEvent<RichTextEditor> {

    /**
     * Interface to response handler
     */
    public interface ResponseChannel {

        /**
         * Apply given URL to edit
         * @param url URL to be applied
         */
        void apply(String url);

        /**
         * Call to remove edited link
         * @throws IllegalStateException Exception is throw if event wasn't for existing link
         */
        void remove() throws IllegalStateException;

        /**
         * Cancel link edit. Make no changes.
         */
        void cancel();

    }

    private final String url;
    private final ResponseChannel response;

    public RichTextEditorLinkEvent(RichTextEditor source, boolean fromClient,
            @EventData("event.detail.linkUrl") String url) {
        super(source, fromClient);
        this.url = Optional.ofNullable(url).filter(s -> !s.isEmpty()).orElse(null);
        this.response = source.createLinkResponseChannel(url);
    }

    /**
     * Get URL related to event. If empty, event isn't mapped to existing link. Remove response will only work with
     * existing links.
     * @return
     */
    public Optional<String> getUrl() {
        return Optional.ofNullable(url);
    }

    public boolean isExisting() {
        return getUrl().isPresent();
    }

    /**
     * Use response channel to send response
     * @return
     */
    public ResponseChannel getResponseChannel() {
        return response;
    }

}
