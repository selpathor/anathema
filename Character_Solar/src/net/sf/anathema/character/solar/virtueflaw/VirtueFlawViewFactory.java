package net.sf.anathema.character.solar.virtueflaw;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.impl.view.SubViewFactory;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.character.library.virtueflaw.presenter.IDescriptiveVirtueFlawView;
import net.sf.anathema.character.library.virtueflaw.view.DescriptiveVirtueFlawView;
import net.sf.anathema.character.platform.RegisteredCharacterView;
import net.sf.anathema.framework.value.IntegerViewFactory;

@RegisteredCharacterView(IDescriptiveVirtueFlawView.class)
public class VirtueFlawViewFactory implements SubViewFactory {
  @Override
  public <T> T create(ICharacterType type) {
    IntegerViewFactory viewFactory = IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(type);
    return (T) new DescriptiveVirtueFlawView(viewFactory);
  }
}