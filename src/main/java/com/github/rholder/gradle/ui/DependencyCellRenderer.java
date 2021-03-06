/*
 * Copyright 2013 Ray Holder
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.rholder.gradle.ui;

import com.github.rholder.gradle.dependency.GradleNode;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

/**
 * Add some text highlighting and other visual indicators to the view of
 * GradleNode nodes in the tree depending on whether they've been replaced by
 * another dependency or omitted because they've been included elsewhere up the
 * tree.
 */
public class DependencyCellRenderer extends JLabel implements TreeCellRenderer {

    public boolean showReplaced = true;

    public GradleNode selectedGradleNode;

    public Color omittedSelected = Color.MAGENTA;
    public Color omittedUnselected = Color.LIGHT_GRAY;
    public Color normalSelected = Color.RED;
    public Color normalUnselected = Color.BLACK;

    public Component getTreeCellRendererComponent(JTree tree,
                                                  Object value,
                                                  boolean selected,
                                                  boolean expanded,
                                                  boolean leaf,
                                                  int row,
                                                  boolean hasFocus) {
        GradleNode gradleDependency = (GradleNode) ((DefaultMutableTreeNode) value).getUserObject();
        String text = showReplaced ? gradleDependency.getFullName() : gradleDependency.getName();

        if(gradleDependency.isOmitted()) {
            setForeground(selected ? omittedSelected : omittedUnselected);
        } else {
            setForeground(selected ? normalSelected : normalUnselected);
        }

        if(selected) {
            this.selectedGradleNode = gradleDependency;
        }

        setText(text);
        setToolTipText(text);
        setPreferredSize(new Dimension(640, getPreferredSize().height));

        return this;
    }
}
