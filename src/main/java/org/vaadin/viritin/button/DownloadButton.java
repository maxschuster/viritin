package org.vaadin.viritin.button;

import com.vaadin.event.ContextClickEvent;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Locale;

/**
 * A helper class to implement typical file downloads.
 * <p>
 * With this class you'll get rid of lots of boilerplate code from your
 * application. It also inverts the bit cumbersome input-output stream API in
 * Vaadin so, normally you just "wire" this button to your backend method that
 * writes your resource to OutputStream (instead of playing around with piped streams or storing
 * resources temporary in memory. Example of usage:
 * <pre><code>
 *  new DownloadButton(invoice::toPdf).setFileName("invoice.pdf")
 * </code></pre>
 * <p>
 * The button extension hooks FileDownloader extension internally and inverts
 * the cumbersome default Vaadin API.
 * <p>
 * The writing of response is also spawn to separate thread, so in case your
 * resource generation takes lots of time, the UI wont block.
 */
public class DownloadButton extends MButton {

    public interface ContentWriter {

        void write(OutputStream stream);
    }

    private ContentWriter writer;

    private final StreamResource streamResource = new StreamResource(
            new StreamResource.StreamSource() {

                @Override
                public InputStream getStream() {
                    try {
                        final PipedOutputStream out = new PipedOutputStream();
                        final PipedInputStream in = new PipedInputStream(out);
                        writeResponce(out);
                        return in;
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            }, "file");

    /**
     * Constructs a new Download button without ContentWriter. Be sure to set
     * the ContentWriter or override its getter, before instance is actually 
     * used.
     */
    public DownloadButton() {
        new FileDownloader(streamResource).extend(this);

    }

    public DownloadButton(ContentWriter writer) {
        this();
        this.writer = writer;
    }

    /**
     * By default just spans a new raw thread to get the input. For strict Java
     * EE fellows, this might not suite, so override and use executor service.
     *
     * @param out the output stream where the output is targeted
     */
    protected void writeResponce(final PipedOutputStream out) {
        new Thread() {
            @Override
            public void run() {
                getWriter().write(out);
            }
        }.start();
    }

    public ContentWriter getWriter() {
        return writer;
    }

    public String getMimeType() {
        return streamResource.getFilename();
    }

    public DownloadButton setMimeType(String mimeType) {
        streamResource.setMIMEType(mimeType);
        return this;
    }

    public DownloadButton setCacheTime(long cacheTime) {
        streamResource.setCacheTime(cacheTime);
        return this;
    }

    public DownloadButton setWriter(ContentWriter writer) {
        this.writer = writer;
        return this;
    }

    public String getFileName() {
        return streamResource.getFilename();
    }

    public DownloadButton setFileName(String fileName) {
        streamResource.setFilename(fileName);
        return this;
    }
    
    /* Fluent setters (FluentAbstractComponent): */

    @Override
    public DownloadButton withHeightUndefined() {
        return (DownloadButton) super.withHeightUndefined();
    }

    @Override
    public DownloadButton withWidthUndefined() {
        return (DownloadButton) super.withWidthUndefined();
    }

    @Override
    public DownloadButton withSizeUndefined() {
        return (DownloadButton) super.withSizeUndefined();
    }

    @Override
    public DownloadButton withSizeFull() {
        return (DownloadButton) super.withSizeFull();
    }

    @Override
    public DownloadButton withWidth(float width, Unit unit) {
        return (DownloadButton) super.withWidth(width, unit);
    }

    @Override
    public DownloadButton withWidth(String width) {
        return (DownloadButton) super.withWidth(width);
    }

    @Override
    public DownloadButton withHeight(float height, Unit unit) {
        return (DownloadButton) super.withHeight(height, unit);
    }

    @Override
    public DownloadButton withHeight(String height) {
        return (DownloadButton) super.withHeight(height);
    }

    @Override
    public DownloadButton withContextClickListener(ContextClickEvent.ContextClickListener listener) {
        return (DownloadButton) super.withContextClickListener(listener);
    }

    @Override
    public DownloadButton withErrorHandler(ErrorHandler errorHandler) {
        return (DownloadButton) super.withErrorHandler(errorHandler);
    }

    @Override
    public DownloadButton withDetachListener(DetachListener listener) {
        return (DownloadButton) super.withDetachListener(listener);
    }

    @Override
    public DownloadButton withAttachListener(AttachListener listener) {
        return (DownloadButton) super.withAttachListener(listener);
    }

    @Override
    public DownloadButton withVisible(boolean visible) {
        return (DownloadButton) super.withVisible(visible);
    }

    @Override
    public DownloadButton withStyleName(String... styles) {
        return (DownloadButton) super.withStyleName(styles);
    }

    @Override
    public DownloadButton withReadOnly(boolean readOnly) {
        return (DownloadButton) super.withReadOnly(readOnly);
    }

    @Override
    public DownloadButton withPrimaryStyleName(String style) {
        return (DownloadButton) super.withPrimaryStyleName(style);
    }

    @Override
    public DownloadButton withId(String id) {
        return (DownloadButton) super.withId(id);
    }

    @Override
    public DownloadButton withIcon(Resource icon) {
        return (DownloadButton) super.withIcon(icon);
    }

    @Override
    public DownloadButton withEnabled(boolean enabled) {
        return (DownloadButton) super.withEnabled(enabled);
    }

    @Override
    public DownloadButton withCaption(String caption) {
        return (DownloadButton) super.withCaption(caption);
    }

    @Override
    public DownloadButton withShortcutListener(ShortcutListener shortcut) {
        return (DownloadButton) super.withShortcutListener(shortcut);
    }

    @Override
    public DownloadButton withResponsive(boolean responsive) {
        return (DownloadButton) super.withResponsive(responsive);
    }

    @Override
    public DownloadButton withData(Object data) {
        return (DownloadButton) super.withData(data);
    }

    @Override
    public DownloadButton withListener(Listener listener) {
        return (DownloadButton) super.withListener(listener);
    }

    @Override
    public DownloadButton withComponentError(ErrorMessage componentError) {
        return (DownloadButton) super.withComponentError(componentError);
    }

    @Override
    public DownloadButton withDescription(String description) {
        return (DownloadButton) super.withDescription(description);
    }

    @Override
    public DownloadButton withImmediate(boolean immediate) {
        return (DownloadButton) super.withImmediate(immediate);
    }

    @Override
    public DownloadButton withLocale(Locale locale) {
        return (DownloadButton) super.withLocale(locale);
    }

    @Override
    public DownloadButton withCaptionAsHtml(boolean captionAsHtml) {
        return (DownloadButton) super.withCaptionAsHtml(captionAsHtml);
    }

    @Override
    public DownloadButton withStyleName(String style, boolean add) {
        return (DownloadButton) super.withStyleName(style, add);
    }

}
