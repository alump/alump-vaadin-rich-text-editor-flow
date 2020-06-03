/*
 * Copyright 2000-2018 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.vaadin.alump.richtexteditor;

/*
 * #%L
 * Vaadin RichTextEditor for Vaadin 10
 * %%
 * Copyright (C) 2018 Vaadin Ltd
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

import java.io.Serializable;
/**
 * The internationalization properties for {@link RichTextEditor}.
 */
public class RichTextEditorI18n implements Serializable {
    private String undo;
    private String redo;
    private String bold;
    private String italic;
    private String underline;
    private String strike;
    private String h1;
    private String h2;
    private String h3;
    private String subscript;
    private String superscript;
    private String listOrdered;
    private String listBullet;
    private String alignLeft;
    private String alignCenter;
    private String alignRight;
    private String image;
    private String link;
    private String blockquote;
    private String codeBlock;
    private String clean;
    private String linkDialogTitle;
    private String ok;
    private String cancel;
    private String remove;
    private String indentLeft;
    private String indentRight;
    private String hr;
    private String fontSize;
    private String fontFamily;
    private String background;
    private String foreground;
    private String customAction;

    /**
     * Gets the translated word for {@code undo}
     *
     * @return the translated word for undo
     */
    public String getUndo() {
        return undo;
    }

    /**
     * Sets the translated word for {@code undo}.
     *
     * @param undo
     *            the translated word for undo
     * @return this instance for method chaining
     */
    public RichTextEditorI18n setUndo(String undo) {
        this.undo = undo;
        return this;
    }

    /**
     * Gets the translated word for {@code redo}
     *
     * @return the translated word for redo
     */
    public String getRedo() {
        return redo;
    }

    /**
     * Sets the translated word for {@code redo}.
     *
     * @param redo
     *            the translated word for redo
     * @return this instance for method chaining
     */
    public RichTextEditorI18n setRedo(String redo) {
        this.redo = redo;
        return this;
    }

    /**
     * Gets the translated word for {@code bold}
     *
     * @return the translated word for bold
     */
    public String getBold() {
        return bold;
    }

    /**
     * Sets the translated word for {@code bold}.
     *
     * @param bold
     *            the translated word for bold
     * @return this instance for method chaining
     */
    public RichTextEditorI18n setBold(String bold) {
        this.bold = bold;
        return this;
    }

    /**
     * Gets the translated word for {@code italic}
     *
     * @return the translated word for italic
     */
    public String getItalic() {
        return italic;
    }

    /**
     * Sets the translated word for {@code italic}.
     *
     * @param italic
     *            the translated word for italic
     * @return this instance for method chaining
     */
    public RichTextEditorI18n setItalic(String italic) {
        this.italic = italic;
        return this;
    }

    /**
     * Gets the translated word for {@code underline}
     *
     * @return the translated word for underline
     */
    public String getUnderline() {
        return underline;
    }

    /**
     * Sets the translated word for {@code underline}.
     *
     * @param underline
     *            the translated word for underline
     * @return this instance for method chaining
     */
    public RichTextEditorI18n setUnderline(String underline) {
        this.underline = underline;
        return this;
    }

    /**
     * Gets the translated word for {@code strike}
     *
     * @return the translated word for strike
     */
    public String getStrike() {
        return strike;
    }

    /**
     * Sets the translated word for {@code strike}.
     *
     * @param strike
     *            the translated word for strike
     * @return this instance for method chaining
     */
    public RichTextEditorI18n setStrike(String strike) {
        this.strike = strike;
        return this;
    }

    /**
     * Gets the translated word for {@code h1}
     *
     * @return the translated word for h1
     */
    public String getH1() {
        return h1;
    }

    /**
     * Sets the translated word for {@code h1}.
     *
     * @param h1
     *            the translated word for h1
     * @return this instance for method chaining
     */
    public RichTextEditorI18n setH1(String h1) {
        this.h1 = h1;
        return this;
    }

