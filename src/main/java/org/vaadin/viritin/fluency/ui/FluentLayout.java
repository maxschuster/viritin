/*
 * Copyright 2016 Matti Tahvonen.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vaadin.viritin.fluency.ui;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Layout;

/**
 * A {@link Layout} complemented by fluent setters.
 * 
 * @author Max Schuster
 * @param <C> Fluent component type
 * @see Layout
 */
public interface FluentLayout<C extends FluentLayout<C>>
        extends Layout, FluentComponentContainer<C> {

    /**
     * A {@link AlignmentHandler} complemented by fluent setters.
     * 
     * @param <C> Fluent component type
     * @see AlignmentHandler
     */
    public interface FluentAlignmentHandler<C extends FluentLayout<C>>
            extends AlignmentHandler, FluentLayout<C> {

        /**
         * Set alignment for one contained component in this layout. Use
         * predefined alignments from Alignment class.
         *
         * Example: <code>
         *      layout.setComponentAlignment(myComponent, Alignment.TOP_RIGHT);
         * </code>
         *
         * @param childComponent the component to align within it's layout cell.
         * @param alignment the Alignment value to be set
         * @return This component
         * @see #setComponentAlignment(com.vaadin.ui.Component, com.vaadin.ui.Alignment) 
         */
        public C withComponentAlignment(Component childComponent,
                Alignment alignment);

        /**
         * Sets the alignment used for new components added to this layout. The
         * default is {@link Alignment#TOP_LEFT}.
         *
         * @param defaultComponentAlignment The new default alignment
         * @return This component
         * @see #setDefaultComponentAlignment(com.vaadin.ui.Alignment) 
         */
        public C withDefaultComponentAlignment(
                Alignment defaultComponentAlignment);
    
        /**
         * 
         * @param component the component to align within it's layout cell.
         * @param alignment the Alignment value to be set
         * @return This component
         */
        public C withComponent(Component component, Alignment alignment);

    }

    /**
     * A {@link SpacingHandler} complemented by fluent setters.
     * 
     * @param <C> Fluent component type
     * @see SpacingHandler
     */
    public interface FluentSpacingHandler<C extends FluentLayout<C>> 
            extends SpacingHandler, FluentLayout<C> {

        /**
         * Enable spacing between child components within this layout.
         *
         * <p>
         * <strong>NOTE:</strong> This will only affect the space between
         * components, not the space around all the components in the layout
         * (i.e. do not confuse this with the cellspacing attribute of a HTML
         * Table). Use {@link #setMargin(boolean)} to add space around the
         * layout.
         * </p>
         *
         * <p>
         * See the reference manual for more information about CSS rules for
         * defining the amount of spacing to use.
         * </p>
         *
         * @param enabled true if spacing should be turned on, false if it
         * should be turned off
         * @return This component
         * @see #setSpacing(boolean) 
         */
        public C withSpacing(boolean enabled);

    }

    /**
     * A {@link MarginHandler} complemented by fluent setters.
     * 
     * @param <C> Fluent component type
     * @see MarginHandler
     */
    public interface FluentMarginHandler<C extends FluentLayout<C>>
            extends MarginHandler, FluentLayout<C> {

        /**
         * Enable layout margins. Affects all four sides of the layout. This
         * will tell the client-side implementation to leave extra space around
         * the layout. The client-side implementation decides the actual amount,
         * and it can vary between themes.
         *
         * @param enabled true if margins should be enabled on all sides, false
         * to disable all margins
         * @return This component
         * @see #setMargin(boolean) 
         */
        public C withMargin(boolean enabled);

        /**
         * Enable margins for this layout.
         *
         * <p>
         * <strong>NOTE:</strong> This will only affect the space around the
         * components in the layout, not space between the components in the
         * layout. Use {@link #setSpacing(boolean)} to add space between the
         * components in the layout.
         * </p>
         *
         * <p>
         * See the reference manual for more information about CSS rules for
         * defining the size of the margin.
         * </p>
         *
         * @param marginInfo MarginInfo object containing the new margins.
         * @return This component
         * @see #setMargin(com.vaadin.shared.ui.MarginInfo) 
         */
        public C withMargin(MarginInfo marginInfo);
    }

}
