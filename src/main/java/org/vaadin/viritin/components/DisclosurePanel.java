package org.vaadin.viritin.components;

import com.vaadin.event.ContextClickEvent;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.FontIcon;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.Locale;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fluency.ui.FluentAbstractComponent;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 */
public class DisclosurePanel extends VerticalLayout implements 
        FluentAbstractComponent {

    private FontIcon closedIcon = FontAwesome.PLUS_CIRCLE;
    private FontIcon openIcon = FontAwesome.MINUS_CIRCLE;

    private MButton toggle = new MButton(closedIcon);
    private MVerticalLayout contentWrapper = new MVerticalLayout();

    public DisclosurePanel() {
        toggle.setStyleName(ValoTheme.BUTTON_BORDERLESS);
        contentWrapper.setVisible(false);
        addComponents(toggle, contentWrapper);
        toggle.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                setOpen(!isOpen());
            }
        });
    }

    public DisclosurePanel(String caption, Component... content) {
        this();
        setCaption(caption);
        contentWrapper.add(content);
    }

    public boolean isOpen() {
        return toggle.getIcon() == openIcon;
    }

    public DisclosurePanel setOpen(boolean open) {
        contentWrapper.setVisible(open);
        toggle.setIcon(open ? getOpenIcon() : getClosedIcon());
        return this;
    }

    public DisclosurePanel setContent(Component... content) {
        this.contentWrapper.removeAllComponents();
        this.contentWrapper.add(content);
        return this;
    }

    @Override
    public void setCaption(String caption) {
        toggle.setCaption(caption);
    }

    public MVerticalLayout getContentWrapper() {
        return contentWrapper;
    }

    public FontIcon getClosedIcon() {
        return closedIcon;
    }

    public void setClosedIcon(FontIcon closedIcon) {
        this.closedIcon = closedIcon;
    }

    public FontIcon getOpenIcon() {
        return openIcon;
    }

    public void setOpenIcon(FontIcon openIcon) {
        this.openIcon = openIcon;
    }
    
    /* Fluent setters (FluentAbstractComponent): */

    @Override
    public DisclosurePanel withStyleName(String style, boolean add) {
        setStyleName(style, add);
        return this;
    }

    @Override
    public DisclosurePanel withCaptionAsHtml(boolean captionAsHtml) {
        setCaptionAsHtml(captionAsHtml);
        return this;
    }

    @Override
    public DisclosurePanel withLocale(Locale locale) {
        setLocale(locale);
        return this;
    }

    @Override
    public DisclosurePanel withImmediate(boolean immediate) {
        setImmediate(immediate);
        return this;
    }

    @Override
    public DisclosurePanel withDescription(String description) {
        setDescription(description);
        return this;
    }

    @Override
    public DisclosurePanel withComponentError(ErrorMessage componentError) {
        setComponentError(componentError);
        return this;
    }

    @Override
    public DisclosurePanel withListener(Listener listener) {
        addListener(listener);
        return this;
    }

    @Override
    public DisclosurePanel withData(Object data) {
        setData(data);
        return this;
    }

    @Override
    public DisclosurePanel withResponsive(boolean responsive) {
        setResponsive(responsive);
        return this;
    }

    @Override
    public DisclosurePanel withShortcutListener(ShortcutListener shortcut) {
        addShortcutListener(shortcut);
        return this;
    }

    @Override
    public DisclosurePanel withCaption(String caption) {
        setCaption(caption);
        return this;
    }

    @Override
    public DisclosurePanel withEnabled(boolean enabled) {
        setEnabled(enabled);
        return this;
    }

    @Override
    public DisclosurePanel withIcon(Resource icon) {
        setIcon(icon);
        return this;
    }

    @Override
    public DisclosurePanel withId(String id) {
        setId(id);
        return this;
    }

    @Override
    public DisclosurePanel withPrimaryStyleName(String style) {
        setPrimaryStyleName(style);
        return this;
    }

    @Override
    public DisclosurePanel withReadOnly(boolean readOnly) {
        setReadOnly(readOnly);
        return this;
    }

    @Override
    public DisclosurePanel withStyleName(String style) {
        setStyleName(style);
        return this;
    }

    @Override
    public DisclosurePanel withVisible(boolean visible) {
        setVisible(visible);
        return this;
    }

    @Override
    public DisclosurePanel withAttachListener(AttachListener listener) {
        addAttachListener(listener);
        return this;
    }

    @Override
    public DisclosurePanel withDetachListener(DetachListener listener) {
        addDetachListener(listener);
        return this;
    }

    @Override
    public DisclosurePanel withErrorHandler(ErrorHandler errorHandler) {
        setErrorHandler(errorHandler);
        return this;
    }

    @Override
    public DisclosurePanel withContextClickListener(ContextClickEvent.ContextClickListener listener) {
        addContextClickListener(listener);
        return this;
    }

    @Override
    public DisclosurePanel withHeight(String height) {
        setHeight(height);
        return this;
    }

    @Override
    public DisclosurePanel withHeight(float height, Unit unit) {
        setHeight(height, unit);
        return this;
    }

    @Override
    public DisclosurePanel withWidth(String width) {
        setWidth(width);
        return this;
    }

    @Override
    public DisclosurePanel withWidth(float width, Unit unit) {
        setWidth(width, unit);
        return this;
    }

    @Override
    public DisclosurePanel withSizeFull() {
        setSizeFull();
        return this;
    }
    
    @Override
    public DisclosurePanel withWidthFull() {
        setWidth(100, Unit.PERCENTAGE);
        return this;
    }

    @Override
    public DisclosurePanel withHeightFull() {
        setHeight(100, Unit.PERCENTAGE);
        return this;
    }

    @Override
    public DisclosurePanel withSizeUndefined() {
        setSizeUndefined();
        return this;
    }

    @Override
    public DisclosurePanel withWidthUndefined() {
        setWidthUndefined();
        return this;
    }

    @Override
    public DisclosurePanel withHeightUndefined() {
        setHeightUndefined();
        return this;
    }

}