    /**
     * Gets the translated word for {@code h2}
     *
     * @return the translated word for h2
     */
    public String getH2() {
        return h2;
    }

    /**
     * Sets the translated word for {@code h2}.
     *
     * @param h2
     *            the translated word for h2
     * @return this instance for method chaining
     */
    public RichTextEditorI18n setH2(String h2) {
        this.h2 = h2;
        return this;
    }

    /**
     * Gets the translated word for {@code h3}
     *
     * @return the translated word for h3
     */
    public String getH3() {
        return h3;
    }

    /**
     * Sets the translated word for {@code h3}.
     *
     * @param h3
     *            the translated word for h3
     * @return this instance for method chaining
     */
    public RichTextEditorI18n setH3(String h3) {
        this.h3 = h3;
        return this;
    }

    /**
     * Gets the translated word for {@code subscript}
     *
     * @return the translated word for subscript
     */
    public String getSubscript() {
        return subscript;
    }

    /**
     * Sets the translated word for {@code subscript}.
     *
     * @param subscript
     *            the translated word for subscript
     * @return this instance for method chaining
     */
    public RichTextEditorI18n setSubscript(String subscript) {
        this.subscript = subscript;
        return this;
    }

    /**
     * Gets the translated word for {@code superscript}
     *
     * @return the translated word for superscript
     */
    public String getSuperscript() {
        return superscript;
    }

    /**
     * Sets the translated word for {@code superscript}.
     *
     * @param superscript
     *            the translated word for superscript
     * @return this instance for method chaining
     */
    public RichTextEditorI18n setSuperscript(String superscript) {
        this.superscript = superscript;
        return this;
    }

    /**
     * Gets the translated word for {@code listOrdered}
     *
     * @return the translated word for listOrdered
     */
    public String getListOrdered() {
        return listOrdered;
    }

    /**
     * Sets the translated word for {@code listOrdered}.
     *
     * @param listOrdered
     *            the translated word for listOrdered
     * @return this instance for method chaining
     */
    public RichTextEditorI18n setListOrdered(String listOrdered) {
        this.listOrdered = listOrdered;
        return this;
    }

    /**
     * Gets the translated word for {@code listBullet}
     *
     * @return the translated word for listBullet
     */
    public String getListBullet() {
        return listBullet;
    }

    /**
     * Sets the translated word for {@code listBullet}.
     *
     * @param listBullet
     *            the translated word for listBullet
     * @return this instance for method chaining
     */
    public RichTextEditorI18n setListBullet(String listBullet) {
        this.listBullet = listBullet;
        return this;
    }

    /**
     * Gets the translated word for {@code alignLeft}
     *
     * @return the translated word for alignLeft
     */
    public String getAlignLeft() {
        return alignLeft;
    }

    /**
     * Sets the translated word for {@code alignLeft}.
     *
     * @param alignLeft
     *            the translated word for alignLeft
     * @return this instance for method chaining
     */
    public RichTextEditorI18n setAlignLeft(String alignLeft) {
        this.alignLeft = alignLeft;
        return this;
    }

    /**
     * Gets the translated word for {@code alignCenter}
     *
     * @return the translated word for alignCenter
     */
    public String getAlignCenter() {
        return alignCenter;
    }

    /**
     * Sets the translated word for {@code alignCenter}.
     *
     * @param alignCenter
     *            the translated word for alignCenter
     * @return this instance for method chaining
     */
    public RichTextEditorI18n setAlignCenter(String alignCenter) {
        this.alignCenter = alignCenter;
        return this;
    }

    /**
     * Gets the translated word for {@code alignRight}
     *
     * @return the translated word for alignRight
     */
    public String getAlignRight() {
        return alignRight;
    }

    /**
     * Sets the translated word for {@code alignRight}.
     *
     * @param alignRight
     *            the translated word for alignRight
     * @return this instance for method chaining
     */
    public RichTextEditorI18n setAlignRight(String alignRight) {
        this.alignRight = alignRight;
        return this;
    }

