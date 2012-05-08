package net.sf.anathema.platform.tree.view.container;

import net.sf.anathema.platform.svgtree.presenter.view.NodeInteractionListener;
import net.sf.anathema.platform.svgtree.presenter.view.NodeProperties;
import net.sf.anathema.platform.tree.view.PolygonPanel;
import net.sf.anathema.platform.tree.view.draw.InteractiveGraphicsElement;

import java.awt.Color;

public class NullCascade implements Cascade {
  @Override
  public void colorNode(String nodeId, Color fillColor) {
    //nothing to do
  }

  @Override
  public void setNodeAlpha(String nodeId, int alpha) {
    //nothing to do
  }

  @Override
  public void addTo(PolygonPanel panel) {
    //nothing to do
  }

  @Override
  public void addInteractionListener(NodeInteractionListener listener) {
    //nothing to do
  }

  @Override
  public void removeInteractionListener(NodeInteractionListener listener) {
    //nothing to do
  }

  @Override
  public void initNodeNames(NodeProperties properties) {
    //nothing to do
  }

  @Override
  public String getIdForElement(InteractiveGraphicsElement element) {
    throw new UnsupportedOperationException("There are no polygons here, so this should never be called.");
  }

  @Override
  public boolean hasElement(InteractiveGraphicsElement element) {
    return false;
  }
}