    /**
     * Gets the translated word for {@code image}
     *
     * @return the translated word for image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the translated word for {@code image}.
     *
     * @param image
     *            the translated word for image
     * @return this instance for method chaining
     */
    public RichTextEditorI18n setImage(String image) {
        this.image = image;
        return this;
    }

    /**
     * Gets the translated word for {@code link}
     *
     * @return the translated word for link
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the translated word for {@code link}.
     *
     * @param link
     *            the translated word for link
     * @return this instance for method chaining
     */
    public RichTextEditorI18n setLink(String link) {
        this.link = link;
        return this;
    }

    /**
     * Gets the translated word for {@code blockquote}
     *
     * @return the translated word for blockquote
     */
    public String getBlockquote() {
        return blockquote;
    }

    /**
     * Sets the translated word for {@code blockquote}.
     *
     * @param blockquote
     *            the translated word for blockquote
     * @return this instance for method chaining
     */
    public RichTextEditorI18n setBlockquote(String blockquote) {
        this.blockquote = blockquote;
        return this;
    }

    /**
     * Gets the translated word for {@code codeBlock}
     *
     * @return the translated word for codeBlock
     */
    public String getCodeBlock() {
        return codeBlock;
    }

    /**
     * Sets the translated word for {@code codeBlock}.
     *
     * @param codeBlock
     *            the translated word for codeBlock
     * @return this instance for method chaining
     */
    public RichTextEditorI18n setCodeBlock(String codeBlock) {
        this.codeBlock = codeBlock;
        return this;
    }

    /**
     * Gets the translated word for {@code clean}
     *
     * @return the translated word for clean
     */
    public String getClean() {
        return clean;
    }

    /**
     * Sets the translated word for {@code clean}.
     *
     * @param clean
     *            the translated word for clean
     * @return this instance for method chaining
     */
    public RichTextEditorI18n setClean(String clean) {
        this.clean = clean;
        return this;
    }

    public String getLinkDialogTitle() {
        return linkDialogTitle;
    }

    public void setLinkDialogTitle(String linkDialogTitle) {
        this.linkDialogTitle = linkDialogTitle;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    public String getRemove() {
        return remove;
    }

    public void setRemove(String remove) {
        this.remove = remove;
    }

    public String getIndentLeft() {
        return indentLeft;
    }

    public void setIndentLeft(String identLeft) {
        this.indentLeft = identLeft;
    }

    public String getIndentRight() {
        return indentRight;
    }

    public void setIndentRight(String indentRight) {
        this.indentRight = indentRight;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getForeground() {
        return foreground;
    }

    public void setForeground(String foreground) {
        this.foreground = foreground;
    }

    public String getCustomAction() {
        return customAction;
    }

    public void setCustomAction(String customAction) {
        this.customAction = customAction;
    }

    /**
     * Gets the stringified values of the tooltips.
     *
     * @return stringified values of the tooltips
     */
    @Override
    public String toString() {
        return  "[" +
                undo + ", " +
                redo + ", " +
                bold + ", " +
                italic + ", " +
                underline + ", " +
                strike + ", " +
                h1 + ", " +
                h2 + ", " +
                h3 + ", " +
                subscript + ", " +
                superscript + ", " +
                listOrdered + ", " +
                listBullet + ", " +
                alignLeft + ", " +
                alignCenter + ", " +
                alignRight + ", " +
                image + ", " +
                link + ", " +
                blockquote + ", " +
                codeBlock + ", " +
                clean + "," +
                linkDialogTitle + "," +
                ok + "," +
                cancel + "," +
                remove + "," +
                indentLeft + "," +
                indentRight + "," +
                hr + "," +
                fontSize + "," +
                fontFamily + "," +
                foreground + "," +
                background + "," +
                customAction  + "]";
    }
}
